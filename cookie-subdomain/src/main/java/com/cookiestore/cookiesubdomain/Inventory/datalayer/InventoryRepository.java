package com.cookiestore.cookiesubdomain.Inventory.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Inventory findByInventoryIdentifierInvId(String invId);
    Boolean existsByInventoryIdentifierInvId(String invId);

}
