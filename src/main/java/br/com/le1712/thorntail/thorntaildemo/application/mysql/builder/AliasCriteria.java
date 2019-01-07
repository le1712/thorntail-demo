package br.com.le1712.thorntail.thorntaildemo.application.mysql.builder;

import java.io.Serializable;

import org.hibernate.criterion.Criterion;
import org.hibernate.sql.JoinType;

public class AliasCriteria implements Serializable {

  private static final long serialVersionUID = 1L;

  private String associationPath;

  private String alias;

  private JoinType joinType;

  private Criterion criterion;

  public AliasCriteria(String associationPath, String alias, JoinType joinType, Criterion criterion) {
    this(associationPath, alias, joinType);
    this.criterion = criterion;
  }

  public AliasCriteria(String associationPath, String alias, JoinType joinType) {
    super();
    this.associationPath = associationPath;
    this.alias = alias;
    this.joinType = joinType;
  }

  public String getAssociationPath() {
    return associationPath;
  }

  public String getAlias() {
    return alias;
  }

  public JoinType getJoinType() {
    return joinType;
  }

  public Criterion getCriterion() {
    return criterion;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((alias == null) ? 0 : alias.hashCode());
    result = prime * result + ((associationPath == null) ? 0 : associationPath.hashCode());
    return result;
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
