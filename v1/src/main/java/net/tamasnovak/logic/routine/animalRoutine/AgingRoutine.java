package net.tamasnovak.logic.routine.animalRoutine;

import net.tamasnovak.model.nature.animal.Animal;

public final class AgingRoutine {
  public AgingRoutine() {}

  public <T extends Animal> void run(T animal) {
    if (animal.getCurrentAge() == animal.getMaximumAge()) {
      animal.perish();
    } else {
      animal.setCurrentAge(animal.getCurrentAge() + 1);
    }
  }
}
