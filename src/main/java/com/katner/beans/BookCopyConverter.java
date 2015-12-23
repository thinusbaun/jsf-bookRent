package com.katner.beans;

import javax.faces.convert.Converter;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;

import com.katner.model.BookCopy;

public class BookCopyConverter implements Converter {

    public BookCopyConverter() {
    }

    public Object getAsObject(FacesContext facesContext, UIComponent uIComponent, String string) {
        if (string == null || string.trim().length() == 0) {
            return null;
        }

        BookCopyBean managedBean = (BookCopyBean) facesContext.getApplication().getVariableResolver().resolveVariable(
                facesContext, "bookCopy");

        final int id = Integer.parseInt(string);

        return managedBean.findEntity(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if (object == null) return null;

        if (object instanceof BookCopy) {
            BookCopy entity = (BookCopy) object;

            final String pk = String.valueOf(entity.getId());

            return pk;
        } else {
            throw new IllegalArgumentException("Incorrect object type: " + object.getClass().getName() + "; must be: BookCopy");
        }
    }
}
