package com.kodilla.libraryfront.client;

import com.google.gson.Gson;
import com.kodilla.libraryfront.configuration.LibraryBackendConfigration;
import com.kodilla.libraryfront.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

@Component
public class LibraryBackendClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LibraryBackendConfigration libraryBackendConfigration;

    private HttpHeaders httpHeaders = new HttpHeaders();

    public List <VolumeDto> getAllGoogleVolumes() {
        VolumeDto[] boardsResponse = restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint() + "/books" , VolumeDto[].class);
        return Stream.of(boardsResponse).collect(Collectors.toList());
    }

    public CartBookAdderDto putBookInCart (Long cartId, VolumeDto volumeDto) {
        Gson gson = new Gson();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String contentInJson = gson.toJson(volumeDto);
        return restTemplate.postForObject(libraryBackendConfigration.getLibrarybackendEndpoint() + "/book/putInCart/" + cartId, contentInJson , CartBookAdderDto.class);
    }

    public VolumeDto getBooksPutInCart(CartBookAdderDto cartBookAdderDto){
        CartBookAdderDto boardResponse = restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint()+"/books/alreadyInCart" + cartBookAdderDto,CartBookAdderDto.class);
        return boardResponse.getVolumeDto();
    }

    public CartBookAdderDto getCartById(Long cartId){
        Gson gson = new Gson();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String contentInJson = gson.toJson(cartId);
        HttpEntity<String> httpRequest = new HttpEntity<String>(contentInJson,httpHeaders);
        return restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint() + "/cart/" + contentInJson, CartBookAdderDto.class);
    }


    public VolumeDto getSpecifiedBook(Long bookId){
        return restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint() + "/book/" + bookId, VolumeDto.class);
    }

    public List <VolumeDto> getVolumeByTitle(String title){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("title",title);
        HttpEntity entity = new HttpEntity(httpHeaders);

        URI url = UriComponentsBuilder.fromHttpUrl(libraryBackendConfigration.getLibrarybackendEndpoint() + "/books/title").build().encode().toUri();

        ResponseEntity <VolumeDto[]> respEntity = restTemplate.exchange(url,HttpMethod.GET,entity,VolumeDto[].class);
        return Arrays.stream(respEntity.getBody()).collect(toCollection(ArrayList::new));

    }


    /*public List<VolumeDto> getBooksAvaiableToRent(boolean rented){
        VolumeDto[] boardResponse = restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint() +"/books/rented/"+rented,VolumeDto[].class);
        return Stream.of(boardResponse).collect(Collectors.toList());
    }*/

    public List<GenreDto> getAllGenres(){
        GenreDto[] boardResponse = restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint()+"/genres",GenreDto[].class);
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
        return restTemplate.postForObject(libraryBackendConfigration.getLibrarybackendEndpoint() + "/reader",httpRequest,ReaderDto.class);
    }

    public void changeReaderData(ReaderDto readerDto){
        Gson gson = new Gson();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String contentInJson = gson.toJson(readerDto);
        HttpEntity<String> httpRequest = new HttpEntity<String>(contentInJson,httpHeaders);
        restTemplate.put(libraryBackendConfigration.getLibrarybackendEndpoint()+"/reader",httpRequest);
    }

    public List<VolumeDto> getBooksRentedByUseer(ReaderDto readerDto){
        ReaderDto boardResponse = restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint()+"/books/rented"+readerDto,ReaderDto.class);
        return boardResponse.getVolumeDtoList();
    }

    public List<ReservationDto> getBooksReservedByUseer(ReaderDto readerDto) {
        ReaderDto boardResponse = restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint() + "/reservation/reserved" + readerDto, ReaderDto.class);
        return boardResponse.getReservationDtoList();
    }

    public void deleteBookFromACart(VolumeDto VolumeDto,Long cartId){
        Gson gson = new Gson();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String contentInJson = gson.toJson(VolumeDto);
        HttpEntity<String> httpRequest = new HttpEntity<String>(contentInJson,httpHeaders);
        restTemplate.delete(libraryBackendConfigration.getLibrarybackendEndpoint()+"/book/delete/"+cartId,httpRequest);
    }


    public ReservationDto createReservationOnCartBasis(VolumeDto VolumeDto,Long cartId){
        Gson gson = new Gson();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String contentInJson = gson.toJson(VolumeDto);
        HttpEntity<String> httpRequest = new HttpEntity<String>(contentInJson,httpHeaders);
        return restTemplate.postForObject(libraryBackendConfigration.getLibrarybackendEndpoint()+"/reservation/create/cart/"+cartId,httpRequest,ReservationDto.class);
    }

    /*public VolumeDto createBook(VolumeDto VolumeDto){
        Gson gson = new Gson();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String contentInJson = gson.toJson(VolumeDto);
        HttpEntity<String> httpRequest = new HttpEntity<String>(contentInJson,httpHeaders);
        return restTemplate.postForObject(libraryBackendConfigration.getLibrarybackendEndpoint()+"/book",httpRequest,VolumeDto.class);
    }

    public void deleteBook(Long bookId){
        restTemplate.delete(libraryBackendConfigration.getLibrarybackendEndpoint()+"/book/"+bookId, VolumeDto.class);
    }

    public GenreDto createGenre(GenreDto genreDto){
        Gson gson = new Gson();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String contentInJson = gson.toJson(genreDto);
        HttpEntity<String> httpRequest = new HttpEntity<String>(contentInJson,httpHeaders);
        return restTemplate.postForObject(libraryBackendConfigration.getLibrarybackendEndpoint()+"/genre",httpRequest,GenreDto.class);
    }

    public List<ReservationDto> getAllReservations(){
        ReservationDto[] boardResponse = restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint() + "/reservations",ReservationDto[].class);
        return Stream.of(boardResponse).collect(Collectors.toList());
    }*/

    public ReservationDto getSpecifiedByIdReservation(Long reservationId){
        return restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint()+"/reservation/"+reservationId,ReservationDto.class);
    }

    public Boolean login(String emailAddress,String password){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("emailAddress",emailAddress);
        httpHeaders.set("password",password);
        HttpEntity<String> entity = new HttpEntity<String>("parameters",httpHeaders);

        URI url = UriComponentsBuilder.fromHttpUrl(libraryBackendConfigration.getLibrarybackendEndpoint() + "/reader/login").build().encode().toUri();

        ResponseEntity<Boolean> respEntity = restTemplate.exchange(url, HttpMethod.GET, entity,  Boolean.class);
        return respEntity.getBody();
    }

    public ReaderDto getReaderByLoginData(String emailAddress,String password){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("emailAddress", emailAddress);
        httpHeaders.set("password", password);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);

        URI url = UriComponentsBuilder.fromHttpUrl(libraryBackendConfigration.getLibrarybackendEndpoint() + "/reader/emailAddress/password").build().encode().toUri();

        ResponseEntity<ReaderDto> respEntity = restTemplate.exchange(url, HttpMethod.GET, entity,  ReaderDto.class);
        return Optional.ofNullable(respEntity.getBody()).orElse(new ReaderDto());
    }

    public ReaderDto getReaderByUid(String uuid){
        return restTemplate.getForObject(libraryBackendConfigration.getLibrarybackendEndpoint() + "/reader/" + uuid, ReaderDto.class);
    }
}
