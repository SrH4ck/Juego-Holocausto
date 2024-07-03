package process;

import beans.*;
import exceptions.JugadorColocadoIncorrectamente;
import exceptions.JugadorGana;
import exceptions.PuertaColocadaEnLocalizacionIncorrecta;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Dado dado = new Dado();
        int accionJuego = -1;
        Scanner sc = new Scanner(System.in);
        try {
            // Creamos la habitación
            Habitacion habitacion = new Habitacion();

            //Asignamos la posición de las puertas en la habitación
            Posicion puertaEntrada = new Posicion(1,Habitacion.ALTO-1);
            Posicion puertaSalida = new Posicion(Habitacion.ANCHO-1,1);

            // se resta 1 para que se coloque justamente delante y "dentro"
            Posicion jugadorPuertaEntrada = new Posicion(puertaEntrada.getPosX(), puertaEntrada.getPosY()-1);

            //Posicionamos al jugador
            Jugador jugador = new Jugador(jugadorPuertaEntrada);
            habitacion.setJugador(jugador);

            //Posicionamos la puerta de entrada
            habitacion.setPuertaEntrada(puertaEntrada);

            //Posicionamos la puerta de salida
            habitacion.setPuertaSalida(puertaSalida);

            while (accionJuego != Juego.SALIR_DEL_JUEGO){
                Juego.pintarHabitacion(habitacion);
                Juego.pintarMenu();

                //Recogemos la opción deseada por el usuario
                accionJuego = sc.nextInt();

                switch (accionJuego){
                    case Juego.SALIR_DEL_JUEGO:
                        System.out.println("Saliendo del juego...");
                        break;
                    case Juego.LANZAR_DADO:
                        //Lanzar el dado de movimiento
                        int movimientosPosibles = jugador.lanzarDado(dado);

                        //Mensajes informativos
                        System.out.println("Valor del dado lanzado: \n" +
                                "beans.Dado --> " + movimientosPosibles);
                        System.out.println("Números de movimiento en horizontal " + movimientosPosibles);
                        System.out.println("Números de movimiento en vertical " + movimientosPosibles);
                        System.out.println("\nCuantos movimientos quieres gastar para moverte en horizontal número positivo \n" +
                                "para moverte  a la derecha , número negativo para moverte a la izquierda");

                        // Leemos número del usuario
                        int numMovX = sc.nextInt();

                        //verificamos que el número de movimientos que quiere el usuario no es mayor al permitido
                        if (Math.abs(numMovX) > movimientosPosibles){

                            System.out.println("Has introducido un movimiento mayor al permitido");

                        }else{

                            //si no es mayor realizamos el decremento de posibles movimientos
                            movimientosPosibles -= Math.abs(numMovX);

                            //Mensajes informativos
                            System.out.println("Números de movimiento en horizontal " + movimientosPosibles);
                            System.out.println("Números de movimiento en vertical " + movimientosPosibles);
                            System.out.println("\nCuantos movimientos quieres gastar para moverte en vertical número positivo\n" +
                                    "para moverte hacia abajo, número negativo para moverte hacia arriba");

                            // Leemos número del usuario
                            int numMovY = sc.nextInt();

                            //verificamos que el número de movimientos que quiere el usuario no es mayor al permitido
                            if (Math.abs(numMovY) > movimientosPosibles){

                                System.out.println("\nHas introducido un movimiento mayor al permitido\n");

                            }else {
                                //Asignamos los valores para posicionar al jugador en su nueva posición
                                int nuevaPosicionX = jugador.getPosicion().getPosX() + numMovX;
                                int nuevaPosicionY = jugador.getPosicion().getPosY() + numMovY;

                                /* Validamos que la posición esté dentro de los parámetros de anchura y altura del mapa,
                                también verificamos si la nueva posición coincide con la salida para poder moverse hasta
                                ella y ganar
                                 */
                                if ((nuevaPosicionY > 0 && nuevaPosicionX > 0) && (nuevaPosicionY < Habitacion.ALTO-1 && nuevaPosicionX < Habitacion.ANCHO -1) ||
                                        (habitacion.getPuertaSalida().getPosX() == nuevaPosicionX) &&
                                                habitacion.getPuertaSalida().getPosY() == nuevaPosicionY){

                                    jugador.moverse(nuevaPosicionX,nuevaPosicionY);

                                    //Si el jugador ha entrado en la puerta de salida lanzamos la excepción
                                    if (jugador.haSalido()) throw new JugadorGana("Has ganado!!");

                                }else{
                                    System.out.println("Posición no posible. ");
                                }
                            }
                        }
                        break;
                    default:
                        System.err.println("No has introducido una opción válida. Vuelve a intentarlo");
                        break;
                }
            }

        }catch (PuertaColocadaEnLocalizacionIncorrecta | JugadorColocadoIncorrectamente | JugadorGana e){
            System.out.println(e.getMessage());
        }catch (InputMismatchException i){
            System.out.println("No has introducido un número");
        }

    }
}