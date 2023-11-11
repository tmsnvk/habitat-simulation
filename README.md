# the-savannah-simulation

### simulation parameters - the savannah
+ a 20x20 matrix area.
+ contains 200 animals that are 65% percent herbivores (zebras), the rest are carnivores (lions).
+ the blank coordinates are grass (but all coordinates have grass implicitly - this is a savannah, after all).
+ run the simulation for 100 years.
+ the simulation is visualised in a terminal window with colour-coded texts and signs.

### simulation parameters - the animals
+ one coordinate may contain one animal only at any given time.
+ every animal is capable of:
    + aging - each animal gets older every year; if the condition is met, the animal dies.
    + eating - if an animal is not able to eat, its hunger increases and if the condition is met, it dies.
    + breeding - if the given conditions are met, a new animal is placed into a neighbouring empty coordinate.
    + moving - if any neighbouring coordinates are empty, the animal moves into one of them.

### Animal class
+ age - the current age of the animal.
+ maximumAge - the maximum age of the animal.
+ hunger - if hunger reaches a given level, the animal dies.
+ isAlive.
+ livingArea - the coordinates where the animal currently lives.
+ age() - every year increases the age; if it reaches the maximumAge, the animal dies.
+ eat() - the animal eats if the conditions are met.
+ breed() - an animal is able to breed if the following conditions are met:
    + willingness - the animal is not hungry and enough time has passed since the last breeding.
    + a potential mate occupies a neighbouring cell that is also ready for breeding.
    + there is an empty neighbouring coordinate for the new animal to be born into.
+ move() - if there is an empty neighbouring coordinate, the animal moves there (random choice if there are more than one cells).

### Carnivore class
+ breeds every third year but only if not hungry.
+ lives for 9-12 years.
+ survives 1 year without eating, otherwise dies at the beginning of the next year.

### Herbivore class
+ breeds every second year.
+ can always eat (there is grass everywhere).
+ lives for 11-14 years.

### Cell record
+ x, y coordinates.

### Savannah class
+ has a matrix of the living area coordinates.
+ findAnimal() - returns an animal's coordinates.
+ perishAnimal() - deletes an animal from the matrix.
+ moveAnimal() - moves an animal onto a new coordinate.
+ listNearbyAnimals() - returns a list of neighbouring animals.
+ listNearbyEmptyCells() - returns a list of neighbouring empty cells.
+ placeAnimal() - places an animal onto a given coordinate.
+ performAnnualRoutine() - control the passage of years:
    + choose an animal and go through its routine - age, eat, breed, move.
    + all animals get their round, except those that were born in the given round.
    + no eligible animals are left out, all animals participate once only and in a random fashion.