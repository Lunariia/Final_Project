package com.epam.pharmacy.dao;

import com.epam.pharmacy.mapper.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AbstractDao<T> implements Dao<T> {

    private final RowMapper<T> rowMapper;
    private final Connection connection;

    public AbstractDao(Connection connection, RowMapper<T> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    protected List<T> executeQuery(String query, RowMapper<T> rowMapper, Object... params) throws DaoException {
        List<T> resultList = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatement(query, params);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                T resultElement = rowMapper.map(resultSet);
                resultList.add(resultElement);
            }
            return resultList;
        } catch (SQLException throwables) {
            throw new DaoException(throwables.getMessage(), throwables);
        }
    }

    //Check method
    private PreparedStatement prepareStatement(String query, Object[] params) {
        return null;
    }

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public Optional<T> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void add(T element) {

    }

    @Override
    public void delete(T element) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        AbstractDao<?> that = (AbstractDao<?>) o;
        return Objects.equals(rowMapper, that.rowMapper) && Objects.equals(connection, that.connection);
    }

    @Override
    public int hashCode() {
        int result = rowMapper != null ? rowMapper.hashCode() : 0;
        result = 31 * result + (connection != null ? connection.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractDao{" +
                "rowMapper=" + rowMapper +
                ", connection=" + connection +
                '}';
    }
}
