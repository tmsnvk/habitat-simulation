package net.tamasnovak.logic.routine.habitatRoutine;

import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.ui.logger.Logger;

import java.util.Random;

public abstract class HabitatInstanceRoutine {
  protected final Random random;
  protected final Logger logger;
  protected final Matrix matrix;

  public HabitatInstanceRoutine(Random random, Logger logger, Matrix matrix) {
    this.random = random;
    this.logger = logger;
    this.matrix = matrix;
  }

  public abstract void run();
}
