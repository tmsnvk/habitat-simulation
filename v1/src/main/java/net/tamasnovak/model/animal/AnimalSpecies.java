package net.tamasnovak.model.animal;

public enum AnimalSpecies {
  ZEBRA('Z'),
  LION('L');

  private final Character sign;

  AnimalSpecies(Character sign) {
    this.sign = sign;
  }

  public Character getSign() {
    return sign;
  }
}
