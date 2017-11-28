package com.shanbay.shanbaywork.model.file;

import android.content.Context;
import java.io.File;
import java.io.IOException;

/**
 * Created by 明月春秋 on 2017/9/24.
 * 操作文件的抽象模版基类，提供给子类继承的通用方法
 * 方法：
 *     1.createOrOpenFile：创建或者打开文件
 */

public abstract class BaseFile {

    /**
     * 根据传入的上下文参数与文件名，判断文件是否存在，创建或者打开文件
     * @param filePath
     *              需要进行操作的文件路径
     * @return
     *              如果成功，返回创建或者打开好的文件；
     *              如果失败，返回null
     */
    protected File createOrOpenFile(String filePath){
        //如果文件路径为空或内容为空，则直接返回
        if (filePath == null || filePath.equals("")){
            return null;
        }
        try {
            File file = new File(filePath);
            if (file.exists() || file.createNewFile()){
                return file;
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建或获取文件夹目录
     * @param context
     *          文件夹目录创建的上下文
     * @param typeDirectory
     *          类型文件夹
     * @return
     */
    public boolean createOrGetFileDirectory(Context context, String typeDirectory){
        String fileDirectory = null;
        try {
            fileDirectory = context.getFilesDir().getCanonicalPath() + typeDirectory;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //如果文件路径为空或内容为空，则直接返回
        if (fileDirectory == null || fileDirectory.equals("")){
            return false;
        }
        File file = new File(fileDirectory);
        if ((file.exists() && file.isDirectory()) || file.mkdir()){
            return true;
        }
        return false;
    }
}
