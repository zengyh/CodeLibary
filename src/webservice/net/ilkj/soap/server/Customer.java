package webservice.net.ilkj.soap.server;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: yh.zeng
 * Date: 14-7-16
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "Customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {

    private long id;

    private String name;

    private Date birthday;

    @XmlMimeType("application/octet-stream")
    private DataHandler imageData;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public DataHandler getImageData() {
        return imageData;
    }

    public void setImageData(DataHandler imageData) {
        this.imageData = imageData;
    }
}
