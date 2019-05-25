package org.yemoge.bigdata.column;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * decimal(m,n)
 */
public class DecimalColumn extends BaseColumn {

    private int m;

    private int n;

    public DecimalColumn(int m, int n) {
        this.length = m + n + 1;
    }

    public DecimalColumn(int m, int n, int cardinality) {
        this.length = m + n + 1;
        this.cardinal = cardinality;
    }

    @Override
    public void makeDict() {
        Set<String> set = new HashSet<>();
        for(int i = 0; i < cardinal || set.size() < cardinal; ++i) {
            String left = RandomStringUtils.randomNumeric(m - n);
            String right = RandomStringUtils.randomNumeric(n);
            set.add(left + "." + right);
        }
        values = new String[cardinal];
        set.toArray(values);
    }

}
