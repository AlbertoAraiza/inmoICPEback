package com.mx.InmoICPE.Service;

import java.util.List;

public interface Metodos {
	public Object guardar(Object obj);
	public boolean editar(Object obj);
	public boolean eliminar(int id);
	public Object buscar(int id);
	public List<Object> listar();
}
