package com.epam.pharmacy.logic.purchaseStory;

import com.epam.pharmacy.dao.exception.DaoException;
import com.epam.pharmacy.dao.purchase.story.PurchaseStoryDao;
import com.epam.pharmacy.dao.manager.DaoConnectionManager;
import com.epam.pharmacy.dao.manager.DaoConnectionManagerFactory;
import com.epam.pharmacy.logic.exception.NotFoundException;
import com.epam.pharmacy.logic.exception.ServiceException;
import com.epam.pharmacy.logic.validator.Validator;
import com.epam.pharmacy.model.entity.PurchaseStory;
import com.google.common.base.Preconditions;

import java.util.List;
import java.util.Optional;

public class PurchaseStoryServiceImpl implements PurchaseStoryService {

    private static final long MIN_ID_VALUE = 1L;
    private static final int MIN_ITEM_ON_PAGE = 1;
    private static final int MIN_PAGE_VALUE = 1;
    private final DaoConnectionManagerFactory factory;
    private final Validator<PurchaseStory> purchaseStoryValidator;


    public PurchaseStoryServiceImpl(DaoConnectionManagerFactory factory, Validator<PurchaseStory> purchaseStoryValidator) {
        this.factory = factory;
        this.purchaseStoryValidator = purchaseStoryValidator;
    }

    @Override
    public PurchaseStory getById(long id) throws Exception {
        Preconditions.checkArgument(id >= MIN_ID_VALUE, "Invalid id value: " + id);

        try (DaoConnectionManager manager = factory.create()) {
            PurchaseStoryDao purchaseStoryDao = manager.createPurchaseStoryDao();
            Optional<PurchaseStory> found = purchaseStoryDao.getById(id);
            return found.orElseThrow(() -> new NotFoundException("No any medicine found by id: " + id));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<PurchaseStory> getPageForOne(long id, int page, int itemsPerPage) throws Exception {
        Preconditions.checkArgument(page >= MIN_PAGE_VALUE, "Invalid page value: " + page);
        Preconditions.checkArgument(itemsPerPage >= MIN_ITEM_ON_PAGE, "Invalid itemsPerPage: " + itemsPerPage);

        int firstItem = (page - 1) * itemsPerPage + 1;
        try (DaoConnectionManager manager = factory.create()) {
            PurchaseStoryDao purchaseStoryDao = manager.createPurchaseStoryDao();
            return purchaseStoryDao.findBatchForOne(id,itemsPerPage, firstItem);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getNumberOfPages(int itemsPerPage) throws Exception {
        Preconditions.checkArgument(itemsPerPage >= MIN_ITEM_ON_PAGE, "Invalid itemsPerPage : " + itemsPerPage);

        try (DaoConnectionManager manager = factory.create()) {
            PurchaseStoryDao purchaseStoryDao = manager.createPurchaseStoryDao();
            long numberOfItems = purchaseStoryDao.getPurchaseStoryAmount();
            return (int) Math.ceil(numberOfItems / (double) itemsPerPage);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<PurchaseStory> getPage(int page, int itemsPerPage) throws Exception {
        Preconditions.checkArgument(page >= MIN_PAGE_VALUE, "Invalid page value: " + page);
        Preconditions.checkArgument(itemsPerPage >= MIN_ITEM_ON_PAGE, "Invalid itemsPerPage: " + itemsPerPage);

        int firstItem = (page - 1) * itemsPerPage + 1;
        try (DaoConnectionManager manager = factory.create()) {
            PurchaseStoryDao purchaseStoryDao = manager.createPurchaseStoryDao();
            return purchaseStoryDao.findBatch(itemsPerPage, firstItem);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
