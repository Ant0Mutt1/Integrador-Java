package Entrega_2;

import Entrega_1.Equipo;
import Entrega_1.Partido;
import Entrega_1.Pronostico;
import Entrega_1.ResultadoEnum;
import Entrega_1.Ronda;
import java.util.HashSet;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, Exception {

        HashSet<String> hashNombreParticipantes = new HashSet<>();
        HashSet<String> hashNombreRondas = new HashSet<>();

        ArrayList<Participante> arrayParticipantes = new ArrayList<>();
        ArrayList<Ronda> arrayRondas = new ArrayList<>();

        String archivo_resultados = "./archivos_entrega2/resultados.csv";
        String archivo_pronosticos = "./archivos_entrega2/pronosticos.csv";

        for (String linea : Files.readAllLines(Paths.get(archivo_resultados))) {
            String[] datos = linea.split(",");

            validadorCampos(datos);

            hashNombreRondas.add(datos[0]);
        }
        for (String nombre : hashNombreRondas) {
            arrayRondas.add(new Ronda(nombre));
        }

        for (String linea : Files.readAllLines(Paths.get(archivo_resultados))) {
            String[] datos = linea.split(",");
            
            for (Ronda r : arrayRondas) {
                if (r.getNro().equals(datos[0])) {

                    Equipo equip1 = new Equipo(datos[1], "...");
                    Equipo equip2 = new Equipo(datos[4], "...");

                    Partido partido = new Partido(equip1, equip2, Integer.parseInt(datos[2]), Integer.parseInt(datos[3]));

                    r.addPartidos(partido);
                }

            }
        }

        ResultadoEnum resulPronosticado = ResultadoEnum.ganador;

        for (String linea : Files.readAllLines(Paths.get(archivo_pronosticos))) {
            String[] datos = linea.split(",");
            String nombre = datos[0];
            hashNombreParticipantes.add(nombre);
        }

        for (String nombre : hashNombreParticipantes) {
            arrayParticipantes.add(new Participante(nombre));
        }

        for (String linea : Files.readAllLines(Paths.get(archivo_pronosticos))) {
            String[] datos = linea.split(",");

            for (Ronda ronda : arrayRondas) {

                for (int x = 0; x < ronda.getPartidos().size(); x++) {
                    for (Participante p : arrayParticipantes) {

                        if (p.getNombre().equals(datos[0])) {

                            Partido partidoSeleccionado = ronda.getPartidos().get(x);

                            if (datos[5].equals(partidoSeleccionado.getEquipo2().getNombre())
                                    && datos[1].equals(partidoSeleccionado.getEquipo1().getNombre())) {
                                if (datos[2].equals("1")) {
                                    resulPronosticado = ResultadoEnum.ganador;
                                }
                                if (datos[3].equals("1")) {
                                    resulPronosticado = ResultadoEnum.empate;
                                }
                                if (datos[4].equals("1")) {
                                    resulPronosticado = ResultadoEnum.perdedor;
                                }

                                Pronostico pron = new Pronostico(partidoSeleccionado, partidoSeleccionado.getEquipo1(), resulPronosticado);
                                p.sumarPuntos(pron.puntos());

                            }
                        }
                    }
                }
            }
        }

        for (Participante p : arrayParticipantes) {
            System.out.println(p.getNombre() + " obtuvo " + p.getPuntaje() + " en " + arrayRondas.size() + " rondas.");

        }

    }

    public static void validadorCampos(String[] linea) throws Exception {
        if (linea.length != 5) {
            System.out.println(linea.length);

            throw new Exception("El numero de campos del archivo no es valido.");

        } else if (linea[0].contains(".")) {
            throw new Exception("Los goles deben ser representado como enteros.");

        }

    }
}
