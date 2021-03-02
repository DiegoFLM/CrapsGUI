/*
 * Pogramación interactiva
 * Autor: Diego Fabián Ledesma - 1928161
 * Caso 1: Juego Craps.
 */
package craps;

import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class VistaConsola. Se encarga de realizar las operaciones I/O por línea de comando usando objeto scanner.
 */
public class VistaConsola {
	 
 	private ControlCraps controlCraps;
 	private String pregunta;	 
 	private Scanner lecturaDatos;
	 
	 /**
 	 * Instantiates a new vista consola. Constructor de la clase, inicia los objetos auxiliares.
 	 */
 	public VistaConsola() {
		 controlCraps = new ControlCraps();
		 lecturaDatos = new Scanner(System.in);
	 }
	 
	 /**
 	 * Iniciar juego. Inicia la ronda de juego.
 	 */
 	public void iniciarJuego() {
		 System.out.println("¿Desea lanzar los dados? y/n");
		 pregunta = lecturaDatos.nextLine();
		 if (pregunta.equalsIgnoreCase("y")) {
			 //iniciar juego
			 controlCraps.calcularTiro();
			 System.out.printf("Dado 1 = %d  Dado 2 = %d  Tiro = %d  \n", controlCraps.getCaraDado1(), 
					 													controlCraps.getCaraDado2(),
					 													controlCraps.getTiro());
			 controlCraps.determinarEstadoJuego();
			 
			 switch(controlCraps.getEstado()) {
			 	case 1: System.out.println("¡Has ganado!!");
			 			break;
			 	case 2: System.out.println("¡Has perdido!!"); 
			 			break;
			    case 3: System.out.printf("Has establecido punto = %d, ¡Debes lanzar nuevamente!!", controlCraps.getPunto());
			    		while(controlCraps.getEstado() == 3) {
			    			System.out.println("¿Desea lanzar? Escribe y/n");
			    			pregunta = lecturaDatos.nextLine();
			    			
			    			if (pregunta.equalsIgnoreCase("y")) {
			    				controlCraps.calcularTiro();
			    				
			    				System.out.printf("Dado 1 = %d  Dado 2 = %d  Tiro = %d  \n", controlCraps.getCaraDado1(), 
																							controlCraps.getCaraDado2(),
																							controlCraps.getTiro());
			    				controlCraps.determinarEstadoJuego();
			    			}
			    			else {
			    				System.out.println("Perdiste por abandonar el juego.");
			    				controlCraps.setAbandono();
			    			}
			    		}
			    		if (controlCraps.getEstado() == 1) {
			    			System.out.println("¡Lograste el punto y ganaste!");
			    		}
			    		else {
			    			System.out.println("¡Perdiste!");
			    		}
			    		break;
			 }
			 seguirJuego();
			 
		 }
		 else {
			 System.out.println("Bye!!");
		 }
		 		 
	 }
	 
	 
	 /**
 	 * Seguir juego. Preguna al usuario si desea seguir jugando y en caso tal inicia una nueva ronda.
 	 */
 	private void seguirJuego() {
		 System.out.println("¿Quieres jugar otra ronda? Escribe y/n");
		 pregunta = lecturaDatos.nextLine();
		 if (pregunta.equalsIgnoreCase("y")) {
			 iniciarJuego();
		 }
		 else {
			 System.out.println("Bye!!");
		 }
	 }
	 
	
	
}
