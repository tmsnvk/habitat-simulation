package net.tamasnovak.model.nature.vegetation;

public enum VegetationSpecies {
  FINGER_GRASS("Finger Grass", "Grass");

  public final String label;
  public final String vegetationType;

  VegetationSpecies(String label, String vegetationType) {
    this.label = label;
    this.vegetationType = vegetationType;
  }

  public String getLabel() {
    return label;
  }

  public String getVegetationType() {
    return vegetationType;
  }
}
