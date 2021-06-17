package entes;

import mapa.Mapa;

public abstract class Ente {
	protected int x;
	protected int y;

	private boolean eliminado = false;

	protected Mapa mapa;

	public void actualizar() {

	}

	public void mostrar() {

	}

	public void eliminar() {
		eliminado = true;
	}

	public boolean estaEliminado() {
		return eliminado;
	}
}
