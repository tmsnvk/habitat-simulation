package net.tamasnovak.logic.animalFactory.carnivoreFactory;

import net.tamasnovak.model.animal.carnivore.Lion;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public interface CarnivoreFactory {
  Lion createLion(Cell livingArea, Random random);
}
