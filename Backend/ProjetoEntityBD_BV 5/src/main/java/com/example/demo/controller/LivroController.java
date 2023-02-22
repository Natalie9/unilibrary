package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Livro;
import com.example.demo.repository.LivroRepository;
import com.example.demo.responses.Response;


@CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)


// A anotação @RestController permite definir um controller com características API REST;
@RestController
public class LivroController {
	// A anotação @Autowired delega ao Spring Boot a inicialização do objeto;
    @Autowired
    private LivroRepository livroRepository;
    // A anotação @RequestMapping permite definir uma rota
    @RequestMapping(value = "/livro", method = RequestMethod.GET)
    public List<Livro> Get() {
        return livroRepository.findAll();
    }
    
    // @PathVariable indica que o valor da variável virá de uma informação da rota;
    @RequestMapping(value = "/livro/{id}", method = RequestMethod.GET)
    public ResponseEntity<Livro> GetById(@PathVariable(value = "id") long id)
    {
    	// https://docs.oracle.com/javase/9/docs/api/java/util/Optional.html (desde v 1.8)
    	// findById espera um retorno do tipo Optional<livro>
        Optional<Livro> livro = livroRepository.findById(id);
        if(livro.isPresent())
            return new ResponseEntity<Livro>(livro.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // @RequestBody indica que o valor do objeto virá do corpo da requisição e 
    //              consegue mapear os dados vindos em Json para os atributos da classe;
    @CrossOrigin
    @RequestMapping(value = "/livro", method =  RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Livro>> Post(@Valid @RequestBody Livro livro, BindingResult result)
    {
    	System.out.println(livro);
    	System.out.println(result);
    	Response<Livro> response = new Response<Livro>();    
    	System.out.println(response);

    	if (result.hasErrors()) {
    		result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
    		return ResponseEntity.badRequest().body(response);
    	}
    	livroRepository.save(livro);
    	response.setData(livro);
        return ResponseEntity.ok(response);
    }        

    @RequestMapping(value = "/livro/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Response<Livro>> Put(@PathVariable(value = "id") long id, @Valid @RequestBody 
    											Livro newlivro, BindingResult result)
    {
        Optional<Livro> oldlivro = livroRepository.findById(id);
    	Response<Livro> response = new Response<Livro>();    	
    	if (result.hasErrors()) {
    		result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
    		return ResponseEntity.badRequest().body(response);
    	}        	        
        if(oldlivro.isPresent()){
            Livro livro = oldlivro.get();
            livro.setNome(newlivro.getNome());
            livro.setIsbn(newlivro.getIsbn());
            livro.setAutor(newlivro.getAutor());
            livro.setAno_publicacao(newlivro.getAno_publicacao());
            livro.setEdicao(newlivro.getEdicao());
            livro.setGenero(newlivro.getGenero());
            livro.setQntd_Acervo(newlivro.getQntd_Acervo());
            livro.setEmprestimos(newlivro.getEmprestimos());
            response.setData(livro);
            livroRepository.save(livro);
            return ResponseEntity.ok(response);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }    
    
    @RequestMapping(value = "/livro/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Livro> livro = livroRepository.findById(id);
        if(livro.isPresent()){
            livroRepository.delete(livro.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }    
    
}	