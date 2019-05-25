package org.yemoge.bigdata.table;



import org.yemoge.bigdata.column.Column;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Table {

    private List<Column> columnList = new ArrayList<>();

    private int nRows = 100;

    String outputDir;

    String name;

    AtomicInteger nextPk = new AtomicInteger(0);

    public Table(String name, int nRows, List<Column> columnList, String outputDir) {
        this.name = name;
        this.nRows = nRows;
        this.columnList = columnList;
        this.outputDir = outputDir;
    }

    public void makeTable() throws IOException {
        String dataPath = outputDir + File.pathSeparator +  "data" + File.pathSeparator + name + File.pathSeparator + "0.csv";
        try(OutputStreamWriter writer = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(new File(dataPath))))) {
            for(int i = 0; i < nRows; ++i) {
                StringBuilder sb = new StringBuilder(String.valueOf(nextPk.incrementAndGet()));
                for(int j = 0; j < columnList.size(); ++j) {
                    sb.append(",");
                    sb.append(columnList.get(i).makeCell());
                }
                sb.append("\n");
                writer.write(sb.toString());
            }
        }


    }

    public static void main(String[] args) {

    }

}
