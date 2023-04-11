package com.cookiestore.cookiesubdomain.Inventory.datamapperlayer;

import com.cookiestore.cookiesubdomain.Inventory.datalayer.Inventory;
import com.cookiestore.cookiesubdomain.Inventory.presentationlayer.CookieInventoryResponseModel;
import com.cookiestore.cookiesubdomain.presentationlayer.CookieResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CookieInventoryResponseMapper {


    @Mapping(expression = "java(inventory.getInventoryIdentifier().getInvId())", target = "invId")
    @Mapping(expression = "java(cookies)", target = "availableCookies")
    CookieInventoryResponseModel entitiesToResponseModel(Inventory inventory, List<CookieResponseModel> cookies);

}
