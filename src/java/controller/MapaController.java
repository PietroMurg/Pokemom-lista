package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Mapa;
import model.MapaDAO;

@WebServlet(name = "MapaController", urlPatterns = {"/MapaController"})
public class MapaController extends HttpServlet {
    private int cod;
    private String nome;
    private String missao;
    private String clima;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //Verificando se há o recebimento do campo "cod"
        if(request.getParameter("cod_mapa") != null) {
            this.cod = Integer.parseInt(request.getParameter("cod_mapa"));
        }
        
        //Recebendo os valores enviados pelo formulário de cadastro
        this.nome = request.getParameter("nome_mapa");
        this.missao = request.getParameter("missao_mapa");
        this.clima = request.getParameter("clima_mapa");
        
        try {
        
            if(request.getParameter("cod_mapa") == null) {
                //Criando um novo objeto da classe Mapa com os valores recebidos
                Mapa objMapa = new Mapa(
                        this.nome,
                        this.missao,
                        this.clima
                );

                    MapaDAO dao = new MapaDAO();
                    dao.insertMapa(objMapa);
                    response.sendRedirect("lista.jsp");
            } else {
                Mapa objMapa = new Mapa(
                        this.cod,
                        this.nome,
                        this.missao,
                        this.clima
                );
                
                MapaDAO dao = new MapaDAO();
                dao.updateMapa(objMapa);
                response.sendRedirect("lista.jsp");
            }
        } catch(ClassNotFoundException | SQLException erro) {
            
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Game</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Ocorreu algum erro: " + erro + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
        
    }//Fim do else

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
