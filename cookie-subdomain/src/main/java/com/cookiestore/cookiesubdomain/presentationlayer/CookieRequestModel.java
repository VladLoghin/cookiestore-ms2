package com.cookiestore.cookiesubdomain.presentationlayer;

import com.cookiestore.cookiesubdomain.datalayer.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CookieRequestModel {
    private String cookieIdentifier;
    private String invId;
    private String name;
    private Integer quantity;
    private Size size;
    private Double price;
}
