package com.kodilla.libraryfront.client;

import com.google.gson.Gson;
import com.kodilla.libraryfront.configuration.LibraryBackendConfigration;
import com.kodilla.libraryfront.dto.BookDto;
import com.kodilla.libraryfront.dto.GenreDto;
import com.kodilla.libraryfront.dto.ReaderDto;
import com.kodilla.libraryfront.dto.ReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class LibraryBackendClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LibraryBackendConfigration libraryBackendConfigration;

    private HttpHeaders httpHeaders;

    public List<BookDto> getAllBooks(){
        BookDto[] boardResponse = restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint() + "/books/",BookDto[].class);
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

    public List<GenreDto> getAllGenres(){
        GenreDto[] boardResponse = restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint()+"/genres/",GenreDto[].class);
        return Stream.of(boardResponse).collect(Collectors.toList());
    }

    public GenreDto getSpecifiedByIdGenre(Long genreId){
        return restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint()+"/genre/"+genreId,GenreDto.class);
    }

    public void deleteReservation(Long reservationId){
        restTemplate.delete(libraryBackendConfigration.getLibrarybackendEndpoint()+"/reservation/"+reservationId, ReservationDto.class);
    }

    public ReaderDto createReader(ReaderDto readerDto){
        Gson gson = new Gson();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String contentInJson = gson.toJson(readerDto);
        HttpEntity<String> httpRequest = new HttpEntity<String>(contentInJson,httpHeaders);
        return restTemplate.postForObject(libraryBackendConfigration.getLibrarybackendEndpoint()+"/reader/",httpRequest,ReaderDto.class);
    }

    public void changeReaderData(ReaderDto readerDto){
        Gson gson = new Gson();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String contentInJson = gson.toJson(readerDto);
        HttpEntity<String> httpRequest = new HttpEntity<String>(contentInJson,httpHeaders);
        restTemplate.put(libraryBackendConfigration.getLibrarybackendEndpoint()+"/reader/",httpRequest);
    }

    public List<BookDto> getBooksRentedByUseer(Long readerId){
        ReaderDto boardResponse = restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint()+"/reservation/rented"+readerId,ReaderDto.class);
        return boardResponse.getBookDtoList();
    }

    public List<ReservationDto> getBooksReservedByUseer(Long readerId) {
        ReaderDto boardResponse = restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint() + "/reservation/reserved" + readerId, ReaderDto.class);
        return boardResponse.getReservationDtoList();
    }

    public void putBookInACart(BookDto bookDto,Long cartId){
        Gson gson = new Gson();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String contentInJson = gson.toJson(bookDto);
        HttpEntity<String> httpRequest = new HttpEntity<String>(contentInJson,httpHeaders);
        restTemplate.put(libraryBackendConfigration.getLibrarybackendEndpoint()+"/book/placed/"+cartId,httpRequest);
    }

    public void deleteBookFromACart(BookDto bookDto,Long cartId){
        Gson gson = new Gson();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String contentInJson = gson.toJson(bookDto);
        HttpEntity<String> httpRequest = new HttpEntity<String>(contentInJson,httpHeaders);
        restTemplate.delete(libraryBackendConfigration.getLibrarybackendEndpoint()+"/book/delete/"+cartId,httpRequest);
    }

    public ReservationDto createReservationOnCartBasis(ReservationDto reservationDto,Long cartId){
        Gson gson = new Gson();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String contentInJson = gson.toJson(reservationDto);
        HttpEntity<String> httpRequest = new HttpEntity<String>(contentInJson,httpHeaders);
        return restTemplate.postForObject(libraryBackendConfigration.getLibrarybackendEndpoint()+"/reservation/create/cart/"+cartId,httpRequest,ReservationDto.class);
    }

    public BookDto createBook(BookDto bookDto){
        Gson gson = new Gson();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String contentInJson = gson.toJson(bookDto);
        HttpEntity<String> httpRequest = new HttpEntity<String>(contentInJson,httpHeaders);
        return restTemplate.postForObject(libraryBackendConfigration.getLibrarybackendEndpoint()+"/book/",httpRequest,BookDto.class);
    }

    public void deleteBook(Long bookId){
        restTemplate.delete(libraryBackendConfigration.getLibrarybackendEndpoint()+"/book/"+bookId, BookDto.class);
    }

    public GenreDto createGenre(GenreDto genreDto){
        Gson gson = new Gson();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String contentInJson = gson.toJson(genreDto);
        HttpEntity<String> httpRequest = new HttpEntity<String>(contentInJson,httpHeaders);
        return restTemplate.postForObject(libraryBackendConfigration.getLibrarybackendEndpoint()+"/genre/",httpRequest,GenreDto.class);
    }

    public List<ReservationDto> getAllReservations(){
        ReservationDto[] boardResponse = restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint() + "/reserations/",ReservationDto[].class);
        return Stream.of(boardResponse).collect(Collectors.toList());
    }

    public ReservationDto getSpecifiedByIdReservation(Long reservationId){
        return restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint()+"/reservation/"+reservationId,ReservationDto.class);
    }

    public Boolean login(String email,String password){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("email",email);
        httpHeaders.set("password",password);
        HttpEntity<String> entity = new HttpEntity<String>("parameters",httpHeaders);

        URI url = UriComponentsBuilder.fromHttpUrl(libraryBackendConfigration.getLibrarybackendEndpoint() + "/reader/login").build().encode().toUri();

        ResponseEntity<Boolean> respEntity = restTemplate.exchange(url, HttpMethod.GET, entity,  Boolean.class);
        return respEntity.getBody();
    }

    public ReaderDto getReaderByLoginData(String email,String password){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("email", email);
        httpHeaders.set("password", password);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);

        URI url = UriComponentsBuilder.fromHttpUrl(libraryBackendConfigration.getLibrarybackendEndpoint() + "/reader/").build().encode().toUri();

        ResponseEntity<ReaderDto> respEntity = restTemplate.exchange(url, HttpMethod.GET, entity,  ReaderDto.class);
        return Optional.ofNullable(respEntity.getBody()).orElse(new ReaderDto());
    }

    public ReaderDto getReaderById(Long readerId){
        return restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint() + "/reader/" + readerId, ReaderDto.class);
    }
}
