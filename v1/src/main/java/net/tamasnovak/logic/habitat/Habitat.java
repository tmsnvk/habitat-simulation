package net.tamasnovak.logic.habitat;

import net.tamasnovak.logic.routines.populatorRoutine.PopulatorRoutine;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.ui.logger.Logger;

import java.util.Random;

public abstract class Habitat {
  protected final Random random;
  protected final Logger logger;
  protected final Matrix matrix;
  protected final HabitatConfiguration habitatConfiguration;
  protected final PopulatorRoutine populatorRoutine;

  protected Habitat(Random random, Logger logger, HabitatConfiguration habitatConfiguration, Matrix matrix, PopulatorRoutine populatorRoutine) {
    this.random = random;
    this.logger = logger;
    this.habitatConfiguration = habitatConfiguration;
    this.matrix = matrix;
    this.populatorRoutine = populatorRoutine;
  }

  public abstract void runHabitat();
}
