package org.switchfully.eurder.api.dto;

import java.math.BigDecimal;
import java.util.List;


public record OrderResponse(String id, String customerId, List<ItemGroupResponse> itemGroups, BigDecimal totalPrice) {
}
