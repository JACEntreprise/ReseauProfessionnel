package model;

import models.*;
import org.junit.*;

import static junit.framework.TestCase.*;

/**
 * Created by brick on 04/06/2016.
 */
public class MembreTest{

    @Before
    public void setup(){
        Membre membre = new Membre();
        Particulier particulier = new Particulier();
        particulier.prenom = "Cheikh";
        particulier.nom = "SALL";
        membre.email = "csall@gmail.com";
        membre.motDePasse = "pass";

        membre.save();
    }

    @Test
    public void testInscription(){
        //on recupère le membre cheikh par son mail
        Membre membre1 = Membre.byEmail("csall@gmail.com");

        //voir si le membre existe
        assertNotNull(membre1);

        //voir si les mot de passe sont les meme
        assertEquals("pass",membre1.motDePasse);
    }
}
