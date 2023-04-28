package Entrega_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException{

        String archivo_resultados = "./archivos_entrega1/resultados.csv";
        String archivo_pronosticos = "./archivos_entrega1/pronosticos.csv";

        Ronda ronda = new Ronda("primera");

        for (String linea : Files.readAllLines(Paths.get(archivo_resultados))) {
            String[] datos = linea.split(",");

            Equipo equip1 = new Equipo(datos[0], "...");
            Equipo equip2 = new Equipo(datos[3], "...");

            Partido partido = new Partido(equip1, equip2, Integer.parseInt(datos[1]), Integer.parseInt(datos[2]));

            ronda.addPartidos(partido);
        }

        int puntosObtenidos = 0;

        ResultadoEnum resulPronosticado = ResultadoEnum.ganador;

        for (String linea : Files.readAllLines(Paths.get(archivo_pronosticos))) {
            String[] datos = linea.split(",");

            for (int x = 0; x < ronda.getPartidos().size(); x++) {
                Partido partidoSeleccionado = ronda.getPartidos().get(x);

                if (datos[4].equals(partidoSeleccionado.getEquipo2().getNombre())
                        && datos[0].equals(partidoSeleccionado.getEquipo1().getNombre())) {
                    if (datos[1].equals("1")) {
                        resulPronosticado = ResultadoEnum.ganador;
                    }
                    if (datos[2].equals("1")) {
                        resulPronosticado = ResultadoEnum.empate;
                    }
                    if (datos[3].equals("1")) {
                        resulPronosticado = ResultadoEnum.perdedor;
                    }

                    Pronostico pron = new Pronostico(partidoSeleccionado, partidoSeleccionado.getEquipo1(), resulPronosticado);
                    puntosObtenidos += pron.puntos();

                }
            }
        }
        System.out.println("puntaje obtenido: " + puntosObtenidos + " de un total posible de " + ronda.puntos() + " puntos");

    }


}
