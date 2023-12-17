package net.tamasnovak.model.animal.carnivore;

public interface Hunting {
  void hunt();
  void increaseHungerLevel();
  void resetHungerLevel();
  void dieIfTooHungry();
}
