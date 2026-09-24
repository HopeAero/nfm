// Extended Mode's model table: GameSparker.loadbase's names, in order.
// A model's code (its index) sets its scale and whether it is a car (ContO's
// constructor), so the lookup rule matters as much as the list.

export const AS = [
  '2000tornados', 'formula7', 'canyenaro', 'lescrab', 'nimi', 'maxrevenge', 'leadoxide', 'koolkat',
  'drifter', 'policecops', 'mustang', 'king', 'audir8', 'masheen', 'radicalone', 'drmonster',
  'newcar1', 'newcar2', 'newcar3', 'newcar4', 'secretcar1', 'secretcar2', 'secretcar3', 'tornadoshark',
  'formula72', 'wowcaninaro', 'lavitacrab', 'nimi2', 'maxrevenge2', 'leadoxide2', 'koolkat2', 'drifterx',
  'swordofjustice', 'highrider', 'elking', 'mightyeight', 'masheen2', 'radicalone2', 'drmonstaa', 'road',
  'froad', 'twister2', 'twister1', 'turn', 'offroad', 'bumproad', 'offturn', 'nroad',
  'nturn', 'roblend', 'noblend', 'rnblend', 'roadend', 'offroadend', 'hpground', 'ramp30',
  'cramp35', 'dramp15', 'dhilo15', 'slide10', 'takeoff', 'sramp22', 'offbump', 'offramp',
  'thewall', 'halfpipe', 'spikes', 'rail', 'sofframp', 'checkpoint', 'fixpoint', 'offcheckpoint',
  'sideoff', 'bsideoff', 'uprise', 'riseroad', 'sroad', 'soffroad', '2000tornadosB', 'formula7B',
  'canyenaroB', 'lescrabB', 'nimiB', 'maxrevengeB', 'leadoxideB', 'koolkatB', 'drifterB', 'policecopsB',
  'mustangB', 'kingB', 'audir8B', 'masheenB', 'radicaloneB', 'drmonsterB', 'newcar1B', 'newcar2B',
  'newcar3B', 'newcar4B', 'secretcar1B', 'secretcar2B', 'secretcar3B', 'tornadosharkB', 'formula72B', 'wowcaninaroB',
  'lavitacrabB', 'nimi2B', 'maxrevenge2B', 'leadoxide2B', 'koolkat2B', 'drifterxB', 'swordofjusticeB', 'highriderB',
  'elkingB', 'mightyeightB', 'masheen2B', 'radicalone2B', 'drmonstaaB', 'tree4', 'tree6', 'offhill',
  'spikefire', 'railfire', 'cactus', 'roll1', 'roll2', 'roll3', 'roll4', 'roll5',
  'roll6',
];

/**
 * loadbase's rule: the LAST name in AS that prefixes the zip entry wins, so
 * "roadend.rad" is roadend (52), not road (39), and "nimi2B.rad" is nimi2B.
 */
export function modelCode(entry) {
  let j = 0;
  for (let k = 0; k < AS.length; k++) if (entry.startsWith(AS[k])) j = k;
  return j;
}
