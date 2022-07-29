package com.Ahorcado.app;

import com.Ahorcado.dominio.Ahorcado;
import com.Ahorcado.dominio.Palabra;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ahorcadoApp {

    public static void main(String[] args) {
        int intentosTotales = 0, intentos = 0, aciertos = 0, puntaje = 0, dificultad, opcion;
        String complejidad;

        // leer por teclado:

        Scanner teclado = new Scanner(System.in);
        teclado.useDelimiter("\n");

        char resp;

        // Random para palabra al azar.

        Random numeroAleatorio = new Random();

        // creando las palabras para meterlas en la lista.

        Palabra palabra = new Palabra("lapiz");
        Palabra palabra2 = new Palabra("variable");
        Palabra palabra3 = new Palabra("papel");
        Palabra palabra4 = new Palabra("informatica");
        Palabra palabra5 = new Palabra("palabra");
        Palabra palabra6 = new Palabra("correspondientemente");
        Palabra palabra7 = new Palabra("esternocleidomastoideo");
        Palabra palabra8 = new Palabra("constante");
        Palabra palabra9 = new Palabra("paciente");
        Palabra palabra10 = new Palabra("electroencefalografo");
        Palabra palabra11 = new Palabra("africanismos");
        Palabra palabra12 = new Palabra("azucar");
        Palabra palabra13 = new Palabra("sol");
        Palabra palabra14 = new Palabra("luna");

        // Creamos un objeto ahorcado.

        Ahorcado ahorcado = new Ahorcado();

        ahorcado.agregarPalabra(palabra);
        ahorcado.agregarPalabra(palabra2);
        ahorcado.agregarPalabra(palabra3);
        ahorcado.agregarPalabra(palabra4);
        ahorcado.agregarPalabra(palabra5);
        ahorcado.agregarPalabra(palabra6);
        ahorcado.agregarPalabra(palabra7);
        ahorcado.agregarPalabra(palabra8);
        ahorcado.agregarPalabra(palabra9);
        ahorcado.agregarPalabra(palabra10);
        ahorcado.agregarPalabra(palabra11);
        ahorcado.agregarPalabra(palabra12);
        ahorcado.agregarPalabra(palabra13);
        ahorcado.agregarPalabra(palabra14);

         //              |       Revisando la lista de palabras :D       |
         // ahorcado.getPalabras().forEach(word -> System.out.println(word.getCaracteres()));

        System.out.println("\t\t\t Adivina la palabra!");

        do {
            // Empezamos a jugar el ahorcado
            System.out.println("\t\t... MENU ... \n 1.) Jugar \n 2.) Mostrar puntaje \n 3.) Agregar palabras \n 4.) Cambiar Palabras \n 5:) Salir");
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1: {
                    do {
                        System.out.println("\n Elije una dificultad: \n 1) --- Facil. \n 2) --- Media.\n 3) ---  Dificil " +
                                "\n #) --- Sorprendeme \n");
                        dificultad = teclado.nextInt();

                        List<Palabra> listaDePalabras = new ArrayList<>();

                        //                                  Dificultades
                        if (dificultad == 1) {
                            intentosTotales = 4;
                            listaDePalabras = ahorcado.palabrasPorDificultad(1);
                            complejidad = "Facil";
                        } else if (dificultad == 2) {
                            intentosTotales = 6;
                            listaDePalabras = ahorcado.palabrasPorDificultad(2);
                            complejidad = "Media";
                        } else if (dificultad == 3) {
                            intentosTotales = 8;
                            listaDePalabras = ahorcado.palabrasPorDificultad(3);
                            complejidad = "Dificil";
                        } else {
                            System.out.println("\n\t Bienvenido al modo por defecto\n ");
                            int juegoAleatorio = numeroAleatorio.nextInt(3 - 1) + 1;
                            intentosTotales = 4;
                            listaDePalabras = ahorcado.palabrasPorDificultad(juegoAleatorio);
                            complejidad = "Aleatoria";
                        }

                        if (listaDePalabras.isEmpty()) {
                            System.out.println("No hay ninguna palabra en la lista." +
                                    " \n Porfavor agregar palabras de nivel [" + complejidad + "] \n");
                            resp = 'n';
                        } else {
                            //     ---- |  Dividimos la palabra y la guardamos en un array de caracteres.  | ----

                            int alea = numeroAleatorio.nextInt(listaDePalabras.size());

                            char[] palabraDividida = dividir(String.valueOf(listaDePalabras.get(alea).getCaracteres()));
                            char[] copia = dividir(String.valueOf(listaDePalabras.get(alea).getCaracteres()));

                            //     ---- |   Array para pintar en pantalla (Guiones o letras vamos).  | ----

                            char[] tusRespuestas = new char[palabraDividida.length];

                            // Rellenamos palabras con guiones

                            for (int i = 0; i < tusRespuestas.length; i++) {
                                tusRespuestas[i] = '_';
                            }

                            while (intentos < intentosTotales && aciertos != tusRespuestas.length) {
                                imprimeOculta(tusRespuestas);

                                // Preguntamos por teclado

                                System.out.println("\n Introduce una letra: ");
                                resp = teclado.next().toLowerCase().charAt(0);

                                // Recorremos el array y comprobamos si se ha producido un acierto

                                boolean acertividad = false;
                                for (int i = 0; i < palabraDividida.length; i++) {
                                    if (palabraDividida[i] == resp) {
                                        tusRespuestas[i] = palabraDividida[i];
                                        palabraDividida[i] = ' ';
                                        aciertos++;
                                        acertividad = true;
                                    }
                                }
                                if (!acertividad) {
                                    intentos++;
                                }
                            }

                            // Si hemos acertado todas imprimimos un mensaje

                            if (aciertos == tusRespuestas.length) {
                                //  Para las palabras ya usadas.
                                listaDePalabras.get(alea).setEstado(false);
                                System.out.print("\nFelicidades!! has acertado la palabra: ");
                                if (dificultad == 1) {
                                    puntaje += 1;
                                    if (intentos == 0) {
                                        puntaje += 1;
                                    }
                                } else if (dificultad == 2) {
                                    puntaje += 2;
                                    if (intentos == 0) {
                                        puntaje += 2;
                                    }
                                } else if (dificultad == 3) {
                                    puntaje += 4;
                                    if (intentos == 0) {
                                        puntaje += 3;
                                    }
                                } else {
                                    puntaje += numeroAleatorio.nextInt(3);
                                    if (intentos == 0) {
                                        puntaje += numeroAleatorio.nextInt(5);
                                    }
                                }
                                imprimeOculta(tusRespuestas);
                            } else {
                                System.out.print("\n Que mal :( , La palabra era: ");
                                for (int i = 0; i < copia.length; i++) {
                                    System.out.print(copia[i] + " ");
                                }
                            }
                            // Reseteamos contadores
                            intentos = 0;
                            aciertos = 0;

                            // Volvemos a preguntarle al usuario si quiere volver a jugar.

                            resp = pregunta("\n\n Quieres volver a jugar?", teclado);
                        }

                        } while (resp != 'n');
                            break;
                        }
                        case 2:
                            System.out.println("\t\t\t Tu puntaje es: " + puntaje);
                            break;
                        case 3: {
                            char respuesta;
                            do {
                                System.out.println("\t\t\t Ingrese la palabra que desea agregar: ");
                                String palabraAAgregar = teclado.next();
                                Palabra nuevaPalabra = new Palabra(palabraAAgregar);
                                ahorcado.agregarPalabra(nuevaPalabra);
                                respuesta = pregunta("\t\t Quieres agregar otra palabra? ", teclado);
                            } while (respuesta != 'n');
                            break;
                        }
                        case 4:
                            System.out.println("Escriba la palabra que quieres cambiar. ");
                            String palabraACambiar = teclado.next();
                            System.out.println("Escriba la palabra de reemplazo. ");
                            String palabraNueva = teclado.next();
                            ahorcado.cambiarPalabra(palabraACambiar, palabraNueva);
                            break;
                        default:
                            System.out.println("Opcion invalida");
                    }
         } while (opcion != 5);
    }

    /**
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

    /**
     * Esto imprime la palabra con espacios
     * @param tusRespuestas el array de caracteres
     */

    private static void imprimeOculta(char[] tusRespuestas){
        for(int i = 0; i < tusRespuestas.length; i++){
            System.out.print(tusRespuestas[i] + " ");
        }
    }

    /**
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
