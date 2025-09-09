package com.mx.InmoICPE.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.InmoICPE.Dao.RealEstatesDao;
import com.mx.InmoICPE.Entities.RealEstates;

@Service
public class ImpRealEstates implements Metodos{
	@Autowired RealEstatesDao dao;

	public boolean eliminar(int id) {
		try {
			RealEstates found = dao.findById(id).orElse(null);
			if(found == null) {
				System.out.println("No se econtro el inmueble");
			}else {
				dao.deleteById(id);
				return true;
			}
		}catch(Exception ex) {
			System.out.println("Error al eliminar: " + ex.getMessage());
		}
		return false;
	}

	public RealEstates buscar(int id) {
		try {
			return dao.findById(id).orElse(null);
		}catch(Exception ex) {
			System.out.println("Error al buscar: " + ex.getMessage());
		}
		return null;
	}

	public List<Object> listar() {
		List<RealEstates> lista = (List<RealEstates>) dao.findAll();
		return lista.stream().collect(Collectors.toList());
	}

	@Override
	public boolean guardar(Object obj) {
		RealEstates cast = (RealEstates) obj;
		try {
			dao.save(cast);
			return true;
		}catch(Exception ex) {
			System.out.println("Error al guardar");
		}
		return false;
	}

	@Override
	public boolean editar(Object obj) {
		try {
			RealEstates cast = (RealEstates) obj;
			RealEstates found = dao.findById(cast.getId()).orElse(null);
			if(found == null) {
				System.out.println("No se econtro el inmueble");
			}else {
				dao.save(cast);
				return true;
			}
		}catch(Exception ex) {
			System.out.println("Error al editar: " + ex.getMessage());
		}
		return false;
	}

}
