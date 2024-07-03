package exceptions;

public class JugadorColocadoIncorrectamente extends RuntimeException{
    public JugadorColocadoIncorrectamente(String mensajeError){
        super(mensajeError);
    }
}
