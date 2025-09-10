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

import com.mx.InmoICPE.Entities.Dates;
import com.mx.InmoICPE.Entities.RealEstates;
import com.mx.InmoICPE.Entities.Results;
import com.mx.InmoICPE.Entities.Users;
import com.mx.InmoICPE.Service.ImpDates;

@RestController
@RequestMapping(path = "api/dates")
@CrossOrigin("*")
public class ApiDates {
	@Autowired ImpDates imp;
	
	//URL: http://localhost:9005/api/real_estates
	@GetMapping
	public ResponseEntity<?> read(){
		return new ResponseEntity<>(imp.listar(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Dates> create(@RequestBody Dates body){
		body = (Dates) imp.guardar(body);
		if (body != null) {
			return ResponseEntity.status(HttpStatus.OK).body(body);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping(produces = "application/json", path = "/{id}")
	public ResponseEntity<Dates> update(@PathVariable Integer id, @RequestBody Dates body){
		Dates found = (Dates) imp.buscar(id);
		if (found != null) {
			if (body.getAppointmentDateHour()!=null) {
				found.setAppointmentDateHour(body.getAppointmentDateHour());
			}
			if (body.getRealEstate() != null && body.getRealEstate().getId() != 0) {
				RealEstates newRealEstate = new RealEstates();
				newRealEstate.setId(body.getRealEstate().getId());
				found.setRealEstate(newRealEstate);
			}
			if (body.getAsesor() != null && body.getAsesor().getId() != 0) {
				Users newAsessor = new Users();
				newAsessor.setId(body.getAsesor().getId());
				found.setAsesor(newAsessor);
			}
			if (body.getResult() != null && body.getResult().getId() != 0) {
				Results newResult = new Results();
				newResult.setId(body.getId());
				found.setResult(newResult);
			}
			if (body.getCustomer() != null && body.getCustomer().getId() != 0) {
				Users newCustomer = new Users();
				newCustomer.setId(body.getCustomer().getId());
				found.setCustomer(newCustomer);
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
		Dates found = (Dates) imp.buscar(id);
		if (found != null) {
			imp.eliminar(found.getId());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
