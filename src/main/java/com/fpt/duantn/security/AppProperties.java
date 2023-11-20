package com.fpt.duantn.security;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;

import java.security.Security;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "custom-config")
public class AppProperties {
    Security security;

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Security {
        String jwtSecret;
        Long jwtExpiration;
        List<Authorization> endpointAuthorizations;
        List<UnprotectedEndpoint> unprotectedEndpoints;
    }

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Authorization {
        String urlPattern;
        String method;
        List<String> roles;
    }

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class UnprotectedEndpoint {
        String urlPattern;
        String method;
    }

    public String getTokenSecret()
    {
        return security.jwtSecret;
    }

}
