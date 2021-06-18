package prereboot.entes.criaturas;

import prereboot.control.Teclado;
import prereboot.graficos.Pantalla;
import prereboot.graficos.Sprite;
import prereboot.mapa.Mapa;

public class Jugador extends Criatura {

	private Teclado teclado;

	private int animacion;

	public Jugador(final Teclado teclado, Sprite sprite, Mapa mapa) {
		this.teclado = teclado;
		this.sprite = sprite;
		this.mapa = mapa;
	}

	public Jugador(final Teclado teclado, Sprite sprite, Mapa mapa, int posicionX, int posicionY) {
		this(teclado, sprite, mapa);
		this.x = posicionX;
		this.y = posicionY;
	}

	public void actualizar() {
		int desplazamientoX = 0;
		int desplazamientoY = 0;

		int velocidadMovimiento = 1;

		if (animacion < 32767) {
			animacion++;
		} else {
			animacion = 0;
		}

		if (teclado.correr) {
			velocidadMovimiento = 2;
		}

		if (teclado.arriba) {
			desplazamientoY -= velocidadMovimiento;
		}
		if (teclado.abajo) {
			desplazamientoY += velocidadMovimiento;
		}
		if (teclado.izquierda) {
			desplazamientoX -= velocidadMovimiento;
		}
		if (teclado.derecha) {
			desplazamientoX += velocidadMovimiento;
		}

		if (desplazamientoX != 0 || desplazamientoY != 0) {
			mover(desplazamientoX, desplazamientoY);
			enMovimiento = true;
		} else {
			enMovimiento = false;
		}

		int resto = animacion % 40;

		if (direccion == 'n') {
			sprite = Sprite.ARRIBA0;
			if (enMovimiento) {
				if (resto > 10 && resto <= 20) {
					sprite = Sprite.ARRIBA1;
				} else if (resto > 20 && resto <= 30) {
					sprite = Sprite.ARRIBA0;
				} else if (resto > 30) {
					sprite = Sprite.ARRIBA2;
				} else {
					sprite = Sprite.ARRIBA0;
				}
			}
		}
		if (direccion == 's') {
			sprite = Sprite.ABAJO0;
			if (enMovimiento) {
				if (resto > 10 && resto <= 20) {
					sprite = Sprite.ABAJO1;
				} else if (resto > 20 && resto <= 30) {
					sprite = Sprite.ABAJO0;
				} else if (resto > 30) {
					sprite = Sprite.ABAJO2;
				} else {
					sprite = Sprite.ABAJO0;
				}
			}
		}
		if (direccion == 'o') {
			sprite = Sprite.IZQUIERDA0;
			if (enMovimiento) {
				if (resto > 10 && resto <= 20) {
					sprite = Sprite.IZQUIERDA1;
				} else if (resto > 20 && resto <= 30) {
					sprite = Sprite.IZQUIERDA0;
				} else if (resto > 30) {
					sprite = Sprite.IZQUIERDA2;
				} else {
					sprite = Sprite.IZQUIERDA0;
				}
			}
		}
		if (direccion == 'e') {
			sprite = Sprite.DERECHA0;
			if (enMovimiento) {
				if (resto > 10 && resto <= 20) {
					sprite = Sprite.DERECHA1;
				} else if (resto > 20 && resto <= 30) {
					sprite = Sprite.DERECHA0;
				} else if (resto > 30) {
					sprite = Sprite.DERECHA2;
				} else {
					sprite = Sprite.DERECHA0;
				}
			}
		}
	}

	public void mostrar(Pantalla pantalla) {
		pantalla.mostrarJugador(x, y, this);
	}
}
