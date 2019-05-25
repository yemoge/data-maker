package org.yemoge.bigdata.column;


import java.util.Map;


public class ColumnFactory {

    public static final String KEY_TABLE_COL_NAME = "name";

    public static final String KEY_TABLE_COL_TYPE = "type";

    public static final String KEY_TABLE_COL_CARDINAL = "cardinal";

    public static final String KEY_TABLE_COL_LENGTH = "length";

//    public static Column parse(String str) {
//        str = str.trim().toUpperCase();
//        if(str.startsWith(DECIMAL.name())) {
//            String remain = str.substring(DECIMAL.name().length()).trim();
//            if(!remain.startsWith("(") || !remain.endsWith(")")) {
//                throw new IllegalArgumentException(str);
//            }
//            String[] arr = remain.split(",");
//            int m = Integer.valueOf(arr[0]);
//            int n = Integer.valueOf(arr[1]);
//            return ;
//        } else {
//            return valueOf(str);
//        }
//        return null;
//    }

    public static Column makeColumn(Map<String,Object> colConfig) {
        return null;
    }

    public static void main(String[] args) {

    }

}
