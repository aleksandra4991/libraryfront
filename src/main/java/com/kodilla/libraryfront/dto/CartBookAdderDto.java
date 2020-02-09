package com.kodilla.libraryfront.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CartBookAdderDto {

    private Long cartId;

    private List<BookDto> bookDtoList;

    public Long getCartId() {
        return cartId;
    }

    public List<BookDto> getBookDtoList() {
        return bookDtoList;
    }
}

