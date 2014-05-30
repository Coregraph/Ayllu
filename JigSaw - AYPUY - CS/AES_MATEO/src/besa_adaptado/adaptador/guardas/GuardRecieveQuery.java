package besa_adaptado.adaptador.guardas;

import adaptation.adapter.AdaptQueryGuardAES;
import adaptation.common.ProfileAES;
import adaptation.common.adaptationmechanism.AdaptationMechanismAES;
import java.util.ArrayList;
import java.util.List;

public class GuardRecieveQuery extends AdaptQueryGuardAES {

    @Override
    public AdaptationMechanismAES selectAdaptationMechanism(List<ProfileAES> context, List<ProfileAES> restrictions) {
        return super.selectAdaptationMechanism(context, restrictions);
    }

    @Override
    public List<ProfileAES> applyRestrictions(List<ProfileAES> context, List<ProfileAES> restrictions) {
        List newContext = new ArrayList(context);

        return newContext;
    }
}
