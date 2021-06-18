package mapa;

import java.util.Random;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;

public class MapaGenerado extends Mapa {

	private static final Random aleatorio = new Random();

	public MapaGenerado(int ancho, int alto) {
		super(ancho, alto);
	}

	protected void generarMapa() {
		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {
				cuadros[x + y * ancho] = aleatorio.nextInt(5);
			}
		}
	}

	public void mostrar(final int compensacionX, final int compensacionY, final Pantalla pantalla) {

		pantalla.setDiferencia(compensacionX, compensacionY);

		int o = compensacionX >> 5;
		int e = (compensacionX + pantalla.getAncho() + Cuadro.LADO) >> 5;
		int n = compensacionY >> 5;
		int s = (compensacionY + pantalla.getAlto() + Cuadro.LADO) >> 5;
		for (int y = n; y < s; y++) {
			for (int x = o; x < e; x++) {
				getCuadro(x, y).mostrar(x, y, pantalla);
			}
		}
	}

}
