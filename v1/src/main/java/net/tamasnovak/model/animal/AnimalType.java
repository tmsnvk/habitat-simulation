package net.tamasnovak.model.animal;

public enum AnimalType {
  ZEBRA('Z'),
  LION('L');

  private final Character sign;

  AnimalType(Character sign) {
    this.sign = sign;
  }

  public Character getSign() {
    return sign;
  }
}
