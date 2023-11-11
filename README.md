# the-savannah-simulation

### simulation parameters - the board
+ a 20x20 area.
+ 200 animals that are 65% percent herbivores (zebras), the rest are carnivores (lions).
+ the blank cells are grass (but all cells have grass - this is the savannah, after all).
+ run the simulation for a hundred years.
+ the simulation is visualised via JavaFX.

### simulation parameters - the animals
+ one cell may contain one animal only at any given time.
+ every animal is capable of:
    + aging - each animal gets older every year; if the condition is met, the animal dies.
    + eating - if the animal is not able to eat it dies.
    + breeding - if the given conditions are met, a new animal is placed into a neighbouring empty cell.
    + moving - if any neighbouring cells are empty.

### Animal class
+ age - the current age of the animal.
+ maximumAge - the maximum age of the animal.
+ hunger - if reaches a given level, the animal dies.
+ isAlive.
+ livingArea - the cell coordinates where the animal currently lives.
+ age() - every year increases the age; if it reaches the maximumAge, the animal dies.
+ breed() - an animal breeds if the following conditions are met:
    + willingness - the animal is not hungry, enough time has passed since the last breeding, etc.
    + a potential mate occupies a neighbouring cell that is also ready for breeding.
    + there is an empty neighbouring space for the new animal to be born.
+ eat() - the animal eats if the conditions are met.
+ move() - if there is an empty neighbouring cell, the animal moves there (random choice if there are more than one cells).

### Carnivore class
+ breeds every third year but only if not hungry.
+ lives for 9-12 years.
+ survives 1 year without eating, otherwise dies.

### Herbivore class
+ breeds every second year.
+ can always eat (there is grass everywhere).
+ lives for 11-14 years.

### Cell record
+ x, y coordinates.

### Savannah class
+ has a matrix for the living area cells.
+ findAnimal() - returns an animal's coordinates.
+ perishAnimal() - deletes an animal from the matrix.
+ moveAnimal() - moves an animal onto a new coordinate.
+ listNearbyAnimals() - returns a list of neighbouring animals.
+ listNearbyEmptyCells() - returns a list of neighbouring empty cells.
+ placeAnimal() - places an animal onto a given coordinate.
+ passYear() - control the passage of years:
    + choose an animal and go through its routine - age, eat, breed, move.
    + all animals get their round, except those that were born in the given round.
    + no eligible animals are left out, all animals participate once only and in a random fashion.