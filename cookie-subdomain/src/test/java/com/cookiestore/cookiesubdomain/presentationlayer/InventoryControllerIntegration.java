package com.cookiestore.cookiesubdomain.presentationlayer;

import com.cookiestore.cookiesubdomain.Inventory.datalayer.Inventory;
import com.cookiestore.cookiesubdomain.Inventory.datalayer.InventoryIdentifier;
import com.cookiestore.cookiesubdomain.Inventory.datalayer.InventoryRepository;
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
    private final String VALID_COOKIE_INVENTORY = "7111";

    private final String VALID_INVENTORY_TYPE="sugar";

    @Autowired
    CookieRepository cookieRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    /*@Autowired
    WebTestClient webTestClient;
     */

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


}