package net.tamasnovak.model.nature.animal.carnivore;

import net.tamasnovak.logic.routine.animalRoutine.agingRoutine.AgingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.breedingRoutine.BreedingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.huntingRoutine.HuntingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.movementRoutine.MovementRoutine;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public final class Leopard extends Carnivore {
  private static final int[] MAXIMUM_AGE_VALUES = new int[]{ 9, 10, 11, 12 };
  private static final int MAXIMUM_HUNGER_LEVEL = 2;
  private static final int MAXIMUM_CELL_MOVEMENT = 2;
  private static final AnimalSpecies SPECIES = AnimalSpecies.LEOPARD;
  private static final String ICON = "🐆";
  private static int idCounter = 0;

  public Leopard(Random random, Cell coordinates, AgingRoutine agingRoutine, HuntingRoutine huntingRoutine, BreedingRoutine breedingRoutine, MovementRoutine movementRoutine) {
    super(createId(), MAXIMUM_HUNGER_LEVEL, MAXIMUM_CELL_MOVEMENT, coordinates, drawMaximumAgeValue(random), ICON, SPECIES, agingRoutine, huntingRoutine, breedingRoutine, movementRoutine);
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
    return this.currentAge % 3 == 0 && this.hungerLevel == 0;
  }

  @Override
  public String toString() {
    return String.format("[Id]: %s | [Species]: %s | [Type]: %s | [Coordinates]: %s | [Maximum age]: %s | [Current Age]: %s | [Maximum Hunger Level]: %s | [Current Hunger Level]: %s | [Is alive?]: %s", id, species, type, coordinates, maximumAge, currentAge, MAXIMUM_HUNGER_LEVEL, hungerLevel, isAlive);
  }
}
