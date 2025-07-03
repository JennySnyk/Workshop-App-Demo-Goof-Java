package com.snyk.workshop.goof.vulnerabilities;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HardcodedSecrets {

    // Vulnerable due to hardcoded secrets
    private final String apiKey = "sk_live_1234567890abcdefghijklmnopqrstuvwx";
    private final String dbPassword = "s3cr3t_p@ssw0rd_f0r_db";

    @GetMapping("/hardcoded-secrets")
    @ResponseBody
    public String getSecrets() {
        return "This class contains hardcoded secrets. In a real application, this would be a major security risk.";
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
