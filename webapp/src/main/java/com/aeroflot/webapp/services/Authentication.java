package com.aeroflot.webapp.services;


import com.aeroflot.webapp.models.personrelated.Session;
import com.aeroflot.webapp.models.personrelated.User;
import com.aeroflot.webapp.repositories.ISessionRepository;
import com.aeroflot.webapp.repositories.IUserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;
import java.util.Optional;



// Dummy impl.
@Component
public class Authentication {

    private static final long DEFAULT_SESSION_TIMEOUT = 10 * 60 * 1000; // 10 minutes.

    @Autowired
    private IUserRepository    mUserRepository;
    @Autowired
    private ISessionRepository mSessionRepository;



    public boolean authenticate(Map<String, String> params, HttpSession httpSession) {

        String username = params.get("username");
        String password = DigestUtils.sha256Hex(params.get("password"));

        Optional<User> user = mUserRepository.findAll()
                                             .stream()
                                             .filter(obj -> obj.getUsername().equals(username))
                                             .findFirst();

        if (user.isPresent() &&
            user.get().getPassword().equals(password)) {

            httpSession.setMaxInactiveInterval(-1);

            Session newSession = new Session();
            newSession.setId(httpSession.getId());
            newSession.setUser(user.get());
            newSession.setExpiresAt(new Date().getTime() + DEFAULT_SESSION_TIMEOUT);

            mSessionRepository.save(newSession);

            return true;
        }
        else {
            return false;
        }
    }



    public User checkIfAuthenticated(HttpSession httpSession) {

        Optional<Session> session = mSessionRepository.findById(httpSession.getId());

        if (session.isPresent()) {

            if ((session.get().getExpiresAt() - new Date().getTime()) > 0) {
                return session.get().getUser();
            }
            else {
                mSessionRepository.delete(session.get());

                return null;
            }
        }

        return null;
    }

}
