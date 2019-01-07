package br.com.le1712.thorntail.thorntaildemo.application.mysql.service;

import java.io.Serializable;

import br.com.le1712.thorntail.thorntaildemo.dto.livro.LivroDto;
import br.com.le1712.thorntail.thorntaildemo.rest.request.AtualizarLivroRequest;

public interface LivroServiceAtualizar extends Serializable {

  LivroDto atualizar(AtualizarLivroRequest atualizarLivroRequest);
}