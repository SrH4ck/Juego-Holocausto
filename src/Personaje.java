public class Personaje {

    //Propiedades
    private String nombre;
    Posicion posicion;


    public Personaje(Posicion posicion){
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

    public void setPosicion(Posicion pos) {
        this.posicion = pos;
    }
}
