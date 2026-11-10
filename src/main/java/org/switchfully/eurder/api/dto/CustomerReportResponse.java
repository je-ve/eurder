package org.switchfully.eurder.api.dto;
import java.util.List;

public class CustomerReportResponse {

    private List<OrderCustomerReportResponse> orderCustomerReportResponses;
    private double total;

    public CustomerReportResponse() { }

    public CustomerReportResponse(List<OrderCustomerReportResponse> orderCustomerReportResponses, double total) {
        this.orderCustomerReportResponses = orderCustomerReportResponses;
        this.total = total;
    }

    public List<OrderCustomerReportResponse> getOrderCustomerReportDtos() {
        return orderCustomerReportResponses;
    }
}
