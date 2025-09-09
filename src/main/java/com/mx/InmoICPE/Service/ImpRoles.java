package com.mx.InmoICPE.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.InmoICPE.Dao.RolesDao;
import com.mx.InmoICPE.Entities.Roles;

@Service
public class ImpRoles implements Metodos{
	@Autowired RolesDao dao;

	public boolean eliminar(int id) {
		try {
			Roles found = dao.findById(id).orElse(null);
			if(found == null) {
				System.out.println("No se econtro el rol");
			}else {
				dao.deleteById(id);
				return true;
			}
		}catch(Exception ex) {
			System.out.println("Error al eliminar: " + ex.getMessage());
		}
		return false;
	}

	public Roles buscar(int id) {
		try {
			return dao.findById(id).orElse(null);
		}catch(Exception ex) {
			System.out.println("Error al buscar: " + ex.getMessage());
		}
		return null;
	}

	public List<Object> listar() {
		List<Roles> lista = (List<Roles>) dao.findAll();
		return lista.stream().collect(Collectors.toList());
	}

	@Override
	public Object guardar(Object obj) {
		Roles cast = (Roles) obj;
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
			Roles cast = (Roles) obj;
			Roles found = dao.findById(cast.getId()).orElse(null);
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
