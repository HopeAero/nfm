// Original polygon model authored directly as NFM .rad. No Blender/DCC.
import { writeFileSync } from 'node:fs';
import { ContO } from '../../web/ContO.js';
import { Medium } from '../../web/Medium.js';
import { Trackers } from '../../web/Trackers.js';
import { problems, readPhysics, readStats, writePhysics } from '../../web/careditor/rad.js';
import { calibrate } from '../../web/careditor/damage.js';
import { CarDefine } from '../../web/CarDefine.js';
const faces=[];
const white=[239,237,227],trim=[34,39,43],glass=[46,67,76],silver=[188,198,200];
function face(points,color=white,label='body'){
 points=points.map(p=>p.map(Math.round));
 if(new Set(points.map(p=>p.join(','))).size<3)throw Error('degenerate '+label);
 faces.push({points,color,label});
}
const mirror=p=>p.map(([x,y,z])=>[-x,y,z]).reverse();
const both=(p,c=white,label)=>{face(p,c,label);face(mirror(p),c,label);};
// The front is positive Z, Y points down. Native wheels supply tyre/rim mesh.
const columns=[-178,-148,-143,-135,-125,-112,-99,-89,-82,-77,-20,45,77,82,89,99,112,125,135,143,148,180];
function bottom(z){
 const d=Math.min(Math.abs(z-112),Math.abs(z+112));
 return d<31?-Math.sqrt(31*31-d*d):16;
}
function upper(z){return z>148?-38:z<-145?-42:-47;}
function width(z){return Math.abs(z)>145?72-(Math.abs(z)-145)*0.16:75;}
for(let i=0;i<columns.length-1;i++){
 const a=columns[i],b=columns[i+1];
 both([[width(a),upper(a),a],[width(b),upper(b),b],[width(b),bottom(b),b],[width(a),bottom(a),a]],white,'flanks / wheel arches');
}
// Crown panels split across X and Z so there are no huge, warped polygons.
function deck(zs,ys,ws,label){
 const fractions=[-1,-0.66,0,0.66,1];
 for(let j=0;j<zs.length-1;j++)for(let i=0;i<fractions.length-1;i++){
  const a=fractions[i],b=fractions[i+1];
  face([[a*ws[j],ys[j]+Math.abs(a)*3,zs[j]],
    [b*ws[j],ys[j]+Math.abs(b)*3,zs[j]],
    [b*ws[j+1],ys[j+1]+Math.abs(b)*3,zs[j+1]],
    [a*ws[j+1],ys[j+1]+Math.abs(a)*3,zs[j+1]]],white,label);
 }
}
deck([64,110,150,180],[-49,-48,-44,-41],[74,75,73,66],'hood');
deck([-178,-140,-108],[-44,-46,-49],[67,74,73],'trunk');
deck([-62,-25,28],[-99,-105,-99],[54,56,54],'roof');
both([[54,-96,-62],[56,-102,-25],[57,-99,-21],[57,-96,-58]],white,'roof rail rear');
both([[56,-102,-25],[54,-96,28],[57,-96,24],[57,-99,-12],[57,-99,-21]],white,'roof rail front');
// Cabin shell: white pillars surround dark inset side windows.
both([[74,-46,64],[54,-99,28],[57,-96,24],[74,-52,55]],white,'A pillar');
both([[54,-99,-62],[73,-46,-108],[74,-52,-99],[57,-96,-58]],white,'C pillar');
both([[74,-52,55],[57,-96,24],[57,-99,-12],[75,-50,-12]],glass,'front side glass');
both([[75,-50,-21],[57,-99,-21],[57,-96,-58],[74,-52,-99]],glass,'rear side glass');
both([[75,-50,-12],[57,-99,-12],[57,-99,-21],[75,-50,-21]],trim,'B pillar');
both([[74,-46,64],[74,-52,55],[75,-50,-21],[75,-47,-21]],white,'window belt front');
both([[75,-47,-21],[75,-50,-21],[74,-52,-99],[73,-46,-108]],white,'window belt rear');
// Windscreen and rear window: two halves keep their bend shallow.
face([[-74,-46,64],[0,-49,64],[0,-99,28],[-54,-96,28]],glass,'windscreen');
face([[0,-49,64],[74,-46,64],[54,-96,28],[0,-99,28]],glass,'windscreen');
face([[-54,-96,-62],[0,-99,-62],[0,-49,-108],[-73,-46,-108]],glass,'rear window');
face([[0,-99,-62],[54,-96,-62],[73,-46,-108],[0,-49,-108]],glass,'rear window');
// Flat white front and rear bumpers, with tapered corner returns.
for(const sign of [1,-1]){
 const z=sign>0?183:-181;
 for(const [a,b] of [[-68,-28],[-28,28],[28,68]]){
  face([[a,-40,z],[b,-40,z],[b,-13,z+sign*3],[a,-13,z+sign*3]],white,'bumper upper');
  face([[a,-13,z+sign*3],[b,-13,z+sign*3],[b,13,z+sign*3],[a,13,z+sign*3]],white,'bumper lower');
 }
 both([[68,-40,z],[width(z-sign*8),upper(z-sign*8),z-sign*8],[width(z-sign*8),16,z-sign*8],[68,13,z+sign*3]],white,'bumper corner');
}
// Corolla's small upper grille and broad, swept headlights.
face([[-28,-40,188],[28,-40,188],[23,-22,190],[-23,-22,190]],trim,'upper grille');
face([[-29,-41,189],[29,-41,189],[29,-39,190],[-29,-39,190]],silver,'grille chrome');
face([[-33,-6,191],[33,-6,191],[30,6,191],[-30,6,191]],[21,26,29],'lower intake');
both([[43,3,190],[61,4,189],[66,-10,184],[51,-8,188]],trim,'bumper side vent');
both([[30,-38,190],[61,-40,190],[68,-34,189],[63,-19,191],[32,-25,191]],[114,130,140],'headlamp edge');
both([[33,-36,193],[59,-37,193],[65,-32,192],[61,-22,193],[35,-27,194]],[214,225,225],'headlamp lens');
both([[40,-34,194],[48,-35,193],[49,-27,194],[40,-28,195]],[244,247,231],'headlamp reflector');
both([[61,-34,194],[65,-31,193],[62,-27,194],[59,-29,194]],[204,148,74],'indicator');
// Tail lights wrap into the rear quarter; trunk remains separate and readable.
both([[39,-41,-184],[66,-42,-181],[74,-37,-166],[72,-22,-168],[63,-25,-184],[41,-28,-186]],[166,39,43],'rear lamp');
both([[42,-31,-188],[62,-31,-186],[62,-27,-188],[43,-27,-189]],[220,221,211],'reverse lamp');
face([[-24,-29,-188],[24,-29,-188],[24,-15,-189],[-24,-15,-189]],[229,227,218],'trunk recess');
// Minimal seams; no interior or tiny badges/handles/mirrors per the guide.
both([[76,-46,-18],[76,-46,-19],[76,12,-19],[76,12,-18]],[183,183,178],'door seam');
both([[74,-47,67],[75,-47,66],[75,-18,67],[74,-18,68]],[183,183,178],'front door seam');
both([[75,-47,-76],[76,-47,-77],[76,12,-77],[75,12,-76]],[183,183,178],'rear door seam');
both([[76,12,-77],[76,12,77],[75,16,77],[75,16,-77]],[216,216,208],'sill');

