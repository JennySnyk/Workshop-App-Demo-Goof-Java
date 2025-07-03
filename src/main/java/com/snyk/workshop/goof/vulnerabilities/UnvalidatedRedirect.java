package com.snyk.workshop.goof.vulnerabilities;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UnvalidatedRedirect {

    @GetMapping("/unvalidated-redirect")
    public void unvalidatedRedirect(@RequestParam(value = "url", defaultValue = "/") String url, HttpServletResponse response) throws IOException {
        // Vulnerable to Unvalidated Redirect: redirecting to a user-provided URL
        response.sendRedirect(url);
    }
}
