package com.mx.InmoICPE.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.InmoICPE.Dao.IntentionsDao;
import com.mx.InmoICPE.Entities.Intentions;

@Service
public class ImpIntentions implements Metodos{
	@Autowired IntentionsDao dao;

	public boolean eliminar(int id) {
		try {
			Intentions found = dao.findById(id).orElse(null);
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

	public Intentions buscar(int id) {
		try {
			return dao.findById(id).orElse(null);
		}catch(Exception ex) {
			System.out.println("Error al buscar: " + ex.getMessage());
		}
		return null;
	}

	public List<Object> listar() {
		List<Intentions> lista = (List<Intentions>) dao.findAll();
		return lista.stream().collect(Collectors.toList());
	}

	@Override
	public Object guardar(Object obj) {
		Intentions cast = (Intentions) obj;
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
			Intentions cast = (Intentions) obj;
			Intentions found = dao.findById(cast.getId()).orElse(null);
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
