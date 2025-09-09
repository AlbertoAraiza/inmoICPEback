package com.mx.InmoICPE.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.InmoICPE.Dao.PermissionsDao;
import com.mx.InmoICPE.Entities.Permissions;

@Service
public class ImpPermissions implements Metodos{
	@Autowired PermissionsDao dao;

	public boolean eliminar(int id) {
		try {
			Permissions found = dao.findById(id).orElse(null);
			if(found == null) {
				System.out.println("No se econtro el permiso");
			}else {
				dao.deleteById(id);
				return true;
			}
		}catch(Exception ex) {
			System.out.println("Error al eliminar: " + ex.getMessage());
		}
		return false;
	}

	public Permissions buscar(int id) {
		try {
			return dao.findById(id).orElse(null);
		}catch(Exception ex) {
			System.out.println("Error al buscar: " + ex.getMessage());
		}
		return null;
	}

	public List<Object> listar() {
		List<Permissions> lista = (List<Permissions>) dao.findAll();
		return lista.stream().collect(Collectors.toList());
	}

	@Override
	public Object guardar(Object obj) {
		Permissions cast = (Permissions) obj;
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
			Permissions cast = (Permissions) obj;
			Permissions found = dao.findById(cast.getId()).orElse(null);
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
