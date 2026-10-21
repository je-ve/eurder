package org.switchfully.eurder.api.dto;

import java.util.List;

public class CustomerReportDto {

    private List<OrderCustomerReportDto> orderCustomerReportDtos;
    private double total;

    public CustomerReportDto() { }

    public CustomerReportDto(List<OrderCustomerReportDto> orderCustomerReportDtos, double total) {
        this.orderCustomerReportDtos = orderCustomerReportDtos;
        this.total = total;
    }

    public List<OrderCustomerReportDto> getOrderCustomerReportDtos() {
        return orderCustomerReportDtos;
    }

    public double getTotal() {
        return total;
    }
}
