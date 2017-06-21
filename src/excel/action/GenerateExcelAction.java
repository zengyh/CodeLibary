package excel.action;

import com.opensymphony.xwork2.ActionSupport;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class GenerateExcelAction extends ActionSupport
{
	private UserService service;
    private String fileName;

	public UserService getService()
	{
		return service;
	}

	public void setService(UserService service)
	{
		this.service = service;
	}
	
	public InputStream getDownloadFile()
	{
        this.fileName = "EXCEL下载例子.xls";
        try {
            this.fileName = new String(fileName.getBytes(),"ISO-8859-1");  //解决下载的文件名乱码
        } catch (UnsupportedEncodingException e) {
        }
        return this.service.getInputStream();
	}
	
	@Override
	public String execute() throws Exception
	{
		return SUCCESS;
	}

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
