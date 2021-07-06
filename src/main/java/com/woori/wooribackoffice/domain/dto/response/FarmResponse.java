package com.woori.wooribackoffice.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.woori.wooribackoffice.domain.entity.Farm;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FarmResponse {
    private Long id;
    private String address;
    private String name;
    private String owner;

    public static FarmResponse from(Farm farm) {
        return new FarmResponse().setId(farm.getId())
                .setName(farm.getName())
                .setAddress(farm.getAddress())
                .setOwner(farm.getOwner());
    }
}
