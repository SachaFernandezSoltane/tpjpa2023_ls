package servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jpa.DAO.UserDAO;
import jpa.entities.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "userlist", urlPatterns = {"/UserList"})
public class UserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Récupérer les données depuis la base de données
        UserDAO userDAO = new UserDAO();
        List<User> userList = userDAO.findAll(); // Imaginons que vous avez une méthode pour récupérer tous les utilisateurs

        // Générer la réponse HTML avec les données récupérées
        out.println("<HTML>\n<BODY>\n" +
                "<H1>Liste des utilisateurs :</H1>\n" +
                "<UL>");

        for (User user : userList) {
            out.println("<LI>Nom: " + user.getUsername() + ", Email: " + user.getEmail() + "</LI>");
        }

        out.println("</UL>\n" +
                "</BODY></HTML>");
    }

    // doPost() peut être laissée vide ou supprimée si non utilisée
}

