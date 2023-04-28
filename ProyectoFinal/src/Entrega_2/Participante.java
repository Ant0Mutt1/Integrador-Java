package Entrega_2;

public class Participante {
    private String nombre;
    private int puntaje ;

    public Participante() {
    }

    public Participante(String nombre) {
        this.nombre = nombre;
        this.puntaje = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void sumarPuntos(int punto){
        puntaje += punto;   
    }

    public int getPuntaje() {
        return puntaje;
    }
    
    
    
}
