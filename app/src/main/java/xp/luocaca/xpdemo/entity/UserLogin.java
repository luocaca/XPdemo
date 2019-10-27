package xp.luocaca.xpdemo.entity;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

import javax.xml.parsers.SAXParserFactory;

public class UserLogin extends DefaultHandler {
    private List<String> elements = null;
    private SAXParserFactory factory = SAXParserFactory.newInstance();
    private UserLoginResp resp;



    public boolean doSubmit(String webServer, String email, String password) {

        return false;
    }

    public UserLoginResp getResult() {
        return this.resp;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        this.elements.remove(this.elements.size() - 1);
    }
}
