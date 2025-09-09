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

import com.mx.InmoICPE.Entities.Permissions;
import com.mx.InmoICPE.Service.ImpPermissions;



@RestController
@RequestMapping(path = "api/permissions")
@CrossOrigin("*")
public class ApiPermissions {
	@Autowired ImpPermissions imp;
	
	//URL: http://localhost:9005/api/zones
	@GetMapping
	public ResponseEntity<?> read(){
		return new ResponseEntity<>(imp.listar(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Permissions> create(@RequestBody Permissions body){
		body = (Permissions) imp.guardar(body);
		if (body != null) {
			return ResponseEntity.status(HttpStatus.OK).body(body);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping(produces = "application/json", path = "/{id}")
	public ResponseEntity<Permissions> update(@PathVariable Integer id, @RequestBody Permissions body){
		Permissions found = (Permissions) imp.buscar(id);
		if (found != null) {
			if (!body.getName().isBlank()) {
				found.setName(body.getName());
			}
			if (!body.getDescription().isBlank()) {
				found.setDescription(body.getDescription());
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
		Permissions found = (Permissions) imp.buscar(id);
		if (found != null) {
			imp.eliminar(found.getId());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
