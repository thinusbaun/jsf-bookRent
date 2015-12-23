package com.katner.beans;

import javax.faces.convert.Converter;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;

import com.katner.model.DjangoSession;

public class DjangoSessionConverter implements Converter {

    public DjangoSessionConverter() {
    }

    public Object getAsObject(FacesContext facesContext, UIComponent uIComponent, String string) {
        if (string == null || string.trim().length() == 0) {
            return null;
        }

        DjangoSessionBean managedBean = (DjangoSessionBean) facesContext.getApplication().getVariableResolver().resolveVariable(
                facesContext, "djangoSession");

        final String id = string;

        return managedBean.findEntity(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if (object == null) return null;

        if (object instanceof DjangoSession) {
            DjangoSession entity = (DjangoSession) object;

            final String pk = String.valueOf(entity.getSessionKey());

            return pk;
        } else {
            throw new IllegalArgumentException("Incorrect object type: " + object.getClass().getName() + "; must be: DjangoSession");
        }
    }
}
