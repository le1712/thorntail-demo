package br.com.le1712.thorntail.thorntaildemo.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.le1712.thorntail.thorntaildemo.domain.repository.PrimaryKeyEntity;

@Entity
@Table(name = "livro")
public class Livro extends PrimaryKeyEntity {

	private static final long serialVersionUID = 2052417134065845282L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column
	private String titulo;

	@Column
	private String descricao;

	@Column
	private String autor;

	@Deprecated
	public Livro() {
	}
	
	public Livro(String titulo, String descricao, String autor) {
		this.setTitulo(titulo);
		this.setDescricao(descricao);
		this.setAutor(autor);
	}

	public Livro(Long id, String titulo, String descricao, String autor) {
		this.setId(id);
		this.setTitulo(titulo);
		this.setDescricao(descricao);
		this.setAutor(autor);
	}

	@Override
	public Long getPrimaryKey() {
		return id;
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
