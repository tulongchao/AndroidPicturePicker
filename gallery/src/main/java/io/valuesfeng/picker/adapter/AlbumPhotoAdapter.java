/*
 * Copyright (C) 2014 nohana, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.valuesfeng.picker.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.File;
import java.util.Map;

import io.valuesfeng.picker.ImageSelectActivity;
import io.valuesfeng.picker.R;
import io.valuesfeng.picker.model.Item;
import io.valuesfeng.picker.control.SelectedUriCollection;
import io.valuesfeng.picker.utils.PicturePickerUtils;
import io.valuesfeng.picker.widget.GridViewItemRelativeLayout;

/**
 */
public class AlbumPhotoAdapter extends CursorAdapter {
    LayoutInflater mInflater;
    Context mContext;
    SelectedUriCollection mCollection;
    Map<Long, String> map;

    public AlbumPhotoAdapter(Context context, Cursor c, SelectedUriCollection mCollection, Map<Long, String> map) {
        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.mCollection = mCollection;
        this.map = map;
    }

    private ViewHolder viewHolder;

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View convertView = mInflater.inflate(R.layout.photopick_gridlist_item, parent, false);
        viewHolder = new ViewHolder(convertView, mCollection);
        return convertView;
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        viewHolder = (ViewHolder) view.getTag();
        viewHolder.itemView.setItem(Item.valueOf(cursor));
    }

    static class ViewHolder {
        GridViewItemRelativeLayout itemView;

        public ViewHolder(View convertView, SelectedUriCollection mCollection) {
            itemView = (GridViewItemRelativeLayout) convertView;
            itemView.setImageView((ImageView) convertView.findViewById(R.id.thumbnail)
                    , (ImageView) convertView.findViewById(R.id.check)
                    , mCollection);
            convertView.setTag(this);
        }
    }
}