/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AYLLU
 */
public class BinaryDAOFactory {

    public static String TIPO_DATO_CURSOS = "CURSOS";
    public static String TIPO_DATO_TEMAS = "TEMAS";
    public static String TIPO_DATO_PREGUNTAS = "PREGUNTAS";
    public static String TIPO_DATO_GRUPOS_PREGUNTAS = "GRUPOS_PREGUNTAS";
    public static String TIPO_DATO_ROLES = "ROLES";
    public static String TIPO_DATO_PERSONAS = "PERSONAS";
    public static String TIPO_DATO_ESTRATEGIAS = "ESTRATEGIAS";
    public static String TIPO_DATO_SERVICIOS = "SERVICIOS";
    public static String TIPO_DATO_GRUPOS = "GRUPOS";
    public static String TIPO_DATO_TRABAJOS_GRUPALES = "TRABAJOS_GRUPALES";
    public static String TIPO_DATO_PERFILES = "PERFILES";
    
    protected static final String dataDirCursos = "data2//cursos";
    protected static final String dataDirTemas = "data2//temas";
    protected static final String dataDirPreguntas = "data2//preguntas";
    protected static final String dataDirGruposPreguntas = "data2//gruposPreguntas";
    protected static final String dataDirRoles = "data2//roles";
    protected static final String dataDirPersonas = "data2//personas";
    protected static final String dataDirEstrategias = "data2//estrategias";
    protected static final String dataDirServicios = "data2//servicios";
    protected static final String dataDirGrupos = "data2//grupos";
    protected static final String dataDirTrabajosGrupales = "data2//trabajosGrupales";
    protected static final String dataDirPerfiles = "data2//perfiles";

    public BinaryDAOFactory() {
    }

