package com.cookiestore.cookiesubdomain.Inventory.datalayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class InventoryPersistenceTest {
    private Inventory preSavedInventory;

    @Autowired
    InventoryRepository inventoryRepository;

    @BeforeEach
    public void setup() {
        inventoryRepository.deleteAll();
        preSavedInventory = inventoryRepository.save(new Inventory(new InventoryIdentifier(), "no-sugar"));
    }

    @Test
    public void saveNewInventory_shouldSucceed() {
        //arrange
        String expectedType = "sugar";
        Inventory newInventory = new Inventory(new InventoryIdentifier(), expectedType);

        //act
        Inventory savedInventory = inventoryRepository.save(newInventory);

        //assert
        assertNotNull(savedInventory);
        assertNotNull(savedInventory.getId());
        assertNotNull(savedInventory.getInventoryIdentifier().getInvId());
        assertEquals(expectedType, savedInventory.getStock_type());
    }

    @Test
    public void updateInventory_shouldSucceed() {
        //arrange
        String updatedType = "no-sugar";
        preSavedInventory.setStock_type(updatedType);

        //act
        Inventory savedInventory = inventoryRepository.save(preSavedInventory);

        //assert
        assertNotNull(savedInventory);
        assertThat(savedInventory, samePropertyValuesAs(preSavedInventory));
    }

    @Test
    public void findByInventoryIdentifier_InventoryId_ShouldSucceed() {
        //act
        Inventory found = inventoryRepository.findByInventoryIdentifierInvId(
                preSavedInventory.getInventoryIdentifier().getInvId());

        //assert
        assertNotNull(found);
        assertThat(preSavedInventory, samePropertyValuesAs(found));
    }

    @Test
    public void existsInventoryIdentifier_InvId_ShouldReturnNull() {
        //act
        Inventory found = inventoryRepository.findByInventoryIdentifierInvId(
                preSavedInventory.getInventoryIdentifier().getInvId() + 1);

        //assert
        assertNull(found);
    }

    @Test
    public void existsInventoryIdentifier_InvId_ShouldReturnTrue() {
        //act
        Boolean found = inventoryRepository.existsByInventoryIdentifierInvId(preSavedInventory.getInventoryIdentifier().getInvId());

        //assert
        assertTrue(found);
    }

    public void existsInvalidInventoryIdentifier_InvId_ShouldReturnFalse() {
        //act
        Boolean found = inventoryRepository.existsByInventoryIdentifierInvId(preSavedInventory.getInventoryIdentifier().getInvId() + 1);

        //assert
        assertFalse(found);
    }

}