import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Dado dado1 = new Dado();
        Dado dado2 = new Dado();
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
                        System.out.println("lanzar dado");
                        int tiradaDado1 = jugador.lanzarDado(dado1);
                        int tiradaDado2 = jugador.lanzarDado(dado2);
                        System.out.println("Valor de los dados lanzados: \n" +
                                "Dado 1 -> " + tiradaDado1 + "\n" +
                                "Dado 2 -> " + tiradaDado2);
                        Posicion nuevaPosicion = new Posicion(tiradaDado1,tiradaDado2);
                        jugador.setPosicion(nuevaPosicion);
                        break;
                    default:
                        System.err.println("No has introducido una opción válida. Vuelve a intentarlo");
                }

            }

        }catch (PuertaColocadaEnLocalizacionIncorrecta | JugadorColocadoIncorrectamente e){
            System.out.println(e.getMessage());
        }catch (InputMismatchException i){
            System.out.println("No has introducido un número");
        }

    }
}