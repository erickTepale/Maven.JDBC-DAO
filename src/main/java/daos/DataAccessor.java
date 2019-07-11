package daos;

import models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccessor implements DataAccess{
    private Connection connection;
    private Statement stmt;

    public DataAccessor() throws SQLException {
        this.connection = ConnectionFactory.getConnectionFactory();
        this.stmt = connection.createStatement();
    }

    public Car findById(Integer id) throws SQLException{
        try{
            ResultSet rs = stmt.executeQuery("SELECT * FROM car WHERE id=" + id);

            if(rs.next()){
                Car a = new Car();
                return new Car(
                        rs.getInt("id"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("year"),
                        rs.getString("color"),
                        rs.getString("vin"));
            }
        }catch(SQLException ex){
            System.out.println("findByID, DataAccessor.java");
            ex.printStackTrace();
            closeConnection();
        }

        return null;
    }

    public List<Car> findAll() throws SQLException{
        List<Car> cars = new ArrayList<Car>();
        try{
            ResultSet rs = stmt.executeQuery("SELECT * FROM car");

            while(rs.next()){
                cars.add(
                        new Car(
                                rs.getInt("id"),
                                rs.getString("make"),
                                rs.getString("model"),
                                rs.getString("year"),
                                rs.getString("color"),
                                rs.getString("vin")
                        )
                );
            }

            return cars;
        }catch (SQLException ex){
            System.out.println("findAll Error, DataAccessor.java");
            ex.printStackTrace();
            closeConnection();
        }
        return null;
    }

    public Car update(Object dto) throws SQLException {
        Car updatedTo= (Car)dto;
        Car inDB = this.findById(updatedTo.getId());

        Car intoDB = new Car(
                inDB.getId(),
                updatedTo.getMake() == null ? inDB.getMake():updatedTo.getMake(),
                updatedTo.getModel() == null ? inDB.getModel():updatedTo.getModel(),
                updatedTo.getYear() == null ? inDB.getYear():updatedTo.getYear(),
                updatedTo.getColor() == null ? inDB.getColor():updatedTo.getColor(),
                updatedTo.getVin() == null ? inDB.getVin():updatedTo.getVin()
        );

        StringBuilder query = new StringBuilder("UPDATE car SET ");
        query.append("make = '" + intoDB.getMake() + "', ");
        query.append("model = '" + intoDB.getModel() + "', ");
        query.append("year = '" + intoDB.getYear() + "', ");
        query.append("color = '" + intoDB.getColor() + "', ");
        query.append("vin = '" + intoDB.getVin() + "' ");
        query.append("WHERE id = " + inDB.getId());



        try{
            stmt.executeUpdate(query.toString());
            return findById(intoDB.getId());
        }catch (SQLException ex){
            System.out.println("update Error, DataAccessor.java");
            ex.printStackTrace();
            closeConnection();
        }

        return null;
    }

    public Car create(Object dto) throws SQLException{
        Car object = (Car)dto;
        StringBuilder a = new StringBuilder();
        a.append("INSERT INTO car(make, model, year, color, vin) VALUES ('");
        a.append(object.getMake() + "', '");
        a.append(object.getModel() + "', '");
        a.append(object.getYear() + "', '");
        a.append(object.getColor() + "', '");
        a.append(object.getVin() + "')");

        try {
            stmt.execute(a.toString());
            List<Car> list = findAll();
            return list.get(list.size() - 1);

        }catch (SQLException ex){
            System.out.println("Create Error, DataAccessor.java");
            ex.printStackTrace();
            closeConnection();
        }
        return null;
    }

    public void delete(Integer d) throws SQLException{
        try {
            stmt.execute("DELETE FROM car WHERE id = " + d);
        }catch (SQLException ex){
            System.out.println("Delete Error, DataAccessor.java");
            ex.printStackTrace();
            closeConnection();
        }

    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
