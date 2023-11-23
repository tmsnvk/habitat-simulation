package net.tamasnovak.logic.savannah;

import net.tamasnovak.model.animal.carnivore.Carnivore;
import net.tamasnovak.model.animal.herbivore.Herbivore;
import net.tamasnovak.ui.logger.Logger;

import java.util.List;
import java.util.Random;

public class SavannahHuntingRoutine {
  private final Logger logger;
  private final Random random;
  private final SavannahAnimalSearch savannahAnimalSearch;

  public SavannahHuntingRoutine(Logger logger, Random random, SavannahAnimalSearch savannahAnimalSearch) {
    this.logger = logger;
    this.random = random;
    this.savannahAnimalSearch = savannahAnimalSearch;
  }

  void run(Carnivore carnivore) {
    List<Herbivore> neighbourHerbivores = savannahAnimalSearch.findSpecificNeighbourAnimalType(carnivore, Herbivore.class);

    if (neighbourHerbivores.isEmpty()) {
      carnivore.increaseHungerLevelAfterUnsuccessfulHunt();
    } else {
      perishRandomHerbivoreNeighbourAfterSuccessfulHunt(neighbourHerbivores);
      carnivore.resetHungerLevelAfterSuccessfulHunt();
    }
  }

  private void perishRandomHerbivoreNeighbourAfterSuccessfulHunt(List<Herbivore> neighbourHerbivores) {
    int randomNumber = random.nextInt(neighbourHerbivores.size());
    Herbivore randomNeighbourHerbivore = neighbourHerbivores.get(randomNumber);
    randomNeighbourHerbivore.perishIfKilledByCarnivore();
  }
}
