package net.tamasnovak.model.nature.animal;

import net.tamasnovak.logic.routine.animalRoutine.agingRoutine.AgingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.breedingRoutine.BreedingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.movementRoutine.MovementRoutine;
import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.nature.Nature;

public abstract class Animal extends Nature {
  private static final String DEAD_ANIMAL_ICON = "\uD83D\uDED1";
  protected final int maximumCellMovement;
  protected int currentAge;
  protected final int maximumAge;
  protected boolean isAlive;
  protected boolean didAlreadyBreedInRunningYear;
  protected final AnimalSpecies species;
  protected final AnimalType type;
  protected final AgingRoutine agingRoutine;
  protected final BreedingRoutine breedingRoutine;
  protected final MovementRoutine movementRoutine;

  public Animal(String id, int maximumCellMovement, Cell coordinates, int maximumAge, String icon, AnimalSpecies species, AnimalType type, AgingRoutine agingRoutine, BreedingRoutine breedingRoutine, MovementRoutine movementRoutine) {
    super(id, coordinates, icon);
    this.maximumCellMovement = maximumCellMovement;
    this.currentAge = 0;
    this.isAlive = true;
    this.didAlreadyBreedInRunningYear = false;
    this.coordinates = coordinates;
    this.maximumAge = maximumAge;
    this.species = species;
    this.type = type;
    this.agingRoutine = agingRoutine;
    this.breedingRoutine = breedingRoutine;
    this.movementRoutine = movementRoutine;
  }

  public void setCoordinates(Cell coordinates) {
    this.coordinates = coordinates;
  }

  public int getMaximumCellMovement() {
    return maximumCellMovement;
  }

  public int getCurrentAge() {
    return currentAge;
  }

  public void setCurrentAge(int currentAge) {
    this.currentAge = currentAge;
  }

  public int getMaximumAge() {
    return maximumAge;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public boolean didAlreadyBreedInGivenYear() {
    return didAlreadyBreedInRunningYear;
  }

  public void setDidAlreadyBreedInRunningYear(boolean didAlreadyBreedInRunningYear) {
    this.didAlreadyBreedInRunningYear = didAlreadyBreedInRunningYear;
  }

  public AnimalSpecies getSpecies() {
    return species;
  }

  public AnimalType getType() {
    return type;
  }

  public void perish() {
    this.isAlive = false;
    this.icon = DEAD_ANIMAL_ICON;
  }

  public abstract boolean isAbleToBreed();
  public abstract void doLifeCycleMethods();
}
