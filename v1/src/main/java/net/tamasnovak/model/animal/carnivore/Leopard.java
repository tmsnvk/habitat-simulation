package net.tamasnovak.model.animal.carnivore;

import net.tamasnovak.logic.routines.huntingRoutine.HuntingRoutine;
import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalSpecies;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public final class Leopard extends Carnivore {
  private static final int[] MAXIMUM_AGE_VALUES = new int[]{ 9, 10, 11, 12 };
  private static final int MAXIMUM_HUNGER_LEVEL = 2;
  private static final AnimalSpecies SPECIES = AnimalSpecies.LEOPARD;
  private static final String ICON = "🐆";
  private static int idCounter = 0;
  private final String id;

  public Leopard(Cell livingArea, HuntingRoutine huntingRoutine, Random random) {
    super(livingArea, drawMaximumAgeValue(random), ICON, SPECIES, huntingRoutine);
    this.id = String.format("%s-%s", SPECIES, ++idCounter);
  }

  private static int drawMaximumAgeValue(Random random) {
    int randomMaximumAge = random.nextInt(MAXIMUM_AGE_VALUES.length);

    return MAXIMUM_AGE_VALUES[randomMaximumAge];
  }

  @Override
  protected void move() {

  }

  @Override
  public void dieIfTooHungry() {
    if (this.getHungerLevel() == MAXIMUM_HUNGER_LEVEL) {
      die();
    }
  }

  @Override
  public boolean canBreed() {
    return this.getCurrentAge() % 3 == 0 && this.getHungerLevel() == 0;
  }

  @Override
  public Animal breed() {
    return null;
  }

  @Override
  public String toString() {
    return String.format("[Id]: %s | [Species]: %s | [Type]: %s | [Living area]: %s | [Maximum age]: %s | [Current Age]: %s | [Is alive?]: %s", id, SPECIES, TYPE, livingArea, maximumAge, currentAge, isAlive);
  }
}
