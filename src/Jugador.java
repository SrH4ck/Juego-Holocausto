public class Jugador extends Personaje{

    public Jugador(Posicion posicion) {
        super(posicion);
    }
    public int lanzarDado(Dado dado){
        return dado.lanzamiento();
    }
}
