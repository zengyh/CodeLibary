package webservice.net.ilkj.soap.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservice.net.ilkj.soap.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SelectMaxLongNameStudentResponse_QNAME = new QName("http://client.soap.ilkj.net.webservice", "selectMaxLongNameStudentResponse");
    private final static QName _SelectMaxLongNameStudent_QNAME = new QName("http://client.soap.ilkj.net.webservice", "selectMaxLongNameStudent");
    private final static QName _SelectMaxAgeStudent_QNAME = new QName("http://client.soap.ilkj.net.webservice", "selectMaxAgeStudent");
    private final static QName _GetCustomerWithAttachment_QNAME = new QName("http://client.soap.ilkj.net.webservice", "getCustomerWithAttachment");
    private final static QName _Customer_QNAME = new QName("http://client.soap.ilkj.net.webservice", "Customer");
    private final static QName _TestWebServiceContextResponse_QNAME = new QName("http://client.soap.ilkj.net.webservice", "testWebServiceContextResponse");
    private final static QName _GetCustomerWithAttachmentResponse_QNAME = new QName("http://client.soap.ilkj.net.webservice", "getCustomerWithAttachmentResponse");
    private final static QName _SelectMaxAgeStudentResponse_QNAME = new QName("http://client.soap.ilkj.net.webservice", "selectMaxAgeStudentResponse");
    private final static QName _TestWebServiceContext_QNAME = new QName("http://client.soap.ilkj.net.webservice", "testWebServiceContext");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservice.net.ilkj.soap.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link webservice.net.ilkj.soap.client.TestWebServiceContextResponse }
     * 
     */
    public TestWebServiceContextResponse createTestWebServiceContextResponse() {
        return new TestWebServiceContextResponse();
    }

    /**
     * Create an instance of {@link webservice.net.ilkj.soap.client.TestWebServiceContext }
     * 
     */
    public TestWebServiceContext createTestWebServiceContext() {
        return new TestWebServiceContext();
    }

    /**
     * Create an instance of {@link webservice.net.ilkj.soap.client.SelectMaxAgeStudent }
     * 
     */
    public SelectMaxAgeStudent createSelectMaxAgeStudent() {
        return new SelectMaxAgeStudent();
    }

    /**
     * Create an instance of {@link GetCustomerWithAttachment }
     * 
     */
    public GetCustomerWithAttachment createGetCustomerWithAttachment() {
        return new GetCustomerWithAttachment();
    }

    /**
     * Create an instance of {@link webservice.net.ilkj.soap.client.SelectMaxAgeStudentResponse }
     * 
     */
    public SelectMaxAgeStudentResponse createSelectMaxAgeStudentResponse() {
        return new SelectMaxAgeStudentResponse();
    }

    /**
     * Create an instance of {@link GetCustomerWithAttachmentResponse }
     * 
     */
    public GetCustomerWithAttachmentResponse createGetCustomerWithAttachmentResponse() {
        return new GetCustomerWithAttachmentResponse();
    }

    /**
     * Create an instance of {@link webservice.net.ilkj.soap.client.SelectMaxLongNameStudentResponse }
     * 
     */
    public SelectMaxLongNameStudentResponse createSelectMaxLongNameStudentResponse() {
        return new SelectMaxLongNameStudentResponse();
    }

    /**
     * Create an instance of {@link webservice.net.ilkj.soap.client.SelectMaxLongNameStudent }
     * 
     */
    public SelectMaxLongNameStudent createSelectMaxLongNameStudent() {
        return new SelectMaxLongNameStudent();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link webservice.net.ilkj.soap.client.SelectMaxLongNameStudentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.ilkj.net.webservice", name = "selectMaxLongNameStudentResponse")
    public JAXBElement<SelectMaxLongNameStudentResponse> createSelectMaxLongNameStudentResponse(SelectMaxLongNameStudentResponse value) {
        return new JAXBElement<SelectMaxLongNameStudentResponse>(_SelectMaxLongNameStudentResponse_QNAME, SelectMaxLongNameStudentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link webservice.net.ilkj.soap.client.SelectMaxLongNameStudent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.ilkj.net.webservice", name = "selectMaxLongNameStudent")
    public JAXBElement<SelectMaxLongNameStudent> createSelectMaxLongNameStudent(SelectMaxLongNameStudent value) {
        return new JAXBElement<SelectMaxLongNameStudent>(_SelectMaxLongNameStudent_QNAME, SelectMaxLongNameStudent.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link webservice.net.ilkj.soap.client.SelectMaxAgeStudent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.ilkj.net.webservice", name = "selectMaxAgeStudent")
    public JAXBElement<SelectMaxAgeStudent> createSelectMaxAgeStudent(SelectMaxAgeStudent value) {
        return new JAXBElement<SelectMaxAgeStudent>(_SelectMaxAgeStudent_QNAME, SelectMaxAgeStudent.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link GetCustomerWithAttachment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.ilkj.net.webservice", name = "getCustomerWithAttachment")
    public JAXBElement<GetCustomerWithAttachment> createGetCustomerWithAttachment(GetCustomerWithAttachment value) {
        return new JAXBElement<GetCustomerWithAttachment>(_GetCustomerWithAttachment_QNAME, GetCustomerWithAttachment.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link Customer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.ilkj.net.webservice", name = "Customer")
    public JAXBElement<Customer> createCustomer(Customer value) {
        return new JAXBElement<Customer>(_Customer_QNAME, Customer.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link webservice.net.ilkj.soap.client.TestWebServiceContextResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.ilkj.net.webservice", name = "testWebServiceContextResponse")
    public JAXBElement<TestWebServiceContextResponse> createTestWebServiceContextResponse(TestWebServiceContextResponse value) {
        return new JAXBElement<TestWebServiceContextResponse>(_TestWebServiceContextResponse_QNAME, TestWebServiceContextResponse.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link GetCustomerWithAttachmentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.ilkj.net.webservice", name = "getCustomerWithAttachmentResponse")
    public JAXBElement<GetCustomerWithAttachmentResponse> createGetCustomerWithAttachmentResponse(GetCustomerWithAttachmentResponse value) {
        return new JAXBElement<GetCustomerWithAttachmentResponse>(_GetCustomerWithAttachmentResponse_QNAME, GetCustomerWithAttachmentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link webservice.net.ilkj.soap.client.SelectMaxAgeStudentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.ilkj.net.webservice", name = "selectMaxAgeStudentResponse")
    public JAXBElement<SelectMaxAgeStudentResponse> createSelectMaxAgeStudentResponse(SelectMaxAgeStudentResponse value) {
        return new JAXBElement<SelectMaxAgeStudentResponse>(_SelectMaxAgeStudentResponse_QNAME, SelectMaxAgeStudentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link webservice.net.ilkj.soap.client.TestWebServiceContext }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.ilkj.net.webservice", name = "testWebServiceContext")
    public JAXBElement<TestWebServiceContext> createTestWebServiceContext(TestWebServiceContext value) {
        return new JAXBElement<TestWebServiceContext>(_TestWebServiceContext_QNAME, TestWebServiceContext.class, null, value);
    }

}
