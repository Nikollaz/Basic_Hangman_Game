package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import enums.*;

public class Main {

	private static EIdioma IDIOMA = EIdioma.English;
	
	private static int CANT_VIDAS;
	private static EEstadoJugador ESTADO;
	private static String NOMBRE_JUGADOR = "No se ingreso ningun nombre";
	private static Integer CANTIDAD_PARTIDAS = 0;
	private static Integer CANTIDAD_GANADAS = 0;
	private static Integer CANTIDAD_PERDIDAS = 0;
	private static List<String> PALABRAS_ADIVINADAS = new ArrayList<String>();
	private static List<String> PALABRAS_NO_ADIVINADAS = new ArrayList<String>();
	private static List<String> LETRAS_USADAS;
	private static Scanner SC = new Scanner(System.in);
	
	public static void main(String[] args) {
		String opcion = "";
			
		/* =============================== Comienzo ======================================= */		
		
		while(!(opcion.equals("s"))){	
			
			opcion = Presentacion();
			
			switch(opcion){
			
				case "c":
					
					/* =============================== Partida ======================================= */
					
					Partida();
					
					/* =============================== Estadisticas ====================================== */
					
					Estadisticas();
					break;
				case "i":
					
					/* =========================== Configuracion de idioma ====================================== */
					
					Idioma();
					break;
			}			
		}
		
		Despedida();
		return;
	}

