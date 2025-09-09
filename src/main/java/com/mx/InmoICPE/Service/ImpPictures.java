package com.mx.InmoICPE.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.InmoICPE.Dao.VideosDao;
import com.mx.InmoICPE.Entities.Videos;

@Service
public class ImpPictures implements Metodos{
	@Autowired VideosDao dao;

	public boolean eliminar(int id) {
		try {
			Videos found = dao.findById(id).orElse(null);
			if(found == null) {
				System.out.println("No se econtro el resultado");
			}else {
				dao.deleteById(id);
				return true;
			}
		}catch(Exception ex) {
			System.out.println("Error al eliminar: " + ex.getMessage());
		}
		return false;
	}

	public Videos buscar(int id) {
		try {
			return dao.findById(id).orElse(null);
		}catch(Exception ex) {
			System.out.println("Error al buscar: " + ex.getMessage());
		}
		return null;
	}

	public List<Object> listar() {
		List<Videos> lista = (List<Videos>) dao.findAll();
		return lista.stream().collect(Collectors.toList());
	}

	@Override
	public boolean guardar(Object obj) {
		Videos cast = (Videos) obj;
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
			Videos cast = (Videos) obj;
			Videos found = dao.findById(cast.getId()).orElse(null);
			if(found == null) {
				System.out.println("No se econtro el resultado");
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
