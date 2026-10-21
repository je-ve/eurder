package org.switchfully.eurder.api.dto;
import java.util.List;

public class OrderCustomerReportDto {
    private final String id;
    private final List<ItemGroupCustomerReportDto> itemGroupCustomerReportDtos;
    private final double totalPrice;

    public OrderCustomerReportDto(String orderId,
                                  List<ItemGroupCustomerReportDto> itemGroupCustomerReportDtos, double totalPrice) {
        this.id = orderId;
        this.itemGroupCustomerReportDtos = itemGroupCustomerReportDtos;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public List<ItemGroupCustomerReportDto> getItemGroups() {
        return itemGroupCustomerReportDtos;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
}
