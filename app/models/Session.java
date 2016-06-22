package models;

/**
 * Created by brick on 01/06/2016.
 */
import java.util.Date;

public class Session {

    private final Membre membre;
    private final Date loggedIn;
    private final Date lastSeen;

    public Session(Membre membre, Date loggedIn, Date lastSeen) {
        this.membre = membre;
        this.loggedIn = loggedIn;
        this.lastSeen = lastSeen;
    }

    public Membre getMembre() {
        return membre;
    }

    public Date getLoggedIn() {
        return loggedIn;
    }

    public Date getLastSeen() {
        return lastSeen;
    }
}
