package com.kodilla.libraryfront.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CartBookAdderDto {

    private Long cartId;

    private List<VolumeDto> VolumeDtoList;

    public Long getCartId() {
        return cartId;
    }

    public List<VolumeDto> getVolumeDtoList() {
        return VolumeDtoList;
    }
}

