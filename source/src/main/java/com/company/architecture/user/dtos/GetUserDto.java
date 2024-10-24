package com.company.architecture.user.dtos;

import com.company.architecture.shared.dtos.PageableDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public final class GetUserDto extends PageableDto {
    private String name;

    public GetUserDto() {
        setSort("name");
    }
}
