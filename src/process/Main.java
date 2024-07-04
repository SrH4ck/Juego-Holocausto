package process;

import beans.*;
import exceptions.JugadorColocadoIncorrectamente;
import exceptions.JugadorGana;
import exceptions.PuertaColocadaEnLocalizacionIncorrecta;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.ejecutar();
    }
}