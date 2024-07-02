public class Habitacion {

    // asignamos el tamaño del mapa modificando estas dos propiedades
    public static final int ANCHO=20;
    public static final int ALTO=20;

    private Posicion puertaEntrada;
    private static Posicion puertaSalida;
    private Jugador jugador;
    private Personaje personaje;


    public Habitacion(){

    }

    public Posicion getPuertaEntrada() {
        return puertaEntrada;
    }

    public void setPuertaEntrada(Posicion puertaEntrada) {
        //Valores permitidos: eje Y = 9 y eje x  = 1-8
        if ((puertaEntrada.getPosY() == ALTO-1) && (puertaEntrada.getPosX() != 0 && puertaEntrada.getPosX() != ANCHO -1))
            /*Solo se pone la puerta de entrada en la parte baja de la habitacion.
            * Si queremos poner puerta arriba debemos modifica la primera condicion del eje Y pudiendo asignar un valor de Y = 0*/
            this.puertaEntrada = puertaEntrada;
        else throw new PuertaColocadaEnLocalizacionIncorrecta("ERROR!!\n" +
                "Puerta de Entrada colocada en localización incorrecta\n" +
                "Valores permitidos: eje Y = "+(Habitacion.ALTO -1) + "y eje x  = 1-"+(Habitacion.ALTO -2));
    }

    public Posicion getPuertaSalida() {
        return puertaSalida;
    }

    public void setPuertaSalida(Posicion puertaSalida) {
        //Valores permitidos: 1-8 en eje Y eje X 9 ó 0
        if ((ALTO-1 != puertaSalida.getPosY() && puertaSalida.getPosY()!=0) && (puertaSalida.getPosX() == 0 || puertaSalida.getPosX() == ANCHO -1))
            Habitacion.puertaSalida = puertaSalida;
        else throw new PuertaColocadaEnLocalizacionIncorrecta("ERROR!!\n" +
                "Puerta de salida colocada en localización incorrecta\n" +
                "Valores permitidos: 1-"+ (Habitacion.ALTO -2) +" en eje Y eje X "+(Habitacion.ANCHO -1) +" ó 0");
    }
    public void setJugador(Jugador j){
        if ((j.getPosicion().getPosX() != 0 && j.getPosicion().getPosX() != ANCHO -1) && (j.getPosicion().getPosY() != 0 && j.getPosicion().getPosY() != ALTO -1)){
            this.jugador = j;
        }else throw new JugadorColocadoIncorrectamente("Jugador Colocado en posición incorrecta\n" +
                "Valores Correctos: X = 1-" + (Habitacion.ANCHO -2) +" Y = 1-"+ (Habitacion.ALTO -2));

    }
    public void setPersonaje(Personaje p){
        this.personaje = p;
    }

    public Jugador getJugador() {
        return jugador;
    }
    public boolean esPuerta(Posicion p){
        if (p.esIgual(puertaEntrada) || p.esIgual(puertaSalida))return true;
        return false;
    }
}
