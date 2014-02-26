/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.integration.service;

import org.jboss.aerogear.integration.model.SimpleUser;
import org.jboss.aerogear.security.auth.AuthenticationManager;
import org.jboss.aerogear.security.auth.LoggedUser;
import org.jboss.aerogear.security.auth.Secret;
import org.jboss.aerogear.security.otp.Totp;
import org.picketlink.idm.model.basic.Agent;
import org.picketlink.idm.model.basic.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/auth")
public class AuthService {

    @Inject
    private AuthenticationManager<Agent> authenticationManager;

    @Inject
    @Secret
    private Instance<String> secret;

    @Inject
    @LoggedUser
    private Instance<String> loggedInUserName;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SimpleUser login(SimpleUser simpleUser) {
        User user = new User(simpleUser.getLoginName());
        authenticationManager.login(user, simpleUser.getPassword());
        return simpleUser;
    }

    @POST
    @Path("/logout")
    public void logout() {
        authenticationManager.logout();
    }


    @GET
    @Path("/otp/secret")
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleUser secret() {
        SimpleUser simpleUser = new SimpleUser();
        String otpSecret =  new Totp(secret.get()).uri(loggedInUserName.get());
        simpleUser.setUri(otpSecret);
        return simpleUser;
    }

    @POST
    @Path("/otp")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleUser otp(SimpleUser simpleUser) {

        Totp totp = new Totp(secret.get());
        boolean result = totp.verify(simpleUser.getSecret());

        if (!result)
            throw new RuntimeException("Invalid OTP");

        return simpleUser;
    }
}
