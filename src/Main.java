public class Main {
    public static void main(String[] args) {
        try {
            Posicion posInicial = new Posicion(3,0);
            //Creamos la habitación
            Habitacion habitacion = new Habitacion();

            //Posicion de las puerta de entrada y salida
            Posicion puertaEntrada = new Posicion(4,5);
            habitacion.setPuertaEntrada(puertaEntrada);
            Posicion puertaSalida = new Posicion(0,1);
            habitacion.setPuertaSalida(puertaSalida);

            Personaje personaje = new Personaje(posInicial);
            Juego.pintarHabitacion(habitacion);
        }catch (PuertaColocadaEnLocalizacionIncorrecta p){
            System.out.println(p.getMessage());
        }

    }
}