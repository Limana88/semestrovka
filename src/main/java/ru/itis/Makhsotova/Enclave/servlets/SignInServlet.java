package ru.itis.Makhsotova.Enclave.servlets;

import ru.itis.Makhsotova.Enclave.Exceptions.FailedHashForPassword;
import ru.itis.Makhsotova.Enclave.Exceptions.NoSuchEmailException;
import ru.itis.Makhsotova.Enclave.dto.SignInForm;
import ru.itis.Makhsotova.Enclave.models.User;
import ru.itis.Makhsotova.Enclave.services.SignInService;
import ru.itis.Makhsotova.Enclave.services.SignInServiceImpl;
import ru.itis.Makhsotova.Enclave.services.SignUpServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private  SignInService signInService;
    private ServletContext servletContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
        signInService = (SignInService) servletContext.getAttribute("signInService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("email") != null) {
            SignInForm signInForm = SignInForm.builder()
                    .email(req.getParameter("email"))
                    .password(req.getParameter("password"))
                    .build();
            try {
                if (signInService.signIn(signInForm, session)) {
                    UUID uuid = signInService.getUUID(signInService.getUser());
                    session.setAttribute("isAuthenticated", true);
                    resp.sendRedirect(servletContext.getContextPath() + "/profile");
                    return;
                } else req.setAttribute("message", "Wrong password");
            } catch (NoSuchEmailException ex) {
                req.setAttribute("message", "No email: " + signInForm.getEmail());
            } catch (FailedHashForPassword ex) {
                throw new IllegalArgumentException(ex);
            }
        }
        servletContext.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp").forward(req, resp);
    }
}
