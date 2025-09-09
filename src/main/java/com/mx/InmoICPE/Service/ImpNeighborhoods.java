package com.mx.InmoICPE.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.InmoICPE.Dao.NeighborhoodsDao;
import com.mx.InmoICPE.Entities.Neighborhoods;

@Service
public class ImpNeighborhoods implements Metodos{
	@Autowired NeighborhoodsDao dao;

	public boolean eliminar(int id) {
		try {
			Neighborhoods neighborhood = dao.findById(id).orElse(null);
			if(neighborhood == null) {
				System.out.println("No se econtro el tipo");
			}else {
				dao.deleteById(id);
				return true;
			}
		}catch(Exception ex) {
			System.out.println("Error al eliminar: " + ex.getMessage());
		}
		return false;
	}

	public Neighborhoods buscar(int id) {
		try {
			return dao.findById(id).orElse(null);
		}catch(Exception ex) {
			System.out.println("Error al buscar: " + ex.getMessage());
		}
		return null;
	}

	public List<Object> listar() {
		List<Neighborhoods> lista = (List<Neighborhoods>) dao.findAll();
		return lista.stream().collect(Collectors.toList());
	}

	@Override
	public Object guardar(Object obj) {
		Neighborhoods neighborhood = (Neighborhoods) obj;
		try {
			return dao.save(neighborhood);
		}catch(Exception ex) {
			System.out.println("Error al guardar");
		}
		return null;
	}

	@Override
	public boolean editar(Object obj) {
		try {
			Neighborhoods neighborhood = (Neighborhoods) obj;
			Neighborhoods found = dao.findById(neighborhood.getId()).orElse(null);
			if(found == null) {
				System.out.println("No se econtro el tipo");
			}else {
				dao.save(neighborhood);
				return true;
			}
		}catch(Exception ex) {
			System.out.println("Error al editar: " + ex.getMessage());
		}
		return false;
	}

}
