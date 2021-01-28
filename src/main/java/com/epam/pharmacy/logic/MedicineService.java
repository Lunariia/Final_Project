package com.epam.pharmacy.logic;

import com.epam.pharmacy.dao.DaoException;
import com.epam.pharmacy.model.entity.Medicine;

import java.util.List;

public interface MedicineService {

    Medicine getById(long id) throws Exception;

    long save(Medicine medicine) throws Exception;

    void deleteMedicineById(long id) throws Exception;

    int getNumberOfPages(int itemsPerPage) throws Exception;

    List<Medicine> getPage(int page, int itemsPerPage) throws Exception;


}
