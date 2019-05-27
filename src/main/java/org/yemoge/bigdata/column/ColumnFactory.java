package org.yemoge.bigdata.column;


import com.google.common.base.Preconditions;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ColumnFactory {

    public static final String KEY_TABLE_COL_NAME = "name";

    public static final String KEY_TABLE_COL_TYPE = "type";

    public static final String KEY_TABLE_COL_CARDINAL = "cardinal";

    public static final String KEY_TABLE_COL_LENGTH = "length";

    public static final int DEFAULT_TABLE_COL_CARDINAL = 10;

    public static final int DEFAULT_TABLE_COL_LENGTH = 10;

    private static final Pattern pattern = Pattern.compile("DECIMAL\\((\\d+),(\\d+)\\)");


    public static Column makeColumn(Map<String,Object> colConfig) {
        String colName = (String) colConfig.get(KEY_TABLE_COL_NAME);
        Preconditions.checkArgument(colName != null && colName.trim().length() > 0);

        String colType = (String) colConfig.get(KEY_TABLE_COL_TYPE);
        Preconditions.checkArgument(colType != null && colType.trim().length() > 0);

        int colCardinal = DEFAULT_TABLE_COL_CARDINAL;
        if(colConfig.containsKey(KEY_TABLE_COL_CARDINAL)) {
            colCardinal = ((Double) colConfig.get(KEY_TABLE_COL_CARDINAL)).intValue();
        }

        int colLength = DEFAULT_TABLE_COL_LENGTH;
        if(colConfig.containsKey(KEY_TABLE_COL_LENGTH)) {
            colLength = ((Double) colConfig.get(KEY_TABLE_COL_LENGTH)).intValue();
        }

        colType = colType.trim().toUpperCase();
        int m = 6;
        int n = 2;

        ColumnType columnType;
        if(colType.startsWith(ColumnType.DECIMAL.name())) {
            columnType = ColumnType.DECIMAL;
        } else {
            columnType = ColumnType.valueOf(colType);
        }

        switch(columnType) {
            case STRING:
                return new StringColumn(colName, colCardinal, colLength);
            case INT:
                return new IntColumn(colName, colCardinal, colLength);
            case DECIMAL:
                Matcher matcher = pattern.matcher(colType.replaceAll("\\s+",""));
                if(matcher.find()) {
                    m = Integer.parseInt(matcher.group(1));
                    n = Integer.parseInt(matcher.group(2));
                }
                return new DecimalColumn(colName, colCardinal, m, n);
            default:
                throw new IllegalArgumentException("can not happen!");
        }

    }


    public static void main(String[] args) {

    }

}
