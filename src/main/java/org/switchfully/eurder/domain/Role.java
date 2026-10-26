package org.switchfully.eurder.domain;

import java.util.List;

public enum Role {
    CUSTOMER(List.of(
            Feature.CREATE_CUSTOMER_ACCOUNT,
            Feature.CREATE_ORDER,
            Feature.REORDER_EXISTING_ORDER,
            Feature.VIEW_CUSTOMER_ORDER_REPORT
    )),
    ADMIN(List.of(
            Feature.ADD_NEW_ITEM,
            Feature.UPDATE_ITEM,
            Feature.VIEW_ALL_CUSTOMERS,
            Feature.VIEW_ITEM_STOCK_OVERVIEW,
            Feature.VIEW_SINGLE_CUSTOMER
    ));

    private final List<Feature> features;

    Role(List<Feature> features) {
        this.features = features;
    }

    public boolean containsFeature(Feature feature) {
        return features.contains(feature);
    }
}
