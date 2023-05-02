package com.cookiestore.cookiesubdomain.presentationlayer;

import com.cookiestore.cookiesubdomain.Inventory.datalayer.Inventory;
import com.cookiestore.cookiesubdomain.Inventory.datalayer.InventoryIdentifier;
import com.cookiestore.cookiesubdomain.Inventory.datalayer.InventoryRepository;
import com.cookiestore.cookiesubdomain.Inventory.presentationlayer.InventoryRequestModel;
import com.cookiestore.cookiesubdomain.datalayer.CookieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import javax.print.attribute.standard.Media;
import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Sql({"/data-mysql.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class InventoryControllerIntegration {

    private final String BASE_URI_INVENTORIES = "/api/v1/cookieInventories";
    private final String VALID_COOKIE_INVENTORY_ID = "11111111";

    private final String VALID_INVENTORY_TYPE = "sugar";

    @Autowired
    CookieRepository cookieRepository;

    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    WebTestClient webTestClient;

    @Sql({"/data-mysql.sql"})
    @Test
    public void whenInventoriesExist_thenReturnAllInventories() {
        //arrange
        Integer expectedNumInventories = 2;

        //act

        webTestClient.get()
                .uri(BASE_URI_INVENTORIES)
                .accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.length()").isEqualTo(expectedNumInventories);
    }

    @Sql({"/data-mysql.sql"})
    @Test
    public void whenGetInventoriesWithValidIdExists_thenReturnInventories(){
        webTestClient.get()
                .uri(BASE_URI_INVENTORIES + "/" + VALID_COOKIE_INVENTORY_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].invId").isEqualTo(VALID_COOKIE_INVENTORY_ID);
    }


    //todo
    @Sql({"/data-mysql.sql"})
    @Test
    public void whenGetInventoriesWithValidIdExistsInCookies_thenReturnInentory() {

    }


    //fix this request as it adds a null
    @Sql({"/data-mysql.sql"})
    @Test
    public void whenCreateInventoryWithValidValues_thenReturnNewInventoryType(){
        String expectedType = "cake";
        InventoryRequestModel inventoryRequestModel = new InventoryRequestModel();
        inventoryRequestModel.setStock_type(expectedType);

        webTestClient.post()
                .uri(BASE_URI_INVENTORIES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(inventoryRequestModel)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()//go back and check if it should be CREATED instead
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                //.jsonPath("$.invId").isNotEmpty()
                .jsonPath("$.stock_type").isEqualTo(expectedType);
    }


    /*
    @Sql({"/data-mysql.sql"})
    @Test
    public void whenCreateInventoryWithValidValues_thenReturnInventory(){


        String newUri= "12334123123";
        InventoryRequestModel inventoryRequestModel = new InventoryRequestModel(expectedType);


        webTestClient.post()
                .uri(BASE_URI_INVENTORIES + "/" + VALID_COOKIE_INVENTORY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(CookieRequestModel)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(CookieResponseModel.class)
                .value((dto) -> {
                   assertNotNull(dto);
                   assertEquals(dto.getInvId(), VALID_COOKIE_INVENTORY_ID);
                   assertEquals(dto.getCookieIdentifier(), newUri);
                   assertEquals(dto.getPrice(), );
                });
    }

     */

    //put inventory

    //put
    @Sql({"/data-mysql.sql"})
    @Test
    public void UpdateInventoryWithValidValues_thenReturnUpdatedInventory(){
        //arrange
        String expectedType = "11111111";
        InventoryRequestModel inventoryRequestModel = new InventoryRequestModel();

        //act and assert
        webTestClient.put()
                .uri(BASE_URI_INVENTORIES + "/" + VALID_COOKIE_INVENTORY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(inventoryRequestModel)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.invId").isEqualTo(VALID_COOKIE_INVENTORY_ID);
                //.jsonPath("$.stock_type").isEqualTo(expectedType);
    }

    //delete
    //expected 204 no_content but was 200 ok
    @Test
    public void whenDeleteInventoryItem_thenDeleteAllDataOnItem(){

        //arrange
        Integer actualNumOfItemsBeforeDelete = 1;
        Integer actualNumOfItemsAfterDelete = 0;


        assertEquals(actualNumOfItemsBeforeDelete,
            cookieRepository.findAllByInventoryIdentifierInvId(VALID_COOKIE_INVENTORY_ID).size());

        webTestClient.delete()
                .uri(BASE_URI_INVENTORIES + "/" + VALID_COOKIE_INVENTORY_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();

        assertEquals(actualNumOfItemsAfterDelete,
                cookieRepository.findAllByInventoryIdentifierInvId(VALID_COOKIE_INVENTORY_ID).size());
        assertFalse(inventoryRepository.existsByInventoryIdentifierInvId(VALID_COOKIE_INVENTORY_ID));
    }



    }

