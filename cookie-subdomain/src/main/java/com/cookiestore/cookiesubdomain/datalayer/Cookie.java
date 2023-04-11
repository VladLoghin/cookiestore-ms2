package com.cookiestore.cookiesubdomain.datalayer;

import com.cookiestore.cookiesubdomain.Inventory.datalayer.InventoryIdentifier;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="Cookie")
@Data
public class Cookie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cookie_id")
    private String cookieIdentifier;

    @Embedded
    private InventoryIdentifier inventoryIdentifier;

    @Enumerated(EnumType.STRING)
    @Column(name = "cookie_size")
    private Size size;

    private Integer quantity;

    @Column(name = "cookie_name")
    private String name;

    private Double price;

    Cookie(){

    }

    public Cookie(String cookieIdentifier, Size size, Integer quantity, String name, Double price) {
        this.cookieIdentifier = cookieIdentifier;
        this.size = size;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }
}
