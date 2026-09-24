// End-of-race highlight selection, kept separate from the rolling instant
// replay so event replays work even when full car-model recording is off.
export const HIGHLIGHT_FRAMES = 300;
export const HIGHLIGHT_PASSES = 3;

export function shouldPlayHighlight({
  hcaught,
  wasted,
  whenwasted,
  stage,
  looped,
  multiplayer = false,
}) {
  if (!hcaught || multiplayer) return false;
  // The original suppresses incidental stunt highlights in the two tutorial
  // tracks until the player has looped once.
  if (wasted === 0 && whenwasted !== 229 && (stage === 1 || stage === 2) && looped !== 0) {
    return false;
  }
  return true;
}

export function highlightTitle({ wasted, whenwasted, closefinish, localPlayer, stage }) {
  if (wasted !== localPlayer) {
    if (closefinish === 0) return "You Wasted 'em!";
    if (closefinish === 1) return 'Close Finish!';
    return 'Close Finish! Almost got it!';
  }
  if (whenwasted === 229) return 'Wasted!';
  return stage > 2 || stage < 0 ? 'Stunts!' : 'Best Stunt!';
}
