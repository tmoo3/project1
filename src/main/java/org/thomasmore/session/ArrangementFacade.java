/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.thomasmore.entity.Arrangement;

/**
 *
 * @author H
 */
@Stateless
public class ArrangementFacade extends AbstractFacade<Arrangement> {
    @PersistenceContext(unitName = "BUNGALOWDB")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArrangementFacade() {
        super(Arrangement.class);
    }
    
}
