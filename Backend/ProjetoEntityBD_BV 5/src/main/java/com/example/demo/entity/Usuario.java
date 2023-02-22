package com.example.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
public class Usuario {

	// PARÂMETROS
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false, unique = true) // UNICO, USADO NO MÉTODO DE PESQUISA OU PARA PREENCHER
	@Pattern(regexp = "[0-9]{8}", message = "A matrícula precisa ser preenchida")
	private String matricula;

	@Column(nullable = false)
	@NotEmpty(message = "Nome completo precisa ser preenchido")
	@Length(min = 3, max = 100, message = "O nome completo precisa ter mais de 2 letras e menos de 100")
	private String nome;

	@Column(nullable = false)
	private String status; // PODE SER ATIVO, INATIVO OU BLOQUEADO

	@Email(message = "Email precisa ser válido")
	private String email;

	@Pattern(regexp = "[0-9]{11}", message = "O telefone precisa ser válido")
	private String telefone;

	@OneToMany(mappedBy = "usuario") // CHAVE ESTRANGEIRA
	private List<Emprestimo> emprestimos;

	// GETTERS
	public long getId() {
		return id;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}

	public String getStatus() {
		return status;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	// SETTERS
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}
}