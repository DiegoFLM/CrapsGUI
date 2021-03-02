/*
 * Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Caso 1: Juego Craps.
 */
package craps;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Dado. Esta clase simula un dado y permite ver el valor de su cara visible.
 */
public class Dado {
	
	/** The cara visible. Valor (entre 1 y 6) obtenido por el usuario en ese dado. */
	private int caraVisible;

	/**
	 * Gets the cara visible. Determina el valor de la cara visible del dado.
	 *
	 * @return the cara visible. Es un valor entre 1 y 6.
	 */
	public int getCaraVisible() {
		Random aleatorio = new Random();
		caraVisible = aleatorio.nextInt(6) + 1;
		return caraVisible;
	}
}
