package net.tamasnovak.logic.animalFactory.herbivoreFactory;

import net.tamasnovak.model.animal.herbivore.Zebra;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public interface HerbivoreFactory {
  Zebra createZebra(Cell livingArea, Random random);
}
