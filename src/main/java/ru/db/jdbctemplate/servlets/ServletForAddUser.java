package ru.db.jdbctemplate.servlets;


import ru.db.jdbctemplate.config.HibernateConfig;
import ru.db.jdbctemplate.models.Car;
import ru.db.jdbctemplate.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 25.11.2020
 * ServletForAddUser
 *
 * @author Sherstobitov Mikhail
 * @version v1.0
 */
@WebServlet("/addUser")
public class ServletForAddUser extends HttpServlet {

    private Session session;
    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();
        Configuration configuration;

        try {
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
            configuration=HibernateConfig.getProperty(properties);

            SessionFactory sessionFactory = configuration.buildSessionFactory();
            this.session = sessionFactory.openSession();

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/addUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");
        int age=Integer.parseInt(req.getParameter("age"));

        session.beginTransaction();
        session.save(new User(firstName, lastName, age));
        session.getTransaction().commit();
        req.getServletContext().getRequestDispatcher("/jsp/addUser.jsp").forward(req, resp);

    }
}
