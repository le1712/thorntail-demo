package br.com.le1712.thorntail.thorntaildemo.application.mysql.builder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

public abstract class DetachedCriteriaBuilder<E> implements Serializable {

	private static final long serialVersionUID = 3593893557822277229L;

	private Class<E> entidade;

	private DetachedCriteria detachedCriteria;

	protected Set<Criterion> listaCriterio;

	protected Set<AliasCriteria> listaAlias;

	protected Set<Order> listaOrder;

	protected Set<Projection> listaProjection;

	public DetachedCriteriaBuilder(Class<E> entidade) {
		this.detachedCriteria = DetachedCriteria.forClass(entidade);
		this.entidade = entidade;
		this.listaCriterio = new HashSet<>();
		this.listaAlias = new HashSet<>();
		this.listaOrder = new HashSet<>();
		this.listaProjection = new LinkedHashSet<>();
	}

	public DetachedCriteria build() {
		this.detachedCriteria = DetachedCriteria.forClass(entidade);

		if (!isEmpty(this.listaAlias)) {
			for (AliasCriteria alias : listaAlias) {
				this.detachedCriteria.createAlias(alias.getAssociationPath(), alias.getAlias(), alias.getJoinType(),
						alias.getCriterion());
			}
		}
		if (!isEmpty(this.listaCriterio)) {
			for (Criterion criterion : this.listaCriterio) {
				this.detachedCriteria.add(criterion);
			}
		}

		if (!isEmpty(this.listaOrder)) {
			for (Order order : this.listaOrder) {
				this.detachedCriteria.addOrder(order);
			}
		}

		if (!isEmpty(this.listaProjection)) {
			ProjectionList projectionList = Projections.projectionList();
			for (Projection projection : this.listaProjection) {
				projectionList = projectionList.add(projection);
			}
			this.detachedCriteria.setProjection(projectionList);
		}

		this.listaCriterio = new HashSet<>();
		this.listaAlias = new HashSet<>();
		this.listaOrder = new HashSet<>();
		this.listaProjection = new LinkedHashSet<>();
		return detachedCriteria;
	}

	public DetachedCriteria buildDistinct() {
		return build().setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	}

	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	public static List<String> convertStringToList(String valor, String separador) {
		if (StringUtils.isEmpty(valor) || StringUtils.isEmpty(separador)) {
			return Collections.emptyList();
		}

		return Arrays.asList(valor.split(separador));
	}

	public static String joinListaComSeparator(List<String> values, String separador) {
		return isEmpty(values) ? null : StringUtils.join(values.toArray(), separador);
	}

}