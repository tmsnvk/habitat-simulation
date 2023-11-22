package net.tamasnovak.logic.savannah;

import java.util.List;

final class SavannahConfiguration {
  static final int LENGTH_OF_SIMULATION_YEARS = 100;
  static final int NUMBER_OF_ANIMALS = 200;
  static final double CHANCE_OF_HERBIVORE = 0.65;
  static final List<List<Integer>> POSSIBLE_NEARBY_COORDINATE_DIFFERENCES = List.of(
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

  private SavannahConfiguration() {}
}
