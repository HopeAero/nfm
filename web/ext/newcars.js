// Extended's new cars: Car Maker cars ADDED after Extended's 39, never replacing one.
// A new car has two numbers. Its INDEX (NEW_BASE + i) addresses the model array and
// every per-car table (newcars-grow.js). Its IDENTITY is its donor's number: every
// `car <op> literal` comparison in the generated classes asks id(car) (ext-ident.mjs),
// so the donor's special power and quirks come with it. Stock cars are their own identity.
// Model array: 0-38 cars, 39-77 track pieces, 78-116 beast cars (cn + 78),
// 117-128 scenery, 129-196 stagecompat's NFM 2 models -- hence 200.
export const STOCK = 39;
export const NEW_BASE = 200;

let cars = [];
let donors = new Int32Array(0);

export function setNewCars(list) {
  cars = list.slice();
  donors = Int32Array.from(cars, (c) => c.donor);
}
export const newCars = () => cars;
export const id = (c) => (c >= NEW_BASE ? donors[c - NEW_BASE] : c);
export const isNew = (c) => c >= NEW_BASE;

// Free Play's car select browses the game's 39 ('game') or the Car Maker's ('mine'), never
// both at once: your cars show only when you ask for them (menus.js).
export const CAR_GROUPS = ['game', 'mine'];
let group = 'game';
export function setCarGroup(g) { group = CAR_GROUPS.includes(g) ? g : 'game'; }
export const carGroup = () => (cars.length ? group : 'game');
export const firstCar = () => (carGroup() === 'mine' ? NEW_BASE : 0);
export const lastCar = () => (carGroup() === 'mine' ? NEW_BASE + cars.length - 1 : STOCK - 1);
export const inGroup = (c) => c >= firstCar() && c <= lastCar();
export const groupOf = (c) => (isNew(c) ? 'mine' : 'game');

/** The Free Play car select's arrows: within the group, no wrap. */
export const nextCar = (c, d) => (d > 0 ? Math.min(c + 1, lastCar()) : Math.max(c - 1, firstCar()));

/** ▴ ▾ on the car select: the other group, and its first car. */
export function cycleCarGroup(c) {
  if (!cars.length) return { group: 'game', car: c };
  setCarGroup(carGroup() === 'game' ? 'mine' : 'game');
  return { group: carGroup(), car: firstCar() };
}
