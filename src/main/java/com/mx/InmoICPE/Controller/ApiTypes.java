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

import com.mx.InmoICPE.Entities.Types;
import com.mx.InmoICPE.Service.ImpTypes;



@RestController
@RequestMapping(path = "api/types")
@CrossOrigin("*")
public class ApiTypes {
	@Autowired ImpTypes imp;
	
	//URL: http://localhost:9005/api/types
	@GetMapping
	public ResponseEntity<?> read(){
		return new ResponseEntity<>(imp.listar(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Types> create(@RequestBody Types zone){
		zone = (Types) imp.guardar(zone);
		if (zone != null) {
			return ResponseEntity.status(HttpStatus.OK).body(zone);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping(produces = "application/json", path = "/{id}")
	public ResponseEntity<Types> update(@PathVariable Integer id, @RequestBody Types body){
		Types zone = (Types) imp.buscar(id);
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
		Types zone = (Types) imp.buscar(id);
		if (zone != null) {
			imp.eliminar(zone.getId());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
