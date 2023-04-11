package com.cookiestore.cookiesubdomain.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CookieRepository extends JpaRepository<Cookie, Integer> {
    List<Cookie> findAllByInventoryIdentifierInvId(String invId);

    //Inventory & qty
    List<Cookie> findAllByInventoryIdentifierInvIdAndQuantityGreaterThan(String invId, int zero);

    //Inventory & Size
    List<Cookie> findAllByInventoryIdentifierInvIdAndSize(String invId, Size sizeCookie);

    // Inventory & qty & Size
    List<Cookie> findAllByInventoryIdentifierInvIdAndQuantityGreaterThanAndSize(String invId, int zero, Size sizeCookie);

    Cookie findByCookieIdentifier(String cookieIdentifier);
}
