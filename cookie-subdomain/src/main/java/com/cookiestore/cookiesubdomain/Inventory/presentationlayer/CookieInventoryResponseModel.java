package com.cookiestore.cookiesubdomain.Inventory.presentationlayer;

import com.cookiestore.cookiesubdomain.presentationlayer.CookieResponseModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CookieInventoryResponseModel {
    private String invId;
    private String stock_type;
    private List<CookieResponseModel> availableCookies;
}
