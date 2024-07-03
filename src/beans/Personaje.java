package beans;

public class Personaje extends ObjetoJuego{

    public Personaje(Posicion posicion){
        super(posicion);
    }

    public void moverse(int nuevaPosicionX, int nuevaPosicionY) {
        Posicion nuevaPosicion = new Posicion(nuevaPosicionX,nuevaPosicionY);
        setPosicion(nuevaPosicion);
    }
}
