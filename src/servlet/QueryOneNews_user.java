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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueryOneNews_user extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String newsid= request.getParameter("newsid");
            String table=request.getParameter("table");
            System.out.println(newsid+table);
            NewsRealeseDao newsRealeseDao = new NewsRealeseDao();
            try {
                HashMap<String,String> result = newsRealeseDao.queryOneNews(newsid,table);


                if (result.size()==0) {
                    System.out.println("查询失败========================QueryOneNews");
                    response.sendRedirect("content_user.jsp");


                } else {
                    HttpSession session=request.getSession();
                    session.setAttribute("newsid",result.get("newsid"));
                    session.setAttribute("head",result.get("head"));
                    session.setAttribute("author",result.get("author"));
                    session.setAttribute("time",result.get("time"));
                    session.setAttribute("newstype",result.get("newstype"));
                    session.setAttribute("content",result.get("content"));
                    System.out.println(session.toString());
                    response.sendRedirect("NewsDetails_user.jsp");
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
