package com.cookiestore.cookiesubdomain.datamapperlayer;

import com.cookiestore.cookiesubdomain.Inventory.datalayer.InventoryIdentifier;
import com.cookiestore.cookiesubdomain.datalayer.Cookie;
import com.cookiestore.cookiesubdomain.presentationlayer.CookieRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CookieRequestMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(inventoryIdentifier)", target = "inventoryIdentifier")
    })
    Cookie requestModelToEntity(CookieRequestModel cookieRequestModel, InventoryIdentifier inventoryIdentifier);

}
