package com.mx.InmoICPE.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.InmoICPE.Dao.TypesDao;
import com.mx.InmoICPE.Entities.Types;

@Service
public class ImpTypes implements Metodos{
	@Autowired TypesDao dao;

	public boolean eliminar(int id) {
		try {
			Types type = dao.findById(id).orElse(null);
			if(type == null) {
				System.out.println("No se econtro la colonia");
			}else {
				dao.deleteById(id);
				return true;
			}
		}catch(Exception ex) {
			System.out.println("Error al eliminar: " + ex.getMessage());
		}
		return false;
	}

	public Types buscar(int id) {
		try {
			return dao.findById(id).orElse(null);
		}catch(Exception ex) {
			System.out.println("Error al buscar: " + ex.getMessage());
		}
		return null;
	}

	public List<Object> listar() {
		List<Types> lista = (List<Types>) dao.findAll();
		return lista.stream().collect(Collectors.toList());
	}

	@Override
	public Object guardar(Object obj) {
		Types type = (Types) obj;
		try {
			return dao.save(type);
		}catch(Exception ex) {
			System.out.println("Error al guardar");
		}
		return null;
	}

	@Override
	public boolean editar(Object obj) {
		try {
			Types type = (Types) obj;
			Types found = dao.findById(type.getId()).orElse(null);
			if(found == null) {
				System.out.println("No se econtro la colonia");
			}else {
				dao.save(type);
				return true;
			}
		}catch(Exception ex) {
			System.out.println("Error al editar: " + ex.getMessage());
		}
		return false;
	}

}
