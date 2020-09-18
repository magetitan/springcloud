package com.my.springcloud.common.singleton;

import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import com.my.springcloud.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */

public class SingletonProgram {
    private static final String PROGRAMA_NAME = "Singleton";
    private static final Logger log = LoggerFactory.getLogger(SingletonProgram.class);
    public static void resource() {
    	System.out.println(SingletonProgram.class.getResource(""));
		System.out.println(SingletonProgram.class.getResource("/"));
		System.out.println(SingletonProgram.class.getClassLoader().getResource(""));
    }
    public static void lockSingletonProgramFile() {
        String programName = PROGRAMA_NAME;
 
        final String startFailureMessage = "Error:start " + programName
                + " application";
        String lockFile = System.getProperty("lockFile");
        log.info("start " + programName + " application with [lockFile] : "
                + lockFile);
        if (null == lockFile) {
        	String parent = System.getProperty("user.dir");
            lockFile =  parent + File.separator
                    + programName + ".lock";
            log.info("does not provide lockFile, it will use default lockFile which is ["
                    + lockFile + "]");
        }
        RandomAccessFile raf = null;
        FileChannel fileChannel = null;
        FileLock flock = null;
        FileWriter writer = null;
        try {
            File file = new File(lockFile);
            if (!file.exists()) {
                String parent = file.getParent();
                File folder = new File(parent);
                if (!folder.exists() || !folder.isDirectory()) {
                    if (!folder.mkdirs()) {
                        log.error(startFailureMessage
                                + " failure: create lock file folder failure:"
                                + parent);
                        System.exit(-1);
                    }
                }
                if (!file.createNewFile()) {
                    log.error(startFailureMessage
                            + " failure: create lock file failure:" + lockFile);
                    System.exit(-1);
                }
            }
            writer = new FileWriter(file);
            writer.write(programName);
            writer.flush();
            writer.close();
            raf = new RandomAccessFile(file, "rw");
            fileChannel = raf.getChannel();
            flock = fileChannel.tryLock();// start to try locking lock file
        } catch (Exception e) {
            log.error(startFailureMessage + " failure: lock file is ["
                    + lockFile + "]:" + StringUtils.getStackTrace(e));
            try {
                if (null != writer) {
                    writer.close();
                }
                if (null != flock) {
                    flock.release();
                }
                if (null != fileChannel) {
                    fileChannel.close();
                }
                if (null != raf) {
                    raf.close();
                }
            } catch (Exception ex) {
                log.error(
                        "Error: close resource failure:"+ StringUtils.getStackTrace(ex));
            }
            log.error("There is a "
                    + programName
                    + " application process in system processes. Now exit starting!");
            System.exit(-1);
        }
    }
 
}