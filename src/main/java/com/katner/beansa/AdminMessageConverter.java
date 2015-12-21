package com.katner.beansa;

import javax.faces.convert.Converter;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;

import katner.model.AdminMessage;

public class AdminMessageConverter implements Converter {

    public AdminMessageConverter() {
    }

    public Object getAsObject(FacesContext facesContext, UIComponent uIComponent, String string) {
        if (string == null || string.trim().length() == 0) {
            return null;
        }

        AdminMessageBean managedBean = (AdminMessageBean) facesContext.getApplication().getVariableResolver().resolveVariable(
                facesContext, "adminMessage");

        final int id = Integer.parseInt(string);

        return managedBean.findEntity(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if (object == null) return null;

        if (object instanceof AdminMessage) {
            AdminMessage entity = (AdminMessage) object;

            final String pk = String.valueOf(entity.getId());

            return pk;
        } else {
            throw new IllegalArgumentException("Incorrect object type: " + object.getClass().getName() + "; must be: AdminMessage");
        }
    }
}
