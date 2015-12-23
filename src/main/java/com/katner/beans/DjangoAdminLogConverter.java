package com.katner.beans;

import javax.faces.convert.Converter;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;

import com.katner.model.DjangoAdminLog;

public class DjangoAdminLogConverter implements Converter {

    public DjangoAdminLogConverter() {
    }

    public Object getAsObject(FacesContext facesContext, UIComponent uIComponent, String string) {
        if (string == null || string.trim().length() == 0) {
            return null;
        }

        DjangoAdminLogBean managedBean = (DjangoAdminLogBean) facesContext.getApplication().getVariableResolver().resolveVariable(
                facesContext, "djangoAdminLog");

        final int id = Integer.parseInt(string);

        return managedBean.findEntity(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if (object == null) return null;

        if (object instanceof DjangoAdminLog) {
            DjangoAdminLog entity = (DjangoAdminLog) object;

            final String pk = String.valueOf(entity.getId());

            return pk;
        } else {
            throw new IllegalArgumentException("Incorrect object type: " + object.getClass().getName() + "; must be: DjangoAdminLog");
        }
    }
}
