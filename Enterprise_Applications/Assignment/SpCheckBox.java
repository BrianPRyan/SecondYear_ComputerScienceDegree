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

@WebServlet(urlPatterns = {"/SpCheckBox"})
public class SpCheckBox extends HttpServlet {

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
             String docType="<!doctype HTML>";
           
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
        
        out.println(
                    "<br><br><p>You are scheduled to see these speakers<br><br></>" + 
                    "  <li><b>Speaker 1:</b> "
                    + request.getParameter("S1") + "\n" +
                    "  <li><b>Speaker 2:</b> "
                    + request.getParameter("S2") + "\n" +
                    "  <li><b>Speaker 3:</b> "
                    + request.getParameter("S3") + "\n" +
                    "  <li><b>Speaker 4:</b> "
                    + request.getParameter("S4") + "\n"
                    );
        
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
