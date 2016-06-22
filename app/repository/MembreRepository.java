package repository;

import com.google.common.base.Preconditions;
import models.Membre;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MembreRepository {

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    private String salt;

    public static final MembreRepository instance = new MembreRepository();

    private MembreRepository() {
        this.salt = BCrypt.gensalt();
    }



    public String hash(String value) {
        return BCrypt.hashpw(value, salt);
    }

    private boolean compare(String password, String hashed) {
        return hash(password).equals(hashed);
    }

}

