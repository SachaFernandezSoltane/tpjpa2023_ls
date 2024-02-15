package servelet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jpa.DAO.UserDAO;
import jpa.entities.Ticket;
import jpa.entities.User;

@WebServlet(name="userinfo",
        urlPatterns={"/UserInfo"})
public class UserInfo extends HttpServlet {

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<HTML>\n<BODY>\n" +
                "<H1>Données insérées dans la table User : </H1>\n" +
                "<UL>\n" +
                " <LI>Nom: "
                + request.getParameter("name") + "\n" +
                " <LI>description: "
                + request.getParameter("email") + "\n" +
                " <LI>status: "
                + request.getParameter("password") + "\n" +
                "</UL>\n" +
                "</BODY></HTML>");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(name, email, password);
        UserDAO userDAO = new UserDAO();
        userDAO.save(user);
    }
}

