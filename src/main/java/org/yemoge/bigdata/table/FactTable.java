package org.yemoge.bigdata.table;


import org.yemoge.bigdata.column.Column;
import org.yemoge.bigdata.column.Ref;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class FactTable {


    private List<Column> columnList = new ArrayList<>();

    private int nRows = 100;

    private List<Ref> refList = new ArrayList<>();

    private AtomicInteger nextPk = new AtomicInteger(0);

    String name;

    String outputDir;


    public FactTable(String name, int nRows, List<Column> columnList, String outputDir, List<Ref> refList) {
        this.name = name;
        this.nRows = nRows;
        this.columnList = columnList;
        this.outputDir = outputDir;

        File outputRoot = new File(outputDir);
        if(!outputRoot.exists()) {
            outputRoot.mkdir();
        }

        this.refList = refList;
    }


    public void makeTable() throws IOException {
        String dataPath = outputDir + File.separator + "0.csv";
        try(OutputStreamWriter writer = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(new File(dataPath))))) {
            writer.write(header());
            writer.write("\n");
            for(int i = 0; i < nRows; ++i) {
                StringBuilder sb = new StringBuilder(String.valueOf(nextPk.incrementAndGet()));

                for(int k =0; k < refList.size(); ++k) {
                    sb.append(",");
                    sb.append(refList.get(k).makeCell());
                }
                for(int j = 0; j < columnList.size(); ++j) {
                    sb.append(",");
                    sb.append(columnList.get(j).makeCell());
                }
                sb.append("\n");
                writer.write(sb.toString());
            }
        }
    }


    private String header() {
        StringBuilder sb = new StringBuilder("id");

        for(int k = 0; k < refList.size(); ++k) {
            sb.append(",");
            sb.append(refList.get(k).title());
        }

        for(int j = 0; j < columnList.size(); ++j) {
            sb.append(",");
            sb.append(columnList.get(j).title());
        }
        return sb.toString();
    }


}
