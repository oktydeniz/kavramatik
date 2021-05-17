package com.kavramatik.kavramatik.util;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class BindingImage {
    @BindingAdapter("imageUrl")
    public static void setImage(ImageView imageView, String url) {
        imageView.setImageBitmap(Base64Util.decodeToBitmap(url));
    }

}
