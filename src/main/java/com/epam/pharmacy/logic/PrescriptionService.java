package com.epam.pharmacy.logic;

import com.epam.pharmacy.model.entity.Medicine;
import com.epam.pharmacy.model.entity.Prescription;

import java.util.List;

public interface PrescriptionService {

    Prescription getById(long id) throws Exception;

    int getNumberOfPages(int itemsPerPage) throws Exception;

    List<Prescription> getPage(int page, int itemsPerPage) throws Exception;

    List<Prescription> getPageForOne(long id, int page, int itemsPerPage) throws Exception;

    List<Prescription> getAllItems(long id,Boolean status) throws Exception;

    long save(Long userId,Long medicineId,String startDate,String endDate) throws Exception;

    long updateAccess(Prescription prescription) throws Exception;

    void deletePrescriptionById(long id) throws Exception;
}
