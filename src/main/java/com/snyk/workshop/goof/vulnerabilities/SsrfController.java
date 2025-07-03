package com.snyk.workshop.goof.vulnerabilities;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

@Controller
public class SsrfController {

    @GetMapping("/ssrf")
    @ResponseBody
    public String ssrf(@RequestParam(value = "url", defaultValue = "https://snyk.io") String url) {
        StringBuilder content = new StringBuilder();
        try {
            // Vulnerable to SSRF: the application makes a request to a user-provided URL
            InputStream is = new URL(url).openStream();
            Scanner scanner = new Scanner(is);
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
        } catch (Exception e) {
            content.append("Error: ").append(e.getMessage());
        }
        return "<pre>" + content.toString() + "</pre>\n<p>Try adding ?url=http://169.254.169.254/latest/meta-data/ to the URL to access cloud metadata (if running in a cloud environment).</p>";
    }
}
