package session.login;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 文件名称: UserList.java
 * 编写人: yh.zeng
 * 编写时间: 17-1-5 下午7:48
 * 文件描述: 已登录的用户列表（在线用户列表）
 */
public class UserList
{
    private static UserList userList = new UserList();

    private Vector<String>  v = null;


    private UserList(){
        v = new Vector<String>();
    }


    public static UserList getInstance(){
        return userList;
    }


    public void addUser(String userName){
        if(userName != null && !v.contains(userName)){
            v.add(userName);
        }
    }

    public void removeUser(String userName){
        if(userName != null){
            v.remove(userName);
        }
    }

    public Enumeration<String>  getUserList(){
        return v.elements();
    }

    public int getUserCount(){
        return v.size();
    }


}
