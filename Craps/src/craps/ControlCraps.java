/*
 * Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Caso 1: Juego Craps.
 */
package craps;

// TODO: Auto-generated Javadoc
/**
 * The Class ControlCraps. Clase que maneja la lógica del juego. Determina el valor del tiro, el estado del juego,
 * el valor del punto, entre otros.
 */
public class ControlCraps {
	
	private Dado dado1, dado2;
	
	private int tiro, punto, estado;
	
	private boolean primerLanzamiento;
	
	private int[] carasDados;

	/**
	 * Instantiates a new control craps. Constructor de la clase que se encarga de crear los objetos a usar.
	 */
	public ControlCraps(){
		dado1 = new Dado();
		dado2 = new Dado();
		primerLanzamiento = true; //ronda de tiro.   			ERROR
		carasDados = new int[2];				
	}
	
	/**
	 * Calcular tiro. Simula el lanzamiento de los dados y establece el valor del tiro.
	 */
	public void calcularTiro() {
		carasDados[0] = dado1.getCaraVisible(); //carasDados[1] = dado1.getCaraVisible();
		carasDados[1] = dado2.getCaraVisible(); //carasDados[2] = dado2.getCaraVisible();
		tiro = carasDados[0] + carasDados[1];	//tiro = carasDados[1] + carasDados[2];
		
	}
	
	/**
	 * Determinar estado juego. Determina el estado del juego: estado = 1 ganar, estado = 2 perder, estado = 3 punto. 
	 */
	public void determinarEstadoJuego() {
		if (primerLanzamiento == true) {
			//ronda de tiro
			if (tiro == 7 || tiro == 11) {
				estado = 1; //ganar
			}else
			if (tiro == 2 || tiro == 3 || tiro == 12) {
				estado = 2; //perder
			}
			else {
				estado = 3; //Entra a ronda de punto 
				punto = tiro;
				primerLanzamiento = false;
			}
		}else {
			//Ha establecido punto
			rondaPunto();
		}
	}
	
	
	/**
	 * Ronda punto. Establece el estado del juego cuando se está en la ronda punto.
	 */
	private void rondaPunto() {
		if (tiro == punto) {
			estado = 1;//Ganar.
			primerLanzamiento = true;
		}
		if (tiro == 7) {
			estado = 2;//Perder.
			primerLanzamiento = true;
		}
	}

	
	/**
	 * Sets the abandono. Establece el estado del juego si el usuario abandona en la ronda de punto.
	 */
	public void setAbandono() {
		estado = 2;
		primerLanzamiento = true;
	}
	
	
	/**
	 * Gets the tiro. Devuelve el valor del tiro a partir de la suma de las caras visibles de los dados.
	 *
	 * @return the tiro
	 */
	public int getTiro() {
		return tiro;
	}

	/**
	 * Gets the punto. Devuelve el valor de punto.
	 *
	 * @return the punto
	 */
	public int getPunto() {
		return punto;
	}

	/**
	 * Gets the estado. Informa el estado del juego. 1 = ganar, 2 = perder.
	 *
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * Gets the caras dados. Devuelve una lista de longitud 2 con los valores de las caras de los dados.
	 *
	 * @return the caras dados
	 */
	public int[] getCarasDados() {
		return carasDados;
	}
	
	/**
	 * Gets the cara dado 1. Devuelve el valor de la cara visible del dado1.
	 *
	 * @return the cara dado 1
	 */
	public int getCaraDado1() {
		return carasDados[0];
	}
	
	/**
	 * Gets the cara dado 2. Devuelve el valor de la cara visible del dado2.
	 *
	 * @return the cara dado 2
	 */
	public int getCaraDado2() {
		return carasDados[1];
	}
	
}
