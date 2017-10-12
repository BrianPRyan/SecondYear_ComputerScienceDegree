/*
Brian Ryan E.A.D Assignment 2016
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


@WebServlet(urlPatterns = {"/Workshops"})
public class Workshops extends HttpServlet 
{            
            String event_ID;
            String name;
            String manager;
            String date;
            String time;
            String location;
            
            Connection conn;
            PreparedStatement prepStat;
            Statement stat;

    public void init() throws ServletException 
    {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "ead_assignment";
        String userName = "root";
        String password = "password";
        
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection (url + dbName,userName,password);
            stat = (Statement) conn.createStatement();
            //stat.execute("DROP TABLE if exists speakers");
            stat.execute ("CREATE TABLE IF NOT EXISTS workshops (event_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name CHAR(40), manager CHAR(40), date DATE, wstime VARCHAR(10), location CHAR(40))");
        }
            catch (Exception e) 
            {
               System.err.println(e);
            }
    }   // end of init () method 

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {    
        event_ID = request.getParameter("event_ID");
        name = request.getParameter("name");
        manager = request.getParameter("manager");
        date = request.getParameter("date");
        time = request.getParameter("wstime");
        location = request.getParameter("location");
        
        try 
        {
            String query = "INSERT INTO workshops VALUES (?,?,?,?,?,?)";
            prepStat = (PreparedStatement) conn.prepareStatement(query);
            prepStat.setString(1, event_ID);
            prepStat.setString(2, name);
            prepStat.setString(3, manager);
            prepStat.setString(4, date);
            prepStat.setString(5, time);
            prepStat.setString(6, location);
            prepStat.executeUpdate();
            response.sendRedirect("about.html");
        }
            catch (Exception e)
            {
                System.err.println(e);
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
