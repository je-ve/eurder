package org.switchfully.eurder.api.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateOrderDto {

    private String customerId;
    private List<CreateItemGroupDto> createItemGroupDtos;

    public CreateOrderDto() {
    }

    public CreateOrderDto(String customerId, List<CreateItemGroupDto> createitemGroupDtos) {
        this.customerId = customerId;
        this.createItemGroupDtos = createitemGroupDtos;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<CreateItemGroupDto> getCreateItemGroupDtos() {
        return createItemGroupDtos;
    }
}
