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


            while (accionJuego != 0){
                Juego.pintarHabitacion(habitacion);
                Juego.pintarMenu();
                accionJuego = sc.nextInt();
                if (accionJuego != 0 && accionJuego !=1){
                    accionJuego = -1;
                    System.err.println("No has introducido correctamente la acción, vuelve a intentarlo");
                }
                if (accionJuego == 1){
                    //Lanzar el dado de movimiento
                    System.out.println("lanzar dado");
                    int tiradaDado1 = jugador.lanzarDado(dado1);
                    int tiradaDado2 = jugador.lanzarDado(dado2);
                    Posicion nuevaPosicion = new Posicion(tiradaDado1,tiradaDado2);
                    jugador.setPosicion(nuevaPosicion);
                }


            }

        }catch (PuertaColocadaEnLocalizacionIncorrecta | JugadorColocadoIncorrectamente e){
            System.out.println(e.getMessage());
        }catch (InputMismatchException i){
            System.out.println("No has introducido un número");
        }

    }
}