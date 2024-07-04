package beans;

public class Objeto extends ObjetoJuego{
    private int peso = 0;

    public Objeto(Posicion posicion){
        super(posicion);
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
