package com.shanbay.shanbaywork.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shanbay.shanbaywork.R;

import java.io.IOException;

/**
 * Created by 明月春秋 on 2017/12/3.
 * 显示具体单词信息的View控件
 * 显示单词文本、音标、解释
 * 点击图标播放单词读音
 */

public class WordInfoView extends RelativeLayout {

    private Animation shownAnimation = null;//View显示动画
    private Animation goneAnimation = null;//View消失动画

    private TextView tvShowContent, tvShowPronunciation, tvShowDefinition;//显示单词文本、音标、释义
    private ImageView ivAudio;//单词读音喇叭图标
    private String audio;//单词的读音文件

    public WordInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addView(getView(context));
    }

    /**
     * 获取填充的布局
     * @param context
     *          布局所在上下文
     * @return
     *          返回具体的布局
     */
    @NonNull
    private View getView(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.wv_word_info, null);
        tvShowContent = view.findViewById(R.id.tv_content);
        tvShowPronunciation = view.findViewById(R.id.tv_pronunciation);
        tvShowDefinition = view.findViewById(R.id.tv_definition);
        ivAudio = view.findViewById(R.id.iv_audio);
        ivAudio.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (audio == null){
                    return;
                }
                final MediaPlayer player = new MediaPlayer();
                try {
                    Uri uri = Uri.parse(audio);
                    player.setDataSource(context, uri);
                    player.prepareAsync();
                    player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    /**
     * 设置单词内容
     * @param content
     *          单词内容
     */
    public void setContent(String content){
        if (content == null || content.equals("")){
            return;
        }
        tvShowContent.setText(content);
    }

    /**
     * 设置单词音标
     * @param pronunciation
     *          单词音标
     */
    public void setPronunciation(String pronunciation){
        if (pronunciation == null || pronunciation.equals("")){
            return;
        }
        tvShowPronunciation.setText(pronunciation);
    }

    /**
     * 设置单词释义
     * @param definition
     *          单词释义
     */
    public void setDefinition(String definition){
        if (definition == null || definition.equals("")){
            return;
        }
        tvShowDefinition.setText(definition);
    }

    /**
     * 设置控件显示动画
     * @param shownAnimation
     *          显示的动画
     */
    public void setShownAnimation(Animation shownAnimation) {
        this.shownAnimation = shownAnimation;
    }

    /**
     * 设置控件消失动画
     * @param goneAnimation
     *          消失的动画
     */
    public void setGoneAnimation(Animation goneAnimation) {
        this.goneAnimation = goneAnimation;
    }

    /**
     * 设置单词读音文件
     * @param audio
     *          读音文件
     */
    public void setAudio(String audio){
        this.audio = audio;
    }

}
