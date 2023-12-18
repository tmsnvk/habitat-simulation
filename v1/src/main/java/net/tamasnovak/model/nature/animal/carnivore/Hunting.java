package net.tamasnovak.model.nature.animal.carnivore;

public interface Hunting {
  void hunt();
  void increaseHungerLevel();
  void resetHungerLevel();
  void dieIfTooHungry();
}
