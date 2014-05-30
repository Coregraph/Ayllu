/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptation.common.filter;

import adaptation.adapter.exceptions.FilterMismatchException;
import adaptation.adapter.exceptions.NotEnoughFieldsException;
import adaptation.common.ProfileAES;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AYLLU
 * Esta clase contiene la base para los filtros que se utilizarán dentro del mecanismo de adaptación.
 */
public abstract class FilterAES {

    private String filterId;
    private String category;
    private List<String> subcategories;

   public FilterAES(String category) {
        this.filterId = UUID.randomUUID().toString();
        this.category = category;
        this.subcategories = new ArrayList<String>();
    }

    public FilterAES(String category, List<String> subcategories) {
        this.filterId = UUID.randomUUID().toString();
        this.category = category;
        this.subcategories = subcategories;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFilterId() {
        return filterId;
    }

    public void setFilterId(String filterId) {
        this.filterId = filterId;
    }

    public List<String> getSubcategories() {
        return subcategories;
    }

    public void addSubCategory(String subcategory) {
        this.subcategories.add(subcategory);

    }
    
    public FilterResultAES execFilter(List<ProfileAES> aList) throws NotEnoughFieldsException,FilterMismatchException {
        /**
         * Realiza la validación de los campos considerados en el filtro
         */
        if (aList.size() > 0) {
            for (ProfileAES p : aList) { //Obtiene cada elemento de la lista con los perfiles (Que son de la misma categoria)
                try {
                    //Obtiene cada elemento de la lista con los perfiles (Que son de la misma categoria)
                    Method getCategory = p.getClass().getMethod("getCategory", new Class[0]);
                    String profCategory = (String) getCategory.invoke(p, new Object [0]);
                    if(!profCategory.equals(this.category)){
                       throw new FilterMismatchException(this.category,p);
                    }
                    if (this.subcategories.size() > p.getClass().getDeclaredFields().length) {
                        throw new NotEnoughFieldsException(p);
                    } else if (this.subcategories.size() < p.getClass().getDeclaredFields().length) {
                        //TODO ReportBESA (1,"Warning "+this.getClass().getName()+": Not all fields in the profile " + p.getClass().getName() + " will be used, information may be lost");
                    }
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(FilterAES.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(FilterAES.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            return null;
        }

        return doFilter(aList);
    }
    /*public Object getValueIn(String field, Object object){
        Object resp=null;
        try {
            Field f = object.getClass().getDeclaredField(field);
            f.setAccessible(true);
            resp = f.get(object);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(FilterAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FilterAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(FilterAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(FilterAES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;

    }*/
    public abstract FilterResultAES doFilter(List<ProfileAES> aList);
}
