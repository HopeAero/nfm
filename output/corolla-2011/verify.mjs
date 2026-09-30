// Drive this custom model in the real port's stage 1, without a browser/server.
import assert from 'node:assert/strict';
import {readFileSync} from 'node:fs';
import {parseZip} from '../../web/vfs.js';
import {Graphics2D} from '../../web/graphics.js';
import {Medium} from '../../web/Medium.js';
import {Trackers} from '../../web/Trackers.js';
import {CheckPoints} from '../../web/CheckPoints.js';
import {Control} from '../../web/Control.js';
import {Record} from '../../web/Record.js';
import {CarDefine} from '../../web/CarDefine.js';
import {Mad} from '../../web/Mad.js';
import {GameSparker} from '../../web/GameSparker.js';
import {XtGraphics} from '../../web/XtGraphics.js';
import {objArray,setSeed} from '../../web/java.js';
const root=new URL('../../',import.meta.url);
setSeed(12345);
const zip=await parseZip(new Uint8Array(readFileSync(new URL('data/models.zip',root))));
const rd=new Graphics2D(null,null,800,450),m=new Medium(),t=new Trackers(),cp=new CheckPoints();
const base=objArray(124),world=objArray(610),cars=objArray(8),gs=new GameSparker();
const cd=new CarDefine(base,m,t,gs),xt=new XtGraphics(m,cd,rd,gs),record=new Record(m);
gs.loadbase(base,m,t,zip);
assert.equal(cd.loadcar('Toyota Corolla 2011',16,readFileSync(new URL('./Toyota Corolla 2011.rad',import.meta.url),'utf8')),16);
for(let i=0;i<8;i++){cars[i]=new Mad(cd,m,record,xt,i);gs.u[i]=new Control(m);xt.sc[i]=i===0?16:1;}
xt.nplayers=7;cp.stage=1;
gs.loadstage(world,base,m,t,cp,xt,cars,record,readFileSync(new URL('stages/1.txt',root),'latin1'));
xt.starcnt=0;m.trk=0;m.iw=0;m.ih=0;m.w=800;m.h=450;
const start=world[0].z;gs.u[0].up=true;
let peak=0,minVertices=Infinity;
for(let tick=0;tick<80;tick++){
 gs.u[0].left=tick>=60&&tick<70;
 rd.begin();gs.tick(rd,m,t,cp,xt,record,world,cars);
 assert.ok(Number.isFinite(cars[0].speed));
 assert.ok([world[0].x,world[0].y,world[0].z].every(Number.isFinite));
 peak=Math.max(peak,cars[0].speed);minVertices=Math.min(minVertices,rd.vertexCount);
}
assert.ok(peak>10,'Throttle failed to accelerate');assert.notEqual(world[0].z,start);assert.ok(minVertices>1000);
console.log(JSON.stringify({customCarAccepted:true,ticks:80,peakSpeed:peak,minSceneVertices:minVertices,damageLimit:cd.maxmag[16]}));
