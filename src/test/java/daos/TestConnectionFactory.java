package daos;

import org.junit.Test;

import java.sql.Connection;

public class TestConnectionFactory {
    @Test
    public void connect(){
        Connection connect = ConnectionFactory.getConnectionFactory();


    }
}
