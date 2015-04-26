/*
 * The MIT License
 *
 * Copyright 2015 H.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.thomasmore.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.thomasmore.controller.SessionDto;
import org.thomasmore.entity.Users;

/**
 *
 * @author H & lucs
 */
@Named(value = "login")
@RequestScoped
public class LoginController {

    @PersistenceContext(unitName = "BUNGALOWDB")
    private EntityManager em;
    private LoginDto dto;

    @Inject
    private SessionDto sessionDto;

    @PostConstruct
    public void init() {
        dto = new LoginDto();
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    /* Will be using NamedQuery but we may also use NativeQuery and SQL commands :) .
     public String getPasswordByName(String userName) {
     String namequery = "SELECT USERPASSWORD FROM USERS u WHERE u.USERNAME='" + userName + "'";
     Query query = em.createNativeQuery(namequery);
     try {
     String myuser = (String) query.getSingleResult().toString();
     if (myuser.length() > 0) {
     System.out.println("Password from DB: " + myuser);
     return myuser;
     } else {
     return null;
     }
     }
     catch (NoResultException nre) {
     return null;
     }
     catch (NonUniqueResultException nure) {
     return null;
     }
     }
     */
    public String getPasswordByName(String userName) {
        Users u = null;
        try {
            u = (Users) em.createNamedQuery("Users.findByUsername").setParameter("username", userName).getSingleResult();
        } catch (NoResultException e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Invalid login. Wrong username or password!");
            facesContext.addMessage(null, facesMessage);
            return null;
        }
        try {
            String myuser = u.getUserpassword();
            if (myuser.length() > 0) {
                System.out.println("Password from DB: " + myuser);
                return myuser;
            } else {
                return null;
            }
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }

    public String login() {
        String up = null;
        up = getPasswordByName(dto.getName());
        if (up == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Invalid login. Wrong username or password!");
            facesContext.addMessage(null, facesMessage);
            return null;
        } else {
            if (up.equals(generateHash(dto.getPassword()))) {
                sessionDto.getUserDto().setLoggedIn(true);
                return "/main.xhtml?faces-redirect=true";
            } else {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Invalid login. Wrong username or password!");
                facesContext.addMessage(null, facesMessage);
                return null;
            }
        }

    }

    public LoginDto getDto() {
        return dto;
    }

    public void setDto(LoginDto dto) {
        this.dto = dto;
    }

    public String generateHash(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            //e.printStackTrace();
            System.out.println("Error generating hash!");
        }
        System.out.println("Generated hash: " + generatedPassword);
        return generatedPassword;
    }
}
