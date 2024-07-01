public class Main {
    public static void main(String[] args) {
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


            Juego.pintarHabitacion(habitacion);
        }catch (PuertaColocadaEnLocalizacionIncorrecta | JugadorColocadoIncorrectamente e){
            System.out.println(e.getMessage());
        }

    }
}