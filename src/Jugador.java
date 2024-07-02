public class Jugador extends Personaje{

    public Jugador(Posicion posicion) {
        super(posicion);
    }
    public int lanzarDado(Dado dado){
        return dado.lanzamiento();
    }

    public void moverse(int nuevaPosicionX, int nuevaPosicionY) {
        Posicion nuevaPosicion = new Posicion(nuevaPosicionX,nuevaPosicionY);
        setPosicion(nuevaPosicion);
    }
    public boolean haSalido(){
        Habitacion hab = new Habitacion();
        if (getPosicion().esIgual(hab.getPuertaSalida())){
            return true;
        }
        return false;
    }
}
