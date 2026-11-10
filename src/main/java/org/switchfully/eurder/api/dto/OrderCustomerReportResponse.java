package org.switchfully.eurder.api.dto;
import java.math.BigDecimal;
import java.util.List;

public class OrderCustomerReportResponse {
    private final String id;
    private final List<ItemGroupCustomerReportResponse> itemGroupCustomerReportResponses;
    private final BigDecimal totalPrice;

    public OrderCustomerReportResponse(String orderId,
                                       List<ItemGroupCustomerReportResponse> itemGroupCustomerReportResponses, BigDecimal totalPrice) {
        this.id = orderId;
        this.itemGroupCustomerReportResponses = itemGroupCustomerReportResponses;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }
}
