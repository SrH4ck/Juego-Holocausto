public class Habitacion {

    public static final int ANCHO=10;
    public static final int ALTO=10;
    Posicion puertaEntrada;
    Posicion puertaSalida;
    private Personaje jugador;


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
                "Valores permitidos: eje Y = 9 y eje x  = 1-8");
    }

    public Posicion getPuertaSalida() {
        return puertaSalida;
    }

    public void setPuertaSalida(Posicion puertaSalida) {
        //Valores permitidos: 1-8 en eje Y eje X 9 ó 0
        if ((ALTO-1 != puertaSalida.getPosY() && puertaSalida.getPosY()!=0) && (puertaSalida.getPosX() == 0 || puertaSalida.getPosX() == ANCHO -1))this.puertaSalida = puertaSalida;
        else throw new PuertaColocadaEnLocalizacionIncorrecta("ERROR!!\n" +
                "Puerta de salida colocada en localización incorrecta\n" +
                "Valores permitidos: 1-8 en eje Y eje X 9 ó 0");
    }
    public void setJugador(Personaje j){
        if ((j.posicion.getPosX() != 0 && j.posicion.getPosX() != ANCHO -1) && (j.posicion.getPosY() != 0 && j.posicion.getPosY() != ALTO -1)){
            this.jugador = j;
        }else throw new JugadorColocadoIncorrectamente("Jugador Colocado en posición incorrecta\n" +
                "Valores Correctos: X = 1-8 Y = 1-8");

    }

    public Personaje getJugador() {
        return jugador;
    }
}
