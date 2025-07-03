package com.snyk.workshop.goof.vulnerabilities;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.MessageDigest;

@Controller
public class InsecureCryptoController {

    @GetMapping("/insecure-crypto")
    @ResponseBody
    public String insecureCrypto(@RequestParam(value = "input", defaultValue = "snyk") String input) {
        try {
            // Vulnerable due to use of a weak hashing algorithm (MD5)
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] hash = md5.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return "MD5 Hash: " + hexString.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
