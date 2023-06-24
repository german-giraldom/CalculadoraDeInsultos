package paq1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;

public class Interfaz extends JFrame {
	
	JFrame ventana;
	JPanel panelprincipal = new JPanel(), 
			paneltexto = new JPanel(), 
			panelnumerico= new JPanel(), 
			panelsimbolos = new JPanel(),
			panelespecial = new JPanel();
	
	String nombres_botones_simbolos[] = {"+","-","*","/","="},
			nombres_botones_especiales[] = {"%","<","C"},
			nombres_botones_numerico[] = {" ","0","."};
	
	JButton arreglo_botones_simbolos[] = new JButton[nombres_botones_simbolos.length],
			arreglo_botones_especiales[] = new JButton[nombres_botones_especiales.length],
			arreglo_botones_numericos[] = new JButton[12];
	
	JTextArea textonum;
	
	// cambiar tipo y tamaño de letra
	Font fuente = new Font(Font.DIALOG, 0, 23);
	
	public Interfaz() {
		super();
		Inciarventana();
		iniciarpaneles();
		iniciarareatexto();
		inciarbotones();
	}

	private void inciarbotones() {
		byte i = 0;
		byte j = 0;
		
		// añadir los botones de numeros al panel numerico
		for (i = 0; i<arreglo_botones_numericos.length; i++) {
			if (i < 9) {
				arreglo_botones_numericos[i] = new JButton(""+(i+1));
			}else {
					arreglo_botones_numericos[i] = new JButton(""+nombres_botones_numerico[j]);
					arreglo_botones_numericos[i].setEnabled((j==0 ? false: true));
					j++;
				}
			arreglo_botones_numericos[i].setFont(fuente);
			arreglo_botones_numericos[i].setBackground(Color.LIGHT_GRAY);
			panelnumerico.add(arreglo_botones_numericos[i]);
		}
		
		// añadir los botones de simbolos al panel de simbolos
		for ( i = 0; i<arreglo_botones_simbolos.length; i++) {
			arreglo_botones_simbolos[i] = new JButton(""+nombres_botones_simbolos[i]);
			arreglo_botones_simbolos[i].setFont(fuente);
			arreglo_botones_simbolos[i].setBackground(Color.gray);
			panelsimbolos.add(arreglo_botones_simbolos[i]);
			}
		
		// añadir los botones especiales al panel especial
		for ( i = 0; i<arreglo_botones_especiales.length; i++) {
			arreglo_botones_especiales[i] = new JButton(""+nombres_botones_especiales[i]);
			arreglo_botones_especiales[i].setFont(fuente);
			arreglo_botones_especiales[i].setBackground(Color.gray);
			panelespecial.add(arreglo_botones_especiales[i]);
		}
	}
	
	private void iniciarareatexto() {
		textonum = new JTextArea();
		textonum.setEditable(true);
		textonum.setFont(fuente);
		//textonum.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); // para que ordene los caracteres de derecha a izquierda
		textonum.setBackground(Color.LIGHT_GRAY);
		paneltexto.add(textonum);
	}

	private void iniciarpaneles() {
		
		// panel principal
		ventana.add(panelprincipal);
		panelprincipal.setLayout(new BorderLayout()); // diseño principal
		
		// panel del campo de texto
		paneltexto.setBackground(Color.gray);
		paneltexto.setLayout(new BoxLayout(paneltexto, BoxLayout.PAGE_AXIS));
		panelprincipal.add(paneltexto, BorderLayout.NORTH);
		
		// panel de los botones numericos
		panelnumerico.setBackground(Color.gray);
		panelnumerico.setLayout(new GridLayout(4, 3));
		panelprincipal.add(panelnumerico, BorderLayout.CENTER);
		
		// panel de los botones de operaciones o simbolos
		panelsimbolos.setBackground(Color.gray);
		panelsimbolos.setLayout(new GridLayout(5, 1));
		panelprincipal.add(panelsimbolos, BorderLayout.EAST);
		
		// panel de los botones especiales
		panelespecial.setBackground(Color.gray);
		panelespecial.setLayout(new GridLayout(3,1));
		panelprincipal.add(panelespecial, BorderLayout.WEST);

	}

	private void Inciarventana() {
		ventana = new JFrame("calculadora de insultos");
		//ventana.setSize(400,400);
		ventana.setMinimumSize(new Dimension(350, 400));
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ventana.setVisible(true);
	}
}
