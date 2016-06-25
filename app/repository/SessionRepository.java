package repository;

import com.google.common.base.Preconditions;
import models.Session;
import models.Membre;
import org.apache.commons.lang3.StringUtils;
import play.Play;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * Created by brick on 01/06/2016.
 */
public class SessionRepository {
    private final Map<String, Session> repo;
    private final long ttl;

    public static final SessionRepository instance = new SessionRepository();

    private SessionRepository() {
        this.repo = new HashMap<>();
        this.ttl = 7200000;
    }

    public boolean isLoggedIn(String email) {
        final Session session = repo.get(email);
        final boolean loggedIn = Optional.ofNullable(session).map(s -> {
            Date now = new Date();
            final long inactivityPeriod = now.getTime() - s.getLastSeen().getTime();
            return inactivityPeriod < ttl;
        }).orElse(false);

        if (!loggedIn) repo.remove(email);
        else repo.put(email, new Session(session.getMembre(), session.getLoggedIn(), new Date()));

        return loggedIn;
    }

    public void login(Membre membre) {
        Preconditions.checkArgument(!isLoggedIn(membre.getEmail()), "user is already logged in");
        final Date now = new Date();
        repo.put(membre.getEmail(), new Session(membre, now, now));
    }

    public void logout(String email) {
        repo.remove(email);
    }
}
