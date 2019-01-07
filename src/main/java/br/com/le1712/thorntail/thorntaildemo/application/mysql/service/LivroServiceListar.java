package br.com.le1712.thorntail.thorntaildemo.application.mysql.service;

import java.io.Serializable;
import java.util.List;

import br.com.le1712.thorntail.thorntaildemo.dto.livro.LivroDto;

public interface LivroServiceListar extends Serializable {

  List<LivroDto> listar();
}