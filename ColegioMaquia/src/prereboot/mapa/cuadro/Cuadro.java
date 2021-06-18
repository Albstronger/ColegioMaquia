package prereboot.mapa.cuadro;

import prereboot.graficos.Pantalla;
import prereboot.graficos.Sprite;

public class Cuadro {
	public int x;
	public int y;

	public Sprite sprite;

	private boolean solido;

	public static final int LADO = 32;

	// colección cuadros
	public static final Cuadro STONE = new Cuadro(Sprite.STONE);
	public static final Cuadro GRASS = new Cuadro(Sprite.GRASS);
	public static final Cuadro SAND = new Cuadro(Sprite.SAND);
	public static final Cuadro WOOD = new Cuadro(Sprite.WOOD, true);
	public static final Cuadro CBSTONE = new Cuadro(Sprite.CBSTONE, true);

	public static final Cuadro VOID = new Cuadro(Sprite.VOID, true);
	// fin colección

	public Cuadro(Sprite sprite) {
		this.sprite = sprite;
		solido = false;
	}

	public Cuadro(Sprite sprite, boolean solido) {
		this.sprite = sprite;
		this.solido = solido;
	}

	public void mostrar(int x, int y, Pantalla pantalla) {
		pantalla.mostrarCuadro(x << 5, y << 5, this);
	}

	public boolean isSolido() {
		return solido;
	}
}
