package com.example.demo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Emprestimo;
import com.example.demo.repository.EmprestimoRepository;
import com.example.demo.responses.Response;

// A anotação @RestController permite definir um controller com características API REST;
@RestController
public class EmprestimoController {
	// A anotação @Autowired delega ao Spring Boot a inicialização do objeto;
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    // A anotação @RequestMapping permite definir uma rota
    @RequestMapping(value = "/emprestimo", method = RequestMethod.GET)
    public String Get() {
        return "emprestimoRepository.findAll()";
    }
    
    // @PathVariable indica que o valor da variável virá de uma informação da rota;
    @RequestMapping(value = "/emprestimo/{id}", method = RequestMethod.GET)
    public ResponseEntity<Emprestimo> GetById(@PathVariable(value = "id") long id)
    {
    	// https://docs.oracle.com/javase/9/docs/api/java/util/Optional.html (desde v 1.8)
    	// findById espera um retorno do tipo Optional<emprestimo>
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);
        if(emprestimo.isPresent())
            return new ResponseEntity<Emprestimo>(emprestimo.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // @RequestBody indica que o valor do objeto virá do corpo da requisição e 
    //              consegue mapear os dados vindos em Json para os atributos da classe;
    @RequestMapping(value = "/emprestimo", method =  RequestMethod.POST)
    public ResponseEntity<Response<Emprestimo>> Post(@Valid @RequestBody Emprestimo emprestimo, BindingResult result)
    {
    	Response<Emprestimo> response = new Response<Emprestimo>();    	
    	if (result.hasErrors()) {
    		result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
    		return ResponseEntity.badRequest().body(response);
    	}
    	emprestimoRepository.save(emprestimo);
    	response.setData(emprestimo);
        return ResponseEntity.ok(response);
    }        

    @RequestMapping(value = "/emprestimo/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Response<Emprestimo>> Put(@PathVariable(value = "id") long id, @Valid @RequestBody 
    		                                         Emprestimo newemprestimo, BindingResult result)
    {
        Optional<Emprestimo> oldemprestimo = emprestimoRepository.findById(id);
    	Response<Emprestimo> response = new Response<Emprestimo>();    	
    	if (result.hasErrors()) {
    		result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
    		return ResponseEntity.badRequest().body(response);
    	}        	        
        if(oldemprestimo.isPresent()){
            Emprestimo emprestimo = oldemprestimo.get();
            emprestimo.setUsuario(newemprestimo.getUsuario());
            emprestimo.setLivro(newemprestimo.getLivro());
            emprestimo.setStatus(newemprestimo.getStatus());
            response.setData(emprestimo);
            emprestimoRepository.save(emprestimo);
            return ResponseEntity.ok(response);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }    
    
    @RequestMapping(value = "/emprestimo/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);
        if(emprestimo.isPresent()){
            emprestimoRepository.delete(emprestimo.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }    
    
}	