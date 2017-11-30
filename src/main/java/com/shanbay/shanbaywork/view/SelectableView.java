package com.shanbay.shanbaywork.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 明月春秋 on 2017/11/29.
 * 自定义文本View
 * 继承自AppCompatTextView
 * 实现文本对齐，以及单词可被点击选中效果
 * 本view部分内容参考devilist的CSDN博客
 */

public class SelectableView extends android.support.v7.widget.AppCompatTextView {

    private Layout mLayout = getLayout();

    private TextPaint mTextPaint = getPaint();

    public SelectableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void drawAlignText(Canvas canvas){

        setTextPaint();

        String content = getText().toString();
        int currentOffsetY = getPaddingTop();
        currentOffsetY += getTextSize();

        int lines = mLayout.getLineCount();
        for (int i = 0; i < lines; i ++){
            int lineStart = mLayout.getLineStart(i);
            int lineEnd = mLayout.getLineEnd(i);
            float wordsWidth = StaticLayout.getDesiredWidth(content, lineStart, lineEnd, mTextPaint);
            String line = content.substring(lineStart, lineEnd);
            if (judgeLineNeedZoom(line)){
                if (i == (lines - 1)){
                    canvas.drawText(line, getPaddingLeft(), currentOffsetY, mTextPaint);
                }
                else {
                    drawAlignLineText(canvas, line, wordsWidth, currentOffsetY);
                }
            }
            else {
                canvas.drawText(line, getPaddingLeft(), currentOffsetY, mTextPaint);
            }
            currentOffsetY += getLineHeight();
        }
    }

    /**
     * 设置TextPaint
     */
    private void setTextPaint() {
        mTextPaint.setColor(getCurrentTextColor());
        mTextPaint.drawableState = getDrawableState();
    }

    /**
     * 判断改行是否需要进行缩放
     * @param line
     *          改行内容字符串
     * @return
     *          返回判断结果
     *          true：改行内容需要进行缩放
     *          false：该行没有内容，或者该行为文本结尾
     */
    private boolean judgeLineNeedZoom(String line){
        if (line.length() == 0){
            return false;
        }
        return line.charAt(line.length() - 1) != '\n';
    }

    private void drawAlignLineText(Canvas canvas, String line, float Width, int offsetY){

    }
}
