package br.com.mrocigno.alicization.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import java.io.ByteArrayOutputStream;

public class ScreenshotUtils {

    public static Uri takeScreenshot(Activity activity){
        Uri imgUri = null;
        if(PermissionUtil.hasPermission(activity, PermissionUtil.MyPermissions.WRITE_EXTERNAL)){
            View v1 = activity.getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);
            imgUri = getImageUri(activity, bitmap);
        }else{
            PermissionUtil.askPermissions(activity);
        }
        return imgUri;
    }

    private static Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}
