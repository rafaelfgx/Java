package com.company.architecture.product.dtos;

import com.company.architecture.shared.dtos.PageableDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public final class GetProductDto extends PageableDto {
    private String description;

    public GetProductDto() {
        setSort("description");
    }
}
