package org.switchfully.eurder.api.dto;
import java.math.BigDecimal;
import java.util.List;

public class OrderCustomerReportDto {
    private final String id;
    private final List<ItemGroupCustomerReportDto> itemGroupCustomerReportDtos;
    private final BigDecimal totalPrice;

    public OrderCustomerReportDto(String orderId,
                                  List<ItemGroupCustomerReportDto> itemGroupCustomerReportDtos, BigDecimal totalPrice) {
        this.id = orderId;
        this.itemGroupCustomerReportDtos = itemGroupCustomerReportDtos;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }
}
