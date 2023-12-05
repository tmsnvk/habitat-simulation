package net.tamasnovak.logic.habitat.savannah;

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

  Herbivore run(Carnivore carnivore) {
    List<Herbivore> neighbourHerbivores = savannahAnimalSearch.findNeighbourAnimalTypeInstances(carnivore, Herbivore.class);
    Herbivore killedHerbivore = null;

    if (neighbourHerbivores.isEmpty()) {
      carnivore.increaseHungerLevelAfterUnsuccessfulHunt();
    } else {
      killedHerbivore = killRandomHerbivoreNeighbour(neighbourHerbivores);
      carnivore.resetHungerLevelAfterSuccessfulHunt();
    }

    return killedHerbivore;
  }

  private Herbivore killRandomHerbivoreNeighbour(List<Herbivore> neighbourHerbivores) {
    int randomNumber = random.nextInt(neighbourHerbivores.size());
    Herbivore randomNeighbourHerbivore = neighbourHerbivores.get(randomNumber);
    randomNeighbourHerbivore.perishIfKilledByCarnivore();

    return randomNeighbourHerbivore;
  }
}
