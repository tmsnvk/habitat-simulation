package net.tamasnovak.ui.display;

public enum Color {
  RESET("\033[0m"),

  BLACK_BOLD("\033[1;30m"),
  RED_BOLD("\033[1;31m"),
  GREEN_BOLD("\033[1;32m"),
  YELLOW_BOLD("\033[1;33m"),
  BLUE_BOLD("\033[1;34m");

  private final String code;

  Color(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }
}
