package com.snyk.workshop.goof.vulnerabilities;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Xss {

    @GetMapping("/xss")
    @ResponseBody
    public String xss(@RequestParam(value = "input", defaultValue = "Hello, World!") String input) {
        // Vulnerable to XSS: user input is directly included in the response
        return "<h1>User Input:</h1><p>" + input + "</p>\n<p>Try adding ?input=&lt;script&gt;alert('XSS')&lt;/script&gt; to the URL.</p>";
    }
}
