package net.tamasnovak.model.nature.animal.carnivore;

import net.tamasnovak.logic.routine.breedingRoutine.BreedingRoutine;
import net.tamasnovak.logic.routine.huntingRoutine.HuntingRoutine;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public final class Leopard extends Carnivore {
  private static final int[] MAXIMUM_AGE_VALUES = new int[]{ 9, 10, 11, 12 };
  private static final int MAXIMUM_HUNGER_LEVEL = 2;
  private static final AnimalSpecies SPECIES = AnimalSpecies.LEOPARD;
  private static final String ICON = "🐆";
  private static int idCounter = 0;

  public Leopard(Random random, Cell coordinates, HuntingRoutine huntingRoutine, BreedingRoutine breedingRoutine) {
    super(createId(), MAXIMUM_HUNGER_LEVEL, coordinates, drawMaximumAgeValue(random), ICON, SPECIES, huntingRoutine, breedingRoutine);
  }

  private static String createId() {
    return String.format("%s-%s", SPECIES, ++idCounter);
  }

  private static int drawMaximumAgeValue(Random random) {
    int randomMaximumAge = random.nextInt(MAXIMUM_AGE_VALUES.length);

    return MAXIMUM_AGE_VALUES[randomMaximumAge];
  }

  @Override
  protected void move() {

  }

  @Override
  protected boolean isAbleToBreed() {
    return this.getCurrentAge() % 3 == 0 && this.getHungerLevel() == 0;
  }

  @Override
  public String toString() {
    return String.format("[Id]: %s | [Species]: %s | [Type]: %s | [Living area]: %s | [Maximum age]: %s | [Current Age]: %s | [Maximum Hunger Level]: %s | [Is alive?]: %s", id, SPECIES, TYPE, coordinates, maximumAge, currentAge, MAXIMUM_HUNGER_LEVEL,  isAlive);
  }
}
