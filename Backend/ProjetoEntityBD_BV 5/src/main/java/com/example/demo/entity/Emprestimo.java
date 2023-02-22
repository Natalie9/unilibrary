package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Emprestimo {
	// PARÂMETROS
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(targetEntity = Usuario.class) // CHAVE ESTRANGEIRA
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	@ManyToOne(targetEntity = Livro.class) // CHAVE ESTRANGEIRA
	@JoinColumn(name = "livro")
	private Livro livro;

//    @todo: colocar como enum e padrão  
	@Column(nullable = false) // PODE SER PENDENTE, ATRASADO OU DEVOLVIDO
	private String status;

	// GETTERS
	public long getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Livro getLivro() {
		return livro;
	}

	public String getStatus() {
		return status;
	}

	// SETTERS
	public void setId(long id) {
		this.id = id;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}