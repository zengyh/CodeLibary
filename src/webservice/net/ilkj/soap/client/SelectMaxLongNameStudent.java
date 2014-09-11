package webservice.net.ilkj.soap.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for selectMaxLongNameStudent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="selectMaxLongNameStudent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="c1" type="{http://client.soap.ilkj.net.webservice}customer" minOccurs="0"/>
 *         &lt;element name="c2" type="{http://client.soap.ilkj.net.webservice}customer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "selectMaxLongNameStudent", propOrder = {
    "c1",
    "c2"
})
public class SelectMaxLongNameStudent {

    protected Customer c1;
    protected Customer c2;

    /**
     * Gets the value of the c1 property.
     * 
     * @return
     *     possible object is
     *     {@link Customer }
     *     
     */
    public Customer getC1() {
        return c1;
    }

    /**
     * Sets the value of the c1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Customer }
     *     
     */
    public void setC1(Customer value) {
        this.c1 = value;
    }

    /**
     * Gets the value of the c2 property.
     * 
     * @return
     *     possible object is
     *     {@link Customer }
     *     
     */
    public Customer getC2() {
        return c2;
    }

    /**
     * Sets the value of the c2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Customer }
     *     
     */
    public void setC2(Customer value) {
        this.c2 = value;
    }

}
