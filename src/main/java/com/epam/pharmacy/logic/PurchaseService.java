package com.epam.pharmacy.logic;

import com.epam.pharmacy.model.entity.Prescription;
import com.epam.pharmacy.model.entity.Purchase;

import java.util.List;

public interface PurchaseService {

    Purchase getById(long id) throws Exception;

    long save(Purchase purchase) throws Exception;

    long saveItem(Long userId,Long medicineId,Long quantity,String purchaseDate) throws Exception;

    void deletePurchaseById(long id) throws Exception;

    int getNumberOfPages(int itemsPerPage) throws Exception;

    List<Purchase> getPage(int page, int itemsPerPage) throws Exception;
}
