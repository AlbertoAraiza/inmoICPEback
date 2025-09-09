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
import com.mx.InmoICPE.Service.ImpRoles;

@RestController
@RequestMapping(path = "api/roles")
@CrossOrigin("*")
public class ApiRoles {
	@Autowired ImpRoles imp;
	
	//URL: http://localhost:9005/api/roles
	@GetMapping
	public ResponseEntity<?> read(){
		return new ResponseEntity<>(imp.listar(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Roles> create(@RequestBody Roles zone){
		zone = (Roles) imp.guardar(zone);
		if (zone != null) {
			return ResponseEntity.status(HttpStatus.OK).body(zone);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping(produces = "application/json", path = "/{id}")
	public ResponseEntity<Roles> update(@PathVariable Integer id, @RequestBody Roles body){
		Roles zone = (Roles) imp.buscar(id);
		if (zone != null) {
			if (!body.getName().isBlank()) {
				zone.setName(body.getName());
			}
			if (imp.editar(zone)) {
				return ResponseEntity.ok(zone);
			}
		}else {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> delete(@PathVariable int id){
		Roles zone = (Roles) imp.buscar(id);
		if (zone != null) {
			imp.eliminar(zone.getId());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
