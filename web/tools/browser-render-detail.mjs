// Real Chrome: identical loaded objects, no physics between A/B draws.
// Geometry counts are comparable; draw timing is a static CPU/GPU submission
// microbenchmark, not a phone FPS prediction.
import assert from 'node:assert/strict';
import { spawn } from 'node:child_process';
import { mkdtempSync, rmSync, writeFileSync, mkdirSync } from 'node:fs';
import { tmpdir } from 'node:os';
import { join, resolve, relative } from 'node:path';
import { setTimeout as sleep } from 'node:timers/promises';
import { attach } from './cdp.mjs';
const profile=mkdtempSync(join(tmpdir(),'nfm-detail-'));
const port=9800+process.pid%100;
const browser=spawn('C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe',[
 '--no-first-run','--no-default-browser-check','--no-sandbox','--mute-audio',
 '--disable-background-timer-throttling','--disable-renderer-backgrounding','--disable-backgrounding-occluded-windows',
 `--remote-debugging-port=${port}`,`--user-data-dir=${profile}`,'about:blank'],{stdio:'ignore'});
let tab; const results=[];
try {
 tab=await attach(port,'about:blank');await tab.send('Page.enable');await tab.send('Runtime.enable');
 const errors=[];tab.on(m=>{if(m.method==='Runtime.exceptionThrown')errors.push(m.params.exceptionDetails.text);});
 await tab.send('Page.addScriptToEvaluateOnNewDocument',{source:`localStorage.setItem('nfm.launcher',JSON.stringify({devmode:true,lang:'es',lightIntro:false,backgroundDetail:'full',mountains:true}));`});
 for(const ext of [false,true]){
  await tab.send('Page.navigate',{url:`http://localhost:8123/web/main.html?stage=9&players=8&res=1&aa=0&ghost=0&music=0&sfxvol=0&debug=1&bench=0.1&warmup=0${ext?'&ext=classic':''}`});
  await sleep(250);
  for(let i=0;i<400;i++){
   if(await tab.evaluate('window.__nfm?.xt.fase === 6')){
    await tab.send('Input.dispatchKeyEvent',{type:'keyDown',key:'Enter',code:'Enter'});
    await tab.send('Input.dispatchKeyEvent',{type:'keyUp',key:'Enter',code:'KeyEnter'});
   }
   if(await tab.evaluate("!!window.__nfm && document.getElementById('log').textContent.includes('BENCHMARK')"))break;
   await sleep(100);
  }
  assert.ok(await tab.evaluate('!!window.__nfm'),'race booted: '+JSON.stringify(await tab.evaluate('({url:location.href,log:document.getElementById("log")?.textContent})'))+' errors '+JSON.stringify(errors));
  const response=await tab.send('Runtime.evaluate',{awaitPromise:true,returnByValue:true,expression:`(async()=>{
   const {Graphics2D}=await import('/web/graphics.js');
   const {installRenderDetail}=await import('/web/render-detail.js');
   const {setDrawPhase}=await import('/web/java.js');
   const n=__nfm,m=n.medium,car=n.co[3],car0=n.co[0];
   const g=document.createElement('canvas'),t=document.createElement('canvas');
   g.width=t.width=${ext?870:800};g.height=t.height=${ext?480:450};
   const rd=new Graphics2D(g,t,g.width,g.height,{overlay:false});
   m.interpolating=true;
   const options={lightIntro:false,backgroundDetail:'full',mountains:true};
   let intro=true; installRenderDetail(m,()=>intro,options);
   const originalCars=JSON.stringify(n.co.map(c=>c && [c.x,c.y,c.z,c.xz,c.xy,c.zy]));
   const draw=()=>{rd.begin();setDrawPhase(true);try{${ext?'n.race.draw(rd)':'n.gs.draw(rd,m,n.xt,n.co,[])'};}finally{setDrawPhase(false);}rd.end();};
   const measure=(name)=>{
    for(let i=0;i<4;i++)draw();
    const start=performance.now();for(let i=0;i<20;i++)draw();
    return {name,msPerDraw:(performance.now()-start)/20,submitted:rd.inputVerts,emitted:rd.count,faces:rd.faceCalls};
   };
   // Re-create a representative high intro pose on this same loaded scene.
   m.adv=1886;m.vxz=70;m.zy=40;m.around(car,true);
   const full=measure('wide intro');
   const fullImage=g.toDataURL('image/png').split(',')[1];
   options.lightIntro=true;
   const light=measure('light intro');
   const lightImage=g.toDataURL('image/png').split(',')[1];
   intro=false;m.follow(car0,0,0,0);
   const normal=measure('race full background');
   const carDistances=n.co.slice(0,n.xt.nplayers).map(c=>c.dist);
   options.mountains=false;const noMountains=measure('race no mountains');
   options.backgroundDetail='low';const low=measure('race reduced background, no mountains');
   const lowImage=g.toDataURL('image/png').split(',')[1];
   if(JSON.stringify(n.co.slice(0,n.xt.nplayers).map(c=>c.dist))!==JSON.stringify(carDistances))throw Error('car visibility changed with drawing distance');
   if(JSON.stringify(n.co.map(c=>c && [c.x,c.y,c.z,c.xz,c.xy,c.zy]))!==originalCars)throw Error('car transforms changed during drawing');
   rd.begin();m.drawmountains(rd);const hidden=rd.count;
   options.mountains=true;rd.begin();m.drawmountains(rd);const shown=rd.count;
   return {full,light,normal,noMountains,low,mountainVertices:{hidden,shown},resdown:m.resdown,images:{full:fullImage,light:lightImage,low:lowImage}};
  })()`});
  if(response.result?.exceptionDetails)throw Error(JSON.stringify(response.result.exceptionDetails));
  const result=response.result.result.value;
  for(const [name,data] of Object.entries(result.images))writeFileSync(join(tmpdir(),`nfm-detail-${ext?'extended':'classic'}-${name}.png`),Buffer.from(data,'base64'));
  delete result.images;
  console.log(JSON.stringify({engine:ext?'Extended':'Classic',...result}));
  assert.ok(result.light.emitted<result.full.emitted,'lighter intro submits less geometry');
  assert.ok(result.low.emitted<result.normal.emitted,'reduced background submits less geometry');
  assert.equal(result.mountainVertices.hidden,0);
  assert.ok(result.mountainVertices.shown>0,'mountain toggle restores real mountains');
  results.push({engine:ext?'Extended':'Classic',...result});
 }
 await tab.send('Page.navigate',{url:'http://localhost:8123/'});
 await sleep(250);
 for(let i=0;i<150;i++){if(await tab.evaluate('!!document.querySelector("[data-row=mountains] .ar.r")'))break;await sleep(100);}
 await tab.evaluate(`document.querySelector('[data-act="menu:5"]').click();document.querySelector('[data-row=mountains] .ar.l').click();document.querySelector('[data-row=backgroundDetail] .ar.r').click();document.querySelector('[data-row=lightIntro] .ar.r').click();`);
 const saved=await tab.evaluate('JSON.parse(localStorage.getItem("nfm.launcher"))');
 assert.equal(saved.mountains,false);assert.equal(saved.backgroundDetail,'low');assert.equal(saved.lightIntro,true);
 await sleep(100);
 assert.equal(await tab.evaluate('document.querySelector("[data-row=mountains] .slabel").textContent'),'Mostrar montañas');
 const shot=await tab.send('Page.captureScreenshot',{format:'png'});
 writeFileSync(join(tmpdir(),'nfm-detail-settings.png'),Buffer.from(shot.result.data,'base64'));
 assert.deepEqual(errors,[],'no browser runtime errors');
 mkdirSync('web/tools/perf-results',{recursive:true});
 writeFileSync('web/tools/perf-results/render-detail-2026-09-29.json',JSON.stringify(results,null,2)+'\n');
} finally {
 tab?.close();browser.kill();await sleep(500);
 const inside=relative(resolve(tmpdir()),resolve(profile));
 if(inside.startsWith('..')||!inside.startsWith('nfm-detail-'))throw Error('unexpected cleanup path');
 rmSync(profile,{recursive:true,force:true,maxRetries:20,retryDelay:250});
}

