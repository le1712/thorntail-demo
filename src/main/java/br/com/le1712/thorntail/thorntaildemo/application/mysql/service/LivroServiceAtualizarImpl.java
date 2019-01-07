package br.com.le1712.thorntail.thorntaildemo.application.mysql.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import br.com.le1712.thorntail.thorntaildemo.application.mysql.service.LivroServiceAtualizar;
import br.com.le1712.thorntail.thorntaildemo.domain.mysql.repository.LivroRepository;
import br.com.le1712.thorntail.thorntaildemo.dto.livro.LivroDto;
import br.com.le1712.thorntail.thorntaildemo.model.entities.Livro;
import br.com.le1712.thorntail.thorntaildemo.rest.request.AtualizarLivroRequest;

@Stateless
@TransactionAttribute
public class LivroServiceAtualizarImpl implements LivroServiceAtualizar {

	private static final long serialVersionUID = 1339920025172113321L;

	@Inject
	private LivroRepository livroRepository;

	@Override
	public LivroDto atualizar(AtualizarLivroRequest AtualizarLivroRequest) {

		if (AtualizarLivroRequest == null) {
			return null;
		}

		Livro livro = criar(AtualizarLivroRequest.getTitulo(), AtualizarLivroRequest.getDescricao(),
				AtualizarLivroRequest.getAutor());

		livroRepository.add(livro);

		livro = livroRepository.porId(livro.getPrimaryKey());

		return criarDto(livro.getPrimaryKey(), livro.getTitulo(), livro.getDescricao(), livro.getAutor());
	}

	private Livro criar(String titulo, String descricao, String autor) {
		return new Livro(titulo, descricao, autor);
	}

	private LivroDto criarDto(Long id, String titulo, String descricao, String autor) {
		return new LivroDto(id, titulo, descricao, autor);
	}

}