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

    private SupplierMaterialFacade getFacade() {
        return ejbFacade;
    }

    public SupplierMaterial prepareCreate() {
        selected = new SupplierMaterial();
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



    public List<SupplierMaterial> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SupplierMaterial> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

 

}
