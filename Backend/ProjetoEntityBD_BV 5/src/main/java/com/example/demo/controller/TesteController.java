package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Teste;
import com.example.demo.repository.TesteRepository;
import com.example.demo.responses.Response;

@RestController
public class TesteController{
	
    @Autowired
	private TesteRepository testeRepository;
    
    @RequestMapping(value = "/teste", method = RequestMethod.GET)
    public List<Teste> Get() {
        return testeRepository.findAll();
    }
    
    @RequestMapping(value = "/teste", method =  RequestMethod.POST)
    public ResponseEntity<Response<Teste>> Post(@Valid @RequestBody Teste teste, BindingResult result)
    {
    	System.out.println("bla bla");

    	
    	
    	System.out.println(teste.toString());

    	Response<Teste> response = new Response<Teste>(); 
    	
    	System.out.println(response);
    	
    	if (result.hasErrors()) {
    		result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
    		return ResponseEntity.badRequest().body(response);
    	}
    	testeRepository.save(teste);
    	response.setData(teste);
        return ResponseEntity.ok(response);
    }
}