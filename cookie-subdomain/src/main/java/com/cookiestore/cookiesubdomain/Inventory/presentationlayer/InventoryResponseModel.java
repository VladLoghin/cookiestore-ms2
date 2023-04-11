package com.cookiestore.cookiesubdomain.Inventory.presentationlayer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InventoryResponseModel {
    private String invId;
    private String stock_type;
}
