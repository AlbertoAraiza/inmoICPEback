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

import com.mx.InmoICPE.Entities.Intentions;
import com.mx.InmoICPE.Entities.Neighborhoods;
import com.mx.InmoICPE.Entities.RealEstates;
import com.mx.InmoICPE.Entities.Status;
import com.mx.InmoICPE.Entities.Types;
import com.mx.InmoICPE.Entities.Users;
import com.mx.InmoICPE.Service.ImpRealEstates;

@RestController
@RequestMapping(path = "api/real_estates")
@CrossOrigin("*")
public class ApiRealEstates {
	@Autowired ImpRealEstates imp;
	
	//URL: http://localhost:9005/api/real_estates
	@GetMapping
	public ResponseEntity<?> read(){
		return new ResponseEntity<>(imp.listar(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<RealEstates> create(@RequestBody RealEstates body){
		body = (RealEstates) imp.guardar(body);
		if (body != null) {
			return ResponseEntity.status(HttpStatus.OK).body(body);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping(produces = "application/json", path = "/{id}")
	public ResponseEntity<RealEstates> update(@PathVariable Integer id, @RequestBody RealEstates body){
		RealEstates found = (RealEstates) imp.buscar(id);
		if (found != null) {
			if (body.getAddress()!=null && !body.getAddress().isBlank()) {
				found.setAddress(body.getAddress());
			}
			if (body.getDetails() !=null && !body.getDetails().isBlank()) {
				found.setDetails(body.getDetails());
			}
			if (body.getBuiltLand() != 0) {
				found.setBuiltLand(body.getBuiltLand());
			}
			if (body.getLand() != 0) {
				found.setLand(body.getLand());
			}
			if (body.getPrice() != 0) {
				found.setPrice(body.getPrice());
			}
			if (body.getIsCredit() != 0) {
				found.setIsCredit(body.getIsCredit());
			}
			if (body.getFacebook() !=null && !body.getFacebook().isBlank()) {
				found.setFacebook(body.getFacebook());
			}
			if (body.getMaps() !=null && !body.getMaps().isBlank()) {
				found.setMaps(body.getMaps());
			}
			if (body.getNeighborhood() != null && body.getNeighborhood().getId() != 0) {
				Neighborhoods newNeighborhood = new Neighborhoods();
				newNeighborhood.setId(body.getNeighborhood().getId());
				found.setNeighborhood(newNeighborhood);
			}
			if (body.getStatus() != null && body.getStatus().getId() != 0) {
				Status newStatus = new Status();
				newStatus.setId(body.getStatus().getId());
				found.setStatus(newStatus);
			}
			if (body.getType() != null && body.getType().getId() != 0) {
				Types newType = new Types();
				newType.setId(body.getType().getId());
				found.setType(newType);
			}
			if(body.getStocker() != null && body.getStocker().getId() != 0) {
				Users newStocker = new Users();
				newStocker.setId(body.getStocker().getId());
				found.setStocker(newStocker);
			}
			if (body.getIntention() != null && body.getIntention().getId() != 0) {
				Intentions newIntention = new Intentions();
				newIntention.setId(body.getIntention().getId());
				found.setIntention(newIntention);
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
		RealEstates found = (RealEstates) imp.buscar(id);
		if (found != null) {
			imp.eliminar(found.getId());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
