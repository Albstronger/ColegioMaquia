package prereboot.entes;

import prereboot.mapa.Mapa;

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
