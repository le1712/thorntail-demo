package br.com.le1712.thorntail.thorntaildemo.dto.livro;

import java.io.Serializable;

public class LivroDto implements Serializable {

	private static final long serialVersionUID = 8973240475794835050L;

	private Long id;

	private String titulo;

	private String descricao;

	private String autor;

	public LivroDto() { }

	public LivroDto(Long id, String titulo, String descricao, String autor) {
		this.setId(id);
		this.setTitulo(titulo);
		this.setDescricao(descricao);
		this.setAutor(autor);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
