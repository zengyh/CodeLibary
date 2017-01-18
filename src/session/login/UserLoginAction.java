package session.login;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * 文件名称: UserLoginAction.java
 * 编写人: yh.zeng
 * 编写时间: 17-1-15 下午6:30
 * 文件描述: 用户登陆
 */
public class UserLoginAction extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        Object userObj = req.getSession().getAttribute("user");
        boolean  hasLogin = false;  //是否已经登陆系统
        if(userObj == null){
             User user = new User(username);
             req.getSession().setAttribute("user", user);
        }else{
            hasLogin = true;
        }

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        if(hasLogin){
           writer.println("<font color='red'>已经登陆系统，无需重新登录！</font><br>");
        }
        writer.println("当前登录用户：" + ( (User)req.getSession().getAttribute("user") ).getUserName() +"<br>");

        //在线用户列表
        UserList userList =  UserList.getInstance();
        writer.println("在线用户数量：" + userList.getUserCount() + "<br>");
        Enumeration<String> userEnum = userList.getUserList();
        boolean isFirst = true;
        while(userEnum.hasMoreElements()){
            if(isFirst){
                writer.print("在线用户：" + userEnum.nextElement());
                isFirst = false;
            }else{
                writer.print("、"+ userEnum.nextElement());
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
