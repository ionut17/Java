package main;

import model.Project;
import service.ProjectService;

import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.ArrayList;

/**
 * Created by Ionut on 07-Nov-17.
 */
public class MyConverter implements Converter {

    public ProjectService getProjectService() {
        return projectService;
    }

    @ManagedProperty("#{projectService}")
    protected ProjectService projectService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        try {


            return projectService.getById(Integer.valueOf(value));

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return ((Project)o).getName();
    }
}
