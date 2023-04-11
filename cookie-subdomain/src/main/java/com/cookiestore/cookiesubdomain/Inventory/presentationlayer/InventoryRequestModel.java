package com.cookiestore.cookiesubdomain.Inventory.presentationlayer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequestModel {

    String stock_type;


    public InventoryRequestModel(){

    }
    public InventoryRequestModel(String stock_type) {
        this.stock_type = stock_type;
    }

    public String getStock_type() {
        return stock_type;
    }

    public void setStock_type(String stock_type) {
        this.stock_type = stock_type;
    }

}
