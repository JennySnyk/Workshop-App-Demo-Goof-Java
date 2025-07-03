package com.snyk.workshop.goof.vulnerabilities;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Controller
public class CommandInjection {

    @GetMapping("/command-injection")
    @ResponseBody
    public String commandInjection(@RequestParam(value = "host", defaultValue = "8.8.8.8") String host) {
        StringBuilder result = new StringBuilder();
        try {
            // Vulnerable to Command Injection: user input is passed to a system command
            Process process = Runtime.getRuntime().exec("ping -c 1 " + host);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (Exception e) {
            result.append("Error: ").append(e.getMessage());
        }
        return "<pre>" + result.toString() + "</pre>\n<p>Try adding ?host=8.8.8.8;whoami to the URL.</p>";
    }
}
