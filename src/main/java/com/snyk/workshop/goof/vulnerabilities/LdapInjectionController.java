package com.snyk.workshop.goof.vulnerabilities;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

@Controller
public class LdapInjectionController {

    @GetMapping("/ldap-injection")
    @ResponseBody
    public String ldapInjection(@RequestParam(value = "user", defaultValue = "test") String user) {
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            // In a real scenario, this would point to a real LDAP server.
            // For the demo, we just show the vulnerability in the code.
            env.put(Context.PROVIDER_URL, "ldap://localhost:389");

            DirContext ctx = new InitialDirContext(env);

            // Vulnerable to LDAP Injection: user input is used to construct an LDAP filter
            String filter = "(uid=" + user + ")";
            ctx.search("ou=users,o=example", filter, null);
            ctx.close();

            return "LDAP search for user '" + user + "' performed successfully (demo only)." + 
                   "\n<p>Try adding ?user=*)&(uid=admin to the URL to bypass authentication.</p>";
        } catch (Exception e) {
            // We expect an error because we're not connected to a real LDAP server.
            // The vulnerability is in how the filter string is constructed.
            return "LDAP search for user '" + user + "' performed (demo only). The vulnerability exists in the code even if the connection fails.";
        }
    }
}
