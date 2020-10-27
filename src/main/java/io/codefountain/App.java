package io.codefountain;

import io.codefountain.configuration.DefaultConfiguration;
import io.codefountain.resource.DefaultResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App extends Application<DefaultConfiguration> {
    final static Logger logger = Logger.getLogger(App.class);
    public static void main( String[] args ) throws Exception {
        logger.info("inside the main method");
        String reqKey= UUID.randomUUID().toString();
        MDC.put("requestKey", reqKey);
        new App().run(args);
    }

    @Override
    public void run(DefaultConfiguration defaultConfiguration, Environment environment) throws Exception {
        logger.info("inside the run method");
        final DefaultResource defaultResource = new DefaultResource(defaultConfiguration.getFirstName(), defaultConfiguration.getLastName());
        environment.jersey().register(defaultResource);
        environment.healthChecks().register("default", new DefaultHealthCheck());
    }
}