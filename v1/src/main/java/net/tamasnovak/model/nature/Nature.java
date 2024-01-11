package net.tamasnovak.model.nature;

import net.tamasnovak.model.matrix.Position;

public abstract class Nature {
  protected final String id;
  protected Position position;
  protected String icon;

  public Nature(String id, Position position, String icon) {
    this.id = id;
    this.position = position;
    this.icon = icon;
  }

  public String getId() {
    return id;
  }

  public String getIcon() {
    return icon;
  }

  public Position getPosition() {
    return position;
  }
}
