package com.mx.InmoICPE.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.InmoICPE.Entities.Roles;
import com.mx.InmoICPE.Entities.Users;
import com.mx.InmoICPE.Service.ImpUsers;


@RestController
@RequestMapping(path = "api/users")
@CrossOrigin("*")
public class ApiUsers {
	@Autowired ImpUsers imp;
	
	//URL: http://localhost:9005/api/roles
	@GetMapping
	public ResponseEntity<?> read(){
		return new ResponseEntity<>(imp.listar(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Users> create(@RequestBody Users body){
		body = (Users) imp.guardar(body);
		if (body != null) {
			return ResponseEntity.status(HttpStatus.OK).body(body);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping(produces = "application/json", path = "/{id}")
	public ResponseEntity<Users> update(@PathVariable Integer id, @RequestBody Users body){
		Users found = (Users) imp.buscar(id);
		if (found != null) {
			if (body.getName()!=null && !body.getName().isBlank()) {
				found.setName(body.getName());
			}
			if (body.getLastName()!=null && !body.getLastName().isBlank()) {
				found.setLastName(body.getLastName());
			}
			if (body.getEmail()!=null && !body.getEmail().isBlank()) {
				found.setEmail(body.getEmail());
			}
			if (body.getPass()!=null && !body.getPass().isBlank()) {
				found.setPass(body.getPass());
			}
			if (body.getPhone()!=null && !body.getPhone().isBlank()) {
				found.setPhone(body.getPhone());
			}
			if (body.getRole() != null && body.getRole().getId() != 0) {
				Roles newRole = new Roles();
				newRole.setId(body.getRole().getId());
				found.setRole(newRole);
			}
			if (imp.editar(found)) {
				return ResponseEntity.ok(found);
			}
		}else {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> delete(@PathVariable int id){
		Users found = (Users) imp.buscar(id);
		if (found != null) {
			imp.eliminar(found.getId());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
