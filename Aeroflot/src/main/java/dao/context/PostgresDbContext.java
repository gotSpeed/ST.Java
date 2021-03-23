package dao.context;


import org.jetbrains.annotations.NotNull;

import java.sql.*;



public class PostgresDbContext {

    public static final String URL      = "jdbc:postgresql://localhost:5445/AeroflotDB";
    public static final String USERNAME = "STJava";
    public static final String PASSWORD = "postgres";



    @NotNull
    public static Object getLastId(String table, String column) {

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if (connection != null) {
                String query = String.format(
                    "SELECT %s FROM %s ORDER BY %s DESC LIMIT 1;",
                    column, table, column
                );

                PreparedStatement preparedStatement = connection.prepareStatement(query);

                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    return result.getLong(column);
                }
            }

            return -1;
        }
        catch (SQLException e) {
            // TODO: logger.
            e.printStackTrace();

            return -1;
        }
    }

}
