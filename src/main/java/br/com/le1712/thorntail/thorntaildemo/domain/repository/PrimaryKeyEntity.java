package br.com.le1712.thorntail.thorntaildemo.domain.repository;

import java.io.Serializable;

public abstract class PrimaryKeyEntity implements Serializable {

  private static final long serialVersionUID = -7970692720564497163L;

  public abstract Object getPrimaryKey();

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    return this.hashCode() == obj.hashCode();
  }

}