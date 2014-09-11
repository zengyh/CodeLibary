package excel.action;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.List;



public class UserServiceImpl implements UserService
{
/*	private UserDAO userDao;

	public UserDAO getUserDao()
	{
		return userDao;
	}

	public void setUserDao(UserDAO userDao)
	{
		this.userDao = userDao;
	}*/

	public void delete(User user)
	{
		//this.userDao.removeUser(user);
	}

	public List<User> findAll()
	{
        return null;
		//return this.userDao.findAllUsers();
	}

	public User findById(Integer id)
	{
        return null;
		//return this.userDao.findUserById(id);
	}

	public void save(User user)
	{
		//this.userDao.saveUser(user);
	}

	public void update(User user)
	{
		//this.userDao.updateUser(user);
	}

        //生成随机的文件名的Excel文件并返回InputStream输入流
	public InputStream getInputStream()
	{
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");

		HSSFRow row = sheet.createRow(0);

		HSSFCell cell = row.createCell((short) 0);
		//cell.setEncoding(HSSFCell.ENCODING_UTF_16);   //高版本的poi支持
		cell.setCellValue("序号");

		cell = row.createCell((short) 1);
		//cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue("姓");

		cell = row.createCell((short) 2);
		//cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue("名");

		cell = row.createCell((short) 3);
		//cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue("年龄");

		List<User> list = this.findAll();

		for (int i = 0; i < list.size(); ++i)
		{
			User user = list.get(i);

			row = sheet.createRow(i + 1);

			cell = row.createCell((short) 0);
			//cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(i + 1);

			cell = row.createCell((short) 1);
			//cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(user.getFirstname());

			cell = row.createCell((short) 2);
			//cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(user.getLastname());

			cell = row.createCell((short) 3);
			//cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(user.getAge());
		}

		//String fileName = CharacterUtils.getRandomString(10);

		String fileName = RandomStringUtils.randomAlphanumeric(10);

		fileName = new StringBuffer(fileName).append(".xls").toString();

		File file = new File(fileName);

		try
		{
			OutputStream os = new FileOutputStream(file);
			wb.write(os);
			os.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		InputStream is = null;
		try
		{
			is = new FileInputStream(file);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		return is;

	}

}