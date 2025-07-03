package com.snyk.workshop.goof.vulnerabilities;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

@Controller
public class XPathInjection {

    private final String xml = "<users><user><name>admin</name><password>s3cr3t</password></user><user><name>user</name><password>p@ssw0rd</password></user></users>";

    @GetMapping("/xpath-injection")
    @ResponseBody
    public String xpathInjection(@RequestParam(value = "username", defaultValue = "user") String username) {
        try {
            InputSource source = new InputSource(new StringReader(xml));
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(source);
            XPath xpath = XPathFactory.newInstance().newXPath();

            // Vulnerable to XPath Injection: user input is used to construct an XPath query
            String expression = "/users/user[name='" + username + "']/password/text()";
            String password = xpath.evaluate(expression, doc);

            return "Password for " + username + ": " + password + "\n<p>Try adding ?username=' or '1'='1 to the URL to extract the admin password.</p>";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
