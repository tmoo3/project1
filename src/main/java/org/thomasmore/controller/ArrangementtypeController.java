package org.thomasmore.controller;

import org.thomasmore.entity.Arrangementtype;
import org.thomasmore.controller.util.JsfUtil;
import org.thomasmore.controller.util.JsfUtil.PersistAction;
import org.thomasmore.session.ArrangementtypeFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("arrangementtypeController")
@SessionScoped
public class ArrangementtypeController implements Serializable {

    @EJB
    private org.thomasmore.session.ArrangementtypeFacade ejbFacade;
    private List<Arrangementtype> items = null;
    private Arrangementtype selected;

    public ArrangementtypeController() {
    }

    public Arrangementtype getSelected() {
        return selected;
    }

    public void setSelected(Arrangementtype selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ArrangementtypeFacade getFacade() {
        return ejbFacade;
    }

    public Arrangementtype prepareCreate() {
        selected = new Arrangementtype();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ArrangementtypeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ArrangementtypeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ArrangementtypeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Arrangementtype> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Arrangementtype getArrangementtype(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Arrangementtype> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Arrangementtype> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Arrangementtype.class)
    public static class ArrangementtypeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ArrangementtypeController controller = (ArrangementtypeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "arrangementtypeController");
            return controller.getArrangementtype(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Arrangementtype) {
                Arrangementtype o = (Arrangementtype) object;
                return getStringKey(o.getArrangementtypeid());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Arrangementtype.class.getName()});
                return null;
            }
        }

    }

}
