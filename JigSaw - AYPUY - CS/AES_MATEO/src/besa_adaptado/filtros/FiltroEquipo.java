package besa_adaptado.filtros;

import adaptation.common.ProfileAES;
import adaptation.common.filter.FilterAES;
import adaptation.common.filter.FilterResultAES;
import besa_adaptado.perfiles.PerfilEquipoAES;
import java.util.List;

public class FiltroEquipo extends FilterAES {

    PerfilEquipoAES perfEquipo;

    public FiltroEquipo(String category) {
        super(category);
    }

    @Override
    public FilterResultAES doFilter(List<ProfileAES> aList) {
        for (int i = 0; i < aList.size(); i++) {
            if (((ProfileAES) aList.get(i)).getCategory().equals("team")) {
                this.perfEquipo = ((PerfilEquipoAES) aList.get(i));
            }
        }
        ResultadoFiltro rf = new ResultadoFiltro(this.perfEquipo);
        rf.setCategory(getCategory());
        return rf;
    }
}
