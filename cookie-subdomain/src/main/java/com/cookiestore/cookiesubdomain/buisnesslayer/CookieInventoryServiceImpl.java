package com.cookiestore.cookiesubdomain.buisnesslayer;

import com.cookiestore.cookiesubdomain.Inventory.datalayer.Inventory;
import com.cookiestore.cookiesubdomain.Inventory.datalayer.InventoryRepository;
import com.cookiestore.cookiesubdomain.Inventory.datamapperlayer.CookieInventoryResponseMapper;
import com.cookiestore.cookiesubdomain.Inventory.datamapperlayer.InventoryRequestMapper;
import com.cookiestore.cookiesubdomain.Inventory.datamapperlayer.InventoryResponseMapper;
import com.cookiestore.cookiesubdomain.Inventory.presentationlayer.CookieInventoryResponseModel;
import com.cookiestore.cookiesubdomain.Inventory.presentationlayer.InventoryRequestModel;
import com.cookiestore.cookiesubdomain.Inventory.presentationlayer.InventoryResponseModel;
import com.cookiestore.cookiesubdomain.datalayer.Cookie;
import com.cookiestore.cookiesubdomain.datalayer.CookieRepository;
import com.cookiestore.cookiesubdomain.datalayer.Size;
import com.cookiestore.cookiesubdomain.datamapperlayer.CookieRequestMapper;
import com.cookiestore.cookiesubdomain.datamapperlayer.CookieResponseMapper;
import com.cookiestore.cookiesubdomain.presentationlayer.CookieRequestModel;
import com.cookiestore.cookiesubdomain.presentationlayer.CookieResponseModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CookieInventoryServiceImpl implements CookieInventoryService{

    private InventoryRepository inventoryRepository;
    private InventoryResponseMapper inventoryResponseMapper;
    private InventoryRequestMapper inventoryRequestMapper;
    private CookieRepository cookieRepository;
    private CookieResponseMapper cookieResponseMapper;
    private CookieInventoryResponseMapper cookieInventoryResponseMapper;
    private CookieRequestMapper cookieRequestMapper;


    public CookieInventoryServiceImpl(InventoryRepository inventoryRepository, InventoryResponseMapper inventoryResponseMapper, InventoryRequestMapper inventoryRequestMapper, CookieRepository cookieRepository, CookieResponseMapper cookieResponseMapper, CookieInventoryResponseMapper cookieInventoryResponseMapper, CookieRequestMapper cookieRequestMapper) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryResponseMapper = inventoryResponseMapper;
        this.inventoryRequestMapper = inventoryRequestMapper;
        this.cookieRepository = cookieRepository;
        this.cookieResponseMapper = cookieResponseMapper;
        this.cookieInventoryResponseMapper = cookieInventoryResponseMapper;
        this.cookieRequestMapper = cookieRequestMapper;
    }

    @Override
    public List<InventoryResponseModel> getInventories(){
        return inventoryResponseMapper.entityListToResponseModelList(inventoryRepository.findAll());
    }

    @Override
    public CookieInventoryResponseModel getInventoryDetails(String invId) {
        //get inventory
        Inventory inventory = inventoryRepository.findByInventoryIdentifierInvId(invId);

        if(inventory == null){
            return null; // later throw exception
        }

        List<Cookie> cookies = cookieRepository.findAllByInventoryIdentifierInvId(invId);

        List<CookieResponseModel> cookieResponseModels = cookieResponseMapper.entityListToResponseModelList(cookies);

        return cookieInventoryResponseMapper.entitiesToResponseModel(inventory, cookieResponseModels);


    }

    @Override
    public List<CookieResponseModel> getCookiesInInventoryByField(String invId, Map<String, String> queryParams) {
        if(!inventoryRepository.existsByInventoryIdentifierInvId(invId)){
            return null;
        }

        //extract the query params
        String inStock = queryParams.get("inStock");
        String size = queryParams.get("size");
        //String identifier = queryParams.get("identifier");

        Boolean isAvailable = (inStock != null && inStock == "Y") ? true : false;

        Map<String, Size> sizeMap = new HashMap<String, Size>();

        sizeMap.put("XS", Size.MICROSCOPIC);
        sizeMap.put("S", Size.SMALL);
        sizeMap.put("M", Size.MEDIUM);
        sizeMap.put("L", Size.LARGE);
        sizeMap.put("XL", Size.EXTRA_LARGE);
        sizeMap.put("XXL", Size.COMICALLY_LARGE);

        if(isAvailable && size != null){
            return cookieResponseMapper.entityListToResponseModelList(cookieRepository.findAllByInventoryIdentifierInvIdAndQuantityGreaterThanAndSize(invId, 0,
                    sizeMap.get(size.toUpperCase())));
        }




        if(isAvailable ){
            return cookieResponseMapper.entityListToResponseModelList(cookieRepository.findAllByInventoryIdentifierInvIdAndQuantityGreaterThan(invId, 0));
        }

        if(size != null){
            return cookieResponseMapper.entityListToResponseModelList(cookieRepository.findAllByInventoryIdentifierInvIdAndSize(invId,
                    sizeMap.get(size.toUpperCase())));
        }



        return cookieResponseMapper.entityListToResponseModelList(cookieRepository.findAllByInventoryIdentifierInvId(invId));

    }

    @Override
    public InventoryResponseModel addInventory(InventoryRequestModel inventoryRequestModel) {

        Inventory inventory = inventoryRequestMapper.requestModelToEntity(inventoryRequestModel);
        Inventory saved = inventoryRepository.save(inventory);

        return inventoryResponseMapper.entityToResponseModel(saved);
    }

    @Override
    public CookieResponseModel addCookieToInventory(CookieRequestModel cookieRequestModel, String invId) {
        Inventory inventory = inventoryRepository.findByInventoryIdentifierInvId(invId);
        if(inventory == null){
            return null; //later throw exception
        }

        Cookie cookie = cookieRequestMapper.requestModelToEntity(cookieRequestModel, inventory.getInventoryIdentifier());

        Cookie saved = cookieRepository.save(cookie);

        return cookieResponseMapper.entityToResponseModel(saved);
    }

    @Override
    public InventoryResponseModel updateInventory(InventoryRequestModel inventoryRequestModel, String invId) {
        Inventory inventory = inventoryRepository.findByInventoryIdentifierInvId(invId);
        if(inventory == null){
            return null; //later throw exception
        }

        Inventory updateInventory = inventoryRequestMapper.requestModelToEntity(inventoryRequestModel);
        updateInventory.setId(inventory.getId());
        updateInventory.setInventoryIdentifier(inventory.getInventoryIdentifier());

        return inventoryResponseMapper.entityToResponseModel(inventoryRepository.save(updateInventory));
    }

    @Override
    public CookieResponseModel updateCookie(CookieRequestModel cookieRequestModel, String invId, String cookieIdentifier) {
        Inventory inventory = inventoryRepository.findByInventoryIdentifierInvId(invId);
        if(inventory == null){
            return null; //later throw exception
        }

        Cookie cookie = cookieRepository.findByCookieIdentifier(cookieIdentifier);
        if(cookie == null){
            return null;
        }

        Cookie updateCookie = cookieRequestMapper.requestModelToEntity(cookieRequestModel, cookie.getInventoryIdentifier());
        updateCookie.setId(cookie.getId());

        return cookieResponseMapper.entityToResponseModel(cookieRepository.save(updateCookie));
    }

    @Override
    public void removeCookieFromInventory(String invId, String cookieId) {
        if(!inventoryRepository.existsByInventoryIdentifierInvId(invId)){
            return;
        }

        Cookie cookie = cookieRepository.findByCookieIdentifier(cookieId);

        if(cookie == null){
            return;
        }

        cookieRepository.delete(cookie);
    }

    @Override
    public void deleteInventoryAndContents(String invId) {
        Inventory inventory = inventoryRepository.findByInventoryIdentifierInvId(invId);
        if(inventory == null){
            return;
        }

        List<Cookie> cookies = cookieRepository.findAllByInventoryIdentifierInvId(invId);

        cookieRepository.deleteAll(cookies);

        inventoryRepository.delete(inventory);
    }
}
