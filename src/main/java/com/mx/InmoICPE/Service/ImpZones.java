package com.mx.InmoICPE.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.InmoICPE.Dao.ZonesDao;
import com.mx.InmoICPE.Entities.Zones;

@Service
public class ImpZones implements Metodos{

	@Autowired ZonesDao dao;
	@Override
	public Object guardar(Object obj) {
		try {
			return dao.save((Zones)obj);
		}catch(Exception ex) {
			System.out.println("Error al guardar: " + ex.getMessage());
		}
		return null;
	}

	@Override
	public boolean editar(Object obj) {
		Zones zone = (Zones) obj;
		try {
			var existing = dao.findById(zone.getId()).orElse(null);
			if (existing == null) {
				System.out.println("No existe la zona");
			}else {
				dao.save(zone);
				return true;
			}
		}catch(Exception ex) {
			System.out.println("Error al editar: " + ex.getMessage());
		}
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		try {
			var existing = dao.findById(id).orElse(null);
			if (existing == null) {
				System.out.println("No existe la zona");
			}else {
				dao.deleteById(id);;
				return true;
			}
		}catch(Exception ex) {
			System.out.println("Error al eliminar: " + ex.getMessage());
		}
		return false;
	}

	@Override
	public Object buscar(int id) {
		try {
			var existing = dao.findById(id).orElse(null);
			if (existing == null) {
				System.out.println("No existe la zona");
			}else {
				return existing;
			}
		}catch(Exception ex) {
			System.out.println("Error al eliminar: " + ex.getMessage());
		}
		return null;
	}

	@Override
	public List<Object> listar() {
		try {
			List<Zones> zones = (List<Zones>) dao.findAll();
			return zones.stream().collect(Collectors.toList());
		}catch(Exception ex) {
			System.out.println("Error al listar: " + ex.getMessage());
		}
		return new ArrayList<Object>();
	}

}
