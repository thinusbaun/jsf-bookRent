package com.katner.beans;

import javax.faces.convert.Converter;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;

import com.katner.model.DjangoMigrations;

public class DjangoMigrationsConverter implements Converter {

    public DjangoMigrationsConverter() {
    }

    public Object getAsObject(FacesContext facesContext, UIComponent uIComponent, String string) {
        if (string == null || string.trim().length() == 0) {
            return null;
        }

        DjangoMigrationsBean managedBean = (DjangoMigrationsBean) facesContext.getApplication().getVariableResolver().resolveVariable(
                facesContext, "djangoMigrations");

        final int id = Integer.parseInt(string);

        return managedBean.findEntity(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if (object == null) return null;

        if (object instanceof DjangoMigrations) {
            DjangoMigrations entity = (DjangoMigrations) object;

            final String pk = String.valueOf(entity.getId());

            return pk;
        } else {
            throw new IllegalArgumentException("Incorrect object type: " + object.getClass().getName() + "; must be: DjangoMigrations");
        }
    }
}
