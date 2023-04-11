package com.cookiestore.cookiesubdomain.presentationlayer;

import com.cookiestore.cookiesubdomain.datalayer.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CookieResponseModel {
    private final String cookieIdentifier;
    private final String invId;
    private final String name;
    private final Integer quantity;
    private final Size size;
    private final Double price;
}
