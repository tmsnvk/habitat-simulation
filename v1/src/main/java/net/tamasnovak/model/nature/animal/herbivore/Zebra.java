package net.tamasnovak.model.nature.animal.herbivore;

import net.tamasnovak.logic.routine.animalRoutine.AgingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.BreedingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.MovementRoutine;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.matrix.Position;

import java.util.Random;

public final class Zebra extends Herbivore {
  private static final int[] MAXIMUM_AGE_VALUES = new int[]{ 11, 12, 13, 14 };
  private static final int MAXIMUM_CELL_MOVEMENT = 1;
  private static final AnimalSpecies SPECIES = AnimalSpecies.ZEBRA;
  private static final String ICON = "🦓";
  private static int idCounter = 0;

  public Zebra(Random random, Position position, AgingRoutine agingRoutine, BreedingRoutine breedingRoutine, MovementRoutine movementRoutine) {
    super(createId(), MAXIMUM_CELL_MOVEMENT, position, drawMaximumAgeValue(random), ICON, SPECIES, agingRoutine, breedingRoutine, movementRoutine);
  }

  private static String createId() {
    return String.format("%s-%s", SPECIES, ++idCounter);
  }

  private static int drawMaximumAgeValue(Random random) {
    int randomMaximumAge = random.nextInt(MAXIMUM_AGE_VALUES.length);

    return MAXIMUM_AGE_VALUES[randomMaximumAge];
  }

  @Override
  public boolean isAbleToBreed() {
    return this.currentAge % 2 == 0;
  }

  @Override
  public String toString() {
    return String.format("[Id]: %s | [Species]: %s | [Type]: %s | [Position]: %s | [Maximum age]: %s | [Current Age]: %s | [Is alive?]: %s", id, species, type, position, maximumAge, currentAge,
      isAlive);
  }
}
