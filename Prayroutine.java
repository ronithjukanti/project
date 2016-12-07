package com.example;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by maverick on 12/7/2016.
 */
public class Prayroutine {

    public void eatGood()
    {
        System.out.println("Eating good food");
    }

    public void sleepGood(int hours) throws IOException {
        hours++;
        FileUtils.write(new File("./Hours.txt"),Integer.toString(hours));
    }

    public void studyHard()
    {
        System.out.println("Good work, keep continuing");
    }
}
