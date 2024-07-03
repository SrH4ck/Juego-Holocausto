package beans;

public class ObjetoJuego {
    //Propiedades
    private String nombre;
    private Posicion posicion;
    private String LETRA_MAPA="";


    public ObjetoJuego(Posicion posicion){
        this.posicion = posicion;

    }

    //GETTERS Y SETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public String getLETRA_MAPA() {
        return LETRA_MAPA;
    }

    public void setLETRA_MAPA(String LETRA_MAPA) {
        this.LETRA_MAPA = LETRA_MAPA;
    }
}
