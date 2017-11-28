package com.shanbay.shanbaywork.model.file;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.shanbay.shanbaywork.constants.FileConstants;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 明月春秋 on 2017/10/3.
 */

public class RawByteFile extends ByteFile {
    @Override
    public boolean saveByteFile(Context context, String filePath) {
        return false;
    }

    @Override
    public boolean getByteFile(Context context, String filePath) {
        return false;
    }

    @Override
    public boolean saveBitmapFile(Context context, String filePath, Bitmap bitmap) {
        //判断图片所在的文件夹目录是否存在
        if (!createOrGetFileDirectory(context, FileConstants.DIR_IMAGES)){
            return false;
        }
        File file = createOrOpenFile(filePath);
        if (file == null){
            return false;
        }
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            boolean result = bitmap.compress(Bitmap.CompressFormat.PNG, 75, bos);
            bos.flush();
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Bitmap getBitmapFile(Context context, String filePath) {
        //判断图片所在的文件夹目录是否存在
        if (!createOrGetFileDirectory(context, FileConstants.DIR_IMAGES)){
            return null;
        }
        //如果文件路径为空或内容为空，则直接返回
        if (filePath == null || filePath.equals("")){
            return null;
        }
        return BitmapFactory.decodeFile(filePath);
    }

}
