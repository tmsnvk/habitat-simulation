package net.tamasnovak.ui.simulation;

import net.tamasnovak.logic.habitat.Habitat;

import java.util.ArrayList;
import java.util.List;

public class SimulationController {
  private final List<Habitat> habitats;

  public SimulationController() {
    this.habitats = new ArrayList<>();
  }

  public void addHabitat(Habitat habitat) {
    habitats.add(habitat);
  }

  public void runSimulation() {

  }
}
