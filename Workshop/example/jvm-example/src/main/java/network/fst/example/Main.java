package network.fst.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class Main {
    public static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Config config = new Config(
                "ACCOUNT_ID",
                "ACCOUNT_PASSWORD",
                "ACCOUNT_PASSPHRASE",
                URI.create("https://api.[DOMAIN].workshop.fst.network/signin"),
                URI.create("https://api.[DOMAIN].workshop.fst.network/api")
        );

        log.info("running scala example");
        Example scalaExample = new ScalaExample(config);
        scalaExample.runExample();

        log.info("running java example");
        Example javaExample = new JavaExample(config);
        javaExample.runExample();
    }
}
