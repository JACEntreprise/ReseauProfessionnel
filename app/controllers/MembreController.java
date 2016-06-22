package controllers;

import controllers.action.Secured;
import models.Membre;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;

/**
 * Created by brick on 22/06/2016.
 */
@Security.Authenticated(Secured.class)
public class MembreController extends Controller {


}

