package com.kodilla.libraryfront.client;

import com.kodilla.libraryfront.configuration.LibraryBackendConfigration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LibraryBackendClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LibraryBackendConfigration libraryBackendConfigration;


}
