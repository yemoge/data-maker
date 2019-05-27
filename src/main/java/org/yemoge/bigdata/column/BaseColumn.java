package org.yemoge.bigdata.column;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import java.util.HashSet;
import java.util.Set;


public abstract class BaseColumn implements Column {

    protected int cardinal = 10;

    protected int length = 5;

    protected String title = "";

    protected String[] values;

    @Override
    public String title() {
        return title;
    }

    @Override
    public String makeCell() {
        return values[RandomUtils.nextInt(0, cardinal)];
    }



    public BaseColumn(String title, int cardinal, int length) {
        this.title = title;
        this.cardinal = cardinal;
        this.length = length;
    }

    public int getCardinal() {
        return cardinal;
    }

    public void setCardinal(int cardinal) {
        this.cardinal = cardinal;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
