/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.client;

import com.jdevsul.jaxb.MessageDetails;
import com.jdevsul.jaxb.MessageFormat;
import com.jdevsul.jaxb.Messages;
import com.jdevsul.jaxb.ObjectFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author gehad
 */
public class GoChat_JAXB {

//    public static void main(String[] args) {
//
//        writeXml();
//
//        readXml();
//    }

    static void readXml() {

        JAXBContext jAXBContext;
        Unmarshaller unmarshaller;
        JAXBElement jAXBElement;
        Messages messages;
        List<String> receivers_List;
        List<MessageDetails> chatMessages;
        MessageDetails tempMsg;
        MessageFormat tempFormat;

        try {
            // read testXml.xml 
            jAXBContext = JAXBContext.newInstance("com.jdevsul.jaxb");
            unmarshaller = jAXBContext.createUnmarshaller();
            jAXBElement = (JAXBElement) unmarshaller.unmarshal(new File("src/main/resources/xml/testXml.xml"));
            messages = (Messages) jAXBElement.getValue();

            System.out.println("Sender is: " + messages.getFrom());

            receivers_List = messages.getTo();

            for (int i = 0; i < receivers_List.size(); i++) {
                System.out.println("Receiver " + (i + 1) + ": " + receivers_List.get(i));
            }

            chatMessages = messages.getMsg();

            for (int i = 0; i < chatMessages.size(); i++) {
                tempMsg = chatMessages.get(i);
                System.out.println(tempMsg.content);
                System.out.println(tempMsg.date);

                tempFormat = tempMsg.getFormat();
                System.out.println("Message Color: " + tempFormat.color);
                System.out.println("Font Name: " + tempFormat.fontName);
                System.out.println("Font Style: " + tempFormat.fontStyle);
                System.out.println("Font Size: " + tempFormat.fontSize);
            }

        } catch (JAXBException ex) {
            Logger.getLogger(GoChat_JAXB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static void writeXml() {
        JAXBContext jAXBContext;
        Unmarshaller unmarshaller;
        JAXBElement jAXBElement;
        Messages messages;
        List<String> receivers_List;
        List<MessageDetails> chatMessages;
        MessageDetails tempMsg = null;
        MessageFormat tempFormat = null;

        try {

            jAXBContext = JAXBContext.newInstance("com.jdevsul.jaxb");
            unmarshaller = jAXBContext.createUnmarshaller();
            jAXBElement = (JAXBElement) unmarshaller.unmarshal(new File("src/main/resources/xml/testXml.xml"));
            messages = (Messages) jAXBElement.getValue();
            ObjectFactory factory = new ObjectFactory();

            messages.setFrom("Eman");

            receivers_List = messages.getTo();
            receivers_List.clear();
            receivers_List.add("Sherif");
            receivers_List.add("Gogo");

            chatMessages = messages.getMsg();
            chatMessages.clear();

            //message 1
            tempMsg = new MessageDetails();
            tempMsg.setContent("Rabna ystor w n5ls el project da:D ");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse("2018-02-10");
            GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
            cal.setTime(date);
            XMLGregorianCalendar result = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
            tempMsg.setDate(result);

            tempFormat = new MessageFormat();
            tempFormat.setColor("blue");
            tempFormat.setFontName("Calibri");
            tempFormat.setFontSize(new BigInteger("17"));
            tempFormat.setFontStyle("Italic");

            tempMsg.setFormat(tempFormat);
            chatMessages.add(tempMsg);

            //message 2
            tempMsg = new MessageDetails();
            tempMsg.setContent("Ostor ya rab");

            formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.parse("2018-02-11");
            cal = (GregorianCalendar) GregorianCalendar.getInstance();
            cal.setTime(date);
            result = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
            tempMsg.setDate(result);

            tempFormat = new MessageFormat();
            tempFormat.setColor("pink");
            tempFormat.setFontName("Calibri");
            tempFormat.setFontSize(new BigInteger("17"));
            tempFormat.setFontStyle("Italic");

            tempMsg.setFormat(tempFormat);
            chatMessages.add(tempMsg);

            JAXBElement<Messages> myMsgMarshal = factory.createChatHistory(messages);
            Marshaller marsh = jAXBContext.createMarshaller();
            marsh.setProperty(JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marsh.marshal(myMsgMarshal, new FileOutputStream(new File("src/main/resources/xml/output.xml")));

        } catch (JAXBException ex) {
            Logger.getLogger(GoChat_JAXB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(GoChat_JAXB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GoChat_JAXB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(GoChat_JAXB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
