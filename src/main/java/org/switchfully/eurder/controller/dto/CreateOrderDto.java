package org.switchfully.eurder.controller.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateOrderDto {

    private List<CreateItemGroupDto> createItemGroupDtos;

    public CreateOrderDto() {
    }

    public CreateOrderDto(List<CreateItemGroupDto> createitemGroupDtos) {
        this.createItemGroupDtos = createitemGroupDtos;
    }

    public List<CreateItemGroupDto> getCreateItemGroupDtos() {
        return createItemGroupDtos;
    }
}
