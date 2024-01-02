package net.tamasnovak.model.nature.vegetation;

public enum VegetationType {
  GRASS("Savannah");

  public final String habitatType;

  VegetationType(String habitatType) {
    this.habitatType = habitatType;
  }

  public String getHabitatType() {
    return habitatType;
  }
}
