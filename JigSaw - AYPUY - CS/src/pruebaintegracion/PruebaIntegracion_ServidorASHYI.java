/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaintegracion;

import estrategias.jigsawASHYI.Interface.InicioGUI_P;
import javax.swing.JFrame;

/**
 *
 * @author luis
 */
public class PruebaIntegracion_ServidorASHYI {
     /**
     * @param args the command line arguments
     * es el número del computador asociado al núermo de contenedor BESA a crear 
     */
    public static void main(String[] args) {
        
        InicioGUI_P iniciar = new InicioGUI_P(args[0]);
        iniciar.getJf_mainFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iniciar.showFrame();
    }
    
}
