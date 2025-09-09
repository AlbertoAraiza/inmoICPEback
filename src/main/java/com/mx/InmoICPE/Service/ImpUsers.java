package com.mx.InmoICPE.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.InmoICPE.Dao.UsersDao;
import com.mx.InmoICPE.Entities.Users;

@Service
public class ImpUsers implements Metodos{
	@Autowired UsersDao dao;

	public boolean eliminar(int id) {
		try {
			Users found = dao.findById(id).orElse(null);
			if(found == null) {
				System.out.println("No se econtro el usuario");
			}else {
				dao.deleteById(id);
				return true;
			}
		}catch(Exception ex) {
			System.out.println("Error al eliminar: " + ex.getMessage());
		}
		return false;
	}

	public Users buscar(int id) {
		try {
			return dao.findById(id).orElse(null);
		}catch(Exception ex) {
			System.out.println("Error al buscar: " + ex.getMessage());
		}
		return null;
	}

	public List<Object> listar() {
		List<Users> lista = (List<Users>) dao.findAll();
		return lista.stream().collect(Collectors.toList());
	}

	@Override
	public Object guardar(Object obj) {
		Users cast = (Users) obj;
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
			Users cast = (Users) obj;
			Users found = dao.findById(cast.getId()).orElse(null);
			if(found == null) {
				System.out.println("No se econtro el rol");
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
