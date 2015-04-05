package org.thomasmore.controller;

import org.thomasmore.entity.SupplierMaterial;
import org.thomasmore.controller.util.JsfUtil;
import org.thomasmore.controller.util.JsfUtil.PersistAction;
import org.thomasmore.session.SupplierMaterialFacade;

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

@Named("supplierMaterialController")
@SessionScoped
public class SupplierMaterialController implements Serializable {

    @EJB
    private org.thomasmore.session.SupplierMaterialFacade ejbFacade;
    private List<SupplierMaterial> items = null;
    private SupplierMaterial selected;

    public SupplierMaterialController() {
    }

    public SupplierMaterial getSelected() {
        return selected;
    }

    public void setSelected(SupplierMaterial selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getSupplierMaterialPK().setMaterialmaterialid(selected.getMaterial().getMaterialid());
        selected.getSupplierMaterialPK().setSuppliersupplierid(selected.getSupplier().getSupplierid());
    }

    protected void initializeEmbeddableKey() {
        selected.setSupplierMaterialPK(new org.thomasmore.entity.SupplierMaterialPK());
    }

    private SupplierMaterialFacade getFacade() {
        return ejbFacade;
    }

    public SupplierMaterial prepareCreate() {
        selected = new SupplierMaterial();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SupplierMaterialCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SupplierMaterialUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SupplierMaterialDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SupplierMaterial> getItems() {
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

    public SupplierMaterial getSupplierMaterial(org.thomasmore.entity.SupplierMaterialPK id) {
        return getFacade().find(id);
    }

    public List<SupplierMaterial> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SupplierMaterial> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SupplierMaterial.class)
    public static class SupplierMaterialControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SupplierMaterialController controller = (SupplierMaterialController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "supplierMaterialController");
            return controller.getSupplierMaterial(getKey(value));
        }

        org.thomasmore.entity.SupplierMaterialPK getKey(String value) {
            org.thomasmore.entity.SupplierMaterialPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new org.thomasmore.entity.SupplierMaterialPK();
            key.setMaterialmaterialid(Integer.parseInt(values[0]));
            key.setSuppliersupplierid(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(org.thomasmore.entity.SupplierMaterialPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getMaterialmaterialid());
            sb.append(SEPARATOR);
            sb.append(value.getSuppliersupplierid());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof SupplierMaterial) {
                SupplierMaterial o = (SupplierMaterial) object;
                return getStringKey(o.getSupplierMaterialPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SupplierMaterial.class.getName()});
                return null;
            }
        }

    }

}
