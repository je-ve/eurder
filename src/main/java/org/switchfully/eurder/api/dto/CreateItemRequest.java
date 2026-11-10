package org.switchfully.eurder.api.dto;

import java.math.BigDecimal;

public record CreateItemRequest(String name, String description, BigDecimal price, int stock) {

}
