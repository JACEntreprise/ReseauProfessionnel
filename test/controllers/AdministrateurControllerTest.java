package controllers;


import models.Administrateur;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by julio on 23/06/2016.
 */
public class AdministrateurControllerTest{

    @Test
    public void adminExist() {
        AdministrateurController cont = new AdministrateurController();
        boolean exite = cont.adminExist();

        assertEquals(false,exite);
    }
}