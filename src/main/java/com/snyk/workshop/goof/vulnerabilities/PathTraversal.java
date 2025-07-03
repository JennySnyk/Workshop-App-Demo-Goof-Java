package com.snyk.workshop.goof.vulnerabilities;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class PathTraversal {

    @GetMapping("/path-traversal")
    @ResponseBody
    public String pathTraversal(@RequestParam(value = "file", defaultValue = "pom.xml") String file) {
        StringBuilder content = new StringBuilder();
        try {
            // Vulnerable to Path Traversal: user input is used to construct a file path
            String filePath = "./" + file;
            content.append(new String(Files.readAllBytes(Paths.get(filePath))));
        } catch (Exception e) {
            content.append("Error: ").append(e.getMessage());
        }
        return "<pre>" + content.toString() + "</pre>\n<p>Try adding ?file=../../../../../../etc/passwd to the URL.</p>";
    }
}
