package com.qst.sys.task;

import com.qst.sys.constast.SysConstast;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@EnableScheduling
//开启定时任务  <task:annotation-driven/>  二选一
public class RecyleTempFileTask {
    @Scheduled(cron = "0/10 * * * * ? ")
    public  void  RecyleTempFile(){
        System.out.println(1);
        File file=new File(SysConstast.FILE_PATH);
        System.out.println(file);
        detele(file);
    }
    public  void  detele(File file){
        File[] files= file.listFiles();
        for (File file1:files){
           if (file1.getName().endsWith(SysConstast.FILE_UPLOAD_TEMP)){
               file1.delete();
           }
            if (file1.isDirectory()){
                detele(file1);
            }
        }
    }

}
