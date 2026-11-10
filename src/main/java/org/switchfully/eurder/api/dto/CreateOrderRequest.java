package org.switchfully.eurder.api.dto;

import org.springframework.stereotype.Component;

import java.util.List;

public class CreateOrderRequest {

    private String customerId;
    private List<CreateItemGroupRequest> createItemGroupRequests;
    public CreateOrderRequest() {
    }
    public CreateOrderRequest(String customerId, List<CreateItemGroupRequest> createitemGroupRequests) {
        this.customerId = customerId;
        this.createItemGroupRequests = createitemGroupRequests;
    }
    public String getCustomerId() {
        return customerId;
    }
    public List<CreateItemGroupRequest> getCreateItemGroupDtos() {
        return createItemGroupRequests;
    }

    public List<CreateItemGroupRequest> getCreateItemGroupRequests() {
        return createItemGroupRequests;
    }
}
