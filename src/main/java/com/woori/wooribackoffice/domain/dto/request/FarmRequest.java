package com.woori.wooribackoffice.domain.dto.request;

import com.woori.wooribackoffice.util.CustomStringUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmRequest {
    private Long id;
    private String address;
    private String name;
    private String owner;

    public void setName(String name) {
        this.name = CustomStringUtil.removeAllWhiteSpace(name);
    }

    public void setOwner(String owner) {
        this.owner = CustomStringUtil.removeAllWhiteSpace(owner);
    }
}
