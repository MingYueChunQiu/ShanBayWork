package com.shanbay.shanbaywork.model.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.Target;
import com.shanbay.shanbaywork.R;

import java.util.List;

/**
 * Created by 明月春秋 on 2017/12/3.
 * 自定义显示图片列表的适配器
 */

public class BitmapAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList;

    public BitmapAdapter(Context context, List<String> list){
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_bitmap, null);
            viewHolder = new ViewHolder();
            viewHolder.ivBitmap = convertView.findViewById(R.id.iv_bitmap);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        //因为已经自定义了一个Glide缓存对象，所以Glide会自动将图片下载到缓存目录里
        Target<Drawable> target = Glide.with(mContext).load(mList.get(position)).into(viewHolder.ivBitmap);
//        target.onLoadCleared(mContext.getResources().getDrawable(R.drawable.load_default));
//        target.onLoadFailed(mContext.getResources().getDrawable(R.drawable.load_default));
        return convertView;
    }

    /**
     * 用于优化ListView显示的类
     */
    private class ViewHolder {
        ImageView ivBitmap;
    }
}
