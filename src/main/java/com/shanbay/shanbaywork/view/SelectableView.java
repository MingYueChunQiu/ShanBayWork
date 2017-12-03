package com.shanbay.shanbaywork.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

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
    private OnWordSelectedListener mListener = null;//监听单词被选中的回调事件
    private Context mContext;
    private int selectedIndex = -1 , selectedNum = -1;//记录选中单词的索引位置以及行数
    private boolean[] notZoomedFlags;//记录没有被缩放的文本行

    public SelectableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    /**
     * 绘制对齐格式的全部文本
     * @param canvas
     *          进行绘制的画布
     */
    private void drawAlignText(Canvas canvas){
        Layout mLayout = getLayout();
        setTextPaint();
        notZoomedFlags = new boolean[getLineCount()];
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
                    notZoomedFlags[i] = true;
                    drawNotZoomedText(canvas, line, getPaddingLeft(), currentOffsetY, i);
//                    canvas.drawText(line, getPaddingLeft(), currentOffsetY, mTextPaint);
                }
                else {
                    drawAlignLineText(canvas, line, wordsWidth, currentOffsetY, i);
                }
            }
            else {
                notZoomedFlags[i] = true;
                drawNotZoomedText(canvas, line, getPaddingLeft(), currentOffsetY, i);
//                canvas.drawText(line, getPaddingLeft(), currentOffsetY, mTextPaint);
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
    private void drawAlignLineText(Canvas canvas, String line, float width, int currentOffsetY, int currentNum){
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
                    drawZoomedText(canvas, charWord, currentOffsetX, currentOffsetY, 0, currentNum);
                    currentOffsetX += (charWidth + singleInsertBlank);
                }
            }
            else {
                float wordWidth = StaticLayout.getDesiredWidth(singleWord, mTextPaint);
                drawZoomedText(canvas, singleWord, currentOffsetX, currentOffsetY, 0, currentNum);
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
            drawZoomedText(canvas, word, currentOffsetX, currentOffsetY, i, currentNum);
            currentOffsetX += (wordWidth + insertBlank);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //不使用TextView原生绘制方法，采用文本两端对齐绘制方法
        drawAlignText(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            setSelectedText(-1, -1);
            float currentX = event.getX();
            float currentY = event.getY();
            //如果用户触摸位置超出了文本界限，则直接返回
            if (currentX <= getPaddingLeft() || currentX >= (getMeasuredWidth() - getPaddingRight())){
                return false;
            }
            if (currentY <= getPaddingTop() || currentY >= (getHeight() - getPaddingBottom())){
                return false;
            }

            Log.d("xyj", currentX + " " + currentY);
            int lineHeight = getLineHeight();
            //计算当前用户选中行数，获取该行文本
            int lineNumber = Math.round(currentY / lineHeight) - 1;
            int start = getLayout().getLineStart(lineNumber);
            int end = getLayout().getLineEnd(lineNumber);
            String line = getText().toString().substring(start, end);
            String[] words = line.split(" ");

            int offsetX = getPaddingLeft();//各个单词的起始位置
            float wordsWidth = StaticLayout.getDesiredWidth(getText().toString(), start, end, mTextPaint);
            //每行所有的空白间距
            int length = words.length;
            float viewWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();//真正文本区域宽度
            float insertBlank = (viewWidth - wordsWidth) / (length - 1);
            for (int i = 0; i < length; i ++){
                String word = words[i] + " ";
                float wordWidth = StaticLayout.getDesiredWidth(word, mTextPaint);
                //判断当前用户手指位置是否属于单词
                if (currentX >= offsetX && currentX <= (offsetX + wordWidth)){
                    Log.d("选中单词", words[i]);
                    setSelectedText(i, lineNumber);
                    if (mListener != null){
                        mListener.onSelected(words[i]);
                    }
                    break;
                }
                if (notZoomedFlags[lineNumber]){
                    offsetX += wordWidth;
                }
                else {
                    offsetX += (wordWidth + insertBlank);
                }
            }
            return true;
        }

        return super.onTouchEvent(event);
    }

    /**
     * 当用户选中某个单词时，提供给外界进行回调，对选中单词进行处理
     */
    public interface OnWordSelectedListener{

        /**
         * 对选中单词的事件处理
         * @param word
         */
        public void onSelected(String word);
    }

    /**
     * 供外界设置对单词选中的监听事件
     * @param listener
     *          监听单词选中事件的监听器
     */
    public void setOnWordSelectedListener(OnWordSelectedListener listener){
        mListener = listener;
    }

    private void setSelectedText(int selectedIndex, int selectedNum){
        this.selectedIndex = selectedIndex;
        this.selectedNum = selectedNum;
        invalidate();
    }

    /**
     * 绘制选中单词高亮
     * @param canvas
     *          绘制的画布
     * @param word
     *          绘制的单词
     * @param currentX
     *          绘制单词的X轴位置
     * @param currentY
     *          绘制单词的Y轴位置
     */
    private void drawSelectedText(Canvas canvas, String word, float currentX, float currentY){
        //更改画笔颜色绘制高亮，然后再将画笔颜色还原
        int color = mTextPaint.getColor();
        mTextPaint.setColor(mContext.getResources().getColor(android.R.color.holo_orange_dark));
        canvas.drawText(word, currentX, currentY, mTextPaint);
        mTextPaint.setColor(color);
    }

    /**
     * 根据条件判断，决定是绘制选中单词还是普通文本
     * @param canvas
     *          绘制的画布
     * @param word
     *          绘制的单词
     * @param currentX
     *          绘制单词的X轴位置
     * @param currentY
     *          绘制单词的Y轴位置
     */
    private void drawZoomedText(Canvas canvas, String word, float currentX, float currentY, int selectedIndex, int selectedNum){
        if (this.selectedIndex == selectedIndex && this.selectedNum == selectedNum){
            Log.d("索引", this.selectedIndex + " " + selectedIndex + " " + this.selectedNum + " " + selectedNum);
            word = getEnglishWord(word);
            drawSelectedText(canvas, word, currentX, currentY);
        }
        else {
            canvas.drawText(word, currentX, currentY, mTextPaint);
        }
    }

    /**
     * 绘制未进行缩放的文本行，在是否选中单词状态下的文本样式
     * @param canvas
     *          绘制的画布
     * @param line
     *          绘制的文本行
     * @param currentX
     *          绘制行的X轴位置
     * @param currentY
     *          绘制行的Y轴位置
     * @param selectedNum
     *          绘制行的索引位置
     */
    private void drawNotZoomedText(Canvas canvas, String line, int currentX, int currentY, int selectedNum){
        if (this.selectedNum == selectedNum){
            Log.d("索引", this.selectedIndex + " "+ " " + this.selectedNum + " " + selectedNum);
            String[] words = line.split(" ");
            for (int i = 0; i < words.length; i ++){
                String word = words[i] + " ";
                float wordWidth = StaticLayout.getDesiredWidth(word, mTextPaint);
                if (selectedIndex == i){
                    word = getEnglishWord(word);
                    drawSelectedText(canvas, word, currentX, currentY);
                }
                else {
                    canvas.drawText(word, currentX, currentY, mTextPaint);
                }
                currentX += wordWidth;
            }
        }
        else {
            canvas.drawText(line, currentX, currentY, mTextPaint);
        }
    }

    /**
     * 将获取到的单词中，所有的非英文部分剔除
     * @param word
     *          需要进行分辨的单词
     * @return
     *          返回纯正的英文单词
     */
    private String getEnglishWord(String word){
        return word.replaceAll("[^a-zA-Z]", "");
    }
}
