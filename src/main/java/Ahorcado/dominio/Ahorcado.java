package Ahorcado.dominio;
import java.util.Scanner;
import java.util.Random;


public class Ahorcado {

    public static void main(String[] args) {
        int intentosTotales = 0;
        int intentos = 0;
        int aciertos = 0;
        int puntaje = 0;

        // leer por teclado:

        Scanner teclado = new Scanner(System.in);
        teclado.useDelimiter("\n");

        char resp;

        // Random para palabra al azar.

        Random rnd = new Random();


        // Creamos unas palabras y le asignamos una aleatoria a una varibale

        String [] arrayPalabras = {"casa","lapiz", "perro",
                "cadena","variable","palabra","software","teclado"};

        do {

            // Desguazamos la palabra y la guardamos en un array de caracteres
            int alea = rnd.nextInt(arrayPalabras.length);
            char[] desguazada = dividir(arrayPalabras[alea]);
            char[] copia = dividir(arrayPalabras[alea]); // Algo auxiliar para mas tarde

            // Array para pintar mierdecillas en pantalla(Guiones o letras vamos)
            char[] tusRespuestas = new char[desguazada.length];

            // Rellenamos palabras ocn guiones
            for(int i = 0; i < tusRespuestas.length; i++){
                tusRespuestas[i] = '_';
            }

            // Empezamos a jugar el ahorcado
            System.out.println("Adivina la palabra!");

            // Mientras que no nos pasemos con los intentos y no la acertemos...
            System.out.println("\n Elije una dificultad: \n 1) --- Facil. \n 2) --- Media.\n 3) ---  Dificil \n");
            int dificultad = teclado.nextInt();

            if (dificultad == 1) {
                intentosTotales = 4;
            } else if (dificultad == 2) {
                intentosTotales = 6;
            } else if (dificultad == 3) {
                intentosTotales = 8;
            } else {
                System.out.println("\nElija una opcion valida \n");
            }

            while(intentos < intentosTotales && aciertos != tusRespuestas.length){
                imprimeOculta(tusRespuestas);
                // Preguntamos por teclado

                System.out.println("\nIntroduce una letra: ");
                resp = teclado.next().toLowerCase().charAt(0);
                // Recorremos el array y comprobamos si se ha producido un acierto
                for(int i = 0; i < desguazada.length; i++){
                    if(desguazada[i]==resp){
                        tusRespuestas[i] = desguazada[i];
                        desguazada[i] = ' ';
                        aciertos++;
                    }
                }
                intentos++;
            }
            // Si hemos acertado todas imprimimos un mensahe
            if(aciertos == tusRespuestas.length){
                System.out.print("\nFelicidades!! has acertado la palabra: ");
                if (dificultad == 1) {
                    puntaje += 1;
                } else if (dificultad == 2) {
                    puntaje += 2;
                } else if (dificultad == 3) {
                    puntaje += 4;
                }
                imprimeOculta(tusRespuestas);
                System.out.print("\n\t\t\t Tu puntaje es: " + puntaje + "\n");
            }
            // Si no otro
            else{
                System.out.print("\n La palabra era: ");
                for(int i = 0; i < copia.length; i++){
                    System.out.print(copia[i] + " ");
                }
                System.out.print("\n\t\t\t Tu puntaje es: " + puntaje + "\n");
            }
            // Reseteamos contadores
            intentos = 0;
            aciertos = 0;
            // Volvemos a preguntarle al usuario si quiere volver a perder el tiempo

            resp = pregunta("\n\nQuieres volver a jugar?",teclado);
        }while(resp != 'n');

    }

    /*
     * Esto divide el String en un array de caracteres
     * retorna un arreglo de letras.
     */
    private static char[] dividir(String palAzar){
        char[] letras;
        letras = new char[palAzar.length()];
        for(int i = 0; i < palAzar.length(); i++){
            letras[i] = palAzar.charAt(i);
        }
        return letras;
    }

    /*
     * Esto imprime la palabra con espacios
     * @param tusRespuestas el array de caracteres
     */
    private static void imprimeOculta(char[] tusRespuestas){

        for(int i = 0; i < tusRespuestas.length; i++){
            System.out.print(tusRespuestas[i] + " ");
        }
    }

    /*
     * Esto nos pregunta si queremos volver a jugar y comprueba los caracteres
     * introducidos
     * texto para mostrar al usuario
     * caracter de respuesta (s/n)
     */
    public static char pregunta(String men, Scanner teclado) {
        char resp;
        System.out.println(men + " (s/n)");
        resp = teclado.next().toLowerCase().charAt(0);
        while (resp != 's' && resp != 'n') {
            System.out.println("Error! solo se admite S o N");
            resp = teclado.next().toLowerCase().charAt(0);
        }
        return resp;
    }

}

