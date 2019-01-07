package br.com.le1712.thorntail.thorntaildemo.application.mysql.builder;

import br.com.le1712.thorntail.thorntaildemo.model.entities.Livro;

public class LivroCriterioBuilder extends DetachedCriteriaBuilder<Livro> {

	private static final long serialVersionUID = 1354225720466944873L;

	public LivroCriterioBuilder() {
		super(Livro.class);
	}

}