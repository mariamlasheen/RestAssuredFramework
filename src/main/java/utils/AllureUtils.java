package utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {
    private AllureUtils(){
        super();
    }

    public static void attacheLogsToAllureReport(){

        try {
            File logFile = FilesUtils.getLastestFile(LogsUtil.LOGS_PATH);
            if(!logFile.exists()){
                LogsUtil.warn("Log file does not exists" + LogsUtil.LOGS_PATH);

            }
            Allure.addAttachment("logs.log", Files.readString(Path.of(logFile.getPath())));
        } catch (Exception e){
            LogsUtil.error("Failed to attach logs to Allure report: " + e.getMessage());
        }
    }
}
