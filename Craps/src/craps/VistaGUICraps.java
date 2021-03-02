package craps;

import javax.swing.JFrame;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;



public class VistaGUICraps extends JFrame {
	//Atributos:
	private JLabel dado1, dado2;
	private JButton lanzar;
	private ImageIcon imagen;
	private Escucha	escucha;
	private ControlCraps controlCraps;
	
	
	//Métodos:
	public VistaGUICraps() {
		//window container y layout
		Container contenedor = this.getContentPane();
		contenedor.setLayout(new FlowLayout());
		
	//Crear objeto escucha y objeto control
		escucha = new Escucha();
		controlCraps = new ControlCraps();
		
	//Agregamos componentes gráficos
		imagen = new ImageIcon("src/imagenes/dado.png");
		dado1 = new JLabel(imagen);
		dado2 = new JLabel(imagen);
		
		lanzar = new JButton("Lanzar");
		lanzar.addActionListener(escucha);
		
		contenedor.add(dado1);
		contenedor.add(dado2);
		contenedor.add(lanzar);
		
		this.setTitle("Juego Craps");
		this.setSize(350, 210);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	//inner class
	private class Escucha implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			if (event.getSource() == lanzar) {
				visualizarCaras();
				controlCraps.determinarEstadoJuego();
				String tiro = "El tiro fue " + controlCraps.getTiro() + "\n";
				Icon icon;
				
				switch(controlCraps.getEstado()) {
				case 1: icon = new ImageIcon("src/imagenes/ganaste.png");
					JOptionPane.showMessageDialog(lanzar, tiro + "¡Has ganado!", "Resultado", JOptionPane.DEFAULT_OPTION, icon);
					break;
				case 2: icon = new ImageIcon("src/imagenes/perdiste.png");
					JOptionPane.showMessageDialog(lanzar, tiro + "¡Has perdido!", "Resultado", JOptionPane.DEFAULT_OPTION, icon);
					break;
				case 3: String punto = "Has establecido punto en: " + controlCraps.getPunto() 
										+ "debes volver a sacar el valor del punto para ganar," + "\n" +" pero si sacas 7 perderás.";
					icon = new ImageIcon("src/imagenes/punto.png");
					JOptionPane.showMessageDialog(lanzar, tiro + punto, "Resultado", JOptionPane.DEFAULT_OPTION, icon);
					break;
				}
			}
		}
		
	}

	
	private void visualizarCaras() {
		controlCraps.calcularTiro();
		imagen = new ImageIcon("src/imagenes/" + controlCraps.getCaraDado1() + ".png");
		dado1.setIcon(imagen);
		imagen = new ImageIcon("src/imagenes/" + controlCraps.getCaraDado2() + ".png");
		dado2.setIcon(imagen);
	}
}
