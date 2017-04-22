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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
public class InsertOneNews extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String table=request.getParameter("table");
            System.out.println(table+"=================================================");
            NewsRealeseDao newsRealeseDao = new NewsRealeseDao();
            HashMap<String,String> addnews_list=new HashMap<>();
            addnews_list.put("newsid",request.getParameter("newsid"));
            addnews_list.put("head",request.getParameter("head"));
            addnews_list.put("content",request.getParameter("content"));
            addnews_list.put("publish_time",request.getParameter("publish_time"));
            addnews_list.put("issueuser",request.getParameter("author"));
            addnews_list.put("newstype",request.getParameter("newstype"));

            System.out.println(addnews_list.toString());
            try {
                 int result = newsRealeseDao.insertOneNews(addnews_list,table);//插入一片新的文章

                 System.out.println(result);
                if (result!=0) {
                    out.write("successful");
                   // response.sendRedirect("AllNews_admin.jsp");
                } else {
                    out.write("failed");
                   // response.sendRedirect("AddNews_admin.jsp");
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
