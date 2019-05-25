package org.yemoge.bigdata;


import com.google.gson.Gson;
import org.yemoge.bigdata.job.Job;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class MakerMain {

    private static Job buildJob(String jobFile) throws IOException {
        try(InputStreamReader reader = new InputStreamReader(new BufferedInputStream(new FileInputStream(new File(jobFile))))) {
            Gson gson = new Gson();
            Map<String,Object> map = gson.fromJson(reader, Map.class);
            return new Job(map);
        }

    }

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("job configuration file must be specified!");
        }

        buildJob((args[1])).execute();
    }

}
