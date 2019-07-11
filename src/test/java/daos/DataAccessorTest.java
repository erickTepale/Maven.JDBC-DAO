package daos;

import models.Car;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class DataAccessorTest {

    @Test
    public void getByID() throws SQLException {
        DataAccessor data = new DataAccessor();

        Assert.assertEquals(Integer.valueOf(1), data.findById(1).getId());
    }

    @Test
    public void getAll() throws SQLException {
        DataAccessor data = new DataAccessor();

        data.findAll().stream().forEach(System.out::println);
    }

    @Test
    public void update() throws SQLException {
        DataAccessor data = new DataAccessor();
        Car a = data.findById(1);

        a.setColor("black");
        data.update(a);
        //Assert.assertEquals(Integer.valueOf(1), data.findAll().);
    }

    @Test
    public void create() throws SQLException {
        DataAccessor data = new DataAccessor();
        Car a = new Car("Honda", "Civic", "2012", "Red", "123432131233");


        System.out.println(data.create(a).toString());
        //Assert.assertEquals(Integer.valueOf(1), data.findAll().);
    }

    @Test
    public void delete() throws SQLException {
        DataAccessor data = new DataAccessor();


        data.delete(3);
        //Assert.assertEquals(Integer.valueOf(1), data.findAll().);
    }

}
