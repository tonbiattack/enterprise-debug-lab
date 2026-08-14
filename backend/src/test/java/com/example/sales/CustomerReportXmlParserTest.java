package com.example.sales;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.sales.reports.CustomerReportXmlParser;
import org.junit.jupiter.api.Test;

class CustomerReportXmlParserTest {
  private final CustomerReportXmlParser parser = new CustomerReportXmlParser();

  @Test
  void B05_parsesTheDocumentedReportNamespace() {
    assertEquals("R-100", parser.parseReportId("<r:report xmlns:r='urn:sales:report'><r:reportId>R-100</r:reportId></r:report>"));
  }

  @Test
  void B05_rejectsAnUnexpectedNamespace() {
    assertThrows(IllegalArgumentException.class, () -> parser.parseReportId("<report><reportId>R-100</reportId></report>"));
  }
}
