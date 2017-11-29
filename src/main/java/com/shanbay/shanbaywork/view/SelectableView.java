package com.shanbay.shanbaywork.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 明月春秋 on 2017/11/29.
 * 自定义文本View
 * 继承自AppCompatTextView
 * 实现文本对齐，以及单词可被点击选中效果
 */

public class SelectableView extends android.support.v7.widget.AppCompatTextView {

    private Layout mLayout = getLayout();

    private TextPaint mTextPaint = getPaint();

    public SelectableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void drawAlignText(){

    }
}
