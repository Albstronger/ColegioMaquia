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

	public int getX() {
		return x;
	}

	public void modificarX(final int desplazamientoX) {
		x += desplazamientoX;
	}

	public int getY() {
		return y;
	}

	public void modificarY(final int desplazamientoY) {
		y += desplazamientoY;
	}

	public boolean estaEliminado() {
		return eliminado;
	}
}
