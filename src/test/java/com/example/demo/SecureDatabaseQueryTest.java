

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.*;
import static org.mockito.Mockito.*;

import com.exemple.demo.SecureDatabaseQuery;

@SpringBootTest   
public class SecureDatabaseQueryTest {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        connection.close();
        preparedStatement.close();
        resultSet.close();
    }

    @Test
    public void testMainWithValidInput() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("nome")).thenReturn("John Doe");
        when(resultSet.getString("email")).thenReturn("john.doe@example.com");

        String[] args = {"John Doe"};
        SecureDatabaseQuery.main(args);

        verify(preparedStatement).setString(1, "John Doe");
        verify(preparedStatement).executeQuery();
    }

    @Test
    public void testMainWithNoResults() throws SQLException {
        when(resultSet.next()).thenReturn(false);

        String[] args = {"Jane Doe"};
        SecureDatabaseQuery.main(args);

        verify(preparedStatement).setString(1, "Jane Doe");
        verify(preparedStatement).executeQuery();
    }
}