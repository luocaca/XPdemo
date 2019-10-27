package xp.luocaca.xpdemo.entity;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

import javax.xml.parsers.SAXParserFactory;

public class GetBilling extends DefaultHandler {
    private List<String> elements = null;
    private SAXParserFactory factory = SAXParserFactory.newInstance();
    private GetBillingResp resp = null;

    public boolean doSubmit(boolean connected, String remoteIp) {

        return false;
    }

    public boolean doSubmit(String webServer, boolean connected, String remoteIp) {
        return true;
    }

    public GetBillingResp getResult() {
        return this.resp;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.elements.add(localName);
        if (localName.equalsIgnoreCase("root")) {
            for (int i = 0; i < attributes.getLength(); i++) {
                String attName = attributes.getLocalName(i);
                String attValue = attributes.getValue(i);

            }
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        this.elements.remove(this.elements.size() - 1);
    }
}
