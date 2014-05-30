package util;

import persistencia.BinaryDAOFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Curso;
import persistencia.GrupoPreguntas;
import persistencia.Persona;
import persistencia.Pregunta;
import persistencia.Tema;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Francisco
 */
public class Simulador {

    public static ArrayList<ArrayList<String>> simularCreacionGrupos() {
        BinaryDAOFactory baseDatos = new BinaryDAOFactory();
        ArrayList<ArrayList<String>> gruposEstudiantes = new ArrayList<ArrayList<String>>();

        //Se consultan todas las personas
        List<Object> personas = baseDatos.getListaDatos(BinaryDAOFactory.TIPO_DATO_PERSONAS);

        //Se identifican todos los estudiantes
        List<String> estudiantes = new ArrayList<String>();
        Persona persona;
        for (int i = 0; i < personas.size(); i++) {
            persona = (Persona) personas.get(i);
            if (persona.getRolBasico().getIdRol().equals("rolEstudiante")) {
                estudiantes.add(persona.getLogin());
            }
        }

        //Se arman los grupos (esto lo debe hacer MATEO. Aca colo se arman aleatoriamente)
        int numeroPersonasPorGrupo = 0;

        if (estudiantes.size() >= 40) {
            numeroPersonasPorGrupo = 5;
        } else if (estudiantes.size() >= 20) {
            numeroPersonasPorGrupo = 4;
        } else if (estudiantes.size() >= 6) {
            numeroPersonasPorGrupo = 3;
        } else {
            Logger.getLogger(Simulador.class.getName()).log(Level.INFO, null, "No se puede armar grupos. Se requiere minimo 6 estudiantes");
        }

        ArrayList<String> grupo = new ArrayList<String>();
        if (numeroPersonasPorGrupo != 0) {
            for (int i = 0; i < estudiantes.size(); i++) {
                grupo.add(estudiantes.get(i));
                if (grupo.size() == numeroPersonasPorGrupo) {
                    gruposEstudiantes.add(grupo);
                    grupo = new ArrayList<String>();
                }
            }
        }

        return gruposEstudiantes;
    }

    public static ArrayList<ArrayList<String>> simularAsignacionRoles(ArrayList<ArrayList<String>> gruposEstudiantes) {
        ArrayList<ArrayList<String>> rolesAsignados = new ArrayList<ArrayList<String>>();
        ArrayList<String> grupo;
        ArrayList<String> rolesPorGrupo;

        for (int i = 0; i < gruposEstudiantes.size(); i++) {
            grupo = gruposEstudiantes.get(i);
            rolesPorGrupo = new ArrayList<String>();
            for (int j = 0; j < grupo.size(); j++) {
                if (j == 0) {
                    rolesPorGrupo.add("Secretario");
                } else {
                    rolesPorGrupo.add("Participante");
                }
            }
            rolesAsignados.add(rolesPorGrupo);
        }

        return rolesAsignados;
    }

    public static ArrayList<Tema> simularTemas(int numeroMinimoTemas) {
        ArrayList<Tema> listaTemas = new ArrayList<Tema>();

        Curso cusroLD = new Curso();
        cusroLD.setIdCurso("CIS1");
        cusroLD.setNombre("Logica Difusa");

        for (int i = 0; i < numeroMinimoTemas; i++) {
            Tema tema = new Tema();
            tema.setIdTema("TC" + i);
            tema.setNombre("tema" + i);
            tema.setDescripcion("desc tema" + i);
            tema.getCursosRelacionados().add(cusroLD);

            listaTemas.add(tema);
        }

        return listaTemas;
    }

    public static Tema simularTema(int numeroTema) {

        Curso cusroLD = new Curso();
        cusroLD.setIdCurso("CIS1");
        cusroLD.setNombre("Logica Difusa");


        Tema tema = new Tema();
        tema.setIdTema("TC" + numeroTema);
        tema.setNombre("tema" + numeroTema);
        tema.setDescripcion("desc tema" + numeroTema);
        tema.getCursosRelacionados().add(cusroLD);
        return tema;
    }

    public static HashMap<Tema, List<GrupoPreguntas>> simularGruposPreguntasPorTemas(ArrayList<Tema> listaIDTemas, int numeroMinimoGruposPreguntas, int numeroMinimoPreguntasPorPersona) {
        HashMap<Tema, List<GrupoPreguntas>> gruposPreguntasPorTemas = new HashMap<Tema, List<GrupoPreguntas>>();
        List<Pregunta> preguntas;
        List<GrupoPreguntas> listaGrupoPreguntas;

        Tema tema;
        for (int i = 0; i < listaIDTemas.size(); i++) {
            tema = listaIDTemas.get(i);
            listaGrupoPreguntas = new ArrayList<GrupoPreguntas>();
            for (int j = 1; j <= numeroMinimoGruposPreguntas; j++) {

                preguntas = new ArrayList<Pregunta>();

                for (int k = 1; k <= numeroMinimoPreguntasPorPersona; k++) {
                    Pregunta pregunta = new Pregunta();
                    pregunta.setIdPregunta("PR" + j + k);
                    pregunta.setPregunta("¿cual es pregunta " + j + k + "1?");

                    preguntas.add(pregunta);
                }

                GrupoPreguntas grupoPreguntas = new GrupoPreguntas();
                grupoPreguntas.setIdGrupoPreguntas("GPR" + j);
                grupoPreguntas.setPreguntas(preguntas);
                grupoPreguntas.setTemaRelacionado(tema);

                listaGrupoPreguntas.add(grupoPreguntas);
            }
            gruposPreguntasPorTemas.put(tema, listaGrupoPreguntas);
        }

        return gruposPreguntasPorTemas;
    }
}
