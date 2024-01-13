package net.tamasnovak.logic.habitat;

import net.tamasnovak.logic.factory.configurationFactory.SavannahConfiguration;
import net.tamasnovak.logic.routine.habitatRoutine.populatorRoutine.PopulatorRoutine;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.ui.display.Display;
import net.tamasnovak.ui.logger.Logger;

import java.util.Random;

public abstract class Habitat {
  protected final Random random;
  protected final Logger logger;
  protected final Display display;
  protected final Matrix matrix;
  protected final HabitatType habitatType;
  protected final HabitatConfiguration habitatConfiguration;
  protected final PopulatorRoutine populatorRoutine;

  protected Habitat(Random random, Logger logger, Display display, HabitatType habitatType, SavannahConfiguration habitatConfiguration, Matrix matrix, PopulatorRoutine populatorRoutine) {
    this.random = random;
    this.logger = logger;
    this.display = display;
    this.habitatType = habitatType;
    this.habitatConfiguration = habitatConfiguration;
    this.matrix = matrix;
    this.populatorRoutine = populatorRoutine;
  }

  public HabitatType getHabitatType() {
    return habitatType;
  }

  public abstract void runHabitat();
}
