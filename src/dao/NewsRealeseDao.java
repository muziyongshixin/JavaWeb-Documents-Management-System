package dao;

import com.lut.beans.NewsRealese;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class NewsRealeseDao {

    public static String driver = "com.mysql.jdbc.Driver";//定义驱动
    public static String url = "jdbc:mysql://localhost:3306/myNews?useUnicode=true&characterEncoding=utf-8";//定义URL
    public static String databseUser = "root";//定义用户名
    public static String password = "root";//定义密码


    private ArrayList getNews(Statement stat, String sql)//处理具体的新闻查询请求，返回所有结果
    {
        ArrayList newsRealese = new ArrayList();
        try {
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {   //实例化VO
                NewsRealese news = new NewsRealese();
                news.setNewsId(rs.getString("newsid"));
                news.setContent(rs.getString("content"));
                news.setHead(rs.getString("head"));
                news.setIssueuser(rs.getString("issueuser"));
                news.setPublish_time(rs.getString("publish_time"));
                news.setNewstype(rs.getString("newstype"));
                newsRealese.add(news);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return newsRealese;
        }
    }


    public ArrayList UserQueryAllNews(String username, String table) throws Exception {//用户查看自己所有的文章
        Connection conn = null;
        ArrayList newsRealese = new ArrayList();
        try {
            //获取连接
            Class.forName(driver);

            conn = DriverManager.getConnection(url, databseUser, password);
            //运行SQL语句
            String sql = "select * from " + table + " where issueuser='" + username + "' order by publish_time desc";
            Statement stat = conn.createStatement();
            newsRealese = getNews(stat, sql);
            if (newsRealese.size() == 0) {
                System.out.println("查询不到任何信息============");
                return null;
            }

            stat.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {//关闭连接
                if (conn != null) {
                    conn.close();

                }
            } catch (Exception ex) {
            }
            return newsRealese;
        }
    }


    public ArrayList UserQueryOthersNews(String username) throws Exception {//查看自己可见的其他人的所有的文章
        Connection conn = null;
        ArrayList rt = new ArrayList();
        try {
            //获取连接
            Class.forName(driver);

            conn = DriverManager.getConnection(url, databseUser, password);
            //运行SQL语句
            String sql = "select * from news where newsId IN (select newsId from authority where username=?) order by publish_time desc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsRealese news = new NewsRealese();
                news.setNewsId(rs.getString("newsid"));
                news.setContent(rs.getString("content"));
                news.setHead(rs.getString("head"));
                news.setIssueuser(rs.getString("issueuser"));
                news.setPublish_time(rs.getString("publish_time"));
                news.setNewstype(rs.getString("newstype"));
                rt.add(news);
            }
            if (rt.size() == 0) {
                System.out.println("查询不到任何信息============Dao.UserQueryOthersNews");
                return null;
            }

            conn.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {//关闭连接
                if (conn != null) {
                    conn.close();

                }
            } catch (Exception ex) {
            }
            return rt;
        }
    }


    public ArrayList AdministorQueryAllNews(String username) throws Exception {//管理员查看所有的文章
        Connection conn = null;
        ArrayList newsRealese = new ArrayList();
        try {
            //获取连接  
            Class.forName(driver);

            conn = DriverManager.getConnection(url, databseUser, password);
            //运行SQL语句 
            String sql = "select * from news order by publish_time desc";//获取
            Statement stat = conn.createStatement();
            newsRealese = getNews(stat, sql);
            if (newsRealese.size() == 0) {
                System.out.println("查询不到任何信息============");
                return null;
            }

            stat.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {//关闭连接
                if (conn != null) {
                    conn.close();

                }
            } catch (Exception ex) {
            }
            return newsRealese;
        }
    }

    //查询一个消息
    public ArrayList AdministorQueryCheckPending() throws Exception {//查询所有的待审核的文章
        Connection conn = null;
        ArrayList newsRealese = new ArrayList();

        try {
            //获取连接  
            Class.forName(driver);

            conn = DriverManager.getConnection(url, databseUser, password);//不安全
            //运行SQL语句 
            Statement stat = conn.createStatement();
            String sql = "select * from check_pending order by publish_time desc";//获取newsid，使用？代替字符串，以免会发生错误
            Statement st = conn.createStatement();
            newsRealese = getNews(st, sql);

            stat.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {//关闭连接
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception ex) {
            }
            return newsRealese;
        }
    }


    //用户删除数据
    public String deleteOneNews(String newsid, String table) throws Exception {
        Connection conn = null;
        ArrayList newsRealese = new ArrayList();
        try {
            //获取连接  
            Class.forName(driver);
            conn = DriverManager.getConnection(url, databseUser, password);//不安全
            //运行SQL语句

            String sql_move = "insert into dustbin select * from news where newsId='" + newsid + "'";

            String sql_delete = "DELETE FROM " + table + " WHERE newsId='" + newsid + "'";//获取newsid，使用？代替字符串，以免会发生错误
            Statement ps = conn.createStatement();

            int rs_move = ps.executeUpdate(sql_move);
            if (rs_move != 0) {
                int rs = ps.executeUpdate(sql_delete);
                if (rs == 0)
                    System.out.println("删除失败==================NewsrealeaseDao");
            } else {
                System.out.println("插入到dustbin出错=============NewsrealeaseDao");
            }


        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {//关闭连接
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
            }
            return newsRealese.toString();
        }
    }


    public String AdmindeleteCheck_pendingNews(String newsid) throws Exception {
        Connection conn = null;
        int rs = 0;
        try {
            //获取连接
            Class.forName(driver);
            conn = DriverManager.getConnection(url, databseUser, password);//不安全
            //运行SQL语句


            String sql_delete = "DELETE FROM  check_pending WHERE newsId='" + newsid + "'";//获取newsid，使用？代替字符串，以免会发生错误
            Statement ps = conn.createStatement();


            rs = ps.executeUpdate(sql_delete);
            if (rs == 0) {
                System.out.println("删除失败==================NewsrealeaseDao");
                return null;
            }
            else
                return "成功删除"+rs;
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {//关闭连接
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
            }

        }
        return null;
    }


    public int AuthorizeOneNews(ArrayList<String> userlist, String newsid) {
        int count = 0;
        Connection conn = null;

        try {
            //获取连接
            Class.forName(driver);
            conn = DriverManager.getConnection(url, databseUser, password);//不安全
            //运行SQL语句
            Statement stat = conn.createStatement();

            for (int i = 0; i < userlist.size(); i++) {
                String sql = "insert into authority  VALUES(?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, newsid);
                ps.setString(2, userlist.get(i));
                System.out.println(ps.toString());
                count += ps.executeUpdate();

            }

            System.out.println("成功添加" + count + "行");
            stat.close();
            conn.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {//关闭连接
                if (conn != null) {
                    conn.close();

                }
            } catch (Exception ex) {
            }
            return count;
        }
    }


    //插入数据
    public int insertOneNews(HashMap<String, String> addnews_list, String table) throws Exception {//插入一个新的新闻
        Connection conn = null;

        try {
            //获取连接  
            Class.forName(driver);
            conn = DriverManager.getConnection(url, databseUser, password);//不安全
            //运行SQL语句 
            Statement stat = conn.createStatement();
            String sql = "insert into " + table + "  VALUES(?,?,?,?,?,?)";//获取newsid，使用？代替字符串，以免会发生错误
            PreparedStatement ps = conn.prepareStatement(sql);
            // ps.setString(1, table);

            ps.setString(1, addnews_list.get("newsid"));
            ps.setString(2, addnews_list.get("head"));
            ps.setString(3, addnews_list.get("content"));
            ps.setString(4, addnews_list.get("publish_time"));
            ps.setString(5, addnews_list.get("issueuser"));
            ps.setString(6, addnews_list.get("newstype"));


            System.out.println(addnews_list.get("newstype") + "===============" + ps.toString());
            int i = ps.executeUpdate();
            System.out.println("成功添加" + i + "行");
            stat.close();
            conn.close();
            return i;
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {//关闭连接
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception ex) {
            }
        }
        return 0;
    }


    public HashMap<String, String> queryOneNews(String newsid, String table) throws SQLException {
        Connection connection = null;
        HashMap<String, String> rt = new HashMap<>();
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, databseUser, password);
            String sql = "select * from " + table + " where newsId='" + newsid + "'";


            Statement ps = connection.createStatement();

            System.out.println("将要执行的=====" + sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                rt.put("newsid", rs.getString("newsId"));
                rt.put("head", rs.getString("head"));
                rt.put("content", rs.getString("content"));
                rt.put("time", rs.getString("publish_time"));
                rt.put("author", rs.getString("issueuser"));
                rt.put("newstype", rs.getString("newstype"));

                return rt;
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return rt;

    }


    //更新数据
    public int updateOneNews(HashMap<String, String> addnews_list) throws Exception {
        Connection conn = null;

        try {
            //获取连接  
            Class.forName(driver);
            conn = DriverManager.getConnection(url, databseUser, password);//不安全

            //获取newsid，使用？代替字符串，以免会发生错误
            String sql = "UPDATE news set head=?,content=?,publish_time=?,issueuser=?,newstype=? where newsId=?";


            PreparedStatement ps = conn.prepareStatement(sql);


            ps.setString(6, addnews_list.get("newsid"));
            ps.setString(1, addnews_list.get("head"));
            ps.setString(2, addnews_list.get("content"));
            ps.setString(3, addnews_list.get("publish_time"));
            ps.setString(4, addnews_list.get("issueuser"));
            ps.setString(5, addnews_list.get("newstype"));

            System.out.println(ps.toString());
            int i = ps.executeUpdate();

            System.out.println("成功更新" + i + "行");

            conn.close();
            return i;
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {//关闭连接
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception ex) {
            }
        }
        return 0;
    }


    public boolean CheckNews(String newsid)//审核通过一片文章
    {
        String sql_insert = "insert into news select * from check_pending where newsId=?";
        String sql_delete = "delete from check_pending where newsId=?";
        Connection con = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, databseUser, password);
            PreparedStatement ps = con.prepareStatement(sql_insert);
            ps.setString(1, newsid);
            int result = ps.executeUpdate();
            if (result != 0) {
                ps = con.prepareStatement(sql_delete);
                ps.setString(1, newsid);
                int r = ps.executeUpdate();
                if (r != 0)
                    return true;
                else
                    return false;
            } else
                return false;

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;

    }


    public boolean ResumeNews(String newsid)//审核通过一片文章
    {
        String sql_insert = "insert into news select * from dustbin where newsId=?";
        String sql_delete = "delete from dustbin where newsId=?";
        Connection con = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, databseUser, password);
            PreparedStatement ps = con.prepareStatement(sql_insert);
            ps.setString(1, newsid);
            int result = ps.executeUpdate();
            if (result != 0) {
                ps = con.prepareStatement(sql_delete);
                ps.setString(1, newsid);
                int r = ps.executeUpdate();
                if (r != 0)
                    return true;
                else
                    return false;
            } else
                return false;

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;

    }


    public boolean ischecked(String user, String pass, String table) throws Exception {//登录验证  table表示从哪个表中查询
        Connection conn = null;
        //获取连接
        Class.forName(driver);


        String sql = "select password from " + table + " where username='" + user + "'";
        System.out.println(sql);
        try {
            conn = DriverManager.getConnection(url, databseUser, password);//不安全
            System.out.println("建立database连接");

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                if (rs.getString("password").equals(pass))
                    return true;
                else
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


    private static void show(ArrayList in) {
        for (int i = 0; i < in.size(); i++) {
            NewsRealese tem = (NewsRealese) in.get(i);

            System.out.println(tem.getHead() + "======" + tem.getContent() + "@@@" + tem.getIssueuser() + "=====" + tem.getPublish_time());
        }
    }


    public static void main(String arg[]) {
        HashMap<String, String> addnews_list = new HashMap<>();
        addnews_list.put("newsid", "1234567892");
        addnews_list.put("head", "第2条测试");
        addnews_list.put("content", "这个是第一条测试文章，里面的内容都是随便乱写的~亏大发老师教辅拉世纪东方拉开圣诞节福利卡士大夫拉克丝打开发送大六块腹肌");
        addnews_list.put("publish_time", "2017-4-4/12:12:12");
        addnews_list.put("issueuser", "木子勇士心");
        addnews_list.put("newstype", "1");

        NewsRealeseDao nd = new NewsRealeseDao();


//        try {
//            nd.insertOneNews(addnews_list);//插入一个订单
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        try {

            ArrayList rs = nd.AdministorQueryCheckPending();  ///查询所有的待审核订单
            show(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // nd.CheckNews("1234567890");  //审核一个订单

    }
}
