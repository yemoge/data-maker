package org.yemoge.bigdata.column;


import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashSet;
import java.util.Set;

public class IntColumn extends BaseColumn {

    @Override
    public void makeDict() {
        Set<String> set = new HashSet<>();
        for(int i = 0; i < cardinal || set.size() < cardinal; ++i) {
            set.add(RandomStringUtils.randomNumeric(length));
        }
        values = new String[cardinal];
        set.toArray(values);
    }

    public IntColumn(String title, int cardinal, int length) {
        super(title, cardinal, length);
        makeDict();
    }

}
