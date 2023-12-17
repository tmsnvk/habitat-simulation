package net.tamasnovak.model.animal;

public enum AnimalSpecies {
  ZEBRA("🦓"),
  LEOPARD("🐆");

  private final String sign;

  AnimalSpecies(String sign) {
    this.sign = sign;
  }

  public String getSign() {
    return sign;
  }
}
