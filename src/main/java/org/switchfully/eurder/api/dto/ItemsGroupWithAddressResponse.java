package org.switchfully.eurder.api.dto;

public class ItemsGroupWithAddressResponse {
    private ItemGroupResponse itemGroupResponse;
    private AddressDto addressDto;

    public ItemsGroupWithAddressResponse(ItemGroupResponse itemGroupResponse, AddressDto addressDto) {
        this.itemGroupResponse = itemGroupResponse;
        this.addressDto = addressDto;
    }

    public ItemsGroupWithAddressResponse() {
    }
}
