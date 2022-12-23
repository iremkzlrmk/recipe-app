package com.meri.recipe_app.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Converters {

    @TypeConverter
    public static String StringListToString(ArrayList<String> list){
        if (list == null) return null;
        return String.join("; ", list);
    }

    @TypeConverter
    public static ArrayList<String> StringToStringList(String string){
        if (string == null) return null;
        return new ArrayList<String>(Arrays.asList(string.split("; ")));
    }

    @TypeConverter
    public static byte[] BitmapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        return baos.toByteArray();
    }

    @TypeConverter
    public static Bitmap StringToBitmap(byte[] byteArray) {
        try {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @TypeConverter
    public static Bitmap DrawableToBitmap (Drawable drawable) {
        // https://stackoverflow.com/a/10600736/13692454

        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
