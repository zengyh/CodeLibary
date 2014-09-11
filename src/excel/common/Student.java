package excel.common;

import java.io.Serializable;
import java.util.Date;


public class Student implements Serializable {

	private static final long serialVersionUID = -8141916472359874289L;

	@AllowExcel(name="编号")
	private Integer id;

	@AllowExcel(name="姓名")
	private String name;

	@AllowExcel(name="生日")
	private Date date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
