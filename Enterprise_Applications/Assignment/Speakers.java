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

@WebServlet(urlPatterns = {"/Speakers"})
public class Speakers extends HttpServlet 
{           
            String speaker_ID;
            String name;
            String email_address;
            String phone;
            String field;
            String sptime;
            String bio;
            
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
            stat.execute ("CREATE TABLE IF NOT EXISTS speakers (speaker_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name CHAR(40), email_address VARCHAR(40), phone VARCHAR(15), field CHAR(40), sptime VARCHAR(10), bio BLOB(400))");
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
        speaker_ID = request.getParameter("speaker_ID");
        name = request.getParameter("name");
        email_address = request.getParameter("email_address");
        phone = request.getParameter("phone");
        field = request.getParameter("field");
        sptime = request.getParameter("sptime");
        bio = request.getParameter("bio");
        
        try 
        {
            String query = "INSERT INTO speakers VALUES (?,?,?,?,?,?,?)";
            prepStat = (PreparedStatement) conn.prepareStatement(query);
            prepStat.setString(1, speaker_ID);
            prepStat.setString(2, name);
            prepStat.setString(3, email_address);
            prepStat.setString(4, phone);
            prepStat.setString(5, field);
            prepStat.setString(6, sptime);
            prepStat.setString(7, bio);
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
