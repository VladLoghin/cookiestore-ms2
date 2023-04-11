package com.cookiestore.cookiesubdomain.datamapperlayer;

import com.cookiestore.cookiesubdomain.datalayer.Cookie;
import com.cookiestore.cookiesubdomain.presentationlayer.CookieResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CookieResponseMapper {
    @Mapping(expression = "java(cookie.getCookieIdentifier())", target = "cookieIdentifier")
    @Mapping(expression = "java(cookie.getInventoryIdentifier().getInvId())", target = "invId")
    CookieResponseModel entityToResponseModel(Cookie cookie);

    List<CookieResponseModel> entityToResponseModel(List<Cookie> cookie);

    List<CookieResponseModel> entityListToResponseModelList(List<Cookie> cookies);
}
