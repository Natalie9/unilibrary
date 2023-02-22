package com.example.demo.entity;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

@Entity
public class Livro
{
	// PARÂMETROS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @NotEmpty(message="Nome do livro precisa ser preenchido")
    @Length(min=1, max=100, message="O nome do livro precisa ter menos de 100 letras")    
    private String nome;
    
    @Column(nullable = false, unique = true) // UNICO, USADO NO MÉTODO DE PESQUISA OU PARA PREENCHER
    @Digits(integer=13, fraction=0, message="Código isbn precisa ser preenchido com 13 dígitos")
    private BigInteger isbn;
    
    @Column(nullable = false)
    @NotEmpty(message="Nome do autor precisa ser preenchido")
    private String autor;
    
    @Length(min=4, max=4, message="O ano de publicação precisa ser válido")
    @NotEmpty(message="Ano de publicação precisa ser preenchido")
    private String ano_publicacao;
    
    @Column(nullable = false)
    private int edicao;
    
    @Length(min=1, max=20, message="Descrição do gênero passou do espaço de letras permitido")
    private String genero;
    
    @Column(nullable = false) // QUANTIDADE DE LIVROS DISPONIVEIS NA BIBLIOTECA
    private int qntd_acervo;
    
    @OneToMany(mappedBy = "livro") // CHAVE ESTRANGEIRA
    private List<Emprestimo> emprestimos;
    
    // GETTERS
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public BigInteger getIsbn() {
        return isbn;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public String getAno_publicacao() {
        return ano_publicacao;
    }
    
    public int getEdicao() {
        return edicao;
    }
    
    public String getGenero() {
        return genero;
    }
    
    public int getQntd_Acervo() {
        return qntd_acervo;
    }
    
    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
    
    // SETTERS
    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setIsbn(BigInteger isbn) {
        this.isbn = isbn;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public void setAno_publicacao(String ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }
    
    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public void setQntd_Acervo(int qntd_acervo) {
        this.qntd_acervo = qntd_acervo;
    }
    
    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }
    
}