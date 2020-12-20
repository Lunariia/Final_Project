package com.epam.pharmacy.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

    T map(ResultSet resultSet) throws SQLException;
}
