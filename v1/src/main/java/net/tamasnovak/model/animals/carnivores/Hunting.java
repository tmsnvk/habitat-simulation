package net.tamasnovak.model.animals.carnivores;

import net.tamasnovak.model.animals.Animal;

import java.util.List;

public interface Hunting {
  void eat(List<Animal> neighbourAnimals);
}
