package com.mx.InmoICPE.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.InmoICPE.Dao.PicturesDao;
import com.mx.InmoICPE.Entities.Pictures;


@Service
public class ImpPictures implements Metodos{
	@Autowired PicturesDao dao;

	public boolean eliminar(int id) {
		try {
			Pictures found = dao.findById(id).orElse(null);
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

	public Pictures buscar(int id) {
		try {
			return dao.findById(id).orElse(null);
		}catch(Exception ex) {
			System.out.println("Error al buscar: " + ex.getMessage());
		}
		return null;
	}

	public List<Object> listar() {
		List<Pictures> lista = (List<Pictures>) dao.findAll();
		return lista.stream().collect(Collectors.toList());
	}

	@Override
	public Object guardar(Object obj) {
		Pictures cast = (Pictures) obj;
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
			Pictures cast = (Pictures) obj;
			Pictures found = dao.findById(cast.getId()).orElse(null);
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
