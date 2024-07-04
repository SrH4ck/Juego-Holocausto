package process;

import beans.Dado;
import beans.Habitacion;
import beans.Jugador;
import beans.Posicion;
import exceptions.JugadorColocadoIncorrectamente;
import exceptions.JugadorGana;
import exceptions.PuertaColocadaEnLocalizacionIncorrecta;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Juego {

    final static int SALIR_DEL_JUEGO = 0;
    final static int LANZAR_DADO = 1;
    private Dado dado;
    private final Scanner sc;
    private Habitacion habitacion = new Habitacion();
    private Jugador jugador;


    public Juego(){
        this.dado = new Dado();
        this.sc =  new Scanner(System.in);
    }
    private void pintarHabitacion(Habitacion habitacion){
            for (int fila = 0; fila < Habitacion.ALTO; fila++){
                for (int colum = 0; colum < Habitacion.ANCHO; colum++){
                    Posicion posicionActual = new Posicion(colum, fila);
                    if (habitacion.esPuerta(posicionActual)) System.out.print("   ");
                     else if  (colum == 0 || colum == Habitacion.ANCHO -1) System.out.print(" | "); // paredes de derecha e izquierda
                    else if (fila == 0 || fila == Habitacion.ALTO -1) System.out.print(" = "); //Dibujo de arriba y abajo
                    else if (fila == habitacion.getJugador().getPosicion().getPosY() && colum == habitacion.getJugador().getPosicion().getPosX()){
                        System.out.print(" @ ");
                    }
                    else System.out.print("   "); //Mapa
                    if (colum == Habitacion.ANCHO -1) System.out.println(); // siguiente linea
                }
            }
    }
    private void pintarMenu(){
        System.out.println("-------------- HOLOCAUSTO H (HADRON) --------------");
        System.out.println("[1] LANZAR DADO DE MOVIMIENTO");
        System.out.println("[0] SALIR DEL JUEGO");
        System.out.println("---------------------------------------------------");
    }

    public void ejecutar() {
        // Creamos la habitación

        //Posicionamos la puerta de entrada
        habitacion.generarPuertaEntrada();
        //Posicionamos la puerta de salida
        habitacion.generarPuertaSalida();

        //Posicionamos al jugador
        Posicion colocarJugadorPuertaEntrada = new Posicion(habitacion.getPuertaEntrada().getPosX(),habitacion.getPuertaEntrada().getPosY()-1);
        this.jugador = new Jugador(colocarJugadorPuertaEntrada);
        habitacion.setJugador(jugador);

        try {
            jugar();
        }catch (PuertaColocadaEnLocalizacionIncorrecta | JugadorColocadoIncorrectamente | JugadorGana e){
            System.out.println(e.getMessage());
        }catch (InputMismatchException i){
            System.out.println("No has introducido un número");
        }
    }

    private void jugar() {
        int accionJuego =-1;
        while (accionJuego != Juego.SALIR_DEL_JUEGO){
            pintarHabitacion(habitacion);
            pintarMenu();

            //Recogemos la opción deseada por el usuario
            accionJuego = sc.nextInt();

            switch (accionJuego){
                case Juego.SALIR_DEL_JUEGO:
                    System.out.println("Saliendo del juego...");
                    break;
                case Juego.LANZAR_DADO:
                    turnoJugador();
                    break;
                default:
                    System.err.println("No has introducido una opción válida. Vuelve a intentarlo");
                    break;
            }
        }
    }

    private void turnoJugador() {
        //Lanzar el dado de movimiento
        int movimientosPosibles = jugador.lanzarDado(dado);

        //Mensajes informativos
        mensajeInformativoPrimeraDecision(movimientosPosibles);

        // Leemos número del usuario
        int numMovX = sc.nextInt();

        //verificamos que el número de movimientos que quiere el usuario no es mayor al permitido
        if (Math.abs(numMovX) > movimientosPosibles){
            System.out.println("Has introducido un movimiento mayor al permitido");
        }else{

            //si no es mayor realizamos el decremento de posibles movimientos
            movimientosPosibles -= Math.abs(numMovX);

            //Mensajes informativos
            mensajeInformativoSegundaDecision(movimientosPosibles);

            // Leemos número del usuario
            int numMovY = sc.nextInt();

            //verificamos que el número de movimientos que quiere el usuario no es mayor al permitido
            if (Math.abs(numMovY) > movimientosPosibles){

                System.out.println("\nHas introducido un movimiento mayor al permitido\n");

            }else {
                posicionarJugador(numMovX,numMovY);
            }
        }
    }

    private void posicionarJugador(int numMovX, int numMovY) {
        //Asignamos los valores para posicionar al jugador en su nueva posición
        int nuevaPosicionX = jugador.getPosicion().getPosX() + numMovX;
        int nuevaPosicionY = jugador.getPosicion().getPosY() + numMovY;
        verficiarPosicionCorrectaJugador(nuevaPosicionX,nuevaPosicionY);
    }

    private void verficiarPosicionCorrectaJugador(int nuevaPosicionX, int nuevaPosicionY) {
        /* Validamos que la posición esté dentro de los parámetros de anchura y altura del mapa,
           también verificamos si la nueva posición coincide con la salida para poder moverse hasta
           ella y ganar
         */
        if ((nuevaPosicionY > 0 && nuevaPosicionX > 0) && (nuevaPosicionY < Habitacion.ALTO-1 && nuevaPosicionX < Habitacion.ANCHO -1) ||
                (habitacion.getPuertaSalida().getPosX() == nuevaPosicionX) &&
                        habitacion.getPuertaSalida().getPosY() == nuevaPosicionY){

            jugador.moverse(nuevaPosicionX,nuevaPosicionY);

            //Si el jugador ha entrado en la puerta de salida lanzamos la excepción
            if (jugadorGana()) throw new JugadorGana("Has ganado!!");

        }else{
            System.out.println("Posición no posible. ");
        }
    }

    private boolean jugadorGana() {
        if (jugador.getPosicion().getPosX() == habitacion.getPuertaSalida().getPosX() &&
                jugador.getPosicion().getPosY() == habitacion.getPuertaSalida().getPosY()){
            return true;
        }
        return false;
    }

    private void mensajeInformativoSegundaDecision(int movimientosPosibles) {
        System.out.println("Números de movimiento en horizontal " + movimientosPosibles);
        System.out.println("Números de movimiento en vertical " + movimientosPosibles);
        System.out.println("\nCuantos movimientos quieres gastar para moverte en vertical número positivo\n" +
                "para moverte hacia abajo, número negativo para moverte hacia arriba");
    }

    private void mensajeInformativoPrimeraDecision(int movimientosPosibles) {
        System.out.println("Valor del dado lanzado: \n" +
                "beans.Dado --> " + movimientosPosibles);
        System.out.println("Números de movimiento en horizontal " + movimientosPosibles);
        System.out.println("Números de movimiento en vertical " + movimientosPosibles);
        System.out.println("\nCuantos movimientos quieres gastar para moverte en horizontal número positivo \n" +
                "para moverte  a la derecha , número negativo para moverte a la izquierda");
    }
}
