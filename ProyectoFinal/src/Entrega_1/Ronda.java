package Entrega_1;

import java.util.ArrayList;

public class Ronda {

    private String nro;
    private ArrayList<Partido> partidos;

    public Ronda(String nro) {
        this.nro = nro;
        this.partidos = new ArrayList<>();
    }

    public Ronda(String nro, ArrayList partidos) {
        this.nro = nro;
        this.partidos = partidos;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void addPartidos(Partido part) {
        this.partidos.add(part);
    }


    public int puntos() {
        return partidos.size();
    }
}
