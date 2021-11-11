package ru.itis.Makhsotova.Enclave.servlets;

import ru.itis.Makhsotova.Enclave.Exceptions.FailedHashForPassword;
import ru.itis.Makhsotova.Enclave.Exceptions.InvalidEmailException;
import ru.itis.Makhsotova.Enclave.Exceptions.OccupiedEmailException;
import ru.itis.Makhsotova.Enclave.Exceptions.WeakPasswordException;
import ru.itis.Makhsotova.Enclave.dto.SignUpForm;
import ru.itis.Makhsotova.Enclave.services.SignUpService;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private ServletContext servletContext;
    private SignUpService signUpService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
        signUpService = (SignUpService) servletContext.getAttribute("signUpService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("password").equals(req.getParameter("repeatPassword"))) {
            SignUpForm signUpForm = SignUpForm.builder()
                    .firstName(req.getParameter("firstName"))
                    .lastName(req.getParameter("lastName"))
                    .email(req.getParameter("email"))
                    .password(req.getParameter("password"))
                    .build();
            try {
                HttpSession session = req.getSession();
                UUID uuid = this.signUpService.signUp(signUpForm, session);
                Cookie cookie = new Cookie(SignUpService.SIGN_UP_COOKIE_NAME, uuid.toString());
                cookie.setMaxAge(60 * 60 * 24 * 365);
                resp.addCookie(cookie);
                req.setAttribute("user", signUpService.getUser());
                resp.sendRedirect(servletContext.getContextPath() + "/profile");
                return;
            } catch (InvalidEmailException e) {
                req.setAttribute("message", "Wrong email");
            } catch (OccupiedEmailException e) {
                req.setAttribute("message", "This email is already taken");
            } catch (WeakPasswordException e) {
                req.setAttribute("message", "Password is too short (minimum length = 6 characters)");
            } catch (FailedHashForPassword ex) {
                req.setAttribute("message", "Failed to save password, please again");
            }
        }
        else {
            req.setAttribute("message", "Passwords don't match");
         }
        servletContext.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp").forward(req, resp);
    }
}
