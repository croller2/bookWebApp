package edu.wctc.car.controllers;

import edu.wctc.car.models.Book;
import edu.wctc.car.models.BookFacade;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chris
 */
@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private BookFacade bs;
    private String BOOK_LIST_PAGE = "/bookList.jsp";
    private String ADD_BOOK_PAGE = "/addBook.jsp";
    private String UPDATE_BOOK_PAGE = "/updateBook.jsp";
    private String PAGE_NOT_FOUND = "/error_404.jsp";
    private String ACTION_PARAM = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String operation = request.getParameter("op");
        RequestDispatcher view = request.getRequestDispatcher(PAGE_NOT_FOUND);
        try {
            if (operation == null) {
                List<Book> listOfBooks = refreshBookList(bs);
                request.setAttribute("bookList", listOfBooks);
                view = request.getRequestDispatcher(BOOK_LIST_PAGE);

            }
            view.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            view = request.getRequestDispatcher(PAGE_NOT_FOUND);
            view.forward(request, response);
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

    public List<Book> refreshBookList(BookFacade bs) throws ClassNotFoundException, SQLException {
        return bs.findAll();
    }
}
