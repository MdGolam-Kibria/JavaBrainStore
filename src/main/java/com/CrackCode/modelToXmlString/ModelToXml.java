package com.CrackCode.modelToXmlString;

import com.CrackCode.modelToXmlString.model.Transaction;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.math.BigDecimal;

public class ModelToXml {

    public static void main(String[] args) {
        Transaction transaction = Transaction.builder()
                .reason("For Pojo Xml Convert Test")
                .amount(BigDecimal.ONE).build();
        System.out.println(jaxbObjectToXML(transaction));//Specific Model Class
        System.out.println(modelToXML(transaction));//Any Object Class
    }


    /**
     * For Convert Any Object To Xml String
     */
    private static String modelToXML(Object object) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

            StringWriter sw = new StringWriter();
            m.marshal(object, sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlString;
    }

    /**
     * For Convert Specific model class To Xml String
     */
    private static String jaxbObjectToXML(Transaction customer) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Transaction.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

            StringWriter sw = new StringWriter();
            m.marshal(customer, sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlString;
    }
}
