package com.Ahorcado.dominio;

public class Palabra {
    private String caracteres;
    private int longitud;
    private boolean estado;
    private int complejidad;
    public Palabra(String caracteres) {
        this.caracteres = caracteres;
        this.estado = true;
        this.longitud = caracteres.length();
        this.complejidad = asignarComplejidad(longitud);
    }

    public String getCaracteres() {
        return caracteres;
    }

    public int getComplejidad() {
        return complejidad;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setCaracteres(String caracteres) {
        this.caracteres = caracteres;
    }

    public void setComplejidad(int complejidad) {
        this.complejidad = complejidad;
    }

    // TODO crear metodo para cambiar complejidad.
    protected int asignarComplejidad(int longitud) {
        if(longitud > 0) {
            if(longitud >=1 && longitud < 6) {
                return 1;
            } else if(longitud > 6 && longitud <= 12) {
                return 2;
            } else {
                return 3;
            }
        }
            return 1;
    }

}
