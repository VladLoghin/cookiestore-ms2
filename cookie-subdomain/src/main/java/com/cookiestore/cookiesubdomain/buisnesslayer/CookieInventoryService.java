package com.cookiestore.cookiesubdomain.buisnesslayer;



import com.cookiestore.cookiesubdomain.Inventory.presentationlayer.CookieInventoryResponseModel;
import com.cookiestore.cookiesubdomain.Inventory.presentationlayer.InventoryRequestModel;
import com.cookiestore.cookiesubdomain.Inventory.presentationlayer.InventoryResponseModel;
import com.cookiestore.cookiesubdomain.presentationlayer.CookieRequestModel;
import com.cookiestore.cookiesubdomain.presentationlayer.CookieResponseModel;

import java.util.List;
import java.util.Map;

public interface CookieInventoryService {
    List<InventoryResponseModel> getInventories();

    CookieInventoryResponseModel getInventoryDetails(String invId);
    List<CookieResponseModel> getCookiesInInventoryByField(String invId, Map<String, String> queryParams);

    CookieResponseModel addCookieToInventory(CookieRequestModel cookieRequestModel, String invId);
    InventoryResponseModel updateInventory(InventoryRequestModel inventoryRequestModel, String invId);

    CookieResponseModel updateCookie(CookieRequestModel cookieRequestModel, String invId, String cookieIdentifier);
    void removeCookieFromInventory(String invId, String cookieId);
    void deleteInventoryAndContents(String invId);

    public InventoryResponseModel addInventory(InventoryRequestModel inventoryRequestModel);
}
