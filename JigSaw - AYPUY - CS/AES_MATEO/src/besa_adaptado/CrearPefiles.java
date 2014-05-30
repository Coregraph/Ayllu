package besa_adaptado;

import adaptation.common.ProfileAES;
import adaptation.profilemanager.persistence.FacadeProfileManagerPersistence;
import besa_adaptado.perfiles.PerfilEquipoAES;
import besa_adaptado.perfiles.PerfilEstudiante;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lectorcsv.LectorCSV;
import mateo_package.RolAES;

public class CrearPefiles {

    public static void main(String[] args) {
        boolean crear = true;

        System.out.println("Probando Persistencia");

        FacadeProfileManagerPersistence facade = new FacadeProfileManagerPersistence();
        if (crear) {
            crearPerfiles(facade);
        } else {
            verPerfiles(facade);
        }
    }

    private static void crearPerfiles(FacadeProfileManagerPersistence facade) {
        System.out.println("Creando perfiles de estudiante");
        LectorCSV lectorColores = new LectorCSV();

        ArrayList datosColores = lectorColores.leerarchivo();

        for (int est = 0; est < datosColores.size(); est++) {
            PerfilEstudiante perfilEst = new PerfilEstudiante();
            perfilEst.setNombrestudiante((String) ((ArrayList) datosColores.get(est)).get(0));
            perfilEst.setEdad(35);
            perfilEst.setGenero("f");
            perfilEst.setContextoEducativo("doctorado");
            perfilEst.setLocalizacion("PUJ");
            ArrayList coloresH = new ArrayList();
            for (int i = 1; i < ((ArrayList) datosColores.get(est)).size(); i++) {
                coloresH.add(((ArrayList) datosColores.get(est)).get(i));
            }
            perfilEst.setColoresHermann(coloresH);
            perfilEst.setPreferenciaDespliegue("documento", "video");
            perfilEst.addFormatos(".pdf");
            perfilEst.addFormatos(".wmv");
            perfilEst.addFormatos(".jpg");
            facade.persist(perfilEst);
        }

        PerfilEquipoAES pequipo = new PerfilEquipoAES();
        pequipo.setComposición("heterogéneo");
        pequipo.setCantEquipos(3);
        pequipo.setCantPersonasEquipo(5);
        pequipo.setVarsColaboracion(new ArrayList());
        ArrayList infoRoles = new ArrayList();

        RolAES roles = new RolAES();
        roles.setNombreRol("Secretario");
        roles.setNumPersRol(1);
        roles.getVarsRol(roles.getNombreRol());
        infoRoles.add(roles);

        pequipo.setLosRoles(infoRoles);
        facade.persist(pequipo);
    }

    private static void verPerfiles(FacadeProfileManagerPersistence facade) {
        List<ProfileAES> profiles = facade.getProfileList();
        for (ProfileAES pb : profiles) {
            System.out.println(pb.getProfileId());
            System.out.println(pb.getCategory());
            Field[] fieldSet = pb.getClass().getDeclaredFields();
            for (Field field : fieldSet) {
                try {
                    field.setAccessible(true);
                    System.out.println(field.getName() + "->" + field.get(pb).toString());
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(CrearPefiles.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(CrearPefiles.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(CrearPefiles.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}