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
export const lastCar = () => (cars.length ? NEW_BASE + cars.length - 1 : STOCK - 1);

/** The Free Play car select's arrows: 0..38, then the new cars, no wrap. */
export function nextCar(c, d) {
  if (d > 0) return c === STOCK - 1 ? (cars.length ? NEW_BASE : c) : Math.min(c + 1, lastCar());
  return c === NEW_BASE ? STOCK - 1 : Math.max(c - 1, 0);
}
