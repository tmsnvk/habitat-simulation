package net.tamasnovak.logic.routine.animalRoutine;

import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.ui.logger.Logger;

import java.util.Random;

public abstract class AnimalInstanceRoutine {
  protected final Random random;
  protected final Logger logger;
  protected final Matrix matrix;

  public AnimalInstanceRoutine(Random random, Logger logger, Matrix matrix) {
    this.random = random;
    this.logger = logger;
    this.matrix = matrix;
  }

  public abstract <T extends Animal> void run(T animal);
}
