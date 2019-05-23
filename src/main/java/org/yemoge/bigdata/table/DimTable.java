package org.yemoge.bigdata.table;


import com.sun.deploy.util.StringUtils;
import org.yemoge.bigdata.column.Column;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DimTable {

    List<Column> columnList = new ArrayList<>();

    int nRows;

    boolean hasHeader;

    DimTable() {}

    public String makeTable() {

        if(hasHeader) {
            makeHeader();
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nRows; ++i) {
            for(int j = 0; j < columnList.size(); ++j) {

            }
        }
        return sb.toString();
    }

    public String makeHeader() {
        List<String> headers = columnList.stream().map(column -> column.title()).collect(Collectors.toList());
        return StringUtils.join(headers, ",");
    }

    public static void main(String[] args) {

    }

}
