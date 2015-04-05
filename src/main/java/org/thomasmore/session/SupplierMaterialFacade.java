/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.thomasmore.entity.SupplierMaterial;

/**
 *
 * @author H
 */
@Stateless
public class SupplierMaterialFacade extends AbstractFacade<SupplierMaterial> {
    @PersistenceContext(unitName = "BUNGALOWDB")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SupplierMaterialFacade() {
        super(SupplierMaterial.class);
    }
    
}
