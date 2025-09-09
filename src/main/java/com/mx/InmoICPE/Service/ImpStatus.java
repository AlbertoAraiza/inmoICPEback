package com.mx.InmoICPE.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.InmoICPE.Dao.StatusDao;
import com.mx.InmoICPE.Entities.Status;

@Service
public class ImpStatus implements Metodos{
	@Autowired StatusDao dao;

	public boolean eliminar(int id) {
		try {
			Status found = dao.findById(id).orElse(null);
			if(found == null) {
				System.out.println("No se econtro el status");
			}else {
				dao.deleteById(id);
				return true;
			}
		}catch(Exception ex) {
			System.out.println("Error al eliminar: " + ex.getMessage());
		}
		return false;
	}

	public Status buscar(int id) {
		try {
			return dao.findById(id).orElse(null);
		}catch(Exception ex) {
			System.out.println("Error al buscar: " + ex.getMessage());
		}
		return null;
	}

	public List<Object> listar() {
		List<Status> lista = (List<Status>) dao.findAll();
		return lista.stream().collect(Collectors.toList());
	}

	@Override
	public Object guardar(Object obj) {
		Status cast = (Status) obj;
		try {
			return dao.save(cast);
		}catch(Exception ex) {
			System.out.println("Error al guardar");
		}
		return null;
	}

	@Override
	public boolean editar(Object obj) {
		try {
			Status cast = (Status) obj;
			Status found = dao.findById(cast.getId()).orElse(null);
			if(found == null) {
				System.out.println("No se econtro el status");
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
