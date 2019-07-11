package daos;

import java.sql.SQLException;
import java.util.List;

public interface DataAccess<T> {
    public T findById(Integer id) throws SQLException;
    public List<T> findAll() throws SQLException;
    public T update(T dto) throws SQLException;
    public T create(T dto) throws SQLException;
    public void delete(Integer d) throws SQLException;
}
