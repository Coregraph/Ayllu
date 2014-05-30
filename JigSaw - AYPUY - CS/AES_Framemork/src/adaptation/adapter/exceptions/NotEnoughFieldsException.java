/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adaptation.adapter.exceptions;

import adaptation.common.ProfileAES;

/**
 *
 * @author Ayllu
 */
public class NotEnoughFieldsException extends Exception {

    public NotEnoughFieldsException() {
        super("There are not enough fields in the profile to match the filter");
    }

    public NotEnoughFieldsException(ProfileAES p) {
       super("There are not enough fields in the profile"+p.getClass().toString()+"to match the filter");
    }


}
