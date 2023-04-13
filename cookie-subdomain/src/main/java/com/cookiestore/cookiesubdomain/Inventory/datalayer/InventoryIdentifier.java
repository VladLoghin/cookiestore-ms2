package com.cookiestore.cookiesubdomain.Inventory.datalayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.UUID;


@Embeddable
public class InventoryIdentifier {
    @Column(name = "INVID", nullable = false, unique = true)
    private String invId;

    public InventoryIdentifier(){this.invId = UUID.randomUUID().toString();}

    public String getInvId() {
        return invId;
    }
}
