/*
 * Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Caso 1: Juego Craps.
 */

package craps;

import java.awt.EventQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class PrincipalCraps. Clase que contiene el método main e inicia el programa.
 */
public class PrincipalCraps {

	/**
	 * The main method. Método principal en Java.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/** VistaConsola consola = new VistaConsola();
		consola.iniciarJuego(); */
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//VistaGUICraps myWindow = new VistaGUICraps(); 	
				VistaGUIGridBagLayout myVista = new VistaGUIGridBagLayout();
			}
		});
		
	}
}
