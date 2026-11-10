package org.switchfully.eurder.api.dto;

import java.math.BigDecimal;

public record UpdateItemRequest(String name, String description, BigDecimal price, int stock) {
}
