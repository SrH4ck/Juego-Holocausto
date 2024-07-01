public class Posicion {
    private int posX;
    private int posY;

    public Posicion(int posx, int posy){
        this.posX = posx; //columnas
        this.posY = posy; // filas

    }


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
