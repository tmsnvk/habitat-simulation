package net.tamasnovak.model.animals;

public enum AnimalTypes {
  ZEBRA('Z'),
  LION('L');

  private final Character sign;

  AnimalTypes(Character sign) {
    this.sign = sign;
  }

  public Character getSign() {
    return sign;
  }
}
