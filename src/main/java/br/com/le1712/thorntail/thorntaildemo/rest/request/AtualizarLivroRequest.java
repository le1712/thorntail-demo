package br.com.le1712.thorntail.thorntaildemo.rest.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AtualizarLivroRequest implements Serializable {

	private static final long serialVersionUID = -2643424005299636902L;

	@NotNull(message = "Campo id obrigatorio.")
	private Long id;
	
	@NotNull(message = "Campo titulo obrigatorio.")
	private String titulo;

	private String descricao;

	private String autor;

	public AtualizarLivroRequest() {
	}

	public AtualizarLivroRequest(String titulo, String descricao, String autor) {
		this.setTitulo(titulo);
		this.setDescricao(descricao);
		this.setAutor(autor);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return this.hashCode() == obj.hashCode();
	}

}
