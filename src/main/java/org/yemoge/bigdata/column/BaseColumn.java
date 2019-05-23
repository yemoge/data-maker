package org.yemoge.bigdata.column;


public abstract class BaseColumn implements Column {

    protected int cardinality;

    protected int length;

    protected String title;

    protected String[] values;

    @Override
    public String title() {
        return title;
    }

    @Override
    public String makeCell() {
        return "";
    }

}
