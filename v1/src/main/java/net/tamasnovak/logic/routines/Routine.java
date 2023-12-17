package net.tamasnovak.logic.routines;

import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.ui.logger.Logger;

import java.util.Random;

public abstract class Routine {
  protected final Random random;
  protected final Logger logger;
  protected final Matrix matrix;

  public Routine(Random random, Logger logger, Matrix matrix) {
    this.random = random;
    this.logger = logger;
    this.matrix = matrix;
  }
}
