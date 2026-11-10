package org.switchfully.eurder.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ItemGroupResponse(String itemId, String name, int amount, BigDecimal pricePerUnit,
                                LocalDate shippingDate) {
}
