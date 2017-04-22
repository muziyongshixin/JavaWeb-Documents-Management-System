/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import dao.NewsRealeseDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteOneNews extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String newsid = request.getParameter("newsid");
            String table = request.getParameter("table");
            String destination=request.getParameter("destination");
            String function=request.getParameter("function");

            NewsRealeseDao newsRealeseDao = new NewsRealeseDao();
            try {
                String newsRealese=null;
                if(function.equals("check_delete"))
                {
                    newsRealese = newsRealeseDao.AdmindeleteCheck_pendingNews(newsid);
                }
                else if(function.equals("user_delete")){
                    newsRealese = newsRealeseDao.deleteOneNews(newsid, table);
                }


                System.out.println("String:" + newsRealese);
                if (newsRealese != null) {
                    response.sendRedirect(destination);
                } else {
                    response.sendRedirect("a_addNews.jsp");
                }
            } catch (Exception ex) {
                Logger.getLogger(checkLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
