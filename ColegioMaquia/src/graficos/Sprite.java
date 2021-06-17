package graficos;

public final class Sprite {
	private final int lado;

	private int x;
	private int y;

	public int[] pixeles;
	private HojaSprites hoja;

	// colecci�n sprites
	public static final Sprite VOID = new Sprite(32, 0x0099ff);

	public static final Sprite STONE = new Sprite(32, 0, 0, HojaSprites.BLOQUES);
	public static final Sprite GRASS = new Sprite(32, 1, 0, HojaSprites.BLOQUES);
	public static final Sprite SAND = new Sprite(32, 2, 0, HojaSprites.BLOQUES);
	public static final Sprite WOOD = new Sprite(32, 3, 0, HojaSprites.BLOQUES);
	public static final Sprite CBSTONE = new Sprite(32, 4, 0, HojaSprites.BLOQUES);
	// fin colecci�n

	public Sprite(final int lado, final int color) {
		this.lado = lado;
		pixeles = new int[lado * lado];

		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = color;
		}
	}

	public Sprite(final int lado, final int columna, final int fila, final HojaSprites hoja) {
		this.lado = lado;

		pixeles = new int[lado * lado];

		this.x = columna * lado;
		this.y = fila * lado;
		this.hoja = hoja;

		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixeles[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.obtenAncho()];
			}
		}
	}

	public int getLado() {
		return lado;
	}
}
