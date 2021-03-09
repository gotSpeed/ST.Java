package thirteenth.querybuilder;


public abstract class AbstractQueryBuilder {

    protected String mTable;
    protected String mParams;



    public AbstractQueryBuilder createQuery() {

        return this;
    }



    public AbstractQueryBuilder setTable(String table) {

        mTable = table;
        return this;
    }



    public AbstractQueryBuilder setParams(String[] params) {

        StringBuilder builder = new StringBuilder(16);

        for (String param : params) {
            if (builder.length() != 0) {
                builder.append(", ");
            }

            builder.append(param);
        }

        mParams = builder.toString();
        return this;
    }



    public abstract AbstractQueryBuilder setArgs(Object[] args);

    public abstract AbstractQueryBuilder addFilter(String column,
                                                   String condition,
                                                   Object value);

    public abstract String build();

}
