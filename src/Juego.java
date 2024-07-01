public class Juego {

    public Juego(){

    }
    public static void pintarHabitacion(Habitacion habitacion){
            for (int fila = 0; fila < Habitacion.ALTO; fila++){
                for (int colum = 0; colum < Habitacion.ANCHO; colum++){
                    if (colum == habitacion.getPuertaSalida().getPosX() && fila==habitacion.getPuertaSalida().getPosY()){ //puerta de salida
                        System.out.print(" ");
                    } else if (fila == habitacion.getPuertaEntrada().getPosY() && colum == habitacion.getPuertaEntrada().getPosX())  { //puerta de entrada
                        System.out.print(" ");
                    } else if  (colum == 0 || colum == Habitacion.ANCHO -1) System.out.print("|"); // paredes de derecha e izquierda
                    else if (fila == 0 || fila == Habitacion.ALTO -1) System.out.print("="); //Dibujo de arriba y abajo
                    else if (fila == habitacion.getJugador().getPosicion().getPosY() && colum == habitacion.getJugador().getPosicion().getPosX()){
                        System.out.print("@");
                    }
                    else System.out.print(" "); //Mapa
                    if (colum == Habitacion.ANCHO -1) System.out.println(); // siguiente linea

                }

            }
    }
}
