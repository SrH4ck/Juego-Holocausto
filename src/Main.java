import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Dado dado = new Dado();
        int accionJuego = -1;
        Scanner sc = new Scanner(System.in);
        try {
            Posicion puertaEntrada = new Posicion(1,9);
            Posicion jugadorPuertaEntrada = new Posicion(8,1);

            Jugador jugador = new Jugador(jugadorPuertaEntrada);

            Habitacion habitacion = new Habitacion();
            habitacion.setJugador(jugador);

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
                        System.out.println("Números de movimiento en vertical " + tiradaDado);
                        System.out.println("\nCuantos movimientos quieres gastar para moverte en horizontal número positivo \n" +
                                "para moverte  a la derecha , número negativo para moverte a la izquierda");
                        int numMovX = sc.nextInt();
                        if (Math.abs(numMovX) > tiradaDado){
                            System.out.println("Has introducido un movimiento mayor al permitido");
                        }else{
                            tiradaDado -= Math.abs(numMovX);
                            System.out.println("Números de movimiento en horizontal " + tiradaDado);
                            System.out.println("Números de movimiento en vertical " + tiradaDado);
                            System.out.println("\nCuantos movimientos quieres gastar para moverte en vertical número positivo\n" +
                                    "para moverte hacia abajo, número negativo para moverte hacia arriba");
                            int numMovY = sc.nextInt();
                            if (Math.abs(numMovY) > tiradaDado){
                                System.out.println("\nHas introducido un movimiento mayor al permitido\n");
                            }else {
                                int nuevaPosicionX = jugador.getPosicion().getPosX() + numMovX;
                                int nuevaPosicionY = jugador.getPosicion().getPosY() + numMovY;
                                if ((nuevaPosicionY > 0 && nuevaPosicionX > 0) && (nuevaPosicionY < Habitacion.ALTO-1 && nuevaPosicionX < Habitacion.ANCHO -1) ||
                                        (habitacion.getPuertaSalida().getPosX() == nuevaPosicionX) &&
                                                habitacion.getPuertaSalida().getPosY() == +nuevaPosicionY){
                                    jugador.moverse(nuevaPosicionX,nuevaPosicionY);
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

        }catch (PuertaColocadaEnLocalizacionIncorrecta | JugadorColocadoIncorrectamente  | JugadorGana e){
            System.out.println(e.getMessage());
        }catch (InputMismatchException i){
            System.out.println("No has introducido un número");
        }

    }
}