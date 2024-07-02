import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Dado dado = new Dado();
        int accionJuego = -1;
        Scanner sc = new Scanner(System.in);
        try {

            Posicion posInicial = new Posicion(1,1);
            Jugador jugador = new Jugador(posInicial);
            Habitacion habitacion = new Habitacion();
            habitacion.setJugador(jugador);
            //Creamos la habitación


            //Posicion de las puerta de entrada y salida
            Posicion puertaEntrada = new Posicion(1,9);
            habitacion.setPuertaEntrada(puertaEntrada);
            Posicion puertaSalida = new Posicion(9,1);
            habitacion.setPuertaSalida(puertaSalida);


            while (accionJuego != Juego.SALIR_DEL_JUEGO){
                Juego.pintarHabitacion(habitacion);
                Juego.pintarMenu();
                accionJuego = sc.nextInt();
                switch (accionJuego){
                    case Juego.SALIR_DEL_JUEGO:
                        System.out.println("Saliendo del juego...");
                        break;
                    case Juego.LANZAR_DADO:
                        //Lanzar el dado de movimiento
                        int tiradaDado = jugador.lanzarDado(dado);
                        System.out.println("Valor del dado lanzado: \n" +
                                "Dado --> " + tiradaDado);
                        System.out.println("Números de movimiento en horizontal " + tiradaDado);
                        System.out.println("Números de movimiento en vertical" + tiradaDado);
                        System.out.println("Cuantos movimientos quieres gastar para moverte en horizontal");
                        int numMovX = sc.nextInt();
                        if (numMovX > tiradaDado){
                            System.out.println("Has introducido un movimiento mayor al permitido");
                        }else{
                            tiradaDado -= numMovX;
                            System.out.println("Números de movimiento en el EJE X " + tiradaDado);
                            System.out.println("Números de movimiento en el EJE Y " + tiradaDado);
                            System.out.println("Cuantos movimientos quieres gastar para moverte en vertical");
                            int numMovY = sc.nextInt();
                            if (numMovY > tiradaDado){
                                System.out.println("\nHas introducido un movimiento mayor al permitido\n");
                            }else {
                                int nuevaPosicionX = jugador.getPosicion().getPosX() + numMovX;
                                int nuevaPosicionY = jugador.getPosicion().getPosY() + numMovY;
                                Posicion nuevaPosicion = new Posicion(nuevaPosicionX,nuevaPosicionY);
                                jugador.setPosicion(nuevaPosicion);
                            }
                        }
                        break;
                    default:
                        System.err.println("No has introducido una opción válida. Vuelve a intentarlo");
                        break;
                }
            }

        }catch (PuertaColocadaEnLocalizacionIncorrecta | JugadorColocadoIncorrectamente e){
            System.out.println(e.getMessage());
        }catch (InputMismatchException i){
            System.out.println("No has introducido un número");
        }

    }
}