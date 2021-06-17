package mapa;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;

public abstract class Mapa {
	protected int ancho;
	protected int alto;

	protected int[] cuadros;
	protected Cuadro[] cuadrosCatalogo;

	public Mapa(final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;

		cuadros = new int[ancho * alto];
		generarMapa();
	}

	public Mapa(String ruta) {
		cargarMapa(ruta);
		generarMapa();
	}

	protected void generarMapa() {

	}

	protected void cargarMapa(String ruta) {

	}

	public void actualizar() {

	}

	public void mostrar(final int compensacionX, final int compensacionY, final Pantalla pantalla) {

		pantalla.setDiferencia(compensacionX, compensacionY);

		int o = compensacionX >> 5;
		int e = (compensacionX + pantalla.getAncho() + Cuadro.LADO) >> 5;
		int n = compensacionY >> 5;
		int s = (compensacionY + pantalla.getAlto() + Cuadro.LADO) >> 5;
		for (int y = n; y < s; y++) {
			for (int x = o; x < e; x++) {
				//getCuadro(x, y).mostrar(x, y, pantalla);
				if(x < 0 || y < 0 || x >= ancho || y >= alto) {
					Cuadro.VOID.mostrar(x, y, pantalla);
				} else {
					cuadrosCatalogo[x + y * ancho].mostrar(x, y, pantalla);
				}
			}
		}
	}

	public Cuadro getCuadro(final int x, final int y) {
		if (x < 0 || y < 0 || x >= ancho || y >= alto) {
			return Cuadro.VOID;
		}
		switch (cuadros[x + y * ancho]) {
		case 0:
			return Cuadro.STONE;
		case 1:
			return Cuadro.GRASS;
		case 2:
			return Cuadro.SAND;
		case 3:
			return Cuadro.WOOD;
		case 4:
			return Cuadro.CBSTONE;
		default:
			return Cuadro.VOID;
		}
	}
}
