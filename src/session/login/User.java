package session.login;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * 文件名称: User.java
 * 编写人: yh.zeng
 * 编写时间: 17-1-5 下午8:30
 * 文件描述: 1）往Session中存放User对象，则自动往UserList用户列表中添加该用户
 *         2）Session中移除User对象，则自动从UserList用户列表中移除该用户
 */
public class User  implements HttpSessionBindingListener
{
    private String userName;

    public User(String userName){
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
         UserList.getInstance().addUser(userName);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
         UserList.getInstance().removeUser(userName);
    }
}
