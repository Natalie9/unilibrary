package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.responses.Response;

@CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)


// A anotação @RestController permite definir um controller com características API REST;
@RestController
public class UsuarioController {
	// A anotação @Autowired delega ao Spring Boot a inicialização do objeto;
	@Autowired
	private UsuarioRepository usuarioRepository;

	// A anotação @RequestMapping permite definir uma rota
	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	public List<Usuario> Get() {
		return usuarioRepository.findAll();
	}

	// @PathVariable indica que o valor da variável virá de uma informação da rota;
	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> GetById(@PathVariable(value = "id") long id) {
		// https://docs.oracle.com/javase/9/docs/api/java/util/Optional.html (desde v
		// 1.8)
		// findById espera um retorno do tipo Optional<usuario>
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent())
			return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// @RequestBody indica que o valor do objeto virá do corpo da requisição e
	// consegue mapear os dados vindos em Json para os atributos da classe;
	@RequestMapping(value = "/usuario", method = RequestMethod.POST)
	public ResponseEntity<Response<Usuario>> Post(@Valid @RequestBody Usuario usuario, BindingResult result) {
		Response<Usuario> response = new Response<Usuario>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		usuarioRepository.save(usuario);
		response.setData(usuario);
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Response<Usuario>> Put(@PathVariable(value = "id") long id,
			@Valid @RequestBody Usuario newusuario, BindingResult result) {
		Optional<Usuario> oldusuario = usuarioRepository.findById(id);
		Response<Usuario> response = new Response<Usuario>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		if (oldusuario.isPresent()) {
			Usuario usuario = oldusuario.get();
			usuario.setMatricula(newusuario.getMatricula());
			usuario.setNome(newusuario.getNome());
			usuario.setStatus(newusuario.getStatus());
			usuario.setEmail(newusuario.getEmail());
			usuario.setTelefone(newusuario.getTelefone());
			response.setData(usuario);
			usuarioRepository.save(usuario);
			return ResponseEntity.ok(response);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			usuarioRepository.delete(usuario.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}