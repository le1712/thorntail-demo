package br.com.le1712.thorntail.thorntaildemo.rest.livro;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.le1712.thorntail.thorntaildemo.application.mysql.service.LivroServiceListar;
import br.com.le1712.thorntail.thorntaildemo.dto.livro.LivroDto;
import br.com.le1712.thorntail.thorntaildemo.rest.response.CadastrarLivroResponse;
import br.com.le1712.thorntail.thorntaildemo.rest.response.ListarLivroResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.infinispan.Cache;
import org.wildfly.swarm.health.Health;


@Api(tags = "/livro/listar")
@Path("/livro/listar")
@Produces(MediaType.APPLICATION_JSON)
public class ListarLivroRest {
	
	@Inject
	Cache<String, List<ListarLivroResponse>> cache;

	@Inject
	private LivroServiceListar livroServiceListar;

	@ApiOperation(nickname = "listarLivro", value = "Listar Livro")
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
	//@Health
	@Counted(name = "listarLivroCount",absolute = true,monotonic = true)
	@POST
	public Response listar() {
		
		List<ListarLivroResponse> listarLivroResponse = cache.get("listarLivro");
	    
	    if(listarLivroResponse != null && listarLivroResponse.size()>0) {
	      return Response.ok(listarLivroResponse).build(); 
	    }    
		
		List<LivroDto> livroDto = livroServiceListar.listar();
		listarLivroResponse = transforma(livroDto);		
		cache.put("listarLivro", listarLivroResponse, 12, TimeUnit.HOURS);
		
		return Response.ok(listarLivroResponse).build(); 
	}

	
	private List<ListarLivroResponse> transforma(List<LivroDto> livros) {

		List<ListarLivroResponse> listarLivroResponse = new LinkedList<>();
		livros.forEach(livro -> listarLivroResponse.add(criarDto(livro)));
		return listarLivroResponse;
	}

	private ListarLivroResponse criarDto(LivroDto livro) {
		ListarLivroResponse listarLivroResponse = new ListarLivroResponse();
		listarLivroResponse.setAutor(livro.getAutor());
		listarLivroResponse.setDescricao(livro.getDescricao());
		listarLivroResponse.setTitulo(livro.getTitulo());
		return listarLivroResponse;
	}
}