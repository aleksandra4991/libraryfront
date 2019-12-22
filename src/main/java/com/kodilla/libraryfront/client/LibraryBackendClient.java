package com.kodilla.libraryfront.client;

import com.kodilla.libraryfront.configuration.LibraryBackendConfigration;
import com.kodilla.libraryfront.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class LibraryBackendClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LibraryBackendConfigration libraryBackendConfigration;

    public List<BookDto> getAllBooks(){
        BookDto[] boardResponse = restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint() + "/books",BookDto[].class);
        return Stream.of(boardResponse).collect(Collectors.toList());
    }

    public List<BookDto> getBooksOfTheAuthor(String author){
        BookDto[] boardResponse = restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint() +"/books/author/"+author,BookDto[].class);
        return Stream.of(boardResponse).collect(Collectors.toList());
    }

    public List<BookDto> getBooksAvaiableToRent(boolean rented){
        BookDto[] boardResponse = restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint() +"/books/rented/"+rented,BookDto[].class);
        return Stream.of(boardResponse).collect(Collectors.toList());
    }


}
