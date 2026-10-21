package org.switchfully.eurder.api.mapper;

import org.springframework.stereotype.Component;
import org.switchfully.eurder.api.dto.ItemGroupCustomerReportDto;
import org.switchfully.eurder.api.dto.ItemGroupDto;
import org.switchfully.eurder.domain.ItemGroup;

import java.util.Collection;
import java.util.List;

@Component
public class ItemGroupMapper {

    public ItemGroupDto mapToDto(ItemGroup itemgroup) {
        return new ItemGroupDto(itemgroup.getItemId(), itemgroup.getName(), itemgroup.getAmount(),
                itemgroup.getPricePerUnit(), itemgroup.getShippingDate());
    }

    public List<ItemGroupDto> mapToDto(Collection<ItemGroup> itemGroups) {
        return itemGroups.stream().map(this::mapToDto).toList();
    }

    public ItemGroupCustomerReportDto mapToCustomerReportDto(ItemGroup itemgroup) {
        return new ItemGroupCustomerReportDto(itemgroup.getItemId(), itemgroup.getName(), itemgroup.getAmount(),
                itemgroup.getPricePerUnit());
    }

    public List<ItemGroupCustomerReportDto> mapToCustomerReportDto(Collection<ItemGroup> itemGroups) {
        return itemGroups.stream().map(this::mapToCustomerReportDto).toList();
    }
}
