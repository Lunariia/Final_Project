package com.epam.pharmacy.logic.purchase;

import com.epam.pharmacy.dao.exception.DaoException;
import com.epam.pharmacy.dao.purchase.PurchaseDao;
import com.epam.pharmacy.dao.manager.DaoConnectionManager;
import com.epam.pharmacy.dao.manager.DaoConnectionManagerFactory;
import com.epam.pharmacy.logic.exception.NotFoundException;
import com.epam.pharmacy.logic.exception.ServiceException;
import com.epam.pharmacy.logic.validator.Validator;
import com.epam.pharmacy.model.entity.Purchase;
import com.google.common.base.Preconditions;

import java.util.List;
import java.util.Optional;

public class PurchaseServiceImpl implements PurchaseService {

    private static final long MIN_ID_VALUE = 1L;
    private static final int MIN_ITEM_ON_PAGE = 1;
    private static final int MIN_PAGE_VALUE = 1;
    private final DaoConnectionManagerFactory factory;
    private final Validator<Purchase> purchaseValidator;

    public PurchaseServiceImpl(DaoConnectionManagerFactory factory, Validator<Purchase> purchaseValidator) {
        this.factory = factory;
        this.purchaseValidator = purchaseValidator;
    }

    @Override
    public Purchase getById(long id) throws Exception {
        Preconditions.checkArgument(id >= MIN_ID_VALUE, "Invalid id value: " + id);

        try (DaoConnectionManager manager = factory.create()) {
            PurchaseDao purchaseDao = manager.createPurchaseDao();
            Optional<Purchase> found = purchaseDao.getById(id);
            return found.orElseThrow(() -> new NotFoundException("No any medicine found by id: " + id));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long save(Purchase purchase) throws Exception {
        if (!purchaseValidator.isValid(purchase)) {
            throw new ServiceException("Invalid Medicine object: " + purchase);
        }

        try (DaoConnectionManager manager = factory.create()) {
            PurchaseDao purchaseDao = manager.createPurchaseDao();
            return purchaseDao.add(purchase);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long saveItem(Long userId, Long medicineId, Long quantity, String purchaseDate) throws Exception {
        try (DaoConnectionManager manager = factory.create()) {
            PurchaseDao purchaseDao = manager.createPurchaseDao();

            return purchaseDao.addItem(userId,medicineId,quantity,purchaseDate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deletePurchaseById(long id) throws Exception {
        Preconditions.checkArgument(id >= MIN_ID_VALUE, "Invalid id value: " + id);

        try (DaoConnectionManager manager = factory.create()) {
            PurchaseDao purchaseDao = manager.createPurchaseDao();
            purchaseDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getNumberOfPages(int itemsPerPage) throws Exception {
        Preconditions.checkArgument(itemsPerPage >= MIN_ITEM_ON_PAGE, "Invalid itemsPerPage : " + itemsPerPage);

        try (DaoConnectionManager manager = factory.create()) {
            PurchaseDao purchaseDao = manager.createPurchaseDao();
            long numberOfItems = purchaseDao.getPurchaseAmount();
            return (int) Math.ceil(numberOfItems / (double) itemsPerPage);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Purchase> getPage(int page, int itemsPerPage) throws Exception {
        Preconditions.checkArgument(page >= MIN_PAGE_VALUE, "Invalid page value: " + page);
        Preconditions.checkArgument(itemsPerPage >= MIN_ITEM_ON_PAGE, "Invalid itemsPerPage: " + itemsPerPage);

        int firstItem = (page - 1) * itemsPerPage + 1;
        try (DaoConnectionManager manager = factory.create()) {
            PurchaseDao purchaseDao = manager.createPurchaseDao();
            return purchaseDao.findBatch(itemsPerPage, firstItem);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
