/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

/**
 *
 * @author Francisco
 */
public class CrearDatos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BinaryDAOFactory baseDatos= new BinaryDAOFactory();
        
        //Se crean los cursos
        Curso cusroLD = new Curso();
        cusroLD.setIdCurso("CIS0001");
        cusroLD.setNombre("Logica Difusa");
        
        //Se crean los temas de los cursos
        Tema tema1 = new Tema();
        tema1.setIdTema("TC0001");
        tema1.setNombre("tema1");
        tema1.setDescripcion("desc tema1");
        tema1.getCursosRelacionados().add(cusroLD);
        
        Tema tema2 = new Tema();
        tema2.setIdTema("TC0002");
        tema2.setNombre("tema2");
        tema2.setDescripcion("desc tema2");
        tema2.getCursosRelacionados().add(cusroLD);
       
        Tema tema3 = new Tema();
        tema3.setIdTema("TC0003");
        tema3.setNombre("tema3");
        tema3.setDescripcion("desc tema3");
        tema3.getCursosRelacionados().add(cusroLD);
        
        Pregunta pregunta1 = new Pregunta();
        pregunta1.setIdPregunta("PR0001");
        pregunta1.setPregunta("¿cual es pregunta 1?");
        
        Pregunta pregunta2 = new Pregunta();
        pregunta2.setIdPregunta("PR0002");
        pregunta2.setPregunta("¿cual es pregunta 2?");
        
        GrupoPreguntas grupoPreguntas1 = new GrupoPreguntas();
        grupoPreguntas1.setIdGrupoPreguntas("GPR0001");
        grupoPreguntas1.getPreguntas().add(pregunta1);
        grupoPreguntas1.getPreguntas().add(pregunta2);
        grupoPreguntas1.setTemaRelacionado(tema1);
        
        Pregunta pregunta3 = new Pregunta();
        pregunta3.setIdPregunta("PR0003");
        pregunta3.setPregunta("¿cual es pregunta 3?");
        
        Pregunta pregunta4 = new Pregunta();
        pregunta4.setIdPregunta("PR0004");
        pregunta4.setPregunta("¿cual es pregunta 4?");
        
        GrupoPreguntas grupoPreguntas2 = new GrupoPreguntas();
        grupoPreguntas2.setIdGrupoPreguntas("GPR0002");
        grupoPreguntas2.getPreguntas().add(pregunta3);
        grupoPreguntas2.getPreguntas().add(pregunta4);
        grupoPreguntas2.setTemaRelacionado(tema1);
        
        Pregunta pregunta5 = new Pregunta();
        pregunta5.setIdPregunta("PR0005");
        pregunta5.setPregunta("¿cual es pregunta 5?");
        
        Pregunta pregunta6 = new Pregunta();
        pregunta6.setIdPregunta("PR0006");
        pregunta6.setPregunta("¿cual es pregunta 6?");
        
        GrupoPreguntas grupoPreguntas3 = new GrupoPreguntas();
        grupoPreguntas3.setIdGrupoPreguntas("GPR0003");
        grupoPreguntas3.getPreguntas().add(pregunta5);
        grupoPreguntas3.getPreguntas().add(pregunta6);
        grupoPreguntas3.setTemaRelacionado(tema1);
        
        Pregunta pregunta7 = new Pregunta();
        pregunta7.setIdPregunta("PR0007");
        pregunta7.setPregunta("¿cual es pregunta 7?");
        
        Pregunta pregunta8 = new Pregunta();
        pregunta8.setIdPregunta("PR0008");
        pregunta8.setPregunta("¿cual es pregunta 8?");
        
        GrupoPreguntas grupoPreguntas4 = new GrupoPreguntas();
        grupoPreguntas4.setIdGrupoPreguntas("GPR0004");
        grupoPreguntas4.getPreguntas().add(pregunta7);
        grupoPreguntas4.getPreguntas().add(pregunta8);
        grupoPreguntas4.setTemaRelacionado(tema2);
        
        Pregunta pregunta9 = new Pregunta();
        pregunta9.setIdPregunta("PR0009");
        pregunta9.setPregunta("¿cual es pregunta 9?");
        
        Pregunta pregunta10 = new Pregunta();
        pregunta10.setIdPregunta("PR0010");
        pregunta10.setPregunta("¿cual es pregunta 10?");
        
        GrupoPreguntas grupoPreguntas5 = new GrupoPreguntas();
        grupoPreguntas5.setIdGrupoPreguntas("GPR0005");
        grupoPreguntas5.getPreguntas().add(pregunta9);
        grupoPreguntas5.getPreguntas().add(pregunta10);
        grupoPreguntas5.setTemaRelacionado(tema2);
        
        Pregunta pregunta11 = new Pregunta();
        pregunta11.setIdPregunta("PR0011");
        pregunta11.setPregunta("¿cual es pregunta 11?");
        
        Pregunta pregunta12 = new Pregunta();
        pregunta12.setIdPregunta("PR0012");
        pregunta12.setPregunta("¿cual es pregunta 12?");
        
        GrupoPreguntas grupoPreguntas6 = new GrupoPreguntas();
        grupoPreguntas6.setIdGrupoPreguntas("GPR0006");
        grupoPreguntas6.getPreguntas().add(pregunta11);
        grupoPreguntas6.getPreguntas().add(pregunta12);
        grupoPreguntas6.setTemaRelacionado(tema2);
        
        Pregunta pregunta13 = new Pregunta();
        pregunta13.setIdPregunta("PR0013");
        pregunta13.setPregunta("¿cual es pregunta 13?");
        
        Pregunta pregunta14 = new Pregunta();
        pregunta14.setIdPregunta("PR0014");
        pregunta14.setPregunta("¿cual es pregunta 14?");
        
        GrupoPreguntas grupoPreguntas7 = new GrupoPreguntas();
        grupoPreguntas7.setIdGrupoPreguntas("GPR0007");
        grupoPreguntas7.getPreguntas().add(pregunta13);
        grupoPreguntas7.getPreguntas().add(pregunta14);
        grupoPreguntas7.setTemaRelacionado(tema3);
        
        Pregunta pregunta15 = new Pregunta();
        pregunta15.setIdPregunta("PR0015");
        pregunta15.setPregunta("¿cual es pregunta 15?");
        
        Pregunta pregunta16 = new Pregunta();
        pregunta16.setIdPregunta("PR0016");
        pregunta16.setPregunta("¿cual es pregunta 16?");
        
        GrupoPreguntas grupoPreguntas8 = new GrupoPreguntas();
        grupoPreguntas8.setIdGrupoPreguntas("GPR0008");
        grupoPreguntas8.getPreguntas().add(pregunta15);
        grupoPreguntas8.getPreguntas().add(pregunta16);
        grupoPreguntas8.setTemaRelacionado(tema3);
        
        Pregunta pregunta17 = new Pregunta();
        pregunta17.setIdPregunta("PR0017");
        pregunta17.setPregunta("¿cual es pregunta 17?");
        
        Pregunta pregunta18 = new Pregunta();
        pregunta18.setIdPregunta("PR0018");
        pregunta18.setPregunta("¿cual es pregunta 18?");
        
        GrupoPreguntas grupoPreguntas9 = new GrupoPreguntas();
        grupoPreguntas9.setIdGrupoPreguntas("GPR0009");
        grupoPreguntas9.getPreguntas().add(pregunta17);
        grupoPreguntas9.getPreguntas().add(pregunta18);
        grupoPreguntas9.setTemaRelacionado(tema3);
        
        //Se crean los Roles
        Rol rolEstudiante = new Rol();
        rolEstudiante.setIdRol("rolEstudiante");
        rolEstudiante.setNombre("estudiante");
        rolEstudiante.setDescripcion("persona que estudia");
        
        Rol rolProfesor = new Rol();
        rolProfesor.setIdRol("rolProfesor");
        rolProfesor.setNombre("profesor");
        rolProfesor.setDescripcion("persona que enseña");
        
        Rol rolSecretario = new Rol();
        rolSecretario.setIdRol("rolSecretario");
        rolSecretario.setNombre("Secretario");
        rolSecretario.setDescripcion("persona que guia en un grupo");
        
        //Se crean las Personas
        Persona personaJuan = new Persona();
        personaJuan.setLogin("juan");
        personaJuan.setPass("juan");
              
        Persona personaMaria = new Persona();
        personaMaria.setLogin("Maria");
        personaMaria.setPass("Maria");
                
        Persona personaLina = new Persona();
        personaLina.setLogin("Lina");
        personaLina.setPass("Lina");
             
        Persona personaAna = new Persona();
        personaAna.setLogin("Ana");
        personaAna.setPass("Ana");
               
        Persona personaPedro = new Persona();
        personaPedro.setLogin("Pedro");
        personaPedro.setPass("Pedro");
        
        Persona personaLucas = new Persona();
        personaLucas.setLogin("Lucas");
        personaLucas.setPass("Lucas");
        
        Persona personaLaura = new Persona();
        personaLaura.setLogin("Laura");
        personaLaura.setPass("Laura");
        
        Persona personaTomas = new Persona();
        personaTomas.setLogin("Tomas");
        personaTomas.setPass("Tomas");
        
        Persona personaRicardo = new Persona();
        personaRicardo.setLogin("Ricardo");
        personaRicardo.setPass("Ricardo");
                
        Persona personaFrancisco = new Persona();
        personaFrancisco.setLogin("Francisco");
        personaFrancisco.setPass("Francisco");
        
        //Se crean las Estrategias
        Estrategia estrategiaJIGSAW = new Estrategia();
        estrategiaJIGSAW.setIdEstrategia("EST001");
        estrategiaJIGSAW.setNombre("JIGSAW");
        
        //Se crean los servicios
        Servicio servicioAgrupar = new Servicio();
        servicioAgrupar.setIdServicio("SER001");
        servicioAgrupar.setDescripcion("Servicio para agrupar persoanas y asignar roles");
        
        Servicio servicioAsignarTemas = new Servicio();
        servicioAsignarTemas.setIdServicio("SER002");
        servicioAsignarTemas.setDescripcion("Servicio para asignar los temas");
        
        //Se asignan los temas a los cursos
        cusroLD.getTemas().add(tema1);
        cusroLD.getTemas().add(tema2);
        cusroLD.getTemas().add(tema3);
        
        //Se asignan los grupos de preguntas a los temas
        
        tema1.getGrupoPreguntas().add(grupoPreguntas1);
        tema1.getGrupoPreguntas().add(grupoPreguntas2);
        tema1.getGrupoPreguntas().add(grupoPreguntas3);
        
        tema2.getGrupoPreguntas().add(grupoPreguntas4);
        tema2.getGrupoPreguntas().add(grupoPreguntas5);
        tema2.getGrupoPreguntas().add(grupoPreguntas6);
        
        tema3.getGrupoPreguntas().add(grupoPreguntas7);
        tema3.getGrupoPreguntas().add(grupoPreguntas8);
        tema3.getGrupoPreguntas().add(grupoPreguntas9);
        

        
               
        //Se asignan los roles basicos a las personas
        personaJuan.setRolBasico(rolEstudiante);
        personaMaria.setRolBasico(rolEstudiante);
        personaLina.setRolBasico(rolEstudiante);
        personaAna.setRolBasico(rolEstudiante);
        personaPedro.setRolBasico(rolEstudiante);
        personaLucas.setRolBasico(rolEstudiante);
        personaLaura.setRolBasico(rolEstudiante);
        personaTomas.setRolBasico(rolEstudiante);
        personaRicardo.setRolBasico(rolEstudiante);
        personaFrancisco.setRolBasico(rolEstudiante);
        
        //Se asignan los roles que intervienen en cada servicio
        servicioAsignarTemas.getRolesPorServicio().add(rolSecretario);
        
        //Se asignan los estudiantes a los cursos
        personaJuan.getMisCursosAsistidos().add(cusroLD);
        cusroLD.getEstudiantes().add(personaJuan);
        personaMaria.getMisCursosAsistidos().add(cusroLD);
        cusroLD.getEstudiantes().add(personaMaria);
        personaLina.getMisCursosAsistidos().add(cusroLD);
        cusroLD.getEstudiantes().add(personaLina);
        personaAna.getMisCursosAsistidos().add(cusroLD);
        cusroLD.getEstudiantes().add(personaAna);
        personaPedro.getMisCursosAsistidos().add(cusroLD);
        cusroLD.getEstudiantes().add(personaPedro);
        personaLucas.getMisCursosAsistidos().add(cusroLD);
        cusroLD.getEstudiantes().add(personaLucas);
        personaLaura.getMisCursosAsistidos().add(cusroLD);
        cusroLD.getEstudiantes().add(personaLaura);
        personaTomas.getMisCursosAsistidos().add(cusroLD);
        cusroLD.getEstudiantes().add(personaTomas);
        personaRicardo.getMisCursosAsistidos().add(cusroLD);
        cusroLD.getEstudiantes().add(personaRicardo);
        
        //Se asigna el profesor de cada curso
        personaFrancisco.getMisCursosDirigidos().add(cusroLD);
        cusroLD.setProfesor(personaFrancisco);
        
        //Se asigna los planes de cada estrategia
        estrategiaJIGSAW.getPlan().add(servicioAgrupar);
        estrategiaJIGSAW.getPlan().add(servicioAsignarTemas);
        
        
        //Se persisten los datos
        baseDatos.persist(cusroLD);
        baseDatos.persist(tema1);
        baseDatos.persist(tema2);
        baseDatos.persist(tema3);
        baseDatos.persist(pregunta1);
        baseDatos.persist(pregunta2);
        baseDatos.persist(pregunta3);
        baseDatos.persist(pregunta4);
        baseDatos.persist(pregunta5);
        baseDatos.persist(pregunta6);
        baseDatos.persist(pregunta7);
        baseDatos.persist(pregunta8);
        baseDatos.persist(pregunta9);
        baseDatos.persist(pregunta10);
        baseDatos.persist(pregunta11);
        baseDatos.persist(pregunta12);
        baseDatos.persist(pregunta13);
        baseDatos.persist(pregunta14);
        baseDatos.persist(pregunta15);
        baseDatos.persist(pregunta16);
        baseDatos.persist(pregunta17);
        baseDatos.persist(pregunta18);
        baseDatos.persist(grupoPreguntas1);
        baseDatos.persist(grupoPreguntas2);
        baseDatos.persist(grupoPreguntas3);
        baseDatos.persist(grupoPreguntas4);
        baseDatos.persist(grupoPreguntas5);
        baseDatos.persist(grupoPreguntas6);
        baseDatos.persist(grupoPreguntas7);
        baseDatos.persist(grupoPreguntas8);
        baseDatos.persist(grupoPreguntas9);       
        baseDatos.persist(rolEstudiante);
        baseDatos.persist(rolProfesor);
        baseDatos.persist(rolSecretario);
        baseDatos.persist(personaAna);
        baseDatos.persist(personaFrancisco);
        baseDatos.persist(personaJuan);
        baseDatos.persist(personaLina);
        baseDatos.persist(personaMaria);
        baseDatos.persist(personaPedro);
        baseDatos.persist(personaLucas);
        baseDatos.persist(personaLaura);
        baseDatos.persist(personaTomas);
        baseDatos.persist(personaRicardo);
        baseDatos.persist(estrategiaJIGSAW);
        baseDatos.persist(servicioAgrupar);
        baseDatos.persist(servicioAsignarTemas);
        
//        Object dato = baseDatos.findDataById(BinaryDAOFactory.TIPO_DATO_CURSOS, "CIS0001.obj");
//        List<Object> datos = baseDatos.getListaDatos(BinaryDAOFactory.TIPO_DATO_PERSONAS);
//        System.out.println(dato);
    }
}
