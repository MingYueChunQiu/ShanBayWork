package com.shanbay.shanbaywork.model.file;

import android.content.Context;

/**
 * Created by 明月春秋 on 2017/9/24.
 * 进行字符文件的操作基类
 * 继承BaseFile，获取其中的通用模版方法
 * 提供对字符文件的读写操作抽象方法标准，供子类实现
 * 方法：
 *     1.saveCharFile：保存字符文件
 *     2.getCharFile：获取字符文件
 */

public abstract class CharFile extends BaseFile{

    /**
     * 保存字符文件
     * @param filePath
     *          需要保存的文件路径
     * @param content
     *          需要保存的文件内容
     * @return
     *          true：   文件保存成功
     *          false：  文件保存失败
     */
    /**
     * 保存字符文件
     * @param context
     *          保存文件的上下文
     * @param filePath
     *          需要保存的文件路径
     * @param content
     *          需要保存的文件内容
     * @return
     *          true：   文件保存成功
     *          false：  文件保存失败
     */
    public abstract boolean saveCharFile(Context context, String filePath, String content);

    /**
     * 获取字符文件内容
     * @param context
     *          获取文件的上下文
     * @param filePath
     *          需要获取文件内容的文件路径
     * @return
     *          返回获取的文件的内容
     */
    public abstract String getCharFile(Context context, String filePath);
}
