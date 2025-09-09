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

import com.mx.InmoICPE.Entities.Neighborhoods;
import com.mx.InmoICPE.Entities.Zones;
import com.mx.InmoICPE.Service.ImpNeighborhoods;



@RestController
@RequestMapping(path = "api/neighborhoods")
@CrossOrigin("*")
public class ApiNeighborhoods {
	@Autowired ImpNeighborhoods imp;
	
	//URL: http://localhost:9005/api/neighborhoods
	@GetMapping
	public ResponseEntity<?> read(){
		return new ResponseEntity<>(imp.listar(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Neighborhoods> create(@RequestBody Neighborhoods body){
		body = (Neighborhoods) imp.guardar(body);
		if (body != null) {
			return ResponseEntity.status(HttpStatus.OK).body(body);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping(produces = "application/json", path = "/{id}")
	public ResponseEntity<Neighborhoods> update(@PathVariable Integer id, @RequestBody Neighborhoods body){
		Neighborhoods found = (Neighborhoods) imp.buscar(id);
		if (found != null) {
			if (!body.getName().isBlank()) {
				found.setName(body.getName());
			}
			if (body.getZone() != null && body.getZone().getId() != 0) {
				Zones newZone = new Zones();
				newZone.setId(body.getZone().getId());
				found.setZone(newZone);
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
		Neighborhoods found = (Neighborhoods) imp.buscar(id);
		if (found != null) {
			imp.eliminar(found.getId());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
