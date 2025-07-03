package com.snyk.workshop.goof.vulnerabilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogForgingController {

    private static final Logger logger = LoggerFactory.getLogger(LogForgingController.class);

    @GetMapping("/log-forging")
    @ResponseBody
    public String logForging(@RequestParam(value = "input", defaultValue = "User logged in successfully") String input) {
        // Vulnerable to Log Forging: user input is logged without sanitization
        logger.info(input);
        return "The following input has been logged: " + input + "\n<p>Try adding ?input=User logged out.\nINFO: Admin user logged in. to the URL.</p>";
    }
}
