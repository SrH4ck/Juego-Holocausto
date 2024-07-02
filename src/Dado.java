import java.util.concurrent.ThreadLocalRandom;
public class Dado {
    private int caras;

    public Dado(){
        this.caras = 9;
    }
    public int lanzamiento(){
        return ThreadLocalRandom.current().nextInt(1,this.caras );
    }
}
