package process;

import beans.Habitacion;
import beans.Posicion;

public class Juego {

    final static int SALIR_DEL_JUEGO = 0;
    final static int LANZAR_DADO = 1;
    public Juego(){

    }
    public static void pintarHabitacion(Habitacion habitacion){
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
    public static void pintarMenu(){
        System.out.println("-------------- HOLOCAUSTO H (HADRON) --------------");
        System.out.println("[1] LANZAR DADO DE MOVIMIENTO");
        System.out.println("[0] SALIR DEL JUEGO");
        System.out.println("---------------------------------------------------");
    }
}
