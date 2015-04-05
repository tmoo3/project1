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
package org.thomasmore.rest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.thomasmore.entity.Setup;
import org.thomasmore.entity.Users;


/**
 *
 * @author H
 */
@Singleton
@Startup
public class SampleDB {

    @PersistenceContext(unitName = "BUNGALOWDB")
    private EntityManager em;

    @PostConstruct
    public void init() {
        List<Object> objectsToSave = new LinkedList<>();
        Setup setupdbEntity = new Setup();
        setupdbEntity.setSetupid(1);
        setupdbEntity.setCommunication("We may show a message about material order here. Or show the firm's motto to employees. This message is stored in the DB.");
        objectsToSave.add(setupdbEntity);
        
        String p = generateHash("a");
        Users userEntity = new Users();
        userEntity.setUserid(1);
        userEntity.setUsername("a");
        userEntity.setUserpassword(p);
        objectsToSave.add(userEntity);
        for (Object objectsToSave1 : objectsToSave) {
            em.persist(objectsToSave1);
        }

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
        }
        catch (NoSuchAlgorithmException e) {
            //e.printStackTrace();
            System.out.println("Error generating hash!");
        }
        System.out.println("Generated hash: " + generatedPassword);
        return generatedPassword;
    }
}