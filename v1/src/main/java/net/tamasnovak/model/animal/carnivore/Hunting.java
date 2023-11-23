package net.tamasnovak.model.animal.carnivore;

public interface Hunting {
  void increaseHungerLevelAfterUnsuccessfulHunt();
  void resetHungerLevelAfterSuccessfulHunt();
  void perishIfTooHungry();
}
