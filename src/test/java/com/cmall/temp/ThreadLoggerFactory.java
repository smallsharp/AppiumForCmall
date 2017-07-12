package com.cmall.temp;

import java.io.IOException;
import java.util.Enumeration;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;

public class ThreadLoggerFactory {  
    static  final String prefix="com.demo.hello.provider.log_test";  
    static Logger parentLogger ;  
    static {  
        parentLogger=Logger.getLogger(prefix);  
    }  
    public  static Logger getLogger(){  
        Logger logger =Logger.getLogger(prefix+Thread.currentThread().getName());  
        addAppender(logger,Thread.currentThread().getName());  
        return logger;  
    }  
    /** 
     * @param name 
     */  
    private static void addAppender(Logger logger,String threadName) {  
        Enumeration<Appender> appends=parentLogger.getAllAppenders();  
        if(!logger.getAllAppenders().hasMoreElements()){  
            while (appends.hasMoreElements()) {  
                Appender appender = (Appender) appends.nextElement();  
                if(appender instanceof ThreadDailyRollingAppender){  
                    ThreadDailyRollingAppender myappender=(ThreadDailyRollingAppender)appender;  
                    try {  
                        logger.addAppender(new ThreadDailyRollingAppender(myappender,threadName));  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }  
                    continue;  
                }  
                logger.addAppender(appender);  
            }  
        }  
          
    }  
}  