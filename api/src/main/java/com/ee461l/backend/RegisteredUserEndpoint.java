package com.ee461l.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "registeredUserApi",
        version = "v1",
        resource = "registeredUser",
        namespace = @ApiNamespace(
                ownerDomain = "backend.ee461l.com",
                ownerName = "backend.ee461l.com",
                packagePath = ""
        )
)
public class RegisteredUserEndpoint {

    private static final Logger logger = Logger.getLogger(RegisteredUserEndpoint.class.getName());

    /**
     * This method gets the <code>RegisteredUser</code> object associated with the specified <code>id</code>.
     *
     * @param id The id of the object to be returned.
     * @return The <code>RegisteredUser</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getRegisteredUser")
    public RegisteredUser getRegisteredUser(@Named("id") Long id) {
        // TODO: Implement this function
        logger.info("Calling getRegisteredUser method");
        RegisteredUser user = ofy().load().type(RegisteredUser.class).id(id).now();
        return user;
    }

    /**
     * This inserts a new <code>RegisteredUser</code> object.
     *
     * @param registeredUser The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertRegisteredUser")
    public RegisteredUser insertRegisteredUser(RegisteredUser registeredUser) {
        // TODO: Implement this function
        logger.info("Calling insertRegisteredUser method");
        ofy().save().entity(registeredUser).now();
        return ofy().load().entity(registeredUser).now();
    }
}