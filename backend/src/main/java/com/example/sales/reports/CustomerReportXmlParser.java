package com.example.sales.reports;

import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Component
public class CustomerReportXmlParser {
  public String parseReportId(String xml) {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true);
      Document document = factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));
      NodeList nodes = document.getElementsByTagNameNS("urn:sales:reports", "reportId");
      if (nodes.getLength() != 1) throw new IllegalArgumentException("reportId is required exactly once");
      return ((Element) nodes.item(0)).getTextContent();
    } catch (Exception exception) {
      throw new IllegalArgumentException("Unable to parse customer report XML", exception);
    }
  }
}
