package com.epam.pharmacy.logic.medicine;

import com.epam.pharmacy.dao.exception.DaoException;
import com.epam.pharmacy.dao.medicine.MedicineDao;
import com.epam.pharmacy.dao.manager.DaoConnectionManager;
import com.epam.pharmacy.dao.manager.DaoConnectionManagerFactory;
import com.epam.pharmacy.logic.exception.NotFoundException;
import com.epam.pharmacy.logic.exception.ServiceException;
import com.epam.pharmacy.logic.validator.Validator;
import com.epam.pharmacy.model.entity.Medicine;
import com.google.common.base.Preconditions;

import java.util.List;
import java.util.Optional;

public class MedicineServiceImpl implements MedicineService {

    private static final long MIN_ID_VALUE = 1L;
    private static final int MIN_ITEM_ON_PAGE = 1;
    private static final int MIN_PAGE_VALUE = 1;
    private final DaoConnectionManagerFactory factory;
    private final Validator<Medicine> medicineValidator;

    public MedicineServiceImpl(DaoConnectionManagerFactory factory, Validator<Medicine> medicineValidator) {
        this.factory = factory;
        this.medicineValidator = medicineValidator;
    }

    @Override
    public Medicine getById(long id) throws Exception {
        Preconditions.checkArgument(id >= MIN_ID_VALUE, "Invalid id value: " + id);

        try (DaoConnectionManager manager = factory.create()) {
            MedicineDao medicineDao = manager.createMedicineDao();
            Optional<Medicine> found = medicineDao.getById(id);
            return found.orElseThrow(() -> new NotFoundException("No any medicine found by id: " + id));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long save(Medicine medicine) throws Exception {
        if (!medicineValidator.isValid(medicine)) {
            throw new ServiceException("Invalid Medicine object: " + medicine);
        }

        try (DaoConnectionManager manager = factory.create()) {
            MedicineDao medicineDao = manager.createMedicineDao();
            return medicineDao.add(medicine);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteMedicineById(long id) throws Exception {
        Preconditions.checkArgument(id >= MIN_ID_VALUE, "Invalid id value: " + id);

        try (DaoConnectionManager manager = factory.create()) {
            MedicineDao medicineDao = manager.createMedicineDao();
            medicineDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getNumberOfPages(int itemsPerPage) throws Exception {
        Preconditions.checkArgument(itemsPerPage >= MIN_ITEM_ON_PAGE, "Invalid itemsPerPage : " + itemsPerPage);

        try (DaoConnectionManager manager = factory.create()) {
            MedicineDao medicineDao = manager.createMedicineDao();
            long numberOfItems = medicineDao.getMedicinesAmount();
            return (int) Math.ceil(numberOfItems / (double) itemsPerPage);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Medicine> getPage(int page, int itemsPerPage) throws Exception {
        Preconditions.checkArgument(page >= MIN_PAGE_VALUE, "Invalid page value: " + page);
        Preconditions.checkArgument(itemsPerPage >= MIN_ITEM_ON_PAGE, "Invalid itemsPerPage: " + itemsPerPage);

        int firstItem = (page - 1) * itemsPerPage + 1;
        try (DaoConnectionManager manager = factory.create()) {
            MedicineDao medicineDao = manager.createMedicineDao();
            return medicineDao.findBatch(itemsPerPage, firstItem);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


}
