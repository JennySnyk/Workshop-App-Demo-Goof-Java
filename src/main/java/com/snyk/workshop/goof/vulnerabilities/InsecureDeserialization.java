package com.snyk.workshop.goof.vulnerabilities;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;

@Controller
public class InsecureDeserialization {

    @GetMapping("/insecure-deserialization")
    @ResponseBody
    public String insecureDeserialization(@RequestParam(value = "data", required = false) String data) {
        if (data == null) {
            return "Please provide a Base64 encoded serialized object in the 'data' parameter.";
        }
        try {
            // Vulnerable to Insecure Deserialization: deserializing user-provided data
            byte[] decodedData = Base64.getDecoder().decode(data);
            ByteArrayInputStream bais = new ByteArrayInputStream(decodedData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            ois.readObject();
            ois.close();
            return "Object deserialized successfully.";
        } catch (Exception e) {
            return "Error during deserialization: " + e.getMessage();
        }
    }
}
