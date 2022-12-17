package com.meri.recipe_app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class Converters {

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
}
