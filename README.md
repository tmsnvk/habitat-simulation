# the-savannah-simulation

### simulation parameters - the savannah
+ a 20x20 matrix area.
+ contains 200 animals that are 65% percent herbivores (zebras), the rest are carnivores (lions).
+ the blank coordinates are grass (but all coordinates have grass implicitly - this is a savannah, after all).
+ the simulation runs for a 100 years.
+ the simulation is visualised in a terminal window with colour-coded texts and signs.

### simulation parameters - the animals
+ one coordinate may contain one animal only at any given time.
+ every animal is capable of:
    + aging - each animal gets older every year; when a certain condition is met, the animal dies.
    + eating - if an animal is not able to eat, its hunger increases and if the condition is met, it dies.
    + breeding - if the given conditions are met, a new animal of the same species is placed into a neighbouring empty coordinate.
    + moving - if there is an empty neighbouring coordinate, the animal moves there.

### Savannah class
+ has a matrix of the living area coordinates.
+ synchronises the pre-annual routines, e.g. sets too hungry animals to dead state.
+ synchronises the annual animal routines: 
    + chooses an animal and go through its routine in the following order: eat, breed, move, age.
    + each animal gets their round, except those that were born in the given year.
    + no eligible animals are left out, each animal participates once only and in a random fashion.

### Animal class
+ age - the current age of the animal.
+ maximumAge - the maximum age of the animal.
+ livingArea - the coordinates that the animal currently occupies.
+ isAlive.
+ aging process:
    + every year the age increases by one; when it reaches the maximumAge, the animal dies.
+ eat process:
    + the animal eats if the conditions are met.
+ breeding process:
    + an animal is able to breed if the following conditions are met:
        + the animal is not hungry and enough time has passed since the last breeding.
        + a potential mate occupies a neighbouring cell that is also ready for breeding.
        + there is an empty neighbouring coordinate for the new animal to be born into.
+ moving process:
    + if there is an empty neighbouring coordinate, the animal moves there (random choice if there are more than one cells).

### Carnivore - Lion class
+ hunger level - the animal survives 2 years without eating, otherwise dies at the beginning of the next year.
+ breeds every third year but only if not hungry.
+ lives for 9-12 years.

### Herbivore - Zebra class
+ can always eat and never goes hungry (there is grass everywhere).
+ breeds every second year.
+ lives for 11-14 years.

### Cell record
+ x, y coordinates.
