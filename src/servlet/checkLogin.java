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
import java.util.logging.Level;
import java.util.logging.Logger;
public class checkLogin extends HttpServlet {



    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String user=request.getParameter("user");
        String pass=request.getParameter("pass");

          NewsRealeseDao newsRealeseDao=new NewsRealeseDao();
        try {    
            boolean checked=newsRealeseDao.ischecked(user,pass,"admin");//利用admin表进行身份验证
             if(checked)
             {
                 HttpSession session=request.getSession();
                 session.setAttribute("username",user);//设置用户的姓名
               //  response.sendRedirect("adminManager.jsp");
                  response.sendRedirect("check_pending.jsp");
             }
             else
             {
                 response.sendRedirect("login_admin.jsp");
                 
             }        
       } 
        catch (Exception ex) 
        {
            Logger.getLogger(checkLogin.class.getName()).log(Level.SEVERE, null, ex);
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
        return "Short description"+"public String getServletInfo() ";
    }// </editor-fold>
}
