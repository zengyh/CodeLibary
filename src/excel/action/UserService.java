package excel.action;

import java.io.InputStream;
import java.util.List;

/**
 * 文件名称: UserService.java
 * 编写人: yh.zeng
 * 编写时间: 14-8-16 下午6:39
 * 文件描述: todo
 */
public interface UserService
{

        public void delete(User user);

    	public List<User> findAll();

    	public User findById(Integer id);

    	public void save(User user);

    	public void update(User user);

         //生成随机的文件名的Excel文件并返回InputStream输入流
    	public InputStream getInputStream();
}
