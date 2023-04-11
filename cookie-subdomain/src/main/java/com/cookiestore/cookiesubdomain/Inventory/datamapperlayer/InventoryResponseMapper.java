package com.cookiestore.cookiesubdomain.Inventory.datamapperlayer;

import com.cookiestore.cookiesubdomain.Inventory.datalayer.Inventory;
import com.cookiestore.cookiesubdomain.Inventory.presentationlayer.InventoryResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryResponseMapper {

    @Mapping(expression = "java(inventory.getInventoryIdentifier().getInvId())", target = "invId")
    InventoryResponseModel entityToResponseModel(Inventory inventory);


    List<InventoryResponseModel> entityListToResponseModelList(List<Inventory> inventories);


}
