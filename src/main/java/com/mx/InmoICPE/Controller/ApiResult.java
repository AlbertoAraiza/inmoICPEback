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

import com.mx.InmoICPE.Entities.Results;
import com.mx.InmoICPE.Service.ImpResults;

@RestController
@RequestMapping(path = "api/results")
@CrossOrigin("*")
public class ApiResult {
	@Autowired ImpResults imp;
	
	//URL: http://localhost:9005/api/results
	@GetMapping
	public ResponseEntity<?> read(){
		return new ResponseEntity<>(imp.listar(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Results> create(@RequestBody Results body){
		body = (Results) imp.guardar(body);
		if (body != null) {
			return ResponseEntity.status(HttpStatus.OK).body(body);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping(produces = "application/json", path = "/{id}")
	public ResponseEntity<Results> update(@PathVariable Integer id, @RequestBody Results body){
		Results found = (Results) imp.buscar(id);
		if (found != null) {
			if (body.getName()!=null && !body.getName().isBlank()) {
				found.setName(body.getName());
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
		Results found = (Results) imp.buscar(id);
		if (found != null) {
			imp.eliminar(found.getId());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
