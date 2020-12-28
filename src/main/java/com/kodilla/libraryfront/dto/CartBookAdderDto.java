package com.kodilla.libraryfront.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CartBookAdderDto {

    private Long cartId;

    private VolumeDto VolumeDto;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public void setVolumeDto(com.kodilla.libraryfront.dto.VolumeDto volumeDto) {
        VolumeDto = volumeDto;
    }

    public VolumeDto getVolumeDto() {
        return VolumeDto;
    }

    public CartBookAdderDto() {
    }

    public CartBookAdderDto(Long cartId) {
        this.cartId = cartId;
    }

    public CartBookAdderDto(Long cartId, com.kodilla.libraryfront.dto.VolumeDto volumeDto) {
        this.cartId = cartId;
        VolumeDto = volumeDto;
    }
}

