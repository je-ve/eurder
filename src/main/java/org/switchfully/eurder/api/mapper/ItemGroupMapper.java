package org.switchfully.eurder.api.mapper;

import org.springframework.stereotype.Component;
import org.switchfully.eurder.api.dto.ItemGroupCustomerReportResponse;
import org.switchfully.eurder.api.dto.ItemGroupResponse;
import org.switchfully.eurder.domain.ItemGroup;

import java.util.Collection;
import java.util.List;

@Component
public class ItemGroupMapper {

    public ItemGroupResponse mapToDto(ItemGroup itemgroup) {
        return new ItemGroupResponse(itemgroup.getItemId(), itemgroup.getName(), itemgroup.getAmount(),
                itemgroup.getPricePerUnit(), itemgroup.getShippingDate());
    }

    public List<ItemGroupResponse> mapToDto(Collection<ItemGroup> itemGroups) {
        return itemGroups.stream().map(this::mapToDto).toList();
    }

    public ItemGroupCustomerReportResponse mapToCustomerReportDto(ItemGroup itemgroup) {
        return new ItemGroupCustomerReportResponse(itemgroup.getItemId(), itemgroup.getName(), itemgroup.getAmount(),
                itemgroup.getPricePerUnit());
    }

    public List<ItemGroupCustomerReportResponse> mapToCustomerReportDto(Collection<ItemGroup> itemGroups) {
        return itemGroups.stream().map(this::mapToCustomerReportDto).toList();
    }
}
