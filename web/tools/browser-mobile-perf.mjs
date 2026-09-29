import { spawn } from 'node:child_process';
import { mkdtempSync, rmSync, writeFileSync, mkdirSync } from 'node:fs';
import { tmpdir } from 'node:os';
import { join } from 'node:path';
import { setTimeout as sleep } from 'node:timers/promises';
import { attach } from './cdp.mjs';
// Real (headful) Chrome, wall clock and GPU. CPU throttling is a stress test,
// not a claim to reproduce any particular phone. Never use virtual time here.
const profile=mkdtempSync(join(tmpdir(),'nfm-perf-'));
const port=9600+process.pid%250;
const browser=spawn('C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe',[
 '--no-first-run','--no-default-browser-check','--no-sandbox','--mute-audio','--window-size=1000,620',
 '--disable-background-timer-throttling','--disable-renderer-backgrounding','--disable-backgrounding-occluded-windows',
 `--remote-debugging-port=${port}`,`--user-data-dir=${profile}`,'about:blank'
],{stdio:'ignore'});
let tab;const results=[];
try {
 tab=await attach(port,'about:blank');await tab.send('Page.enable');await tab.send('Runtime.enable');const errors=[];tab.on(m=>{if(m.method==='Runtime.exceptionThrown')errors.push(m.params.exceptionDetails.text+': '+m.params.exceptionDetails.exception?.description)});
 await tab.send('Page.addScriptToEvaluateOnNewDocument',{source:`localStorage.setItem('nfm.launcher',JSON.stringify({devmode:true,touch:'on',musicvol:0,sfxvol:0}));`});
 const cases=[
  {name:'stage9-start-stress',stage:9,res:2,cpu:4,view:0},
  {name:'stage9-start-1x',stage:9,res:1,cpu:4,view:0},
  {name:'stage9-start-30fps',stage:9,res:1,cpu:4,view:0,maxfps:30},
  {name:'stage9-racing-parked',stage:9,res:2,cpu:4,view:0,racing:true},
  {name:'stage9-racing-orbit',stage:9,res:2,cpu:4,view:1,racing:true},
  {name:'stage9-racing-1x',stage:9,res:1,cpu:4,view:0,racing:true},
 ];
 for(const c of cases){
  await tab.send('Emulation.setCPUThrottlingRate',{rate:1});
  await tab.send('Page.navigate',{url:`http://localhost:8123/web/main.html?stage=${c.stage}&players=${c.players||8}&car=2&res=${c.res}&aa=0&music=0&sfxvol=0&ghost=0&perf=all&debug=1&bench=5&warmup=2500&prof=0&maxfps=${c.maxfps||0}`});
  await sleep(250);
  for(let i=0;i<400;i++){if(await tab.evaluate('!!window.__nfm'))break;await sleep(100);}
  await tab.send('Emulation.setCPUThrottlingRate',{rate:c.cpu});
  await tab.evaluate(`__nfm.gs.view=${c.view};${c.racing?'__nfm.xt.starcnt=0;':''}`);
  const renderer = await tab.evaluate(`(() => { const gl=document.getElementById('gl').getContext('webgl2');const x=gl.getExtension('WEBGL_debug_renderer_info');return x?gl.getParameter(x.UNMASKED_RENDERER_WEBGL):'unavailable'; })()`);
  let report='';
  for(let i=0;i<400;i++){
   report=await tab.evaluate("document.getElementById('log').textContent");
   if(report?.includes('BENCHMARK'))break;await sleep(100);
  }
  if(!report?.includes('BENCHMARK'))throw Error('benchmark did not complete '+c.name+' '+report+' '+JSON.stringify(errors));
  results.push({...c,renderer,report});console.log(c.name+'\n'+report);
 }
 mkdirSync('web/tools/perf-results',{recursive:true});
 writeFileSync('web/tools/perf-results/mobile-2026-09-29.json',JSON.stringify(results,null,2)+'\n');
 // CPU profile of the last active-race scene, after its assets/JIT have warmed.
 await tab.send('Emulation.setCPUThrottlingRate',{rate:4});
 await tab.send('Input.dispatchKeyEvent',{type:'keyDown',key:'r',code:'KeyR'});
 await tab.send('Input.dispatchKeyEvent',{type:'keyUp',key:'r',code:'KeyR'});
 await tab.send('Profiler.enable');await tab.send('Profiler.start');await sleep(4000);
 const p=(await tab.send('Profiler.stop')).result.profile;
 const byId=new Map(p.nodes.map(n=>[n.id,n]));const sum=new Map();
 for(const id of p.samples||[]){const f=byId.get(id)?.callFrame;const k=(f?.functionName||'(anonymous)')+' '+(f?.url||'').split('/').slice(-1)[0]+':'+(f?.lineNumber||0);sum.set(k,(sum.get(k)||0)+1);}
 console.log('PROFILE',JSON.stringify([...sum].sort((a,b)=>b[1]-a[1]).slice(0,18)));
 writeFileSync('web/tools/perf-results/mobile-cpu-2026-09-29.json',JSON.stringify([...sum].sort((a,b)=>b[1]-a[1]))+'\n');
}finally{tab?.close();browser.kill();await sleep(500);rmSync(profile,{recursive:true,force:true,maxRetries:10,retryDelay:200});}
