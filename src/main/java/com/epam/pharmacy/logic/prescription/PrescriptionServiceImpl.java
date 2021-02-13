package com.epam.pharmacy.logic.prescription;

import com.epam.pharmacy.dao.exception.DaoException;
import com.epam.pharmacy.dao.prescription.PrescriptionDao;
import com.epam.pharmacy.dao.manager.DaoConnectionManager;
import com.epam.pharmacy.dao.manager.DaoConnectionManagerFactory;
import com.epam.pharmacy.logic.exception.NotFoundException;
import com.epam.pharmacy.logic.exception.ServiceException;
import com.epam.pharmacy.logic.validator.Validator;
import com.epam.pharmacy.model.entity.Prescription;
import com.google.common.base.Preconditions;

import java.util.List;
import java.util.Optional;

public class PrescriptionServiceImpl implements PrescriptionService {

    private static final long MIN_ID_VALUE = 1L;
    private static final int MIN_ITEM_ON_PAGE = 1;
    private static final int MIN_PAGE_VALUE = 1;

    private final DaoConnectionManagerFactory factory;
    private final Validator<Prescription> prescriptionValidator;

    public PrescriptionServiceImpl(DaoConnectionManagerFactory factory, Validator<Prescription> prescriptionValidator) {
        this.factory = factory;
        this.prescriptionValidator = prescriptionValidator;
    }

    @Override
    public Prescription getById(long id) throws Exception {
        Preconditions.checkArgument(id >= MIN_ID_VALUE, "Invalid id value: " + id);

        try (DaoConnectionManager manager = factory.create()) {
            PrescriptionDao prescriptionDao = manager.createPrescriptionDao();
            Optional<Prescription> found = prescriptionDao.getById(id);
            return found.orElseThrow(() -> new NotFoundException("No prescription found by id: " + id));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getNumberOfPages(int itemsPerPage) throws Exception {
        Preconditions.checkArgument(itemsPerPage >= MIN_ITEM_ON_PAGE, "Invalid itemsPerPage : " + itemsPerPage);

        try (DaoConnectionManager manager = factory.create()) {
            PrescriptionDao prescriptionDao = manager.createPrescriptionDao();
            long numberOfItems = prescriptionDao.getPrescriptionAmount();
            return (int) Math.ceil(numberOfItems / (double) itemsPerPage);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Prescription> getPage(int page, int itemsPerPage) throws Exception {
        Preconditions.checkArgument(page >= MIN_PAGE_VALUE, "Invalid page value: " + page);
        Preconditions.checkArgument(itemsPerPage >= MIN_ITEM_ON_PAGE, "Invalid itemsPerPage: " + itemsPerPage);

        int firstItem = (page - 1) * itemsPerPage + 1;
        try (DaoConnectionManager manager = factory.create()) {
            PrescriptionDao prescriptionDao = manager.createPrescriptionDao();
            return prescriptionDao.findBatch(itemsPerPage, firstItem);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Prescription> getPageForOne(long id, int page, int itemsPerPage) throws Exception {
        Preconditions.checkArgument(page >= MIN_PAGE_VALUE, "Invalid page value: " + page);
        Preconditions.checkArgument(itemsPerPage >= MIN_ITEM_ON_PAGE, "Invalid itemsPerPage: " + itemsPerPage);

        int firstItem = (page - 1) * itemsPerPage + 1;
        try (DaoConnectionManager manager = factory.create()) {
            PrescriptionDao prescriptionDao = manager.createPrescriptionDao();
            return prescriptionDao.findBatchForOne(id,itemsPerPage, firstItem);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Prescription> getAllItems(long id,Boolean status) throws Exception {
        try (DaoConnectionManager manager = factory.create()) {
            PrescriptionDao prescriptionDao = manager.createPrescriptionDao();
            return prescriptionDao.findAllItems(id,status) ;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long save(Long userId,Long medicineId,String startDate,String endDate) throws Exception {

        try (DaoConnectionManager manager = factory.create()) {
            PrescriptionDao prescriptionDao = manager.createPrescriptionDao();
            return prescriptionDao.addSingle(userId, medicineId, startDate, endDate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long updateAccess(Prescription prescription) throws Exception {
        if (!prescriptionValidator.isValid(prescription)) {
            throw new ServiceException("Invalid Prescription object: " + prescription);
        }

        try (DaoConnectionManager manager = factory.create()) {
            PrescriptionDao prescriptionDao = manager.createPrescriptionDao();
            return prescriptionDao.updateAccess(prescription);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deletePrescriptionById(long id) throws Exception {
        Preconditions.checkArgument(id >= MIN_ID_VALUE, "Invalid id value: " + id);

        try (DaoConnectionManager manager = factory.create()) {
            PrescriptionDao prescriptionDao = manager.createPrescriptionDao();
            prescriptionDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


}
