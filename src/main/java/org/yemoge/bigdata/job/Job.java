package org.yemoge.bigdata.job;


import org.yemoge.bigdata.column.Column;
import org.yemoge.bigdata.column.ColumnFactory;
import org.yemoge.bigdata.column.Ref;
import org.yemoge.bigdata.table.DimTable;
import org.yemoge.bigdata.table.FactTable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Job {

    public static final String KEY_DIM_TABLES = "dimTables";

    public static final String KEY_FACT_TABLES = "factTables";

    public static final String KEY_OUTPUT_DIR = "outputDir";

    public static final String KEY_DIM_TABLE_NAME = "name";

    public static final String KEY_DIM_TABLE_ROW_NUM = "rowNum";

    public static final String KEY_DIM_TABLE_COLS = "cols";

    public static final int DEFAULT_TABLE_ROW_NUM = 100;

    public static final String KEY_FACT_TABLE_NAME = "name";

    public static final String KEY_FACT_TABLE_REFS = "refs";

    public static final String KEY_FACT_TABLE_REF_NAME = "name";

    public static final String KEY_FACT_TABLE_REF_TABLE = "table";

    public static final String KEY_FACT_TABLE_COLS = "cols";

    protected List<DimTable> dimTables = new ArrayList<>();

    protected List<FactTable> factTables = new ArrayList<>();

    protected String outputDir = ".";


    public DimTable findDimTable(String tableName) {
        for(DimTable dimTable : dimTables) {
            if(dimTable.getName().equals(tableName)) {
                return dimTable;
            }
        }
        return null;
    }

    public Job(Map<String,Object> config) {

        if(config.containsKey(KEY_OUTPUT_DIR)) {
            outputDir = (String) config.get(KEY_OUTPUT_DIR);
        }

        if(config.containsKey(KEY_DIM_TABLES)) {
            this.dimTables = initDimTables((List<Map<String, Object>>) config.get(KEY_DIM_TABLES));
        }

        if(config.containsKey(KEY_FACT_TABLES)) {
            this.factTables = initFactTables((List<Map<String, Object>>) config.get(KEY_FACT_TABLES));
        }

    }

    private List<DimTable> initDimTables(List<Map<String,Object>> tablesConfig) {
        List<DimTable> dimTables = tablesConfig.stream().map(this::buildDimTable).collect(Collectors.toList());
        return dimTables;
    }

    private DimTable buildDimTable(Map<String,Object> tableConfig) {
        List<Column> columns = buildCols((List<Map<String, Object>>) tableConfig.get(KEY_DIM_TABLE_COLS));
        String tableName = (String) tableConfig.get(KEY_DIM_TABLE_NAME);
        String tableDir = outputDir + File.separator + tableName;
        int nRows = DEFAULT_TABLE_ROW_NUM;
        if(tableConfig.containsKey(KEY_DIM_TABLE_ROW_NUM)) {
            nRows = ((Double) tableConfig.get(KEY_DIM_TABLE_ROW_NUM)).intValue();
        }
        return new DimTable(tableName, nRows, columns, tableDir);
    }

    private List<Column> buildCols(List<Map<String,Object>> colsConfig) {
        List<Column> columns = new ArrayList<>();
        for (Map<String,Object> colConfig :colsConfig) {
            columns.add(ColumnFactory.makeColumn(colConfig));
        }
        return columns;
    }

    private List<FactTable> initFactTables(List<Map<String,Object>> tablesConfig) {
        List<FactTable> factTables = tablesConfig.stream().map(this::buildFactTable).collect(Collectors.toList());
        return factTables;
    }

    private FactTable buildFactTable(Map<String,Object> tableConfig) {
        List<Column> columns = buildCols((List<Map<String, Object>>) tableConfig.get(KEY_FACT_TABLE_COLS));
        List<Ref> refs = buildRefs((List<Map<String, Object>>) tableConfig.get(KEY_FACT_TABLE_REFS));
        String tableName = (String) tableConfig.get(KEY_FACT_TABLE_NAME);
        String tableDir = outputDir + File.separator + tableName;
        int nRows = DEFAULT_TABLE_ROW_NUM;
        if(tableConfig.containsKey(KEY_DIM_TABLE_ROW_NUM)) {
            nRows = ((Double) tableConfig.get(KEY_DIM_TABLE_ROW_NUM)).intValue();
        }
        return new FactTable(tableName, nRows, columns, tableDir, refs);
    }

    private List<Ref> buildRefs(List<Map<String,Object>> refsConfig) {
        List<Ref> refs = new ArrayList<>();
        for (Map<String,Object> refConfig :refsConfig) {
            String title = (String) refConfig.get(KEY_FACT_TABLE_REF_NAME);
            String table = (String) refConfig.get(KEY_FACT_TABLE_REF_TABLE);
            refs.add(new Ref(title, table, this));
        }
        return refs;
    }


    public void execute() throws IOException {

        File outputRoot =  new File(outputDir);

        if(!outputRoot.exists()) {
            outputRoot.mkdir();
        }

        for(DimTable dimTable : dimTables) {
            dimTable.makeTable();
        }

        for(FactTable factTable : factTables) {
            factTable.makeTable();
        }
    }

}
