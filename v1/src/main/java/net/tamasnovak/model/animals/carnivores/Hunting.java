package net.tamasnovak.model.animals.carnivores;

public interface Hunting {
  void increaseHungerLevelAfterUnsuccessfulHunt();
  boolean perishIfTooHungry();
}
