package com.example.view.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BitmapOptions {
    // 质量压缩方法
    public static Bitmap compressImage(Bitmap bitmap) {
        // 把bitmap转换成字节
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 质量参数
        int options = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        // 压缩图片100k以内
        while (baos.toByteArray().length / 1024 > 100) {
            // 清空baos
            baos.reset();
            // 每次质量减少10
            options -= 10;
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            Log.e("BitmapOptions", "质量压缩：" + baos.toByteArray().length);
        }
        // 把压缩的数据存储到InputStream中
        ByteArrayInputStream in = new ByteArrayInputStream(baos.toByteArray());
        Bitmap bmp = BitmapFactory.decodeStream(in);
        try {
            baos.flush();
            baos.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmp;
    }

    // 按尺寸压缩
    public static Bitmap scalePathImage(String path) {
        // 图片的信息
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 开始读取图片时，inJustDecodeBounds = true,decode时，返回null，只返回图片的宽和高
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false;
        // 获取图片的宽度和高度
        int w = options.outWidth;
        int h = options.outHeight;
//			Log.i("aa", "inJustDecodeBounds = false:w=" + w + "--h=" + h);
        // 设置压缩图片的尺寸
        float ww = 800f;
        float hh = 480f;
        // 缩放比例
        int scale = 1;
        // 如果宽度大于高度的时候，就按宽度来固定大小
        if (w > h || ww > hh) {
            scale = (int) (w / ww);
        } else {
            scale = (int) (h / hh);
        }

        if (scale <= 0) {
            scale = 1;
        }
        // 设置缩放比例
        options.inSampleSize = scale;
        Bitmap bmp = BitmapFactory.decodeFile(path, options);
        return compressImage(bmp);
    }


    public static Bitmap scaleImage(Bitmap bmp) {
        // 把bitmap转换成字节流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        // 把图片压缩到100k以为
        while (baos.toByteArray().length > 1024 * 100) {
            baos.reset();
            options -= 10;
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
            //Log.i("aa", "质量压缩：" + baos.toByteArray().length);
        }
        //
        ByteArrayInputStream in = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options2);
        options2.inJustDecodeBounds = false;
        // 获取图片的宽度和高度
        int w = options2.outWidth;
        int h = options2.outHeight;
        // 设置压缩图片的尺寸
        float ww = 800f;
        float hh = 480f;
        // 缩放比例
        int scale = 1;
        // 如果宽度大于高度的时候，就按宽度来固定大小
        if (w > h && ww > hh) {
            scale = (int) (w / ww);
        } else {
            scale = (int) (h / hh);
        }
        if (scale <= 0) {
            scale = 1;
        }
        // 设置缩放比例
        options2.inSampleSize = scale;
        // 重新读入图片
        ByteArrayInputStream in2 = new ByteArrayInputStream(baos.toByteArray());
        Bitmap bmp2 = BitmapFactory.decodeStream(in2, null, options2);
        try {
            baos.flush();
            baos.close();
            in.close();
            in2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp2;
    }
}

