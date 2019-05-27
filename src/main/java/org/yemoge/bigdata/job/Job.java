package org.yemoge.bigdata.job;


import com.google.common.base.Preconditions;
import org.yemoge.bigdata.column.Column;
import org.yemoge.bigdata.column.ColumnFactory;
import org.yemoge.bigdata.table.Table;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Job {

    public static final String KEY_TABLES = "tables";

    public static final String KEY_OUTPUT_DIR = "outputDir";

    public static final String KEY_TABLE_NAME = "name";

    public static final String KEY_TABLE_ROW_NUM = "rowNum";

    public static final String KEY_TABLE_COLS = "cols";

    public static final int DEFAULT_TABLE_ROW_NUM = 100;


    protected List<Table> tables = new ArrayList<>();

    protected String outputDir = ".";

    public Job(Map<String,Object> config) {

        if(config.containsKey(KEY_OUTPUT_DIR)) {
            outputDir = (String) config.get(KEY_OUTPUT_DIR);
        }

        if(config.containsKey(KEY_TABLES)) {
            this.tables = initTables((List<Map<String, Object>>) config.get(KEY_TABLES));
        }

    }

    private List<Table> initTables(List<Map<String,Object>> tablesConfig) {
        List<Table> tables = tablesConfig.stream().map(this::buildTable).collect(Collectors.toList());
        return tables;
    }

    private Table buildTable(Map<String,Object> tableConfig) {
        List<Column> columns = buildCols((List<Map<String, Object>>) tableConfig.get(KEY_TABLE_COLS));
        String tableName = (String) tableConfig.get(KEY_TABLE_NAME);
        String tableDir = outputDir + File.separator + tableName;
        int nRows = DEFAULT_TABLE_ROW_NUM;
        if(tableConfig.containsKey(KEY_TABLE_ROW_NUM)) {
            nRows = ((Double) tableConfig.get(KEY_TABLE_ROW_NUM)).intValue();
        }
        return new Table(tableName, nRows, columns, tableDir);
    }

    private List<Column> buildCols(List<Map<String,Object>> colsConfig) {
        List<Column> columns = new ArrayList<>();
        for (Map<String,Object> colConfig :colsConfig) {
            columns.add(ColumnFactory.makeColumn(colConfig));
        }
        return columns;
    }


    public void execute() throws IOException {

        File outputRoot =  new File(outputDir);

        if(!outputRoot.exists()) {
            outputRoot.mkdir();
        }

        for(Table table : tables) {
            table.makeTable();
        }
    }

}
