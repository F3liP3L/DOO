package com.Ahorcado.dominio;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ahorcado {
    private List<Palabra> palabras;

    public List<Palabra> getPalabras() {
        return palabras;
    }
    public Ahorcado() {
        this.palabras = new ArrayList<>();
    }

    public void agregarPalabra(Palabra palabra) {
        if(perteneceAAhorcado(palabra.getCaracteres()) == null) {
            this.palabras.add(palabra);
        } else {
            System.out.println("La palabra " + palabra.getCaracteres() + " ya se encuentra en la lista. ");
        }
    }

    public void cambiarPalabra(String palabraACambiar, String palabraNueva) {
        Palabra palabraReemplazo = perteneceAAhorcado(palabraACambiar);
        if(palabraReemplazo != null) {
            palabraReemplazo.setCaracteres(palabraNueva.toLowerCase());
            palabraReemplazo.setLongitud(palabraNueva.length());
            palabraReemplazo.asignarComplejidad(palabraReemplazo.getLongitud());
            palabraReemplazo.setEstado(true);
            int posicionReemplazo = this.palabras.indexOf(palabraReemplazo);
            this.palabras.set(posicionReemplazo, palabraReemplazo);
        } else {
            System.out.println("\n No se pudo cambiar la palabra.");
        }
    }

    public List<Palabra> palabrasPorDificultad(int dificultad) {
        return this.palabras.stream()
                .filter(palabra -> palabra.getComplejidad() == dificultad && palabra.isEstado())
                .collect(Collectors.toList());
    }

    private Palabra perteneceAAhorcado (String palabraABuscar) {
        Palabra palabraBuscada = this.palabras.stream()
                .filter(recurso1 -> recurso1.getCaracteres().equalsIgnoreCase(palabraABuscar)).findAny().orElse(null);
        if (palabraBuscada!=null) {
            return palabraBuscada;
        } else {
            return null;
        }
    }

}

