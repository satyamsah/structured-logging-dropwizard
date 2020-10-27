package io.codefountain.resource;

import com.codahale.metrics.annotation.Timed;
import io.codefountain.App;
import io.codefountain.model.Person;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.UUID;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class DefaultResource {

    private final String defaultFirstName;
    private final String defaultLastName;

    public DefaultResource(String defaultFirstName, String defaultLastName){
        this.defaultFirstName = defaultFirstName;
        this.defaultLastName = defaultLastName;
    }

    @GET
    @Timed
    public Person getPerson(@QueryParam("firstName") Optional<String> firstName,
                            @QueryParam("lastName") Optional<String> lastName){
        Logger logger = Logger.getLogger(App.class);
        String reqKey= UUID.randomUUID().toString();
        MDC.put("requestKey", reqKey);
        logger.info("inside GET");
        return new Person(firstName.orElse(defaultFirstName), lastName.orElse(defaultLastName));
    }
}