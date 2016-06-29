package com.swacorp.crew.microservices.core.qualtype.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by x220553 on 6/15/2016.
 */
@EnableOAuth2Client
@Configuration
public class Oauth2RestComponentService {

    private static final Logger LOGGER = Logger.getLogger(Oauth2RestComponentService.class.getName());

    @Value("${security.oauth2.client.clientId}")
    private String CLIENT_ID;
    @Value("${security.oauth2.client.clientSecret}")
    private String CLIENT_SECRET;
    @Value("${security.oauth2.client.grantType}")
    private String GRANT_TYPE;
    @Value("${security.oauth2.client.accessTokenUri}")
    private String ACCESS_TOKEN_URI;

    //private static Map<String, String> oauthValues;

    @Bean
    public OAuth2ProtectedResourceDetails resource() {

        /**
         *

        if (oauthValues == null) {
            oauthValues = new HashMap<String, String>();
            oauthValues.put("CLIENT_ID", CLIENT_ID);
            oauthValues.put("CLIENT_SECRET", CLIENT_SECRET);
            oauthValues.put("GRANT_TYPE", GRANT_TYPE);
            oauthValues.put("ACCESS_TOKEN_URI", ACCESS_TOKEN_URI);
        }
         */

        /**
         *

        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setAccessTokenUri(oauthValues.get("ACCESS_TOKEN_URI"));
        resourceDetails.setAuthenticationScheme(AuthenticationScheme.header);
        resourceDetails.setClientId(oauthValues.get("CLIENT_ID"));
        resourceDetails.setClientSecret(oauthValues.get("CLIENT_SECRET"));
        resourceDetails.setGrantType("client_credentials");

         */

        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setAccessTokenUri(ACCESS_TOKEN_URI);
        resourceDetails.setAuthenticationScheme(AuthenticationScheme.header);
        resourceDetails.setClientId(CLIENT_ID);
        resourceDetails.setClientSecret(CLIENT_SECRET);
        resourceDetails.setGrantType("client_credentials");

        List<String> scopes = new ArrayList<String>();
        scopes.add("read");
        resourceDetails.setScope(scopes);

        return resourceDetails;

    }

    @Bean
    public OAuth2RestTemplate restTemplate() {
        return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext());
    }

}
