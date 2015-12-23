package com.katner.beans;

import javax.faces.convert.Converter;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;

import com.katner.model.AuthGroup;

public class AuthGroupConverter implements Converter {

    public AuthGroupConverter() {
    }

    public Object getAsObject(FacesContext facesContext, UIComponent uIComponent, String string) {
        if (string == null || string.trim().length() == 0) {
            return null;
        }

        AuthGroupBean managedBean = (AuthGroupBean) facesContext.getApplication().getVariableResolver().resolveVariable(
                facesContext, "authGroup");

        final int id = Integer.parseInt(string);

        return managedBean.findEntity(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if (object == null) return null;

        if (object instanceof AuthGroup) {
            AuthGroup entity = (AuthGroup) object;

            final String pk = String.valueOf(entity.getId());

            return pk;
        } else {
            throw new IllegalArgumentException("Incorrect object type: " + object.getClass().getName() + "; must be: AuthGroup");
        }
    }
}
