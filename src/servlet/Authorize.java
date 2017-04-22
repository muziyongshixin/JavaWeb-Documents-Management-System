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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Authorize extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            NewsRealeseDao newsRealeseDao=new NewsRealeseDao();

            String alluser=request.getParameter("user_list");
            String newsid=request.getParameter("newsid");
            System.out.println(newsid+"================================================="+alluser);


            String tem[]=alluser.split(",");
            ArrayList<String> user_list=new ArrayList<>();
            for(int i=0;i<tem.length;i++)
            {
                user_list.add(tem[i]);
            }

            try {
                int result = newsRealeseDao.AuthorizeOneNews(user_list,newsid);//插入一片新的文章

                System.out.println(result);
                if (result!=0) {
                    String re="successful,成功授权给"+alluser;
                    out.write(re);
                    // response.sendRedirect("AllNews_admin.jsp");
                } else {
                    out.write("failed,授权失败！请检查后重试");
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
