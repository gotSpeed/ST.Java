package fourteenth.manager;


import fourteenth.commands.Command;
import thirteenth.manager.DbManager;
import thirteenth.querybuilder.AbstractQueryBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;



public class DbManagerExt extends DbManager {

    protected static final String OPT_ARG   = "-a";
    protected static final String OPT_TABLE = "-t";

    protected String   mTable;
    protected String[] mLastQueryParams;
    protected Object[] mLastQueryArgs;



    public String getTable() {

        return mTable;
    }



    public void setTable(String table) {

        mTable = table;
    }



    public void insert(String query) {

        try {
            mConnection.createStatement()
                       .executeUpdate(query);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }



    public void insert(String[] args) {

        Map<String, Object> pairs = new LinkedHashMap<>();
        parseArgs(args, pairs);

        mLastQueryParams = pairs.keySet().toArray(new String[0]);
        mLastQueryArgs   = pairs.values().toArray(new Object[0]);

        InsertQueryBuilder builder = new InsertQueryBuilder();
        String query = builder.createQuery()
                              .setTable(mTable)
                              .setParams(mLastQueryParams)
                              .setArgs(mLastQueryArgs)
                              .build();

        insert(query);
    }



    public void parseArgsAndExecute(String[] args) {

        if (mConnection == null) {
            return;
        }

        Command command;
        try {
            command = Command.valueOf(args[0].toUpperCase());
        }
        // Exception is thrown if the first argument (command) if not the one of CRUD
        catch (IllegalArgumentException e) {
            return;
        }

        switch (command) {
            case INSERT:
                insert(args);
                break;
            // Other commands
            default:
                break;
        }
    }



    public void selectAllExpensesAndPrint(String table) {

        try {
            ResultSet resultSet =
                mConnection.createStatement()
                           .executeQuery(String.format("SELECT * FROM %s", table));

            while (resultSet.next()) {
                String output = String.format(
                    "\nId: %d\nSender: %s\nReceiver: %s\nDate: %s\nAmount: %d\n",
                    resultSet.getLong("id"),
                    resultSet.getString("sender"),
                    resultSet.getString("receiver"),
                    resultSet.getString("payment_date"),
                    resultSet.getLong("amount")
                );
                System.out.println(output);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }



    protected void parseArgs(String[] args, Map<String, Object> pairs) {

        List<String> argsList = Arrays.asList(args);
        ListIterator<String> iter = argsList.listIterator();

        String arg;
        while (iter.hasNext()) {
            arg = iter.next();

            if (arg.compareTo(OPT_ARG) == 0 &&
                iter.hasNext()) {

                arg = iter.next();

                parseArgToKeyValue(arg, pairs);
            }
            if (arg.compareTo(OPT_TABLE) == 0 &&
                iter.hasNext()) {

                arg = iter.next();
                if (!arg.startsWith("-")) {
                    mTable = arg;
                }
            }
        }
    }



    protected void parseArgToKeyValue(String arg, Map<String, Object> pairs) {

        if (!arg.startsWith("-")) {

            String[] kv = arg.split("=");
            if (kv.length == 2) {
                pairs.put(kv[0], kv[1]);
            }
        }
    }


    // region InsertQueryBuilder
    public static class InsertQueryBuilder extends AbstractQueryBuilder {

        protected String mArgs;



        @Override
        public AbstractQueryBuilder setArgs(Object[] args) {

            StringBuilder builder = new StringBuilder(16);

            for (Object obj : args) {
                if (builder.length() != 0) {
                    builder.append(", ");
                }

                builder.append(obj.toString());
            }

            mArgs = builder.toString();
            return this;
        }



        @Override
        public AbstractQueryBuilder addFilter(String column,
                                              String condition,
                                              Object value) {

            return this;
        }



        @Override
        public String build() {

            return String.format("INSERT INTO %s (%s) VALUES (%s);",
                                 mTable,
                                 mParams,
                                 mArgs);
        }

    }
    // endregion

}
