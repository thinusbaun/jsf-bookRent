package com.katner.beans;

import javax.faces.convert.Converter;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;

import com.katner.model.DjangoContentType;

public class DjangoContentTypeConverter implements Converter {

    public DjangoContentTypeConverter() {
    }

    public Object getAsObject(FacesContext facesContext, UIComponent uIComponent, String string) {
        if (string == null || string.trim().length() == 0) {
            return null;
        }

        DjangoContentTypeBean managedBean = (DjangoContentTypeBean) facesContext.getApplication().getVariableResolver().resolveVariable(
                facesContext, "djangoContentType");

        final int id = Integer.parseInt(string);

        return managedBean.findEntity(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if (object == null) return null;

        if (object instanceof DjangoContentType) {
            DjangoContentType entity = (DjangoContentType) object;

            final String pk = String.valueOf(entity.getId());

            return pk;
        } else {
            throw new IllegalArgumentException("Incorrect object type: " + object.getClass().getName() + "; must be: DjangoContentType");
        }
    }
}
