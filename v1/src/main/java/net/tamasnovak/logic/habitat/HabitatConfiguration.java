package net.tamasnovak.logic.habitat;

import java.util.List;

public abstract class HabitatConfiguration {
  public final int LENGTH_OF_SIMULATION_YEARS = 100;
  public final int NUMBER_OF_ANIMALS = 200;
  public final List<List<Integer>> POSSIBLE_NEARBY_COORDINATE_DIFFERENCES = List.of(
    // up-down, left-right
    List.of(0, 1),
    List.of(0, -1),
    List.of(-1, 0),
    List.of(1, 0)
    // across
//    List.of(1, 1),
//    List.of(1, -1),
//    List.of(-1, -1),
//    List.of(-1, 1)
  );

  public HabitatConfiguration() {}
}
