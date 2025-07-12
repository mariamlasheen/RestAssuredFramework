package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class FilesUtils {
    private FilesUtils(){
        super();
    }

    public static File getLastestFile(String folderpath){
        File folder = new File(folderpath);
        File[] files = folder.listFiles();
        if(files == null || files.length==0){
            LogsUtil.warn("No files found " + folderpath) ;
            return null;
        }
        File latestFile= files[0];
        for(File file : files){
            if(file.lastModified() > latestFile.lastModified()){
                latestFile = file;
            }
        }
        return latestFile;
    }
    public static void deleteFiles(File dirpath)  {
        if( dirpath ==null || !dirpath.exists()){
            LogsUtil.warn("Directory does not exist: " + dirpath );
        }

        File[] fileList = dirpath.listFiles();
        if(fileList == null){
            LogsUtil.warn("Failed to list files in " + dirpath);
        }

       else{
           for (File file: fileList){
            if(file.isDirectory()){
                deleteFiles(file);
            }
            else {
                try {
                    Files.delete(file.toPath());

                } catch (IOException e) {
                    LogsUtil.error("Failed to delete file " + file);
                }

            }
           }

        }

    }
}
