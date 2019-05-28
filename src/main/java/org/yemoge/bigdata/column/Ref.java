package org.yemoge.bigdata.column;


import org.apache.commons.lang3.RandomUtils;
import org.yemoge.bigdata.job.Job;

public class Ref {

    private String title;

    private String table;

    private Job parent;

    private int cardinal = 10;


    public Ref(String title, String table, Job parent) {
        this.title = title;
        this.table = table;
        this.parent = parent;
        this.cardinal = parent.findDimTable(table).size();
    }

    public String makeCell() {
        return String.valueOf(RandomUtils.nextInt(0, cardinal));
    }

    public String title() {
        return title;
    }


}
