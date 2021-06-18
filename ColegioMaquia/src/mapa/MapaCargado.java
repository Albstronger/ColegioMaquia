package mapa;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;

public class MapaCargado extends Mapa {

	private int pixeles[];

	public MapaCargado(final String ruta) {
		super(ruta);
	}

	protected void cargarMapa(String ruta) {
		try {
			BufferedImage imagen = ImageIO.read(MapaCargado.class.getResource(ruta));

			ancho = imagen.getWidth();
			alto = imagen.getHeight();

			cuadrosCatalogo = new Cuadro[ancho * alto];
			pixeles = new int[ancho * alto];

			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void generarMapa() {
		for (int i = 0; i < pixeles.length; i++) {
			switch (pixeles[i]) {
			case 0xff8a8a8a:
				cuadrosCatalogo[i] = Cuadro.STONE;
				continue;
			case 0xff35a830:
				cuadrosCatalogo[i] = Cuadro.GRASS;
				continue;
			case 0xffe5d7a4:
				cuadrosCatalogo[i] = Cuadro.SAND;
				continue;
			case 0xff7e5a2f:
				cuadrosCatalogo[i] = Cuadro.WOOD;
				continue;
			case 0xffd9c08c:
				cuadrosCatalogo[i] = Cuadro.CBSTONE;
				continue;
			default:
				cuadrosCatalogo[i] = Cuadro.VOID;
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
				if (x < 0 || y < 0 || x >= ancho || y >= alto) {
					Cuadro.VOID.mostrar(x, y, pantalla);
				} else {
					cuadrosCatalogo[x + y * ancho].mostrar(x, y, pantalla);
				}
			}
		}
	}
}
