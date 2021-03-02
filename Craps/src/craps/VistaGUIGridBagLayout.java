package craps;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import misComponentes.Titulos;

public class VistaGUIGridBagLayout extends JFrame {

	//Atributos
	private JPanel zonaJuego, zonaResultados;
	private JLabel dado1, dado2, tiro, punto;
	private JTextField valorTiro, valorPunto;
	private JButton salir, lanzar;
	private ImageIcon imagen;
	private JTextArea mensajes;
	private Titulos titulo,  resultados;
	private ControlCraps controlCraps;
	private Escucha escucha;
	private JFrame vistaGridBagLayout;
	
	
	//Métodos
	public VistaGUIGridBagLayout() {
		initGUI();
		
		//set default window configuration
		//this.setTitle("Juego Craps");
		this.setUndecorated(true);
		this.setBackground(new Color(255, 255, 25, 100));
		this.pack();
		//this.setSize(310, 180);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI() {
		//set up container - layout
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		//create objects escucha, control and others
		escucha = new Escucha();
		controlCraps = new ControlCraps();
		vistaGridBagLayout = this;
		
		//set up GUIComponents
		titulo = new Titulos("Juego Craps", 30, new Color(0, 0, 0));
		titulo.addMouseListener(escucha);
		titulo.addMouseMotionListener(escucha);
		titulo.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(titulo, constraints);
		
		//zonaJuego
		zonaJuego = new JPanel();
		imagen = new ImageIcon("src/imagenes/dado.png");
		dado1 = new JLabel (imagen);
		dado2 = new JLabel (imagen);
		lanzar = new JButton("lanzar");
		lanzar.addActionListener(escucha);
		zonaJuego.add(dado1);
		zonaJuego.add(dado2);
		zonaJuego.add(lanzar);
		zonaJuego.setPreferredSize(new Dimension(310, 180));
		zonaJuego.setBorder(new TitledBorder("Zona Juego"));
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		constraints.fill = GridBagConstraints.NONE;
		add(zonaJuego, constraints);
		
		//salir
		salir = new JButton("Salir");
		salir.addActionListener(escucha);
		salir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.LAST_LINE_END;
		add(salir, constraints);
		
		//titulo resultados
		resultados = new Titulos("Resultados", 24, new Color(255, 20, 155));
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor=GridBagConstraints.CENTER;
		add(resultados, constraints);
		
		//zonaResultados
		zonaResultados = new JPanel();
		zonaResultados.setLayout(new GridLayout(2, 2));
		tiro = new JLabel("Tiro");
		punto = new JLabel("Punto");
		valorTiro = new JTextField(2);
		valorTiro.setEditable(false);
		valorPunto = new JTextField(2);
		valorPunto.setEditable(false);
		zonaResultados.add(tiro);
		zonaResultados.add(valorTiro);		
		zonaResultados.add(punto);
		zonaResultados.add(valorPunto);
		zonaResultados.setBackground(Color.white);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.CENTER;
		add(zonaResultados, constraints);
		
		mensajes = new JTextArea(10, 20);
		mensajes.setText("Lanza los dados para iniciar el juego. \n");
		mensajes.setEditable(false);
		JScrollPane scroll = new JScrollPane(mensajes);
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 2;
		constraints.fill = GridBagConstraints.VERTICAL;
		constraints.anchor=GridBagConstraints.CENTER;
		add(scroll, constraints);
		
		
	}
	
	
	private class Escucha implements ActionListener, MouseListener, MouseMotionListener{

		private int x, y;
		
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			// TODO Auto-generated method stub
			if (actionEvent.getSource() == salir) {
				System.exit(0);
			}else {
				controlCraps.calcularTiro();
				//Visualizacion grafica de los dados
				imagen = new ImageIcon("src/imagenes/" + controlCraps.getCaraDado1() + ".png");
				dado1.setIcon(imagen);
				imagen = new ImageIcon("src/imagenes/" + controlCraps.getCaraDado2() + ".png");
				dado2.setIcon(imagen);
				
				controlCraps.determinarEstadoJuego();
				valorTiro.setText(String.valueOf(controlCraps.getTiro()));
				
				switch (controlCraps.getEstado()) {
				case 1: //gano
					mensajes.append("Has ganado!! \n");
					break;
				case 2: //perdio
					mensajes.append("Has perdido!! \n");
					break;
				case 3: //punto
					valorPunto.setText(String.valueOf(controlCraps.getPunto()));
					String mensaje = "Estas en punto!! \nDebes seguir lanzando\n" + 
									 "Ganas si sacas nuevamente " + String.valueOf(controlCraps.getPunto()) +
									 "\nPierdes si antes sacas 7. \n";
					mensajes.append(mensaje);
					break;
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent eventMouse) {
			// TODO Auto-generated method stub
			//zonaResultados.setBackground(Color.CYAN);
		}

		@Override
		public void mousePressed(MouseEvent eventMouse) {
			// TODO Auto-generated method stub
			//lanzar.setBackground(Color.RED);
			x = eventMouse.getX();
			y = eventMouse.getY();
			System.out.println("x = " + x);
			System.out.println("y = " + y);
		}

		@Override
		public void mouseReleased(MouseEvent eventMouse) {
			// TODO Auto-generated method stub
			//salir.setBackground(Color.ORANGE);
		}

		@Override
		public void mouseEntered(MouseEvent eventMouse) {
			// TODO Auto-generated method stub
			//salir.setBackground(Color.BLUE);
		}

		@Override
		public void mouseExited(MouseEvent eventMouse) {
			// TODO Auto-generated method stub
			//salir.setBackground(Color.GREEN);
		}

		@Override
		public void mouseDragged(MouseEvent eventMouseMotion) {
			// TODO Auto-generated method stub
			//System.out.println("Mouse Motion Dragged " + eventMouseMotion.getX());
			setLocation(vistaGridBagLayout.getLocation().x + eventMouseMotion.getX() - x, vistaGridBagLayout.getLocation().y + eventMouseMotion.getY() - y);
		}

		@Override
		public void mouseMoved(MouseEvent eventMouseMotion) {
			// TODO Auto-generated method stub
			//System.out.println("Mouse Motion Moved " + eventMouseMotion.getX());

		}
		
	}
	
}
