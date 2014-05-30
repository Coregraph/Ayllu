package besa_adaptado.filtros;

import adaptation.common.filter.FilterResultAES;
import besa_adaptado.perfiles.PerfilEquipoAES;

public class ResultadoFiltro extends FilterResultAES {

    String category;
    String respuestaEstud;
    PerfilEquipoAES respuestaEquipo;

    public ResultadoFiltro(String fw) {
        this.respuestaEstud = fw;
    }

    public ResultadoFiltro(PerfilEquipoAES perfEq) {
        this.respuestaEquipo = perfEq;
    }

    public String getRespuestaEstudiante() {
        return this.respuestaEstud;
    }

    public PerfilEquipoAES getRespuestaEquipo() {
        return this.respuestaEquipo;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }
}