package prereboot.entes.criaturas;

import prereboot.entes.Ente;
import prereboot.graficos.Sprite;

public abstract class Criatura extends Ente {
	protected Sprite sprite;
	protected char direccion = 'n';
	protected boolean enMovimiento = false;

	public void actualizar() {

	}

	public void mostrar() {

	}

	public void mover(int desplazamientoX, int desplazamientoY) {
		if (desplazamientoX > 0) {
			direccion = 'e';
		}

		if (desplazamientoX < 0) {
			direccion = 'o';
		}

		if (desplazamientoY > 0) {
			direccion = 's';
		}

		if (desplazamientoY < 0) {
			direccion = 'n';
		}

		if (!estaEliminado()) {
			if (!enColision(desplazamientoX, 0)) {
				modificarX(desplazamientoX);
			}
			if (!enColision(0, desplazamientoY)) {
				modificarY(desplazamientoY);
			}
		}
	}

	private boolean enColision(int desplazamientoX, int desplazamientoY) {

		boolean colision = false;

		int posicionX = x + desplazamientoX;
		int posicionY = y + desplazamientoY;

		int margenIzquierdo = -6;
		int margenDerecho = 18;

		int margenSuperior = -4;
		int margenInferior = 31;

		int bordeIzquierdo = (posicionX + margenDerecho) / sprite.getLado();
		int bordeDerecho = (posicionX + margenDerecho + margenIzquierdo) / sprite.getLado();
		int bordeSuperior = (posicionY + margenInferior) / sprite.getLado();
		int bordeInferior = (posicionY + margenInferior + margenSuperior ) / sprite.getLado();

		if (mapa.getCatalogo(bordeIzquierdo + bordeSuperior * mapa.getAncho()).isSolido()) {
			colision = true;
		}
		if (mapa.getCatalogo(bordeIzquierdo + bordeInferior * mapa.getAncho()).isSolido()) {
			colision = true;
		}
		if (mapa.getCatalogo(bordeDerecho + bordeSuperior * mapa.getAncho()).isSolido()) {
			colision = true;
		}
		if (mapa.getCatalogo(bordeDerecho + bordeInferior * mapa.getAncho()).isSolido()) {
			colision = true;
		}

		return colision;
	}

	public Sprite getSprite() {
		return sprite;
	}
}
