public class Hadron extends Personaje{


    private boolean visible;
    public Hadron(Posicion posicion) {
        super(posicion);
        this.visible = true;
    }
    public boolean cambioVisibilidad(){
        if (this.visible)this.visible=false;
        else this.visible=true;
        return this.visible;
    }
}
