package com.kodilla.libraryfront.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class librarybackendConfiguration {

    @Value("${librarybackend.endpoint}")
    private String librarybackendEndpoint;

    public String getLibrarybackendEndpoint() {
        return librarybackendEndpoint;
    }
}
