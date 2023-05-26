package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;


@WebServlet(name = "LoginController", urlPatterns = {"/login_controller"})
public class LoginController extends HttpServlet {
    //Atributos
    private String user;
    private String pass;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        this.user = request.getParameter("user");
        this.pass = request.getParameter("pass");
        
        User newUser = new User(this.user, this.pass);
        
        try {
        
        if(newUser.isLogged()) {
            HttpSession session = request.getSession();
            session.setAttribute("userSession", newUser);
            //request.setAttribute("userLogged", newUser);
            request.getRequestDispatcher("index.html")
                    .forward(request, response);
        } else {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Game</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<script>");
                out.println("alert('Acesso negado!');");
                out.println("window.location.replace('login.html');");
                out.println("</script>");
                out.println("</body>");
                out.println("</html>");
            }
        }
        } catch(ClassNotFoundException | SQLException erro) {
                try (PrintWriter out = response.getWriter()){
                    out.print(erro);
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
