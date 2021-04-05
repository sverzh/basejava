package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.storage.SqlStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class ResumeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         SqlStorage sqlStorage = Config.get().getSqlStorage();
         try {
             Class.forName("org.postgresql.Driver");
         }
         catch (Exception e){
             e.getStackTrace();
         }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
         String name = request.getParameter("name");
         sqlStorage.sqlHelper.execute("SELECT * FROM resume ORDER BY full_name",ps -> {
             ResultSet rs = ps.executeQuery();
             try {
                 PrintWriter out = response.getWriter();
             out.println(
                     "<html>\n" +
                             "<head><title>Resumes</title></head>\n" +
                             "<body bgcolor = \"#f0f0f0\">\n" +
                             "<h1 align = \"center\">Resumes</h1>\n");
             out.println("<table width=\"50%\" height=\"90\" border=\"1\">\n"+
    "<tbody>" +
                     "<tr>" +
                     "<td width=\"300\" height=\"40\">uuid</td>" +
                     "<td width=\"450\">full_name</td>" +
                     "</tr>");
             while (rs.next()){
                out.println("<tr><td height=\"40\">"+rs.getString("uuid")+
                        "</td>" +
                        "<td>"+rs.getString("full_name")+"</td></tr>");
                    }
             out.println("</table></body></html>");
             }
             catch (Exception e){
             }
             return null;
         });
    }
}