package servlets;


import services.Authentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(name = "AuthenticationServlet", urlPatterns = {"/auth"})
public class AuthenticationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
                                                                                   ServletException,
                                                                                   IOException {

        request.getRequestDispatcher("/authentication.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
                                                                                    ServletException,
                                                                                    IOException {

        if (Authentication.authenticate(request)) {
            response.sendRedirect("/home");
        }
        else {
            request.getRequestDispatcher("/authentication.jsp").forward(request, response);
        }
    }

}
