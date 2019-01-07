package br.com.le1712.thorntail.thorntaildemo.domain.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface Repository<E extends PrimaryKeyEntity> extends Serializable {

  public E porId(Object id);

  public E single(DetachedCriteria detachedCriteria);

  public List<E> list(DetachedCriteria detachedCriteria);
  
  public List<E> listComNumeroMaximoRegistros(DetachedCriteria detachedCriteria, Integer numeroMaximoRegistros);
  
  public List<?> listWithProjection(DetachedCriteria detachedCriteria);

  public void add(E entity);

  public void remove(E entity);

}