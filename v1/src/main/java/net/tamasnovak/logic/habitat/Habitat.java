package net.tamasnovak.logic.habitat;

import net.tamasnovak.logic.routines.populatorRoutine.PopulatorRoutine;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.ui.logger.Logger;

import java.util.Random;

public abstract class Habitat {
  protected final Random random;
  protected final Logger logger;
  protected final Matrix matrix;
  protected final PopulatorRoutine populatorRoutine;

  protected Habitat(Random random, Logger logger, Matrix matrix, PopulatorRoutine populatorRoutine) {
    this.random = random;
    this.logger = logger;
    this.matrix = matrix;
    this.populatorRoutine = populatorRoutine;
  }

  public abstract void runSimulation();
}
