package com.shanbay.shanbaywork.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 明月春秋 on 2017/11/29.
 * 自定义文本View
 * 继承自AppCompatTextView
 * 实现文本对齐，以及单词可被点击选中效果
 * 本view部分内容参考devilist的CSDN博客
 */

public class SelectableView extends android.support.v7.widget.AppCompatTextView {

    private TextPaint mTextPaint;//文本画笔

    public SelectableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 绘制对齐格式的全部文本
     * @param canvas
     *          进行绘制的画布
     */
    private void drawAlignText(Canvas canvas){
        Layout mLayout = getLayout();
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
            //如果该行需要进行文本缩放调整，则进行绘制判断，否则直接调用canvas绘制
            if (judgeLineNeedZoom(line)){
                //如果是最后一行，则直接进行绘制
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
        mTextPaint = getPaint();
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
        Log.d("行尾", (line.charAt(line.length() - 1) != '\n')+"");
        return line.charAt(line.length() - 1) != '\n';
    }

    /**
     * 绘制每行经过对齐的文本
     * @param canvas
     *          绘制的画布
     * @param line
     *          进行绘制的一行文本内容
     * @param width
     *          该行文本不包含字符间距的宽度
     * @param currentOffsetY
     *          当前行文本的Y轴偏移量
     */
    private void drawAlignLineText(Canvas canvas, String line, float width, int currentOffsetY){
        //每行画笔的初始位置
        float currentOffsetX = getPaddingLeft();
        float viewWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();//真正文本区域宽度
        //每行所有的空白间距
        float insertBlank = viewWidth - width;
        String[] words = line.split(" ");
        //如果只有一个单词，则判断单词是否只有一位，不只一位则进行字符循环绘制
        if (words.length == 1){
            float singleInsertBlank = insertBlank;
            String singleWord = words[0] + " ";
            int singleLength = singleWord.length();
            if (singleLength > 1){
                singleInsertBlank = insertBlank / (singleLength - 1);
                for (int j = 0; j < singleLength; j ++){
                    String charWord = String.valueOf(singleWord.charAt(j));
                    float charWidth = StaticLayout.getDesiredWidth(charWord, mTextPaint);
                    canvas.drawText(charWord, currentOffsetX, currentOffsetY, mTextPaint);
                    currentOffsetX += (charWidth + singleInsertBlank);
                }
            }
            else {
                float wordWidth = StaticLayout.getDesiredWidth(singleWord, mTextPaint);
                canvas.drawText(singleWord, currentOffsetX, currentOffsetY, mTextPaint);
                currentOffsetX += (wordWidth + insertBlank);
            }
        }
        //如果有多个单词，则循环遍历各个单词进行绘制
        if (words.length > 1){
            insertBlank = insertBlank / (words.length - 1);
        }
        for (int i = 0; i < words.length; i ++){
            String word = words[i] + " ";
            float wordWidth = StaticLayout.getDesiredWidth(word, mTextPaint);
            canvas.drawText(word, currentOffsetX, currentOffsetY, mTextPaint);
            currentOffsetX += (wordWidth + insertBlank);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawAlignText(canvas);
    }

}
