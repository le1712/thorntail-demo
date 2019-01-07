package br.com.le1712.thorntail.thorntaildemo.domain.mysql.repository;

import br.com.le1712.thorntail.thorntaildemo.domain.repository.RepositoryImpl;
import br.com.le1712.thorntail.thorntaildemo.model.entities.Livro;

public class LivroRepositoryImpl extends RepositoryImpl<Livro> implements LivroRepository {

	private static final long serialVersionUID = 2925431107125718803L;

	public LivroRepositoryImpl() {
	    super(Livro.class);
	  }

}