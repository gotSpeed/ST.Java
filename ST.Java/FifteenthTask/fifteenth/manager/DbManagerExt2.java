package fifteenth.manager;


import fourteenth.manager.DbManagerExt;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;



public class DbManagerExt2 extends DbManagerExt {

    // Hardcoded
    @Override
    public void insert(String[] args) {

        Map<String, Object> pairs = new LinkedHashMap<>();
        parseArgs(args, pairs);

        mLastQueryParams = pairs.keySet().toArray(new String[0]);
        mLastQueryArgs   = pairs.values().toArray(new Object[0]);

        try {
            PreparedStatement preparedStatement =
                mConnection.prepareStatement(
                    "INSERT INTO payments (sender, receiver, amount) VALUES (?, ?, ?);"
                );

            preparedStatement.setString(1, mLastQueryArgs[0].toString());
            preparedStatement.setString(2, mLastQueryArgs[1].toString());
            preparedStatement.setLong(3, Long.parseLong(mLastQueryArgs[2].toString()));

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
