package org.thomasmore.controller;

import org.thomasmore.entity.Monitoring;
import org.thomasmore.controller.util.JsfUtil;
import org.thomasmore.controller.util.JsfUtil.PersistAction;
import org.thomasmore.session.MonitoringFacade;

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

@Named("monitoringController")
@SessionScoped
public class MonitoringController implements Serializable {

    @EJB
    private org.thomasmore.session.MonitoringFacade ejbFacade;
    private List<Monitoring> items = null;
    private Monitoring selected;

    public MonitoringController() {
    }

    public Monitoring getSelected() {
        return selected;
    }

    public void setSelected(Monitoring selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getMonitoringPK().setArrangementarrangementid(selected.getArrangement().getArrangementid());
        selected.getMonitoringPK().setMonitormonitorid(selected.getMonitor().getMonitorid());
    }

    protected void initializeEmbeddableKey() {
        selected.setMonitoringPK(new org.thomasmore.entity.MonitoringPK());
    }

    private MonitoringFacade getFacade() {
        return ejbFacade;
    }

    public Monitoring prepareCreate() {
        selected = new Monitoring();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MonitoringCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MonitoringUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MonitoringDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Monitoring> getItems() {
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

    public Monitoring getMonitoring(org.thomasmore.entity.MonitoringPK id) {
        return getFacade().find(id);
    }

    public List<Monitoring> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Monitoring> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Monitoring.class)
    public static class MonitoringControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MonitoringController controller = (MonitoringController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "monitoringController");
            return controller.getMonitoring(getKey(value));
        }

        org.thomasmore.entity.MonitoringPK getKey(String value) {
            org.thomasmore.entity.MonitoringPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new org.thomasmore.entity.MonitoringPK();
            key.setMonitormonitorid(Integer.parseInt(values[0]));
            key.setArrangementarrangementid(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(org.thomasmore.entity.MonitoringPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getMonitormonitorid());
            sb.append(SEPARATOR);
            sb.append(value.getArrangementarrangementid());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Monitoring) {
                Monitoring o = (Monitoring) object;
                return getStringKey(o.getMonitoringPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Monitoring.class.getName()});
                return null;
            }
        }

    }

}
