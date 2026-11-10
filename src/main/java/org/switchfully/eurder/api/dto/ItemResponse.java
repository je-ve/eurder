package org.switchfully.eurder.api.dto;

import java.math.BigDecimal;

public record ItemResponse(String id, String name, String description, BigDecimal price, int stock) {

}
