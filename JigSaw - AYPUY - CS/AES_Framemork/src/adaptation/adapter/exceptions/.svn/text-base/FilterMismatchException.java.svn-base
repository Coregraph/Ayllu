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
public class FilterMismatchException extends Exception{
    public FilterMismatchException(String category, ProfileAES p) {
        super("The Filter category: "+category+"Does not match the profile "+p.getClass().toString()+" which category is: "+p.getCategory());
    }

}
