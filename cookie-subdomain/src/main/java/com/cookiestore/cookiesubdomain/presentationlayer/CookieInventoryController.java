package com.cookiestore.cookiesubdomain.presentationlayer;

import com.cookiestore.cookiesubdomain.Inventory.presentationlayer.InventoryRequestModel;
import com.cookiestore.cookiesubdomain.Inventory.presentationlayer.InventoryResponseModel;
import com.cookiestore.cookiesubdomain.buisnesslayer.CookieInventoryService;
import com.cookiestore.cookiesubdomain.utils.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/cookieInventories")
public class CookieInventoryController {

    @Autowired
    private CookieInventoryService cookieInventoryService;

    @GetMapping()
    public List<InventoryResponseModel> getAllInventories(){
        return cookieInventoryService.getInventories();
    }

    //get cookies in inventory
    @GetMapping("{invId}")
    List<CookieResponseModel> getCookiesInInventory(@PathVariable String invId,
                                                      @RequestParam Map<String, String> queryParams){

        return cookieInventoryService.getCookiesInInventoryByField(invId, queryParams);
    }

    //get cookies inventory
    @GetMapping("{invId}/cookies")
    List<CookieResponseModel> getInventoryDetails(@PathVariable String invId, @RequestParam Map<String, String> queryParams){
        return cookieInventoryService.getCookiesInInventoryByField(invId, queryParams);
    }

    //add to inventory
    @PostMapping()
    InventoryResponseModel addInventory(InventoryRequestModel inventoryRequestModel){
        return cookieInventoryService.addInventory(inventoryRequestModel);
    }

    @PostMapping("/{invId}/cookies")
    CookieResponseModel addCookieToInventory(@RequestBody CookieRequestModel cookieRequestModel, @PathVariable String invId){
        if(cookieRequestModel.getCookieIdentifier().length() != 17){
            throw new InvalidInputException("Invalid CookieIdentifier provided: "+cookieRequestModel.getCookieIdentifier());
        }
        return cookieInventoryService.addCookieToInventory(cookieRequestModel, invId);
    }

    @PutMapping("/{invId}")
    public InventoryResponseModel updateInventory(@RequestBody InventoryRequestModel inventoryRequestModel, @PathVariable
                                                 String invId){
        return cookieInventoryService.updateInventory(inventoryRequestModel, invId);
    }

    @PutMapping("/{invId}/cookies/{cookieId}")
    public CookieResponseModel updateCookie(@RequestBody CookieRequestModel cookieRequestModel, @PathVariable String invId, @PathVariable String cookieId){
        return cookieInventoryService.updateCookie(cookieRequestModel, invId, cookieId);
    }

    @DeleteMapping("/{invId}/cookies/{cookieId}")
    void removeCookieFromInventory(@PathVariable String invId, @PathVariable String cookieId){
        cookieInventoryService.removeCookieFromInventory(invId, cookieId);
    }

    @DeleteMapping("/{invId}")
    void deleteInventoryAndContents(@PathVariable String invId){
        cookieInventoryService.deleteInventoryAndContents(invId);
    }

}
