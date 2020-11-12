package org.owasp.webgoat.selenium;

import org.apache.commons.cli.*;


// Chrome driver: /opt/google/chrome/chromedriver

public class Main {
    static String url = "http://localhost:8080/WebGoat"; // Default URL
    static String user = "webgoat"; // Default username
    static String pass = "webgoat"; // Default password


    public static void main(String[] args) {


        Options options = new Options();
        options.addOption("U", "URL", true, "URL for WebGoat")
                .addOption("u", "user", true, "User name to login")
                .addOption("p", "password", true,  "Password to login");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("U")) {
                url = cmd.getOptionValue("U");
                System.out.println("Accessing WebGoat on URL: " + url);
            }

            if (cmd.hasOption("u")) {
                user = cmd.getOptionValue("u");
                System.out.println("Using user name: " + user);
            }

            if (cmd.hasOption("p")) {
                pass = cmd.getOptionValue("p");
                System.out.println("Using provided password: " + pass);
            }
        } catch (ParseException pe) {
            System.out.println("Error parsing command-line arguments!");
            System.out.println("");

            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("webgoat-selenium-tests", options);

            System.exit(1);
        }

    }
}
