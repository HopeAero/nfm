// What the launcher lists for Extended's free play, without loading the game:
// its 39 cars (xtGraphics.names) and its own stages (data/Files/tracks.radq,
// the jar's normal mode). catalog.test.js checks both against the sources.
// Cars 0-22 are Extended's (the career's), 23-38 the NFM2 cars with powers.

export const EXT_CARS = ['Remington', 'Speedy 7', 'Damn Van', 'Blizzard Rush', 'Twingoor', 'Steel Falcon', 'Oldskool',
  'Revonater', 'Comet', 'Das Cop', 'Hellfire', 'Old Van', 'Redspeed', 'Stampede', 'Skyrider', 'DR Chaos', 'Bounty Hunter',
  'Radical Racer', 'Titan', 'Deity', 'Agent Waster', 'Agent Racer', 'Tesco Lorry', 'Tornado Shark', 'Formula 7',
  'Wow Caninaro', 'La Vita Crab', 'Nimi', 'MAX Revenge', 'Lead Oxide', 'Kool Kat', 'Drifter X', 'Sword of Justice',
  'High Rider', 'EL KING', 'Mighty Eight', 'M A S H E E N', 'Radical One', 'DR Monstaa'];

// ponytail: stage 26 (the Premier Tournament, matchtracks.radq's five rounds) is left out
export const EXT_STAGES = [[1, 'Introductory Stage'], [2, 'Awesomeness Begins'], [3, 'Consequences'], [4, 'The Chase'],
  [5, 'Peaceful Realm'], [6, 'Drag Race of Epicness'], [7, 'The Garden of the Van'], [8, "Van's Revenge"],
  [9, 'Snowy Raceway'], [10, "Grand Ark's Challenge"], [11, 'Peer Pressure'], [12, 'Killer Grocery Store'],
  [13, "Lorry's Heaven"], [14, 'The Gun Run'], [15, 'Dances with Monsters'], [16, 'Four Dimensional Vertigo'],
  [17, 'Race on Sunrise II'], [18, 'Peaceful Sunset'], [19, 'On the Moon II'], [20, 'Race of the Century'],
  [21, 'The Lair of Hell'], [22, 'Serving the Boy'], [23, 'Stranger Danger'], [24, 'Final Showdown'],
  [25, 'The "Finale"'], [27, 'The Warzone'], [28, 'A Competitive Ending']];

// The RPG career's stages (careertracks.radq), listed only in developer mode:
// practising them in free play would spoil the career.
// ponytail: the four bonus stages (bonus/N.txt, raced through xtGraphics.bonstage) are left out
export const CAREER_STAGES = [[1, "Beginner's Road"], [2, 'Grassy Fields'], [3, 'Madness Alleyway'],
  [4, 'Under Pressure'], [5, 'Crystal Cavern'], [6, 'Ghost Planet'], [7, 'Rave in the Matrix'],
  [8, 'Chaotic Carnival'], [9, 'Burning Abyss'], [10, 'Galaxy Raceway'], [11, 'Corrupted Junkyard'],
  [12, 'Infiltrated Factory'], [13, 'Sky Fortress'], [14, 'Mythical Forest'], [15, 'Spaceship Chaos'],
  [16, 'Sheer Cold'], [17, 'Rise of the Undead'], [18, 'Desolate Desert'], [19, 'Glitch World'],
  [20, 'Race of the Century'], [21, 'Hellzone'], [22, 'Desert Night'], [23, 'Grassland Fires'],
  [24, 'Underwater Base'], [25, 'Undead Apocalypse'], [26, 'Sky Carnival'], [27, 'The Warzone'],
  [28, 'Twisted Revenge'], [29, "Beginner's Road"], [30, "Beginner's Road"], [31, "Beginner's Road"]];

/**
 * The jar's classic-mode stage number for a base NFM2 stage: base 11-27 are
 * NFM2's own seventeen, which classictracks.radq numbers 1-17, and Control's
 * AI is tuned per stage number. The NFM1 (1-10) and multiplayer (28-32)
 * stages have no classic twin.
 */
// ponytail: stage 1's AI for the stages with no twin; per-stage AI for them would need Control's help
export const classicTwin = (n) => (n >= 11 && n <= 27 ? n - 10 : 1);
