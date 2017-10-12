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

@WebServlet(urlPatterns = {"/Gexhibitors"})
public class Gexhibitors extends HttpServlet 
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
            throws ServletException, IOException 
    {
        Statement stmt;
        
        //Set response content type
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
 
        String docType = "<!doctype html>";
        out.println(
                    docType + "<html>\n" + "<head>"
                        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"readData.css\">"
                        + "</head>"
                    );
        
       out.println(
                   "<div id=\"header\">\n" +
                        "<nav id = \"nav\">\n" +
                            "<ol type=\"I\">\n" +
                                "<li><a href=\"landing.html\">HOME</a></li>\n" +
                                "<li><a href=\"attLanding.html\">ABOUT</a></li>\n" +
                            "</ol>\n" +
                        "</nav>\n" +
                    "</div>"
                   );
       
        out.println(
                    "<div id=\"wrapper\">"
                   ); 
        
        try 
        {
            // Execute SQL query
            stmt = (Statement) conn.createStatement();
            String sql;
            sql = "SELECT name, product, date, location FROM exhibitors";
            ResultSet rs = stmt.executeQuery(sql);
            
            //extract data from result set
            while(rs.next())
            {
                //Retrieve by column name
                String name = rs.getString("name");
                String product = rs.getString("product");
                String date = rs.getString("date");
                //String time = rs.getString("time");
                String location = rs.getString("location");
                   
                //Display values
                out.println("<br><br><br>");
                out.println("<u><b>Exhibit Name:</b></u> " + name + " --");
                out.println("<u><b>Product Line:</b></u> " + product + " --");
                out.println("<u><b>Date:</b></u> " + date + " --");
                //out.println("<u><b>Time:</b></u> " + time + " --");
                out.println("<u><b>Location:</b></u> " + location + " --");
          
            }//end while loop
            
        out.println(
                    "</div>\n"
                   ); 
                    
        out.println(
                    "<div id=\"footer\">\n" +
                        "<iframe src=\"http://www.facebook.com/plugins/like.php?href=http%3A%2F%2Fwww.RyanDesigns.com&amp;layout=standard&amp;\n" +
                            "show_faces=true&amp;action=like&amp;colorscheme=light&amp\" style=\"overflow:hidden;width:250px;height:20px;float:left;\" scrolling=\"no\" frameborder=\"0\" allowTransparency=\"true\">\n" +
                            "<a href=\"http://www.trivoo.net\" class=\"fbook\">www.trivoo.net</a>\n" +
                        "</iframe>\n" +
                    "</div> "
                    );
        
        out.println(
                    "</body></html>"
                   );
        }//end try
            catch(Exception e)
            {
                e.printStackTrace();
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
