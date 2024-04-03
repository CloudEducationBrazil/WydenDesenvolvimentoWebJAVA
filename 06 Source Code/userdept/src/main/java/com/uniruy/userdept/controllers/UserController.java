package com.uniruy.userdept.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniruy.userdept.entities.User;
import com.uniruy.userdept.repositories.UserRepository;
import com.uniruy.userdept.util.HTTPErrorHandling;

@RestController
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired // Componente
	private UserRepository repository; // injeção de dependencia
	
	@GetMapping()
	public List<User> findAllUser(){
		List<User> result = repository.findAll();
		return result;
	}

	@GetMapping(value="/{id}")
	public User findById(@PathVariable Long id){
		try {
			User result = repository.findById(id).get();
			return result;
		} catch (Exception e) {
			System.out.println("Usuário não encontrado!");
			return null;
		}
	}

	@PostMapping()
	public User insertUser(@RequestBody User user){	
		User result = repository.save(user);
		return result;
	}
	
	@DeleteMapping(value="/{id}")
	public void deleteById(@PathVariable Long id){
		repository.deleteById(id);
	}

	@PutMapping(value="/{id}")
	public User updateUser(@RequestBody User user, @PathVariable Long id){
		
		HTTPErrorHandling retURL = new HTTPErrorHandling("http://localhost:8080/users/1"); 
		
		String resposta = retURL.retornoURL();
		
		System.out.println(resposta);
		
		User result  = repository.findById(id).get();
		System.out.println("antes: " + result.toString());
		result = user;
		System.out.println("depois: " + result.toString());
		repository.save(result);
		return result;
	}
}