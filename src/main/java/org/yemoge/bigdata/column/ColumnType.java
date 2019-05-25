package org.yemoge.bigdata.column;


public enum ColumnType {
    INT,
    STRING,
    DECIMAL,
    DATE,
    DATETIME,
    TIME;

//    private int m = 0;
//
//    private int n = 0;
//
//    public ColumnType parse(String str) {
//        str = str.trim().toUpperCase();
//        if(str.startsWith(DECIMAL.name())) {
//            String remain = str.substring(DECIMAL.name().length()).trim();
//            if(!remain.startsWith("(") || !remain.endsWith(")")) {
//                throw new IllegalArgumentException(str);
//            }
//            //String[] remain.split(",");
//
//            return null;
//        } else {
//            return valueOf(str);
//        }
//    }
//
//
//    public static void main(String[] args) {
//        String str = "decimal(10,3)";
//        String ss = "string";
//        //String[] arr = str.split("\\(");
//        //System.out.println(arr);
//        int index = ss.indexOf("(");
//        System.out.println(index);
//
//    }

}
