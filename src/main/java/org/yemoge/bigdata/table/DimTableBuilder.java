package org.yemoge.bigdata.table;


import org.yemoge.bigdata.column.Column;

public class DimTableBuilder {

    private DimTable dimTable = new DimTable();

    public DimTableBuilder setRowNum(int rowNum) {
        dimTable.nRows = rowNum;
        return this;
    }

    public DimTableBuilder setHasHeader(boolean hasHeader) {
        dimTable.hasHeader = hasHeader;
        return this;
    }

    public DimTableBuilder addColumn(Column column) {
        dimTable.columnList.add(column);
        return this;
    }

    public DimTable build() {
        return dimTable;
    }

}
