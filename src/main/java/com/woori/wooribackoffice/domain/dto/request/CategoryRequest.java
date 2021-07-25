package com.woori.wooribackoffice.domain.dto.request;

import com.woori.wooribackoffice.util.CustomStringUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
    private Long id;
    private String name;
    private String description;

    public void setName(String name) {
        this.name = CustomStringUtil.removeAllWhiteSpace(name);
    }
}
