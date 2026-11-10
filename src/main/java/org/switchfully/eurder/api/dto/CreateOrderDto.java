package org.switchfully.eurder.api.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateOrderDto {

    private String customerId;
    private List<CreateItemGroupRequest> createItemGroupRequests;

    public CreateOrderDto() {
    }

    public CreateOrderDto(String customerId, List<CreateItemGroupRequest> createitemGroupRequests) {
        this.customerId = customerId;
        this.createItemGroupRequests = createitemGroupRequests;
    }

    public String getCustomerId() {
        return customerId;
    }
    public List<CreateItemGroupRequest> getCreateItemGroupDtos() {
        return createItemGroupRequests;
    }
}
