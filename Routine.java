package com.example;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class Routine {


Prayroutine prayroutine=new Prayroutine();

    @Faithful
    @Scheduled(fixedRate=2000)
    public void eat() {
        String[] a={"good","bad"};
        for(int i=0;i<a.length;i++)
        {
            if(a[i]=="good")
            {
                prayroutine.eatGood();
            }
            else
            {
                System.out.println("Bad food");
            }
        }
    }

    @Faithful
    @Scheduled(fixedRate=2500)
    public void sleep() throws IOException {
        String sleepHours= FileUtils.readFileToString(new File("./Hours.txt"), CharEncoding.UTF_8);
        if(Integer.parseInt(sleepHours)>=8)
        {
            prayroutine.sleepGood(Integer.parseInt(sleepHours));
        }
    }

    @Faithful
    @Scheduled(fixedRate=3000)
    public void study() {
        int[] b={8,2};
        if(b[0]==8)
        {
            prayroutine.studyHard();
        }
        else{
            System.out.println("Study more");
        }

    }


}
