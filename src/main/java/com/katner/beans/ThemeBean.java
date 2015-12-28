package com.katner.beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Created by michal on 28.12.15.
 */
@ManagedBean
@ApplicationScoped
public class ThemeBean {
    public String getTheme() {
        if (theme == null)
        {
            return "/faces/javax.faces.resource/bootstrap.min.css?ln=css";

        } else {
            return "/faces/javax.faces.resource/" + theme + "/bootstrap.min.css?ln=css";
        }
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    private String theme;

    public String saveTheme()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String theme = (String) map.get("theme");
        this.theme = theme;
        return "";
    }

}
