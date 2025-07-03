package com.snyk.workshop.goof.vulnerabilities;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Controller
public class SqlInjection {

    @GetMapping("/sqli")
    @ResponseBody
    public String sqli(@RequestParam(value = "username", defaultValue = "testuser") String username) {
        StringBuilder result = new StringBuilder();
        try {
            // The H2 database is included as a vulnerable dependency
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "user", "password");
            Statement stmt = conn.createStatement();

            // Vulnerable SQL query: concatenating user input directly
            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(query);

            result.append("Query executed successfully. Results:\n");
            while (rs.next()) {
                result.append(rs.getString("username")).append("\n");
            }
            // In a real app, you'd have a setup script. For the demo, we just show the vulnerability.
            // The table 'users' doesn't exist, but the query is still formed and sent to the DB driver.
        } catch (Exception e) {
            result.append("Error: ").append(e.getMessage());
        }
        return "<pre>" + result.toString() + "</pre>";
    }
}
