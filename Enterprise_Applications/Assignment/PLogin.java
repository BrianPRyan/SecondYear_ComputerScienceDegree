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
import java.sql.ResultSet;

@WebServlet(urlPatterns = {"/PLogin"})
public class PLogin extends HttpServlet 
{
            Connection conn;
            PreparedStatement prepStat;
            Statement stmt;
            
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
            stmt = (Statement) conn.createStatement();

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
        Statement stmt;
        
        String uname = request.getParameter("username");
        String pwd =  request.getParameter("password");
        
        boolean found =  false;
        
        //Set response content type
        //response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        
        //String title = "Database Result";
        //String docType = "<!doctype html>";
        //out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" + "<h1 align=\"center\">" + title + "</h1>\n");
        try
        {
                        // Execute SQL query
            stmt = (Statement) conn.createStatement();
            String query;
            query = "SELECT username, password FROM attregistrations";
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            
            //extract data from result set
            while(rs.next())
            {
                //Retrieve by column name
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println(username);
                System.out.println(password);
                
                if(uname.equals(username) && pwd.equals(password))
                {
 
                    found = true;
                }
                
            }//end while loop
                if(found)
                {
 
                    //System.out.println("Ok");
                    response.sendRedirect("attLanding.html");
                }
                
                else
                {
                    //System.out.println("Login Fail, please try again");
                    response.sendRedirect("loginFailure.html");
                }

                    //out.println("</body></html>");
        }
            catch(Exception e)
            {   
                System.err.println(e);
            }
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
