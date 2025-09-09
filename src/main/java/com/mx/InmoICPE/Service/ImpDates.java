package com.mx.InmoICPE.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.InmoICPE.Dao.DatesDao;
import com.mx.InmoICPE.Entities.Dates;

@Service
public class ImpDates implements Metodos{
	@Autowired DatesDao dao;

	public boolean eliminar(int id) {
		try {
			Dates found = dao.findById(id).orElse(null);
			if(found == null) {
				System.out.println("No se econtro la intencion");
			}else {
				dao.deleteById(id);
				return true;
			}
		}catch(Exception ex) {
			System.out.println("Error al eliminar: " + ex.getMessage());
		}
		return false;
	}

	public Dates buscar(int id) {
		try {
			return dao.findById(id).orElse(null);
		}catch(Exception ex) {
			System.out.println("Error al buscar: " + ex.getMessage());
		}
		return null;
	}

	public List<Object> listar() {
		List<Dates> lista = (List<Dates>) dao.findAll();
		return lista.stream().collect(Collectors.toList());
	}

	@Override
	public boolean guardar(Object obj) {
		Dates cast = (Dates) obj;
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
			Dates cast = (Dates) obj;
			Dates found = dao.findById(cast.getId()).orElse(null);
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
