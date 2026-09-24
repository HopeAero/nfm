// The desktop pause screen is xtGraphics.pausedgame() over pauseimage(). The
// browser uses the original paused.gif for its panel and selection positions;
// Instant Replay plays the rolling 300-tick recording, while the event replay
// uses Record's separate highlight buffer. Instructions use a browser-native
// pane in place of the original AWT screen.

export function createRaceMenu(stage, { multiplayer, pauseArt, pauseArtBackgroundOnly = false, onLeave, onReplay, onSkipReplay, onToggle }) {
  const root = document.createElement('div');
  root.className = `race-menu${multiplayer ? ' race-menu--online' : ''}${pauseArt && !multiplayer ? (pauseArtBackgroundOnly ? ' race-menu--art-bg' : ' race-menu--art') : ''}`;
  root.hidden = true;
  root.setAttribute('role', 'dialog');
  root.setAttribute('aria-modal', 'true');
  root.setAttribute('aria-label', multiplayer ? 'Online race menu' : 'Pause menu');
  const choices = multiplayer
    ? [['resume', 'Resume Game'], ['leave', 'Quit Game']]
    : [['resume', 'Resume Game'], ['replay', 'Instant Replay'],
       ['instructions', 'Game Instructions'], ['leave', 'Quit Game']];
  root.innerHTML = `
    <div class="race-menu__panel">
      ${pauseArt && !multiplayer ? `<img class="race-menu__art" alt="" aria-hidden="true">` : ''}
      <div class="race-menu__title">${multiplayer ? 'ONLINE MENU' : '– PAUSED –'}</div>
      ${choices.map(([action, label], index) =>
        `<button type="button" class="race-menu__choice race-menu__choice--${index}" data-action="${action}" aria-label="${label}"><span>${label}</span></button>`
      ).join('')}
      <div class="race-menu__footer">${multiplayer ? 'RACE CONTINUES' : '– PAUSED –'}</div>
    </div>
    <div class="race-menu__guide" hidden>
      <h2>GAME INSTRUCTIONS</h2>
      <p>Drive with the arrow keys. Space uses the handbrake.</p>
      <p><b>A</b> points the arrow toward cars or the track. <b>S</b> shows the radar map.</p>
      <p><b>Esc</b> returns to the pause menu. Press it again to resume.</p>
      <button type="button" data-action="back">Back to pause menu</button>
    </div>
    <div class="race-menu__replay" hidden>
      <div class="race-menu__replay-title">INSTANT REPLAY</div>
      <div class="race-menu__replay-track" role="progressbar" aria-label="Replay progress" aria-valuemin="0" aria-valuemax="300" aria-valuenow="0"><i></i></div>
      <div class="race-menu__replay-status" aria-live="polite">0.0 / 15.9 seconds</div>
      <div class="race-menu__replay-hint">Enter or Esc returns to pause</div>
      <button type="button" data-action="skip-replay">Skip replay</button>
    </div>
    <div class="race-menu__notice" role="status" hidden></div>`;
  stage.append(root);
  const panel = root.querySelector('.race-menu__panel');
  const guide = root.querySelector('.race-menu__guide');
  const replay = root.querySelector('.race-menu__replay');
  const replayTitle = root.querySelector('.race-menu__replay-title');
  const replayTrack = root.querySelector('.race-menu__replay-track');
  const replayProgress = replayTrack.querySelector('i');
  const replayStatus = root.querySelector('.race-menu__replay-status');
  const replayHint = root.querySelector('.race-menu__replay-hint');
  const replayButton = replay.querySelector('button');
  const notice = root.querySelector('.race-menu__notice');
  let noticeTimer = 0;
  const buttons = [...panel.querySelectorAll('button')];
  const art = root.querySelector('.race-menu__art');
  if (art) {
    art.src = pauseArt;
    art.addEventListener('error', () => root.classList.remove('race-menu--art', 'race-menu--art-bg'));
  }
  let selected = 0;
  let mode = 'closed';
  let replayTotal = 300;

  const select = (index) => {
    selected = (index + buttons.length) % buttons.length;
    buttons.forEach((button, i) => button.classList.toggle('is-selected', i === selected));
    buttons[selected].focus();
    notice.hidden = true;
  };
  const closeGuide = () => {
    mode = 'pause';
    guide.hidden = true;
    panel.hidden = false;
    select(selected);
  };
  const close = () => {
    if (mode === 'closed') return;
    mode = 'closed';
    root.hidden = true;
    panel.hidden = false;
    guide.hidden = true;
    replay.hidden = true;
    notice.hidden = true;
    stage.classList.remove('race-menu-open');
    stage.classList.remove('race-menu-replay');
    stage.classList.remove('race-menu-highlight');
    stage.classList.remove('race-menu-online');
    onToggle(false);
  };
  const show = () => {
    if (mode !== 'closed') return;
    mode = 'pause';
    root.hidden = false;
    root.setAttribute('aria-label', multiplayer ? 'Online race menu' : 'Pause menu');
    stage.classList.add('race-menu-open');
    if (multiplayer) stage.classList.add('race-menu-online');
    onToggle(true);
    select(0);
  };
  // The replays draw their own UI on the canvas, as the Java does (replyn,
  // levelhigh). These keep the MODE -- Enter/Space/Esc skip -- and show no
  // panel.
  const showReplay = () => {
    mode = 'replay';
    root.hidden = true;
    root.setAttribute('aria-label', 'Instant Replay');
    panel.hidden = true;
    guide.hidden = true;
    replay.hidden = false;
    notice.hidden = true;
    stage.classList.remove('race-menu-open');
    stage.classList.remove('race-menu-highlight');
    stage.classList.add('race-menu-replay');
    replayTitle.textContent = 'INSTANT REPLAY';
    replayHint.textContent = 'Enter or Esc returns to pause';
    replayButton.textContent = 'Skip replay';
    updateReplayProgress(0, 300);
    replayButton.focus();
  };
  const showHighlight = (title) => {
    mode = 'highlight';
    root.hidden = true;
    root.setAttribute('aria-label', `${title} replay`);
    panel.hidden = true;
    guide.hidden = true;
    replay.hidden = false;
    notice.hidden = true;
    stage.classList.remove('race-menu-open');
    stage.classList.add('race-menu-replay', 'race-menu-highlight');
    replayTitle.textContent = title.toUpperCase();
    replayHint.textContent = 'RACE HIGHLIGHT · ENTER OR ESC TO SKIP';
    replayButton.textContent = 'Skip highlight';
    updateReplayProgress(0, 600);
    replayButton.focus();
  };
  const completeHighlight = ({ skipped = false } = {}) => {
    if (mode !== 'highlight') return;
    mode = 'postrace';
    replayTitle.textContent = skipped ? 'HIGHLIGHT SKIPPED' : 'HIGHLIGHT COMPLETE';
    replayHint.textContent = 'ENTER OR ESC TO RETURN TO THE LAUNCHER';
    replayButton.textContent = 'Continue';
    replayStatus.textContent = skipped ? 'Replay skipped' : 'Replay finished';
    replayButton.focus();
  };
  const returnToPause = () => {
    if (mode !== 'replay') return;
    mode = 'pause';
    root.hidden = false;              // showReplay hid it: the replay has no panel
    panel.hidden = false;
    replay.hidden = true;
    root.setAttribute('aria-label', 'Pause menu');
    stage.classList.remove('race-menu-replay');
    stage.classList.add('race-menu-open');
    onToggle(true);
    select(0);
  };
  const updateReplayProgress = (frame, total = replayTotal) => {
    replayTotal = total;
    const current = Math.max(0, Math.min(total, frame));
    const percent = current / total * 100;
    replayTrack.setAttribute('aria-valuenow', String(current));
    replayTrack.setAttribute('aria-valuemax', String(total));
    replayProgress.style.width = `${percent}%`;
    replayStatus.textContent = `${(current * 0.053).toFixed(1)} / ${(total * 0.053).toFixed(1)} seconds`;
  };
  const activate = () => {
    const action = buttons[selected].dataset.action;
    if (action === 'resume') close();
    if (action === 'leave') onLeave();
    if (action === 'instructions') {
      mode = 'guide';
      panel.hidden = true;
      guide.hidden = false;
      guide.querySelector('button').focus();
    }
    if (action === 'replay') {
      const result = onReplay();
      if (result !== true) {
        notice.textContent = result || 'Sorry not enough replay data to play available, please try again later.';
        notice.hidden = false;
        // The Java's fase -8 shows it for 150 ticks, then back to the pause.
        clearTimeout(noticeTimer);
        noticeTimer = setTimeout(() => { notice.hidden = true; }, 150 * 53);
        return;
      }
      showReplay();
    }
  };
  buttons.forEach((button, index) => {
    button.addEventListener('focus', () => {
      selected = index;
      buttons.forEach((other, i) => other.classList.toggle('is-selected', i === index));
    });
    button.addEventListener('click', () => { selected = index; activate(); });
  });
  guide.querySelector('button').addEventListener('click', closeGuide);
  replayButton.addEventListener('click', () => {
    if (mode === 'postrace') onLeave();
    else onSkipReplay();
  });
  // Escape starts on the focused Back button. Consume it before changing focus
  // to a menu choice, so the same key press cannot also close the pause menu.
  guide.addEventListener('keydown', (e) => {
    if (e.code !== 'Escape') return;
    e.preventDefault();
    e.stopPropagation();
    if (!e.repeat) closeGuide();
  });

  return {
    get isOpen() { return mode !== 'closed'; },
    show,
    close,
    returnToPause,
    showHighlight,
    completeHighlight,
    updateReplayProgress,
    handleKey(e) {
      if (e.code === 'Escape') {
        if (!e.repeat) {
          if (mode === 'closed') show();
          else if (mode === 'replay') onSkipReplay();
          else if (mode === 'highlight') onSkipReplay();
          else if (mode === 'postrace') onLeave();
          else if (mode === 'pause') close();
        }
        e.preventDefault();
        return true;
      }
      if (mode === 'closed') return false;
      if (mode === 'replay') {
        if ((e.code === 'Enter' || e.code === 'Space') && !e.repeat) onSkipReplay();
      } else if (mode === 'highlight') {
        if ((e.code === 'Enter' || e.code === 'Space') && !e.repeat) onSkipReplay();
      } else if (mode === 'postrace') {
        if (e.code === 'Enter' && !e.repeat) onLeave();
      } else if (mode === 'guide') {
        if ((e.code === 'Enter' || e.code === 'Space') && !e.repeat) closeGuide();
      } else if (mode === 'pause') {
        if (!guide.hidden) {
          mode = 'guide';
          if ((e.code === 'Enter' || e.code === 'Space') && !e.repeat) closeGuide();
        } else if (e.code === 'ArrowUp' || e.code === 'ArrowDown') {
          select(selected + (e.code === 'ArrowDown' ? 1 : -1));
        } else if ((e.code === 'Enter' || e.code === 'Space') && !e.repeat) {
          activate();
        }
      }
      e.preventDefault();
      return true;
    },
  };
}
