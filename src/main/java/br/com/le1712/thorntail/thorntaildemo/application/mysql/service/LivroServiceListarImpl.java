package br.com.le1712.thorntail.thorntaildemo.application.mysql.service;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import br.com.le1712.thorntail.thorntaildemo.application.mysql.builder.LivroCriterioBuilder;
import br.com.le1712.thorntail.thorntaildemo.domain.mysql.repository.LivroRepository;
import br.com.le1712.thorntail.thorntaildemo.dto.livro.LivroDto;
import br.com.le1712.thorntail.thorntaildemo.model.entities.Livro;

@Stateless
@TransactionAttribute
public class LivroServiceListarImpl implements LivroServiceListar {

	private static final long serialVersionUID = 1339920025172113321L;

	@Inject
	private LivroRepository livroRepository;

	@Override
	public List<LivroDto> listar() {

		List<Livro> livros = livroRepository.list(new LivroCriterioBuilder().build());

		return criarDto(livros);
	}

	private List<LivroDto> criarDto(List<Livro> livros) {

		if (isEmpty(livros)) {
			return Collections.emptyList();
		}

		List<LivroDto> livrosDto = new LinkedList<>();
		livros.forEach(livro -> livrosDto.add(criarDto(livro)));

		return livrosDto;
	}

	private LivroDto criarDto(Livro livro) {
		return new LivroDto(livro.getId(), livro.getTitulo(), livro.getDescricao(), livro.getAutor());
	}

	private static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}
}