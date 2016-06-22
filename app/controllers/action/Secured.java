package controllers.action;

import org.apache.commons.lang3.StringUtils;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import repository.MembreRepository;

public class Secured extends Security.Authenticator {


    public Secured() {

    }

    @Override
    public String getUsername(Http.Context context) {
        final String email = context.session().get("membre");
        if (StringUtils.isNotBlank(email)) {
            return email;
        } else {
            return null;
        }
    }

    @Override
    public Result onUnauthorized(Http.Context context) {
        return redirect("/");
    }
}