let text='// Toyota Corolla 2011 - white sedan, original stylized NFM mesh\n'+
 '// Coordinates authored directly; no Blender. Front +Z, up -Y.\n'+
 '// Native NFM wheels. Details prioritise silhouette and lamps.\n\n'+
 '1stColor(239,237,227)\n2ndColor(34,39,43)\n\n';
for(const f of faces){
 const clean = ['flanks / wheel arches','hood','roof','trunk','bumper upper','bumper lower','bumper corner','windscreen','rear window'].includes(f.label);
 const color=f.label==='flanks / wheel arches'?[224,223,216]:f.color;
 text+=`// ${f.label}\n<p>\nc(${color.join(',')})\n${clean?'noOutline\n':''}${f.points.map(p=>'p('+p.join(',')+')').join('\n')}\n</p>\n\n`;
}
text+='gwgr(0)\nrims(194,197,198,17,10)\nw(-70,0,112,11,20,21)\nw(70,0,112,11,-20,21)\n\n'+
 'gwgr(0)\nrims(194,197,198,17,10)\nw(-70,0,-112,0,20,21)\nw(70,0,-112,0,-20,21)\n\n'+
 'stat(116,132,108,116,128)\nphysics(65,60,72,35,0,35,50,55,55,55,55,65,55,45,0,0)\nhandling(78)\n';
const m=new Medium();m.loadnew=true;
const physics=readPhysics(text);
const cm={m,t:new Trackers(),editor:{getText:()=>text},stat:readStats(text),crash:physics.crash.slice(),
  ox:0,oy:0,oz:0,oxz:0,oxy:0,ozy:0,showMessageDialog:(_a,message)=>{throw Error(message);}};
calibrate(cm);
text=writePhysics(text,{...physics,crash:cm.crash.slice(),actmag:cm.actmag});
const o=new ContO(text,m,new Trackers());
const issues=problems(text,o);
if(o.errd||issues.length)throw Error(JSON.stringify({error:o.err,issues}));
if(faces.length>210)throw Error('above original NFM face budget');
const cd=new CarDefine(new Array(56).fill(null),new Medium(),new Trackers(),null);
if(cd.loadcar('Toyota Corolla 2011',16,text)!==16)throw Error('Race loader rejected the car');
if(!(cd.maxmag[16]>0&&Number.isFinite(cd.dammult[16])))throw Error('Invalid damage calibration');
writeFileSync(new URL('./Toyota Corolla 2011.rad',import.meta.url),text);
writeFileSync(new URL('./manifest.json',import.meta.url),JSON.stringify({name:'Toyota Corolla 2011',version:1,
  authoring:'Original code-authored mesh; native NFM polygons and wheels; no Blender.',
  reference:'User-provided white Corolla 2011 photograph; front and side visible. Rear is an approximation.',
  guide:'https://docs.google.com/presentation/d/1MiJ1Lbp8c2HN3KRx5jy5fPyffyOdCrXLT5VjUF0FyKo/edit',
  bodyPolygons:faces.length,loadedPieces:o.npl,wheels:4,tyreHeight:o.wh,radius:o.maxR,
  stats:'Class B, 600-point budget. Initial game handling; not a physical vehicle simulation.',
  damageCalibration:{actmag:cm.actmag,crash:cm.crash,maxmag:cd.maxmag[16],dammult:cd.dammult[16]},
  nativeRaceLoader:'accepted at custom car slot 16',readiness:issues},null,2)+'\n');
console.log(JSON.stringify({bodyPolygons:faces.length,loadedPieces:o.npl,wheels:4,radius:o.maxR,actmag:cm.actmag,issues}));
