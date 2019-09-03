package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.storage.SqlStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    private SqlStorage sqlStorage;

    @Override
    public void init() {
        sqlStorage = new SqlStorage(Config.get().getDbUrl(),
                Config.get().getDbUser(),
                Config.get().getDbPassword());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("resumes", sqlStorage.getSortedStorage());
        request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
    }

    /*
    private void printResume(HttpServletResponse response) throws IOException {
        List<Resume> list = sqlStorage.getSortedStorage();
        PrintWriter writer = response.getWriter();

        writer.println("<table>\n" +
                "<tbody>\n" +
                "<table border=\"1\">\n" +
                "<tr>\n" +
                "<th>UUID</th>\n" +
                "<th>Full Name</th>\n" +
                "</tr>");

        for (Resume r : list) {
            writer.println("<tr>");
            writer.println("<td>" + r.getUuid() + "</td>");
            writer.println("<td>" + r.getFullName() + "</td>");
            writer.println("</tr>");
        }

        writer.println("</tbody\n" +
                "</table>");
    }
     */
}