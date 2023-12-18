package net.tamasnovak.model.nature;

import net.tamasnovak.model.matrix.Cell;

public abstract class Nature {
  protected final String id;
  protected Cell coordinates;
  protected String icon;

  public Nature(String id, Cell coordinates, String icon) {
    this.id = id;
    this.coordinates = coordinates;
    this.icon = icon;
  }

  public String getId() {
    return id;
  }

  public String getIcon() {
    return icon;
  }
}
