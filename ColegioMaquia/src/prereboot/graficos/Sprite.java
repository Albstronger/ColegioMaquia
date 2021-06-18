package prereboot.graficos;

public final class Sprite {
	private final int lado;

	private int x;
	private int y;

	public int[] pixeles;
	private HojaSprites hoja;

	// colección sprites prueba
	public static final Sprite VOID = new Sprite(32, 0x0099ff);

	public static final Sprite STONE = new Sprite(32, 0, 0, 0, HojaSprites.BLOQUES);
	public static final Sprite GRASS = new Sprite(32, 1, 0, 0, HojaSprites.BLOQUES);
	public static final Sprite SAND = new Sprite(32, 2, 0, 0, HojaSprites.BLOQUES);
	public static final Sprite WOOD = new Sprite(32, 3, 0, 0, HojaSprites.BLOQUES);
	public static final Sprite CBSTONE = new Sprite(32, 4, 0, 0, HojaSprites.BLOQUES);
	// fin colección

	// colección sprites personajes
	public static final Sprite ABAJO0 = new Sprite(32, 0, 0, 0, HojaSprites.JUGADOR);
	public static final Sprite ABAJO1 = new Sprite(32, 0, 1, 0, HojaSprites.JUGADOR);
	public static final Sprite ABAJO2 = new Sprite(32, 0, 2, 0, HojaSprites.JUGADOR);

	public static final Sprite ARRIBA0 = new Sprite(32, 1, 0, 0, HojaSprites.JUGADOR);
	public static final Sprite ARRIBA1 = new Sprite(32, 1, 1, 0, HojaSprites.JUGADOR);
	public static final Sprite ARRIBA2 = new Sprite(32, 1, 2, 0, HojaSprites.JUGADOR);

	public static final Sprite DERECHA0 = new Sprite(32, 2, 0, 0, HojaSprites.JUGADOR);
	public static final Sprite DERECHA1 = new Sprite(32, 2, 1, 0, HojaSprites.JUGADOR);
	public static final Sprite DERECHA2 = new Sprite(32, 2, 2, 0, HojaSprites.JUGADOR);

	public static final Sprite IZQUIERDA0 = new Sprite(32, 3, 0, 0, HojaSprites.JUGADOR);
	public static final Sprite IZQUIERDA1 = new Sprite(32, 3, 1, 0, HojaSprites.JUGADOR);
	public static final Sprite IZQUIERDA2 = new Sprite(32, 3, 2, 0, HojaSprites.JUGADOR);
	// fin coleccion

	public Sprite(final int lado, final int color) {
		this.lado = lado;
		pixeles = new int[lado * lado];

		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = color;
		}
	}

	public Sprite(final int lado, final int columna, final int fila, final int version, final HojaSprites hoja) {
		this.lado = lado;

		pixeles = new int[lado * lado];

		this.x = columna * lado;
		this.y = fila * lado;
		this.hoja = hoja;

		cargarSprite(version);
	}

	public int getLado() {
		return lado;
	}

	private void cargarSprite(final int version) {
		if (version == 0) {
			cargaNormal();
		} else {
			cargaManipulada(version);
		}
	}

	private void cargaNormal() {
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixeles[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.obtenAncho()];
			}
		}
	}

	private void cargaManipulada(int version) {
		int[] pixelesTemporales = iniciarPixelesTemporales();

		switch (version) {
		case 1:
			invertirX(pixelesTemporales);
			break;
		case 2:
			invertirY(pixelesTemporales);
			break;
		case 3:
			invertirXY(pixelesTemporales);
			break;
		case 4:
			rotar90I(pixelesTemporales);
			break;
		case 5:
			rotar90D(pixelesTemporales);
			break;
		case 6:
			rotar90IYI(pixelesTemporales);
			break;
		case 7:
			rotar90DYI(pixelesTemporales);
			break;
		default:
			cargaNormal();
		}

	}

	private int[] iniciarPixelesTemporales() {
		int[] pixelesTemporales = new int[lado * lado];
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixelesTemporales[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.obtenAncho()];
			}
		}
		return pixelesTemporales;
	}

	// 1
	private void invertirX(int[] pixelesTemporales) {
		int i = 0;
		for (int y = 0; y < lado; y++) {
			for (int x = lado - 1; x > -1; x--) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// 2
	private void invertirY(int[] pixelesTemporales) {
		int i = 0;
		for (int y = lado - 1; y > -1; y--) {
			for (int x = 0; x < lado; x++) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// 3
	private void invertirXY(int[] pixelesTemporales) {
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = pixelesTemporales[pixelesTemporales.length - 1 - i];
		}
	}

	// 4
	private void rotar90I(int[] pixelesTemporales) {
		int i = 0;
		for (int x = lado - 1; x > -1; x--) {
			for (int y = 0; y < lado; y++) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// 5
	private void rotar90D(int[] pixelesTemporales) {
		int i = 0;
		for (int x = 0; x < lado; x++) {
			for (int y = lado - 1; y > -1; y--) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// 6
	private void rotar90IYI(int[] pixelesTemporales) {
		int i = 0;
		for (int x = 0; x < lado; x++) {
			for (int y = 0; y < lado; y++) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// 7
	private void rotar90DYI(int[] pixelesTemporales) {
		int i = 0;
		for (int x = lado - 1; x > -1; x--) {
			for (int y = lado - 1; y > -1; y--) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}
}
