package com.epam.pharmacy.logic.purchaseStory;

import com.epam.pharmacy.model.entity.Purchase;
import com.epam.pharmacy.model.entity.PurchaseStory;

import java.util.List;

public interface PurchaseStoryService {

    PurchaseStory getById(long id) throws Exception;

    List<PurchaseStory> getPageForOne(long id, int page, int itemsPerPage) throws Exception;

    int getNumberOfPages(int itemsPerPage) throws Exception;

    List<PurchaseStory> getPage(int page, int itemsPerPage) throws Exception;

}
