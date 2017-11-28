package com.shanbay.shanbaywork.model.file;

import android.content.Context;

import com.shanbay.shanbaywork.constants.FileConstants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by 明月春秋 on 2017/9/25.
 * 使用系统原生方法进行字符文件操作的类
 * 继承CharFile，覆写其中跟字符文件相关的操作
 * 实现对字符文件的读写操作
 * 方法：
 *     1.saveCharFile：保存字符文件
 *     2.getCharFile：获取字符文件
 */

public class RawCharFile extends CharFile {
    @Override
    public boolean saveCharFile(Context context, String filePath, String content) {
        //判断文本所在的文件夹目录是否存在
        if (!createOrGetFileDirectory(context, FileConstants.DIR_DOUCES)){
            return false;
        }
        File file = createOrOpenFile(filePath);
        if (file == null){
            return false;
        }
        BufferedWriter bw = null;
        //判断获取的文件是否为空，若不空则进行写操作并返回true，否则返回false
        if (file != null){
            try {
                bw = new BufferedWriter(new FileWriter(file));
                bw.write(content);
                bw.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public String getCharFile(Context context, String filePath) {
        //判断文本所在的文件夹目录是否存在
        if (!createOrGetFileDirectory(context, FileConstants.DIR_DOUCES)){
            return null;
        }
        File file = createOrOpenFile(filePath);
        if (file == null){
            return null;
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String s = null;
            while ((s = br.readLine()) != null){
                stringBuilder.append(s);
                //读取完一行文本后就换行，防止文本缩进在一行中
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