    public boolean persist(Object obj) {

        String idArchivo = "";
        File outFile = null;

        boolean persist = false;

        if (obj instanceof Curso) {
            idArchivo = ((Curso) obj).getIdCurso();
            outFile = newFile(idArchivo, dataDirCursos);
        } else if (obj instanceof Tema) {
            idArchivo = ((Tema) obj).getIdTema();
            outFile = newFile(idArchivo, dataDirTemas);
        } else if (obj instanceof Pregunta) {
            idArchivo = ((Pregunta) obj).getIdPregunta();
            outFile = newFile(idArchivo, dataDirPreguntas);
        }else if (obj instanceof GrupoPreguntas) {
            idArchivo = ((GrupoPreguntas) obj).getIdGrupoPreguntas();
            outFile = newFile(idArchivo, dataDirGruposPreguntas);
        }else if (obj instanceof Estrategia) {
            idArchivo = ((Estrategia) obj).getIdEstrategia();
            outFile = newFile(idArchivo, dataDirEstrategias);
        } else if (obj instanceof Grupo) {
            idArchivo = ((Grupo) obj).getIdGrupo();
            outFile = newFile(idArchivo, dataDirGrupos);
        } else if (obj instanceof Persona) {
            idArchivo = ((Persona) obj).getLogin();
            outFile = newFile(idArchivo, dataDirPersonas);
        } else if (obj instanceof Servicio) {
            idArchivo = ((Servicio) obj).getIdServicio();
            outFile = newFile(idArchivo, dataDirServicios);
        } else if (obj instanceof TrabajoGrupal) {
            idArchivo = ((TrabajoGrupal) obj).getIdTrabajoGrupal();
            outFile = newFile(idArchivo, dataDirTrabajosGrupales);
        } else if (obj instanceof Rol) {
            idArchivo = ((Rol) obj).getIdRol();
            outFile = newFile(idArchivo, dataDirRoles);
        } else if (obj instanceof ProfileUsuarioAyllu) {
            idArchivo = ((ProfileUsuarioAyllu) obj).getProfileId();
            outFile = newFile(idArchivo, dataDirPerfiles);
        }


        if (!idArchivo.equals("")) {

            FileOutputStream flStream = null;
            ObjectOutputStream objStream = null;
            try {
                if (outFile != null) {
                    flStream = new java.io.FileOutputStream(outFile);
                    objStream = new ObjectOutputStream(flStream);
                    objStream.writeObject(obj);
                    objStream.close();
                    persist = true;
                } else {
                    Logger.getLogger(BinaryDAOFactory.class.getName()).log(Level.SEVERE, null, "Error en persistencia de datos");
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BinaryDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BinaryDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        return persist;
    }

    public List<Object> getListaDatos(String tipoDato) {
        List<Object> datos = new ArrayList<Object>();
        String[] children = null;

        if (tipoDato.equals(TIPO_DATO_CURSOS)) {
            children = fileList(dataDirCursos);
        } else if (tipoDato.equals(TIPO_DATO_TEMAS)) {
            children = fileList(dataDirTemas);
        }else if (tipoDato.equals(TIPO_DATO_PREGUNTAS)) {
            children = fileList(dataDirPreguntas);
        }else if (tipoDato.equals(TIPO_DATO_GRUPOS_PREGUNTAS)) {
            children = fileList(dataDirGruposPreguntas);
        }else if (tipoDato.equals(TIPO_DATO_ESTRATEGIAS)) {
            children = fileList(dataDirEstrategias);
        } else if (tipoDato.equals(TIPO_DATO_GRUPOS)) {
            children = fileList(dataDirGrupos);
        } else if (tipoDato.equals(TIPO_DATO_PERSONAS)) {
            children = fileList(dataDirPersonas);
        } else if (tipoDato.equals(TIPO_DATO_ROLES)) {
            children = fileList(dataDirRoles);
        } else if (tipoDato.equals(TIPO_DATO_SERVICIOS)) {
            children = fileList(dataDirServicios);
        } else if (tipoDato.equals(TIPO_DATO_TRABAJOS_GRUPALES)) {
            children = fileList(dataDirTrabajosGrupales);
        } else if (tipoDato.equals(TIPO_DATO_PERFILES)) {
            children = fileList(dataDirPerfiles);
        }

        if (children != null) {
            for (int i = 0; i < children.length; i++) {
                String filename = children[i];
                if (!filename.equalsIgnoreCase(".svn")) {
                    Object data = findDataById(tipoDato, filename);
                    datos.add(data);
                }
            }
        } else {
            Logger.getLogger(BinaryDAOFactory.class.getName()).log(Level.SEVERE, null, "Error en la consulta de datos");
        }
        return datos;
    }

    private String[] fileList(String dataDir) {
        File dir = new File(dataDir);
        String[] children = dir.list();
        return children;
    }

    /*public HashMap<String, ProfileAES> getKeyProfileList()
    {
    HashMap<String, ProfileAES> profiles = new HashMap<String, ProfileAES>();
    String[] children = fileList();
    for (int i = 0; i < children.length; i++) {
    String filename = children[i];
    ProfileAES profile = findProfileById(filename);
    profiles.put(filename, profile);
    }
    return profiles;
    }*/
    private File newFile(String uniqueFileName, String dataDir) {
        try {
            File outFile = new java.io.File(dataDir + "/" + uniqueFileName + ".obj");
            return outFile;
        } catch (Exception ex) {
            Logger.getLogger(BinaryDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Object findDataById(String tipoDato, String id) {

        File outFile = null;
        FileInputStream flStream = null;
        ObjectInputStream objStream = null;

        if (tipoDato.equals(TIPO_DATO_CURSOS)) {
            outFile = new java.io.File(dataDirCursos + "/" + id);
        } else if (tipoDato.equals(TIPO_DATO_TEMAS)) {
            outFile = new java.io.File(dataDirTemas + "/" + id);
        } else if (tipoDato.equals(TIPO_DATO_PREGUNTAS)) {
            outFile = new java.io.File(dataDirPreguntas + "/" + id);
        } else if (tipoDato.equals(TIPO_DATO_GRUPOS_PREGUNTAS)) {
            outFile = new java.io.File(dataDirGruposPreguntas + "/" + id);
        } else if (tipoDato.equals(TIPO_DATO_ESTRATEGIAS)) {
            outFile = new java.io.File(dataDirEstrategias + "/" + id);
        } else if (tipoDato.equals(TIPO_DATO_GRUPOS)) {
            outFile = new java.io.File(dataDirGrupos + "/" + id);
        } else if (tipoDato.equals(TIPO_DATO_PERSONAS)) {
            outFile = new java.io.File(dataDirPersonas + "/" + id);
        } else if (tipoDato.equals(TIPO_DATO_ROLES)) {
            outFile = new java.io.File(dataDirRoles + "/" + id);
        } else if (tipoDato.equals(TIPO_DATO_SERVICIOS)) {
            outFile = new java.io.File(dataDirServicios + "/" + id);
        } else if (tipoDato.equals(TIPO_DATO_TRABAJOS_GRUPALES)) {
            outFile = new java.io.File(dataDirTrabajosGrupales + "/" + id);
        }

        try {
            if (outFile != null) {
                flStream = new java.io.FileInputStream(outFile);
                objStream = new ObjectInputStream(flStream);
                Object data = (Object) objStream.readObject();
                objStream.close();
                return data;
            } else {
                Logger.getLogger(BinaryDAOFactory.class.getName()).log(Level.SEVERE, null, "Error en la consulta de datos por id");
                return null;
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(BinaryDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(BinaryDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(BinaryDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
