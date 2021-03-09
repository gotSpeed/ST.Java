package thirteenth.manager;


import thirteenth.struct.ExpensesQueryStruct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class DbManager {

    private static final String DUMMY_DB_URL     =
        "jdbc:postgresql://localhost:5445/st_java";
    private static final String DUMMY_USER       = "STJava";
    private static final String DEFAULT_PASSWORD = "postgres";

    private Connection mConnection;



    public DbManager() {}



    public int establishConnection(String url, String name, String password) {

        try {
            mConnection = DriverManager.getConnection(url, name, password);
            if (mConnection != null) {
                return 0;
            }
            else {
                return 1;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }



    public int establishConnection() {

        return establishConnection(DUMMY_DB_URL, DUMMY_USER, DEFAULT_PASSWORD);
    }



    public List<ExpensesQueryStruct> getExpenses(String[] tables, long threshold) {

        String query = buildExpensesQuery(tables, threshold);

        try {
            ResultSet result = mConnection.createStatement()
                                          .executeQuery(query);
            List<ExpensesQueryStruct> rows = new ArrayList<>();

            while (result.next()) {

                long id = result.getLong("id");
                String receiver = result.getString("receiver");
                String timestamp = result.getString("payment_date");
                long amount = result.getLong("amount");

                rows.add(new ExpensesQueryStruct(id, receiver, timestamp, amount));
            }

            return rows;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }



    private String buildExpensesQuery(String[] tables, long threshold) {

        StringBuilder builder = new StringBuilder();

        for (String table : tables) {
            if (builder.length() != 0) {
                builder.append("\nUNION ALL\n");
            }

            builder.append(
                String.format(Locale.US,
                              "SELECT id, receiver, payment_date, amount\n" +
                              "FROM %s WHERE amount >= %d\n",
                              table,
                              threshold)
            );
        }

        return builder.append(";").toString();
    }

}
