// Browser-only drawing preferences shared by Classic and Extended.
// Keep resdown, collision trackers, original meshes and painter order intact.
export function readRenderDetail() {
  let saved = {};
  try { saved = JSON.parse(localStorage.getItem('nfm.launcher') || '{}') || {}; } catch {}
  return {
    lightIntro: saved.lightIntro !== false,
    backgroundDetail: saved.backgroundDetail === 'low' ? 'low' : 'full',
    mountains: saved.mountains !== false,
  };
}

export function installRenderDetail(medium, intro, options = readRenderDetail()) {
  const originals = new Map();
  const light = () => options.lightIntro && intro();
  const reduced = () => options.backgroundDetail === 'low' || light();
  for (const name of ['drawmountains', 'drawclouds', 'groundpolys']) {
    const original = medium[name];
    originals.set(name, original);
    medium[name] = function (...args) {
      if (name === 'drawmountains' ? !options.mountains || light() : reduced()) return;
      return original.apply(this, args);
    };
  }
  // A separate, visual-only object horizon. Original fog distances and
  // resdown are simulation inputs and must never be repurposed for this.
  const previous = Object.getOwnPropertyDescriptor(medium, 'renderDistance');
  Object.defineProperty(medium, 'renderDistance', {configurable:true,
    get: () => light() ? 4000 : options.backgroundDetail === 'low' ? 8000 : Infinity});
  return () => {
    for (const [name, original] of originals) medium[name] = original;
    if (previous) Object.defineProperty(medium, 'renderDistance', previous);
    else delete medium.renderDistance;
  };
}
