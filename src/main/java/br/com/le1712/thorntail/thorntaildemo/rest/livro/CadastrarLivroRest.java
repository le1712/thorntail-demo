package br.com.le1712.thorntail.thorntaildemo.rest.livro;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.le1712.thorntail.thorntaildemo.application.mysql.service.LivroServiceCadastrar;
import br.com.le1712.thorntail.thorntaildemo.dto.livro.LivroDto;
import br.com.le1712.thorntail.thorntaildemo.rest.request.CadastrarLivroRequest;
import br.com.le1712.thorntail.thorntaildemo.rest.response.CadastrarLivroResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "/livro/cadastrar")
@Path("/livro/cadastrar")
@Produces(MediaType.APPLICATION_JSON)
public class CadastrarLivroRest {

	private static final String LIVRO_VALIDO = "O request não é valido.";

	@Inject
	private LivroServiceCadastrar livroServiceCadastrar;

	@ApiOperation(nickname = "cadastrarLivro", value = "Cadastrar Livro")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Tudo funcionou como esperado", response = CadastrarLivroResponse.class),
			@ApiResponse(code = 400, message = "Existe algum erro na requisição informada"),
			@ApiResponse(code = 404, message = "Não existe veículo para a placa informada"),
			@ApiResponse(code = 500, message = "Ocorreu algum erro interno"),
			@ApiResponse(code = 602, message = "Veículo não encontrado"),
			@ApiResponse(code = 604, message = "Sistema Detran Indisponível"),
			@ApiResponse(code = 605, message = "Placa Inválida, Não Encontrada no Detran"),
			@ApiResponse(code = 607, message = "Cliente não encontrado na base de dados públicos"),
			@ApiResponse(code = 608, message = "Cliente se declara como pessoa restrita e bloqueou seus dados"),
			@ApiResponse(code = 609, message = "Acesso ao servidor negado") })
	@POST
	public Response cadastrar(@Valid @NotNull(message = LIVRO_VALIDO) CadastrarLivroRequest cadastrarLivroRequest) {
		LivroDto livroDto = livroServiceCadastrar.cadastrar(cadastrarLivroRequest);
		return Response.ok(transforma(livroDto)).build();
	}

	private CadastrarLivroResponse transforma(LivroDto livroDto) {
		if (livroDto == null) {
			return null;
		}
		CadastrarLivroResponse cadastrarLivroResponse = new CadastrarLivroResponse();
		cadastrarLivroResponse.setAutor(livroDto.getAutor());
		cadastrarLivroResponse.setDescricao(livroDto.getDescricao());
		cadastrarLivroResponse.setTitulo(livroDto.getTitulo());
		return cadastrarLivroResponse;
	}
}