	/***
	 * Metodo de presentacion al usuario
	 */
	private static String Presentacion() {
		String opcion = new String("");
		PALABRAS_ADIVINADAS = new ArrayList<String>();
		PALABRAS_NO_ADIVINADAS = new ArrayList<String>();
		CANTIDAD_PARTIDAS = 0;
		CANTIDAD_GANADAS = 0;
		CANTIDAD_PERDIDAS = 0;
		
		while(!(opcion.equals("c")) && !(opcion.equals("C")) && !(opcion.equals("s")) && !(opcion.equals("S")) && !(opcion.equals("i")) && !(opcion.equals("I"))){
			lineSeparator(50);			
			System.out.println("dMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMb");
			System.out.println("MPP _____   .__                                       .___    VVM");
			System.out.println("MP /  _  \\  |  |__    ____ _______  ____  _____     __| _/ ___ VM");  
			System.out.println("M /  /_\\  \\ |  |  \\  /  _ \\\\_   _ \\/ ___\\ \\__  \\   / __ | / _ \\ M"); 
			System.out.println("M/    |    \\|   Y  \\(  <_> )|  | \\/\\ \\___  / __ \\_/ /_/ |( <_> )M");
			System.out.println("M\\____|__  /|___|  / \\____/ |__|    \\__  >(____  /\\____ | \\___/ M"); 
			System.out.println("M        \\/      \\/                    \\/      \\/      \\/	M"); 
			System.out.println("M\t\t\t\t\t\t\t\tM");
			System.out.println("M\t\tdMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMb\t\tM");
			System.out.println("M\t\tM\t\t\t\tM\t\tM");
			System.out.println("M\t\tM\t   (C)omenzar\t\tM\t\tM");
			System.out.println("M\t\tM\t   (I)dioma \t\tM\t\tM");
			System.out.println("M\t\tM\t   (S)alir \t\tM\t\tM");
			System.out.println("M\t\tM\t\t\t\tM\t\tM");
			System.out.println("Mb\t\tVMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMP\t       dM");
			System.out.println("MMb\t\t\t\t\t\t\t      dMM");
			System.out.println("MMMb\t\t\t\t\t\t\t     dMMM");
			System.out.println("MMMMb\t\t\t\t\t\t\t    dMMMM");
			System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
			System.out.println("M\t\t\t\t\t\t\t\tM");
			System.out.println("M=== Bienvenido a TP final: ahorcado (1.0.0) ===\t\tM");
			System.out.println("M\t\t\t\t\t\t\t\tM");
			System.out.println("M Este proyecto fue hecho por el alumno Nicolas Saavedra \tM");
			System.out.println("M para PROGRAMACIÓN JAVA INICIAL - SUN-ORACLE \t\t\tM");
			System.out.println("M\t\t\t\t\t\t\t\tM");
			System.out.println("VMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMP");
			opcion = SC.nextLine();
		}
		
		switch(opcion){
			case "c":
			case "C":
				System.out.print(" > Ingrese su nombre: ");
				NOMBRE_JUGADOR = SC.nextLine();
				System.out.println("M===============================================================M");
				System.out.println("M Bienvenido " + NOMBRE_JUGADOR + ", espero disfrute el juego \t\t\tM");
				System.out.println("M===============================================================M");
				try {
					Thread.sleep(1500);
					System.out.print("\nCargando nueva partida");
					Thread.sleep(1000);
					System.out.print(". ");
					Thread.sleep(1000);
					System.out.print(". ");
					Thread.sleep(1000);
					System.out.print(". ");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
				return "c";		
			case "i":
			case "I":
				return "i";	
			default:
				return "s";
		}
	}	

	/**
	 * Metodo que guardara todo el proceso de la partida del usuario y la reiteracion de la misma
	 */
	private static void Partida(){
		boolean eleccion = true;

		while (eleccion) {		
			try {
				CANT_VIDAS = 5;
				ESTADO = EEstadoJugador.Jugando;
				CANTIDAD_PARTIDAS++;
				LETRAS_USADAS = new ArrayList<String>();
				eleccion = progresoPartida(GeneradorDePalabras.conseguirPalabra(IDIOMA));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}				
	}	
	
	/**
	 * Metodo de muestra de estadisticas al usuario
	 */
	private static void Estadisticas(){
		lineSeparator(50);
		System.out.println("dMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMb");
		System.out.println("M\t\t\t\t\t\t\t\tM");
		System.out.println("M\t\t\tSesion terminada\t\t\tM");
		System.out.println("M\t\t\t\t\t\t\t\tM");
		System.out.println("M\t\tdMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMb\t\tM");
		System.out.println("M\t\tM\t\t\t\tM\t\tM");
		System.out.println("M\t\tM\t  Estadisticas\t\tM\t\tM");
		System.out.println("M\t\tM\t\t\t\tM\t\tM");
		System.out.println("M\t\tVMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMb\t\tP");
		System.out.println("M\t\t\t\t\t\t\t\tM");
		System.out.println("M\t\t\t\t\t\t\t\tM");
		System.out.println("VMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMP");
		System.out.println();
		System.out.println(" Nombre de jugador: " + NOMBRE_JUGADOR);
		System.out.println(" Cantidad de partidas: " + CANTIDAD_PARTIDAS);
		System.out.println(" Cantidad de partidas ganadas: " + CANTIDAD_GANADAS);
		System.out.println(" Cantidad de partidas perdidas: " + CANTIDAD_PERDIDAS);
		System.out.println(" Palabras adivinadas: \n");
		if(PALABRAS_ADIVINADAS.size() == 0)
			System.out.println(" 0");
		else
			for (String string : PALABRAS_ADIVINADAS) {
				System.out.println(" "+string);
			}
		System.out.println("\n Palabras no adivinadas: \n");
		if(PALABRAS_NO_ADIVINADAS.size() == 0)
			System.out.println(" 0");
		else
			for (String string : PALABRAS_NO_ADIVINADAS) {
				System.out.println(" "+string);
			}
		System.out.println("\nd===============================================================b");
		System.out.println("M\t\t\t\t\t\t\t\tM");
		System.out.println("M > Presione cualquier tecla para continuar... \t\t\tM");
		System.out.println("M\t\t\t\t\t\t\t\tM");
		System.out.println("V===============================================================P");
		SC.nextLine();
		System.out.println("\t\t\t\t\t\t\t\t");
		System.out.print(" > Volviendo a la pantalla principal ");
		try {
		Thread.sleep(1500);
		System.out.print(". ");
		Thread.sleep(1500);
		System.out.print(". ");
		Thread.sleep(1500);
		System.out.print(". ");
		Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Metodo de despedida al usuario
	 */
	private static void Despedida(){
		lineSeparator(50);
		System.out.println("dMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMb");
		try {
			System.out.println("Mppp\t\t\t\t\t\t\t     VVVM");
			Thread.sleep(750);
			System.out.println("Mpp\t\t       TP final: ahorcado\t\t      VVM");
			Thread.sleep(750);
			System.out.println("Mp\t\t\t\t\t\t\t       VM");
			Thread.sleep(750);
			System.out.println("M 	 		Tecnologia usada		        M");
			Thread.sleep(750);
			System.out.println("M            Eclipse Java EE IDE for Web Developers             M");
			Thread.sleep(750);
			System.out.println("M\t\t\t\t\t\t\t\tM");
			Thread.sleep(750);
			System.out.println("M 			    Profesor 			\tM");
			Thread.sleep(750);
			System.out.println("M			  Martin Isusi 	            	 \tM");
			Thread.sleep(750);
			System.out.println("M\t\t\t\t\t\t\t\tM");
			Thread.sleep(750);
			System.out.println("M 			     Creador     \t\t\tM");
			Thread.sleep(750);
			System.out.println("M 			 Nicolas Saavedra  \t\t\tM");
			Thread.sleep(750);
			System.out.println("Mb\t\t\t\t\t\t\t       dM");
			Thread.sleep(750);
			System.out.println("Mbb\t\t\t\t\t\t\t      ddM");
			Thread.sleep(750);
			System.out.println("dMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMb");
			Thread.sleep(750);
			System.out.println("MMMMMMMP\t\t\t\t\t\t VMMMMMMM");
			Thread.sleep(750);
			System.out.println("MMMMMMP\t\t\t\t\t\t\t  VMMMMMM");
			Thread.sleep(750);
			System.out.println("MMMMMP\t\t\t\t\t\t\t   VMMMMM");
			Thread.sleep(750);
			System.out.println("MMMMP\t\t\t\t\t\t\t    VMMMM");
			Thread.sleep(750);
			System.out.println("MMMP\t\t\t\t\t\t\t     VMMM");
			Thread.sleep(750);
			System.out.println("MMP\t\t\t\t\t\t\t      VMM");
			Thread.sleep(750);
			System.out.println("MP\t\t\t\t\t\t\t       VM");
			Thread.sleep(750);
			System.out.println("M\t\t\t\t\t\t\t\tM");
			Thread.sleep(750);
			System.out.println("M      	 			 o/ 				M");
			Thread.sleep(750);
			System.out.println("M 				/|				M");
			Thread.sleep(750);
			System.out.println("M 				/ \\				M");
			Thread.sleep(750);
			System.out.println("M\t\tdMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMb\t\tM");
			Thread.sleep(750);
			System.out.println("M\t\tM\t\t\t\tM\t\tM");
			Thread.sleep(750);
			System.out.println("M\t\tM   Muchas gracias por jugar!\tM\t\tM");
			Thread.sleep(750);
			System.out.println("M\t\tM\t\t\t\tM\t\tM");
			Thread.sleep(750);
			System.out.println("M\t\tVMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMP\t        M");
			Thread.sleep(750);
			System.out.println("M\t\t\t\t\t\t\t\tM");
			Thread.sleep(750);
			System.out.println("Mb\t\t\t\t\t\t\t       dM");
			Thread.sleep(750);
			System.out.println("MMb\t\t\t\t\t\t\t      dMM");
			Thread.sleep(750);
			System.out.println("MMMb\t\t\t\t\t\t\t     dMMM");
			Thread.sleep(750);
			System.out.println("MMMMb\t\t\t\t\t\t\t    dMMMM");
			Thread.sleep(750);
			System.out.println("MMMMMb\t\t\t\t\t\t\t   dMMMMM");
			Thread.sleep(750);
			System.out.println("MMMMMMb\t\t\t\t\t\t\t  dMMMMMM");
			Thread.sleep(750);
			System.out.println("MMMMMMMb\t\t\t\t\t\t dMMMMMMM");
			Thread.sleep(750);
			System.out.println("VMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMP");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		
		SC.close();
	}
	
	/***
	 * Metodo que guardara el proceso de la partida en ejecucion
	 */
	private static boolean progresoPartida(String palabraInicial) {
		String palabraEnJuego = palabraInicial;		
		String letraEscogida = null;
		String seguir;
		
		while (ESTADO == EEstadoJugador.Jugando) {
			switch (CANT_VIDAS) {
			case 5:
				interfaceHeader(palabraInicial, palabraEnJuego);
				System.out.print("MM |	 \t");
				System.out.println("");
				System.out.println("MM |								");
				System.out.println("MM |								");
				System.out.println("MM |								");
				interfaceFooter();
				break;
			case 4:
				interfaceHeader(palabraInicial, palabraEnJuego);
				System.out.print("MM |	 o\t");
				System.out.println("");
				System.out.println("MM |	 |							");
				System.out.println("MM |								");
				System.out.println("MM |								");
				interfaceFooter();
				break;
			case 3:
				interfaceHeader(palabraInicial, palabraEnJuego);
				System.out.print("MM |	 o\t");
				System.out.println("");
				System.out.println("MM |	 |\\							");
				System.out.println("MM |	  							");
				System.out.println("MM |								");
				interfaceFooter();
				break;
			case 2:
				interfaceHeader(palabraInicial, palabraEnJuego);
				System.out.print("MM |	 o\t");
				System.out.println("");
				System.out.println("MM |	/|\\							");
				System.out.println("MM |								");
				System.out.println("MM |								");
				interfaceFooter();
				break;
			case 1:
				interfaceHeader(palabraInicial, palabraEnJuego);
				System.out.print("MM |	 o\t");
				System.out.println("");
				System.out.println("MM |	/|\\							");
				System.out.println("MM |	  \\							");
				System.out.println("MM |								");
				interfaceFooter();
				break;
			}

			System.out.print("Presione la letra que desee utilizar:  ");			
			letraEscogida = SC.nextLine();
			while (letraEscogida.length() > 1 || !(letraEscogida.matches("[a-zA-Z]"))) {
				System.out.println("Presione una letra valida: ");
				letraEscogida = SC.nextLine();
			}

			palabraEnJuego = ValidarLetraEnPalabra(palabraInicial, palabraEnJuego, letraEscogida);

		}

		if(ESTADO == EEstadoJugador.Gano){
			interfaceHeader(palabraInicial, palabraEnJuego);
			System.out.print("MM |	 \t" + mensaje());
			System.out.println("");
			System.out.println("MM |    \\o/ 							");
			System.out.println("MM |	 |								");
			System.out.println("MM |	/ \\							");
			interfaceFooter();
			CANTIDAD_GANADAS++;
		}
		else if(ESTADO == EEstadoJugador.Perdio){
			interfaceHeader(palabraInicial, palabraEnJuego);
			System.out.print("MM |	 o\t" + mensaje());
			System.out.println("");
			System.out.println("MM |	/|\\							");
			System.out.println("MM |	/ \\							");
			System.out.println("MM |								");
			interfaceFooter();
			CANTIDAD_PERDIDAS++;
		}		

		System.out.print("Desea volver a jugar? s/n: ");
		seguir = SC.nextLine();
		while (!(seguir.equals("s")) && !(seguir.equals("S")) && !(seguir.equals("N")) && !(seguir.equals("n"))) {
			System.out.println("Presione una letra valida: ");
			seguir = SC.nextLine();
		}
		if (seguir.equals("s") || seguir.equals("S")) {
			return true;
		}

		return false;
	}
	
	/**
	 * Header de la interfaz grafica del juego
	 * @param palabraInicial
	 * @param palabraEnJuego
	 */
	private static void interfaceHeader(String palabraInicial, String palabraEnJuego) {
		lineSeparator(50);
		System.out.println("dMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMb");
		System.out.println("MMP");
		System.out.print("MM /----\\				");
		DibujarPalabra(palabraInicial, palabraEnJuego);
		System.out.println("			");
		System.out.println("MM |	 |							");
	}
	
	/**
	 * Footer de la interface grafica del juego
	 */
	private static void interfaceFooter() {
		System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
		System.out.print("      Cantidad de vidas: " + CANT_VIDAS + "\t\tLetras: ");
		letrasUtilizadas();
		System.out.println();
		System.out.println("VMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMP");
	}
	
	/**
	 * Metodo encargado de dibujar la palabra con guiones bajos en las letras sin adivinar, 
	 * y con letras en las que fueron adivinadas
	 * @param palabraInicial Palabra original sin modificaciones
	 * @param palabraEnJuego Palabra en juego con o sin caracteres adivinados
	 */
	private static void DibujarPalabra(String palabraInicial, String palabraEnJuego) {
		/**
		 * Dividio la palabra en juego a char, pongo en minuscula la letra
		 * escogida, y pongo contadores y flags para validar si el jugador gano
		 * o no, y las vidas restantes
		 */
		char[] palabraEnJuegoChar = palabraEnJuego.toCharArray();

		/**
		 * En caso de seguir jugando, imprimo los caracteres adivinados, y los
		 * que no, los dejo con guiones
		 */
		for (int i = 0; i < palabraInicial.length(); i++) {

			if (palabraEnJuegoChar[i] == 0) {
				System.out.print(palabraInicial.charAt(i) + " ");
			} else {
				System.out.print("_ ");
			}
		}

	}
	
	/**
	 * Metodo encargado de validar si la letra escogida se encuentra en la palabra
	 * @param palabraEnJuego Palabra en juego con o sin caracteres adivinados
	 * @param letraEscogida Letra escogida por el usuario
	 * @return La palabra en juego con o sin los caracteres adivinados marcados
	 */
	private static String ValidarLetraEnPalabra(String palabraInicial, String palabraEnJuego, String letraEscogida) {

		/**
		 * Dividio la palabra en juego a char, pongo en minuscula la letra
		 * escogida, y pongo contadores y flags para validar si el jugador gano
		 * o no, y las vidas restantes
		 */
		char[] palabraEnJuegoChar = palabraEnJuego.toCharArray();
		letraEscogida = letraEscogida.toLowerCase();
		int cerosCounter = 0;
		boolean correctLetterFlag = false;

		/* ------------------- VALIDACION DE LETRA EN PALABRA ------------------------------ */
		
		/**
		 * busco en el array de letras, la letra escogida, y la reemplazo con 0
		 * para posterior validacion. Si no encuentra nada deja el flag en false
		 * y eso descontara una vida
		 */
		for (int i = 0; i < palabraEnJuego.length(); i++) {

			if (palabraEnJuegoChar[i] == letraEscogida.charAt(0)) {
				palabraEnJuegoChar[i] = 0;
				correctLetterFlag = true;
			}
		}

		/**
		 * Descuento una vida
		 */
		if (!correctLetterFlag){
			CANT_VIDAS = CANT_VIDAS - 1;
			LETRAS_USADAS.add(letraEscogida);
		}

		/* ----------------------------------------------------------------------------------- */
		
		/* ------------------------ VALIDACION DE DERROTA ------------------------------ */
		/**
		 * Valido si la cantidad de vidas es 0, el jugador habra perdido
		 */
		if (CANT_VIDAS == 0) {
			ESTADO = EEstadoJugador.Perdio;
			PALABRAS_NO_ADIVINADAS.add(palabraInicial);
			return new String(palabraEnJuegoChar);
		}
		/* ------------------------------------------------------------------------------ */
		
		/* ------------------------ VALIDACION DE VICTORIA ------------------------------ */
		/**
		 * Dependiendo de la cantidad de ceros se validara si el jugador gano o
		 * mp
		 */
		for (int i = 0; i < palabraEnJuego.length(); i++) {

			if (palabraEnJuegoChar[i] == 0) {
				cerosCounter++;
			}
		}

		if (cerosCounter == palabraEnJuego.length()) {
			ESTADO = EEstadoJugador.Gano;
			PALABRAS_ADIVINADAS.add(palabraInicial);
			return new String(palabraEnJuegoChar);
		}
		/* ------------------------------------------------------------------------------ */

		return new String(palabraEnJuegoChar);
	}
	
	/**
	 * Metodo encargado de mostrar un mensaje en relacion al ESTADO del jugador
	 * @return
	 */
	private static String mensaje() {
		String mensaje = "";
		if (ESTADO == EEstadoJugador.Gano)
			mensaje = "CONTRATULATIONS! :D";
		else if (ESTADO == EEstadoJugador.Perdio)
			mensaje = "GAME OVER! :(";
		return mensaje;
	}
	
	/***
	 * Metodo auxiliar que creara la cantidad de println ingresados por parametro
	 * 
	 * @param limit
	 *            cantidad de println a repetir
	 */
	private static void lineSeparator(int limit) {
		for (int i = 0; i < limit; i++)
			System.out.println();
	}	
	
	/***
	 * Metodo encargado de dibujar las letras ingresadas por el usuario y que no se 
	 * encontraron en la palabra
	 */
	private static void letrasUtilizadas(){
		for (String string : LETRAS_USADAS) {
			System.out.print(string+" ");
		}
	}
	
	/*
	 * Metodo encargado de elegir el idioma de las palabras de la partida
	 */
	private static void Idioma(){
		
		String opcion = "";
		
		while(!(opcion.equals("e")) && !(opcion.equals("E")) && !(opcion.equals("i")) && !(opcion.equals("I"))){
			lineSeparator(50);			
			System.out.println("dMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMb");
			System.out.println("M\t\t\t\t\t\t\t\tM");   
			System.out.println("M\t\tdMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMb\t\tM");
			System.out.println("M\t\tM\t\t\t\tM\t\tM");
			System.out.println("M\t\tM\t     Idioma\t\tM\t\tM");
			System.out.println("M\t\tM\t\t\t\tM\t\tM");
			System.out.println("M\t\tVMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMP\t        M");
			System.out.println("M\t\t\t\t\t\t\t\tM");
			System.out.println("M\t\t\t\t\t\t\t\tM");
			System.out.println("M\t\t\t\t\t\t\t\tM");
			System.out.println("M\t\t\t\t\t\t\t\tM");
			System.out.println("M\t\tdMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMb\t\tM");
			System.out.println("M\t\tM\t\t\t\tM\t\tM");
			System.out.println("M\t\tM\t    (E)spañol\t\tM\t\tM");
			System.out.println("M\t\tM\t    (I)ngles \t\tM\t\tM");
			System.out.println("M\t\tM\t\t\t\tM\t\tM");
			System.out.println("M\t\tVMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMP\t        M");
			System.out.println("M\t\t\t\t\t\t\t\tM");
			System.out.println("M\t\t\t\t\t\t\t\tM");
			System.out.println("M\t\t\t\t\t\t\t\tM");
			System.out.println("M\t\t\t\t\t\t\t\tM");
			System.out.println("M\t\t\t\t\t\t\t\tM");
			System.out.println("M\t\t\t\t\t\t\t\tM");
			System.out.println("M\t\t\t\t\t\t\t\tM");
			System.out.println("VMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMP");
			opcion = SC.nextLine();
			
			switch(opcion){
			case "e":
			case "E":
				IDIOMA = EIdioma.Spanish;
				break;
			case "i":
			case "I":
				IDIOMA = EIdioma.English;
				break;
			}
		}

		System.out.println("\nd===============================================================b");
		System.out.println("M\t\t\t\t\t\t\t\tM");
		System.out.println("M > Idioma configurado con exito! \t\t\t\tM");
		System.out.println("M\t\t\t\t\t\t\t\tM");
		System.out.println("M > Presione cualquier tecla para continuar... \t\t\tM");
		System.out.println("M\t\t\t\t\t\t\t\tM");
		System.out.println("V===============================================================P");
		SC.nextLine();
		System.out.println("\t\t\t\t\t\t\t\t");
		System.out.print(" > Volviendo a la pantalla principal ");
		try {
		Thread.sleep(1000);
		System.out.print(". ");
		Thread.sleep(1000);
		System.out.print(". ");
		Thread.sleep(1000);
		System.out.print(". ");
		Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
}
