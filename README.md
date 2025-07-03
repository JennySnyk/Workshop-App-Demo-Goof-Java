# Workshop App Demo - Java Edition

This is a deliberately vulnerable Java application built with Spring Boot, designed for security demonstrations and workshops. It contains a wide range of vulnerabilities that can be detected by Snyk.

## How to Run

1.  **Build the application:**
    ```bash
    mvn clean install
    ```
2.  **Run the application:**
    ```bash
    java -jar target/goof-0.0.1-SNAPSHOT.jar
    ```
3.  Open your browser to [http://localhost:8080](http://localhost:8080).

## Vulnerabilities Included

### Open Source (SCA) Vulnerabilities

The `pom.xml` file includes a dozen dependencies with known vulnerabilities, including:

*   **Log4Shell** (`log4j-core:2.14.1`)
*   **Insecure Deserialization** (`commons-collections:3.2.1`, `jackson-databind:2.11.0`, `xstream:1.4.10`, `snakeyaml:1.26`)
*   **Remote Code Execution** (`struts2-core:2.3.30`, `h2:1.4.197`)
*   **Denial of Service** (`commons-fileupload:1.3.3`)
*   **HTTP Request Smuggling** (`netty-codec-http:4.1.45.Final`)
*   **Weak Cryptography** (`bcprov-jdk15:1.46`)
*   And more...

### Static Application Security Testing (SAST) Vulnerabilities

The application contains the following SAST vulnerabilities, each in its own controller in the `com.snyk.workshop.goof.vulnerabilities` package:

*   **SQL Injection** (`/sqli`)
*   **Cross-Site Scripting (XSS)** (`/xss`)
*   **Command Injection** (`/command-injection`)
*   **Path Traversal** (`/path-traversal`)
*   **Insecure Deserialization** (`/insecure-deserialization`)
*   **Server-Side Request Forgery (SSRF)** (`/ssrf`)
*   **Unvalidated Redirect** (`/unvalidated-redirect`)
*   **Insecure Cryptography** (`/insecure-crypto`)
*   **Hardcoded Secrets** (`/hardcoded-secrets`)
*   **Log Forging** (`/log-forging`)
*   **XPath Injection** (`/xpath-injection`)
*   **LDAP Injection** (`/ldap-injection`)

### Container Vulnerabilities

The `Dockerfile` is based on an old, vulnerable version of `openjdk:8u151-jre-alpine`.

### Infrastructure as Code (IaC) Misconfigurations

The `main.tf` file contains the following misconfigurations:

*   An S3 bucket with public read access.
*   An unencrypted SQS queue.
