package Graficos;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Administracion.Gerente;
import Administracion.Ejecutar;
import Alimentos.*;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Ventana extends JFrame {

	private JPanel contentPane;
	private JTextField textStatGanancia;
	private JTextField textStatPerdida;
	private JTextField textClientesPerdidos;
	private JTextField textOrdenesCompletadas;
	private JTextField textCTicks;
	private JTextField textVGanancia;
	private JTextField textVPerdida;
	private JTextField textVCliente_Perdido;
	private JTextField textVOrde_Completadas;
	private JTextField textVTicks;
	private JTextField textClienteActual;
	private JTextField textCAContadorEspera;
	private JTextField textCAContadorPaciencia;
	private JTextField textClienteEspera;
	private JTextField textCEContadorPaciencia;
	private JTextField textCEContadorEspera;
	private JTextField textVCAContadorEspera;
	private JTextField textVCAContadorPaciencia;
	private JTextField textVCEContadorEspera;
	private JTextField textVCEContadorPaciencia;
	private JLabel PersonaActual_1;
	private JLabel Mostrador;
	private JLabel diagonal;
	private JLabel Mostrador_1;
	private JTextField txtCocina;
	private JLabel filaCliente;
	private JLabel filaCliente_1;
	private JTextArea SeparadorStats_1;
	private JTextField textColaFaltantes;
	private JTextField textColaProduccion;
	private Gerente adminGerente;
	private JTextArea textVColaFaltante;
	private JTextArea textVColaProduccion;


	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Ventana(Gerente adminGerente, Thread main) {

		this.adminGerente = adminGerente;


		setFont(new Font("Arial", Font.BOLD, 14));
		setBackground(SystemColor.menu);
		setTitle("Proyecto 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.GRAY);
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = mipantalla.getScreenSize();
		int largoPantalla = tamanoPantalla.height;
		int anchoPantalla = tamanoPantalla.width;
		 
		setSize(9*anchoPantalla/10, 9*largoPantalla/10);
		setLocation(anchoPantalla/20, largoPantalla/20);
		getContentPane().setLayout(null);
		
		JLabel PersonaActual = new JLabel("");
		PersonaActual.setIcon(new ImageIcon(Ventana.class.getResource("/Graficos/personas(58x94).png")));
		PersonaActual.setBounds(938, 616, 58, 94);
		getContentPane().add(PersonaActual);
		
		textStatGanancia = new JTextField();
		textStatGanancia.setBackground(new Color(153, 204, 255));
		textStatGanancia.setHorizontalAlignment(SwingConstants.TRAILING);
		textStatGanancia.setFont(new Font("Arial", Font.BOLD, 14));
		textStatGanancia.setText("Ganancia Dinero>> ");
		textStatGanancia.setEditable(false);
		textStatGanancia.setBounds(1113, 10, 155, 19);
		getContentPane().add(textStatGanancia);
		textStatGanancia.setColumns(10);
		
		textStatPerdida = new JTextField();
		textStatPerdida.setBackground(new Color(153, 204, 255));
		textStatPerdida.setHorizontalAlignment(SwingConstants.TRAILING);
		textStatPerdida.setFont(new Font("Arial", Font.BOLD, 14));
		textStatPerdida.setText("Perdida Dinero>> ");
		textStatPerdida.setEditable(false);
		textStatPerdida.setBounds(1113, 39, 155, 19);
		getContentPane().add(textStatPerdida);
		textStatPerdida.setColumns(10);
		
		textClientesPerdidos = new JTextField();
		textClientesPerdidos.setBackground(new Color(153, 204, 255));
		textClientesPerdidos.setHorizontalAlignment(SwingConstants.TRAILING);
		textClientesPerdidos.setFont(new Font("Arial", Font.BOLD, 14));
		textClientesPerdidos.setText("Clientes Perdidos>> ");
		textClientesPerdidos.setEditable(false);
		textClientesPerdidos.setBounds(1113, 68, 155, 19);
		getContentPane().add(textClientesPerdidos);
		textClientesPerdidos.setColumns(10);
		
		textOrdenesCompletadas = new JTextField();
		textOrdenesCompletadas.setBackground(new Color(153, 204, 255));
		textOrdenesCompletadas.setHorizontalAlignment(SwingConstants.TRAILING);
		textOrdenesCompletadas.setText("Ord-Completadas>> ");
		textOrdenesCompletadas.setFont(new Font("Arial", Font.BOLD, 14));
		textOrdenesCompletadas.setEditable(false);
		textOrdenesCompletadas.setBounds(1113, 97, 155, 19);
		getContentPane().add(textOrdenesCompletadas);
		textOrdenesCompletadas.setColumns(10);
		
		textCTicks = new JTextField();
		textCTicks.setBackground(new Color(153, 204, 255));
		textCTicks.setHorizontalAlignment(SwingConstants.TRAILING);
		textCTicks.setFont(new Font("Arial", Font.BOLD, 14));
		textCTicks.setText("Ticks>> ");
		textCTicks.setEditable(false);
		textCTicks.setBounds(1113, 126, 155, 19);
		getContentPane().add(textCTicks);
		textCTicks.setColumns(10);
		
		textVGanancia = new JTextField();
		textVGanancia.setBackground(new Color(255, 255, 255));
		textVGanancia.setEditable(false);
		textVGanancia.setFont(new Font("Arial", Font.BOLD, 14));
		textVGanancia.setBounds(1278, 11, 80, 19);
		getContentPane().add(textVGanancia);
		textVGanancia.setColumns(10);
		
		textVPerdida = new JTextField();
		textVPerdida.setBackground(new Color(255, 255, 255));
		textVPerdida.setEditable(false);
		textVPerdida.setFont(new Font("Arial", Font.BOLD, 14));
		textVPerdida.setColumns(10);
		textVPerdida.setBounds(1278, 40, 80, 19);
		getContentPane().add(textVPerdida);
		
		textVCliente_Perdido = new JTextField();
		textVCliente_Perdido.setBackground(new Color(255, 255, 255));
		textVCliente_Perdido.setEditable(false);
		textVCliente_Perdido.setFont(new Font("Arial", Font.BOLD, 14));
		textVCliente_Perdido.setColumns(10);
		textVCliente_Perdido.setBounds(1278, 69, 80, 19);
		getContentPane().add(textVCliente_Perdido);
		
		textVOrde_Completadas = new JTextField();
		textVOrde_Completadas.setBackground(new Color(255, 255, 255));
		textVOrde_Completadas.setEditable(false);
		textVOrde_Completadas.setFont(new Font("Arial", Font.BOLD, 14));
		textVOrde_Completadas.setColumns(10);
		textVOrde_Completadas.setBounds(1278, 98, 80, 19);
		getContentPane().add(textVOrde_Completadas);
		
		textVTicks = new JTextField();
		textVTicks.setBackground(new Color(255, 255, 255));
		textVTicks.setEditable(false);
		textVTicks.setFont(new Font("Arial", Font.BOLD, 14));
		textVTicks.setColumns(10);
		textVTicks.setBounds(1278, 127, 80, 19);
		getContentPane().add(textVTicks);
		
		JTextArea SeparadorStats = new JTextArea();
		SeparadorStats.setBackground(new Color(102, 204, 204));
		SeparadorStats.setEnabled(false);
		SeparadorStats.setEditable(false);
		SeparadorStats.setBounds(1037, 0, 42, 740);
		getContentPane().add(SeparadorStats);
		
		JButton btnAvanzarPaso = new JButton("Avanzar un Paso");
		btnAvanzarPaso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		btnAvanzarPaso.setFont(new Font("Arial", Font.BOLD, 20));
		btnAvanzarPaso.setBounds(1089, 423, 269, 68);
		getContentPane().add(btnAvanzarPaso);
		
		JButton btnAvanzarTick = new JButton("Avanzar Tick");
		btnAvanzarTick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				synchronized(main){
					main.notify();
					
				}
				
				
			}
		});
		btnAvanzarTick.setFont(new Font("Arial", Font.BOLD, 20));
		btnAvanzarTick.setBounds(1089, 535, 269, 68);
		getContentPane().add(btnAvanzarTick);
		
		JButton btnTerminarPrograma = new JButton("Cerrar Programa");
		btnTerminarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				///xxxccc
				
				
			}
		});
		btnTerminarPrograma.setFont(new Font("Arial", Font.BOLD, 20));
		btnTerminarPrograma.setBounds(1089, 642, 269, 68);
		getContentPane().add(btnTerminarPrograma);
		
		textClienteActual = new JTextField(); // no hace nada mas
		textClienteActual.setBackground(new Color(204, 255, 153));
		textClienteActual.setEditable(false);
		textClienteActual.setHorizontalAlignment(SwingConstants.CENTER);
		textClienteActual.setText("Cliente Actual");
		textClienteActual.setFont(new Font("Arial", Font.BOLD, 14));
		textClienteActual.setBounds(1113, 184, 245, 19);
		getContentPane().add(textClienteActual);
		textClienteActual.setColumns(10);
		
		textCAContadorEspera = new JTextField();
		textCAContadorEspera.setBackground(new Color(255, 204, 204));
		textCAContadorEspera.setEditable(false);
		textCAContadorEspera.setHorizontalAlignment(SwingConstants.TRAILING);
		textCAContadorEspera.setText("Cntdr Espera>> ");
		textCAContadorEspera.setFont(new Font("Arial", Font.BOLD, 14));
		textCAContadorEspera.setBounds(1113, 222, 155, 19);
		getContentPane().add(textCAContadorEspera);
		textCAContadorEspera.setColumns(10);
		
		textCAContadorPaciencia = new JTextField();
		textCAContadorPaciencia.setBackground(new Color(255, 204, 204));
		textCAContadorPaciencia.setEditable(false);
		textCAContadorPaciencia.setHorizontalAlignment(SwingConstants.TRAILING);
		textCAContadorPaciencia.setText("Cntdr Paciencia>>");
		textCAContadorPaciencia.setFont(new Font("Arial", Font.BOLD, 14));
		textCAContadorPaciencia.setBounds(1113, 251, 155, 19);
		getContentPane().add(textCAContadorPaciencia);
		textCAContadorPaciencia.setColumns(10);
		
		textClienteEspera = new JTextField();
		textClienteEspera.setBackground(new Color(255, 204, 153));
		textClienteEspera.setEditable(false);
		textClienteEspera.setHorizontalAlignment(SwingConstants.CENTER);
		textClienteEspera.setText("Cliente en Espera");
		textClienteEspera.setFont(new Font("Arial", Font.BOLD, 14));
		textClienteEspera.setBounds(1113, 308, 245, 19);
		getContentPane().add(textClienteEspera);
		textClienteEspera.setColumns(10);
		
		textCEContadorPaciencia = new JTextField();
		textCEContadorPaciencia.setBackground(new Color(204, 255, 255));
		textCEContadorPaciencia.setEditable(false);
		textCEContadorPaciencia.setText("Cntdr Paciencia>>");
		textCEContadorPaciencia.setHorizontalAlignment(SwingConstants.TRAILING);
		textCEContadorPaciencia.setFont(new Font("Arial", Font.BOLD, 14));
		textCEContadorPaciencia.setColumns(10);
		textCEContadorPaciencia.setBounds(1113, 373, 155, 19);
		getContentPane().add(textCEContadorPaciencia);
		
		textCEContadorEspera = new JTextField();
		textCEContadorEspera.setBackground(new Color(204, 255, 255));
		textCEContadorEspera.setEditable(false);
		textCEContadorEspera.setText("Cntdr Espera>> ");
		textCEContadorEspera.setHorizontalAlignment(SwingConstants.TRAILING);
		textCEContadorEspera.setFont(new Font("Arial", Font.BOLD, 14));
		textCEContadorEspera.setColumns(10);
		textCEContadorEspera.setBounds(1113, 344, 155, 19);
		getContentPane().add(textCEContadorEspera);
		
		textVCAContadorEspera = new JTextField();
		textVCAContadorEspera.setBackground(new Color(255, 255, 255));
		textVCAContadorEspera.setEditable(false);
		textVCAContadorEspera.setFont(new Font("Arial", Font.BOLD, 14));
		textVCAContadorEspera.setColumns(10);
		textVCAContadorEspera.setBounds(1278, 223, 80, 19);
		getContentPane().add(textVCAContadorEspera);
		
		textVCAContadorPaciencia = new JTextField();
		textVCAContadorPaciencia.setBackground(new Color(255, 255, 255));
		textVCAContadorPaciencia.setEditable(false);
		textVCAContadorPaciencia.setFont(new Font("Arial", Font.BOLD, 14));
		textVCAContadorPaciencia.setColumns(10);
		textVCAContadorPaciencia.setBounds(1278, 252, 80, 19);
		getContentPane().add(textVCAContadorPaciencia);
		
		textVCEContadorEspera = new JTextField();
		textVCEContadorEspera.setBackground(new Color(255, 255, 255));
		textVCEContadorEspera.setEditable(false);
		textVCEContadorEspera.setFont(new Font("Arial", Font.BOLD, 14));
		textVCEContadorEspera.setColumns(10);
		textVCEContadorEspera.setBounds(1278, 345, 80, 19);
		getContentPane().add(textVCEContadorEspera);
		
		textVCEContadorPaciencia = new JTextField();
		textVCEContadorPaciencia.setBackground(new Color(255, 255, 255));
		textVCEContadorPaciencia.setEditable(false);
		textVCEContadorPaciencia.setFont(new Font("Arial", Font.BOLD, 14));
		textVCEContadorPaciencia.setColumns(10);
		textVCEContadorPaciencia.setBounds(1278, 373, 80, 19);
		getContentPane().add(textVCEContadorPaciencia);
		
		PersonaActual_1 = new JLabel("");
		PersonaActual_1.setIcon(new ImageIcon(Ventana.class.getResource("/Graficos/personas(58x94).png")));
		PersonaActual_1.setBounds(938, 423, 58, 94);
		getContentPane().add(PersonaActual_1);
		
		Mostrador = new JLabel("");
		Mostrador.setIcon(new ImageIcon(Ventana.class.getResource("/Graficos/mostrador.png")));
		Mostrador.setBounds(836, 383, 74, 357);
		getContentPane().add(Mostrador);
		
		diagonal = new JLabel("");
		diagonal.setIcon(new ImageIcon(Ventana.class.getResource("/Graficos/diagonal.png")));
		diagonal.setBounds(703, 251, 207, 141);
		getContentPane().add(diagonal);
		
		Mostrador_1 = new JLabel("");
		Mostrador_1.setIcon(new ImageIcon(Ventana.class.getResource("/Graficos/mostrador.png")));
		Mostrador_1.setBounds(703, 0, 74, 251);
		getContentPane().add(Mostrador_1);
		
		txtCocina = new JTextField();
		txtCocina.setEditable(false);
		txtCocina.setHorizontalAlignment(SwingConstants.CENTER);
		txtCocina.setText("Cocina");
		txtCocina.setFont(new Font("Arial", Font.BOLD, 39));
		txtCocina.setBackground(new Color(51, 153, 204));
		txtCocina.setBounds(814, 21, 198, 202);
		getContentPane().add(txtCocina);
		txtCocina.setColumns(10);
		
		filaCliente = new JLabel("");
		filaCliente.setIcon(new ImageIcon(Ventana.class.getResource("/Graficos/personas (385x94).png")));
		filaCliente.setBounds(392, 597, 385, 133);
		getContentPane().add(filaCliente);
		
		filaCliente_1 = new JLabel("");
		filaCliente_1.setIcon(new ImageIcon(Ventana.class.getResource("/Graficos/personas (385x94).png")));
		filaCliente_1.setBounds(392, 412, 385, 133);
		getContentPane().add(filaCliente_1);
		
		SeparadorStats_1 = new JTextArea();
		SeparadorStats_1.setEditable(false);
		SeparadorStats_1.setBackground(new Color(204, 255, 51));
		SeparadorStats_1.setBounds(291, 0, 42, 740);
		getContentPane().add(SeparadorStats_1);
		
		textColaFaltantes = new JTextField();
		textColaFaltantes.setBackground(new Color(102, 204, 255));
		textColaFaltantes.setHorizontalAlignment(SwingConstants.CENTER);
		textColaFaltantes.setText("Cola Faltante");
		textColaFaltantes.setEditable(false);
		textColaFaltantes.setFont(new Font("Arial", Font.BOLD, 16));
		textColaFaltantes.setBounds(79, 41, 117, 28);
		getContentPane().add(textColaFaltantes);
		textColaFaltantes.setColumns(10);
		
		textColaProduccion = new JTextField();
		textColaProduccion.setBackground(new Color(102, 204, 153));
		textColaProduccion.setHorizontalAlignment(SwingConstants.CENTER);
		textColaProduccion.setText("Cola Producci\u00F3n");
		textColaProduccion.setFont(new Font("Arial", Font.BOLD, 16));
		textColaProduccion.setEditable(false);
		textColaProduccion.setBounds(79, 412, 135, 32);
		getContentPane().add(textColaProduccion);
		textColaProduccion.setColumns(10);
		
		textVColaFaltante = new JTextArea();
		textVColaFaltante.setEditable(false);
		textVColaFaltante.setFont(new Font("Arial", Font.BOLD, 14));
		textVColaFaltante.setText("1. \r\n2. \r\n3. \r\n4. \r\n5. ");
		textVColaFaltante.setBackground(new Color(204, 255, 255));
		textVColaFaltante.setBounds(10, 98, 271, 251);
		getContentPane().add(textVColaFaltante);
		
		textVColaProduccion = new JTextArea();
		textVColaProduccion.setEditable(false);
		textVColaProduccion.setText("1. \r\n2. \r\n3. \r\n4. \r\n5. ");
		textVColaProduccion.setFont(new Font("Arial", Font.BOLD, 14));
		textVColaProduccion.setBackground(new Color(255, 255, 204));
		textVColaProduccion.setBounds(10, 459, 271, 251);
		getContentPane().add(textVColaProduccion);
		
	}


	public void actualizarValoresGerencia(int ganancia, int perdida, int clientePerdidos, int ordenesCompletadas, int cantidadTicks){


		this.textVGanancia.setText(String.valueOf(ganancia));
		this.textVPerdida.setText(String.valueOf(perdida));
		this.textVCliente_Perdido.setText(String.valueOf(clientePerdidos));
		this.textVOrde_Completadas.setText(String.valueOf(ordenesCompletadas));
		this.textVTicks.setText(String.valueOf(cantidadTicks));
		
	}

	public void actualizarValoresClienteActual(int contadorEspera, int contadorPaciencia){

		this.textVCAContadorEspera.setText(String.valueOf(contadorEspera));
		this.textVCAContadorPaciencia.setText(String.valueOf(contadorPaciencia));

	}

	public void actualizarValoresClienteActual(int contadorEspera, boolean contadorPaciencia){

		this.textVCAContadorEspera.setText(String.valueOf(contadorEspera));
		this.textVCAContadorPaciencia.setText("0");

	}


	public void actualizarValoresClienteEsperando(int contadorEspera, int contadorPaciencia){

		this.textVCEContadorEspera.setText(String.valueOf(contadorEspera));
		this.textVCEContadorPaciencia.setText(String.valueOf(contadorPaciencia));

	}

	public void actualizarValoresClienteEsperando(int contadorEspera, boolean contadorPaciencia){

		this.textVCEContadorEspera.setText(String.valueOf(contadorEspera));
		this.textVCEContadorPaciencia.setText("0");

	}


	public void actualizarPedidosProduccion(Comida colacomida5[]){

		this.textVColaProduccion.setText("1. " + colacomida5[0] + "\r\n2. " + colacomida5[1] + "\r\n3. " + colacomida5[2]
		 +"\r\n4. "+ colacomida5[3] + "\r\n5. " + colacomida5[4]);


	} 

	public void actualizarPedidosFaltantes(Comida colacomida5[]){

		this.textVColaFaltante.setText("1. " + colacomida5[0] + "\r\n2. " + colacomida5[1] + "\r\n3. " + colacomida5[2]
		 +"\r\n4. "+ colacomida5[3] + "\r\n5. " + colacomida5[4]);


	} 


}
