package controllers;


import models.Administrateur;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by julio on 23/06/2016.
 */
public class AdministrateurControllerTest{

    @Test
    public void adminExist() {
        List<Administrateur> listAdmins;
        listAdmins = Administrateur.listAdministrateurs();

        assertEquals(listAdmins,null);
    }
}