package paq1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.text.html.HTMLDocument.HTMLReader.CharacterAction;

public class Acciones extends Interfaz {
	
	boolean punto =false;
	
	String addstring = "",
			insulto = "";
	
	char simbolo = ' ';
	
	float numresultado = 0,
			numtemp = 0;
	
	double numd = 0;
	
	Accion_botones_numericos acciondebotonesnumericos = new Accion_botones_numericos();
	Accion_botones_simbolos acciondebotonessimbolos = new Accion_botones_simbolos();
	Accion_botones_especiales acciondebotonesespeciales = new Accion_botones_especiales(); 
	
	public Acciones () { // constructor
		super();
		Iniciaraccionbotones();
	}
	
	
	private void Iniciaraccionbotones() {
		byte i=0;
		
		// agregar acciones a los botones numericos
		for ( i=0; i<arreglo_botones_numericos.length; i++) {
			
			arreglo_botones_numericos[i].addActionListener(acciondebotonesnumericos);
			
			// agregar acciones a los botones con simbolos
			if (i<arreglo_botones_simbolos.length) {
				arreglo_botones_simbolos[i].addActionListener(acciondebotonessimbolos);
			}
			
			// agregar acciones a los botones especiales
			if (i<arreglo_botones_especiales.length) {
				arreglo_botones_especiales[i].addActionListener(acciondebotonesespeciales);
			}
		}
	}
	
	private class Accion_botones_numericos implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// para que borre el contenido del campo si ya mostro el resultado y ponga los nuevos caracteres
			if (simbolo == '=') { 
				numresultado = numtemp = 0;
				textonum.setText("");
			    simbolo =' ';
		    }
			
			// para permitir un solo punto por numero
			if (e.getActionCommand().equals(".")) {
				if (punto == false) {
					addstring += e.getActionCommand();
					textonum.append(e.getActionCommand());
					punto = true;
				}else {}
			}else {
				// concatena todos los caracteres
				addstring += e.getActionCommand();
				// agrega los caracteres al campo
				textonum.append(e.getActionCommand());
			}
		}
	}
	
	private class Accion_botones_simbolos implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			punto = false;
			
			try {
			// valida primero que se haya ingresado un numero
			if ( simbolo =='=' ){
				
				// comprobar si es decimal o entero e imprime
				if(numresultado%1 == 0) { // no es decimal
					textonum.setText(""+(int)numresultado);
				}else { // es decimal
					textonum.setText(""+numresultado);
				}
				textonum.append(e.getActionCommand());
				simbolo =e.getActionCommand().charAt(0);
				
			}else
				if(addstring != "") {
					
					if (e.getSource() == arreglo_botones_simbolos[0]) {
						Resultado();
						simbolo = '+';
					}else
						if (e.getSource() == arreglo_botones_simbolos[1]) {
							Resultado();
							simbolo = '-';
						}else
							if(e.getSource() == arreglo_botones_simbolos[2]) {
								Resultado();
								simbolo ='*';
							}else
								if(e.getSource() == arreglo_botones_simbolos[3]) {
									Resultado();
									simbolo = '/';
								}else
									// al pulsar igual =
									if(e.getSource() == arreglo_botones_simbolos[4]) {
										Resultado();
										
										simbolo = '=';
										textonum.append("\n"+simbolo);
										
										// comprobar si es decimal o entero e imprime
										
										if(numresultado%1 == 0) { // no es decimal
											textonum.append(" "+(int)numresultado);
										}else { // es decimal
											textonum.append(" "+numresultado);
										}
										
										//textonum.append(" "+Insultador());
									}
					
					//reinicia la concatenacion de numeros
					addstring = "";
					
					// agrega los simbolos al campo
					if(simbolo != '=') {
						textonum.append(""+simbolo);
					}
				}
		}catch (Exception numero) {
			simbolo = '=';
			textonum.setText("error");
			numresultado = numtemp = 0;
			addstring = "";
		}
		}
	}
	
	private void Resultado () {
		
			if(simbolo == ' ') {
				numresultado = Float.parseFloat(addstring);
				
			}else if(simbolo == '+') {
					numtemp = Float.parseFloat(addstring);
					numresultado += numtemp;
				}else
					if(simbolo == '-') {
						numtemp = Float.parseFloat(addstring);
						numresultado -= numtemp;
					}else
						if(simbolo == '*') {
							numtemp = Float.parseFloat(addstring);
							numresultado *= numtemp;
						}else
							if(simbolo == '/') {
								numtemp = Float.parseFloat(addstring);
								if(numtemp != 0) {
									numresultado /= numtemp;
								}
							}
	
	}	
	
	private class Accion_botones_especiales implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// % o porcentaje
			if (e.getSource() == arreglo_botones_especiales[0] && (addstring != "")) {
				
			}else // boton < o borrar un espacio
				if( (e.getSource() == arreglo_botones_especiales[1]) && (addstring != "")) {
					textonum.requestFocus();
					textonum.setText(textonum.getText().substring(0, textonum.getText().length()-1));
					textonum.setFocusable(false);
					addstring = addstring.substring(0, addstring.length()-1);
					
					punto = false;
					
				}else // boton C o borrar todo
					if (e.getSource() == arreglo_botones_especiales[2]) {
						numresultado = numtemp = 0;
						textonum.setText("");
						simbolo = ' ';
						arreglo_botones_especiales[1].setEnabled(true);
						punto = false;
					}
			
		}
	}
	
	private String Insultador() {
		if (numresultado == 0) {
			insulto = " money";
		}else
			if(numresultado == 1) {
				insulto = " no se que decir hombre";
			}else
				if(numresultado == 2) {
					insulto = " se la empujo a ustedes dos";
				}else
					if(numresultado == 3) {
						insulto = " hay en tu relacion";
					}else
						if(numresultado == 4) {
							insulto = " te pongo";
						}else
							if(numresultado == 5) {
								insulto = " por el culo te la inco";
							}else
								if(numresultado == 6) {
									insulto = " buscame en feis";
								}else
									if(numresultado == 7) {
										insulto = " ";
									}
		return insulto;
	}
}