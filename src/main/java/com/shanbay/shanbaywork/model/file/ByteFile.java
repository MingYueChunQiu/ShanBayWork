package com.shanbay.shanbaywork.model.file;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by 明月春秋 on 2017/9/26.
 * 进行字节文件的操作基类
 * 继承BaseFile，获取其中的通用模版方法
 * 提供对字节文件的读写操作抽象方法标准，供子类实现
 * 方法：
 *     1.saveByteFile：保存字节文件
 *     2.getByteFile：获取字节文件
 *     3.saveBitmapFile：保存图片文件
 */

public abstract class ByteFile extends BaseFile {

    /**
     * 保存字节文件
     * @param context
     *          保存字节文件的上下文
     * @param filePath
     *          需要保存的文件路径
     * @return
     *          true：   文件保存成功
     *          false：  文件保存失败
     */
    public abstract boolean saveByteFile(Context context, String filePath);

    /**
     * 获取字节文件
     * @param context
     *          获取字节文件的上下文
     * @param filePath
     *          需要获取文件内容的文件路径
     * @return
     *          返回获取的文件的内容
     */
    public abstract boolean getByteFile(Context context, String filePath);

    /**
     * 保存图片文件
     * @param context
     *          保存图片文件的上下文
     * @param filePath
     *          需要保存的文件路径
     * @param bitmap
     *          需要保存的图片
     * @return
     *          true：   文件保存成功
     *          false：  文件保存失败
     */
    public abstract boolean saveBitmapFile(Context context, String filePath, Bitmap bitmap);

    /**
     * 根据文件地址获取图片
     * @param context
     *          获取图片文件的上下文
     * @param filePath
     *          文件所在路径
     * @return
     *          返回由文件路径解析得到的图片
     */
    public abstract Bitmap getBitmapFile(Context context, String filePath);
}
