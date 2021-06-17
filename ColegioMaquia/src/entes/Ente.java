package entes;

import mapa.Mapa;

public abstract class Ente {
	private int x;
	private int y;

	private boolean eliminado = false;

	protected Mapa mapa;

	public void actualizar() {

	}

	public void mostrar() {

	}

	public void eliminar() {
		eliminado = true;
	}

	public int posicionX() {
		return x;
	}

	public int posicionY() {
		return y;
	}

	public boolean estaEliminado() {
		return eliminado;
	}
}
