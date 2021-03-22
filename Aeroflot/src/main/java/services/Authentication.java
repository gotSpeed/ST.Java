package services;


import core.models.personrelated.Session;
import core.models.personrelated.User;
import dao.implementstions.SessionDaoImpl;
import dao.implementstions.UserDaoImpl;
import dao.interfaces.SessionDao;
import dao.interfaces.UserDao;
import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;



// Dummy impl.
public class Authentication {

    private static final long DEFAULT_SESSION_TIMEOUT = 10 * 60 * 1000;

    // TODO: Bean here.
    private static UserDao    mUserDao    = new UserDaoImpl();
    private static SessionDao mSessionDao = new SessionDaoImpl();



    public static boolean authenticate(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = DigestUtils.sha256Hex(request.getParameter("password"));

        Optional<User> ret = mUserDao.getAll()
                                     .stream()
                                     .filter(obj -> obj.getUsername().equals(username))
                                     .findFirst();

        if (ret.isPresent() &&
            ret.get().getPassword().equals(password)) {

            HttpSession httpSession = request.getSession();

            Session newSession = new Session();
            newSession.setId(httpSession.getId());
            newSession.setUser(ret.get());
            newSession.setExpiresAt(new Date().getTime() + DEFAULT_SESSION_TIMEOUT);

            mSessionDao.create(newSession);

            return true;
        }
        else {
            return false;
        }
    }



    @Nullable
    public static User checkIfAuthenticated(HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        Session session = mSessionDao.get(httpSession.getId());

        if (session != null) {

            if ((session.getExpiresAt() - new Date().getTime()) > 0) {
                return session.getUser();
            }
            else {
                mSessionDao.delete(session);

                return null;
            }
        }

        return null;
    }

}
