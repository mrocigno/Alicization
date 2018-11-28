package br.com.mrocigno.alicization.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.security.Permission;
import java.util.ArrayList;
import java.util.jar.Manifest;

public class PermissionUtil {

    enum MyPermissions{
        WRITE_EXTERNAL(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        private String value;

        MyPermissions(String writeExternalStorage) {
            this.value = writeExternalStorage;
        }

        public String getValue() {
            return value;
        }
    }

    public static boolean hasPermission(Context context, MyPermissions permission){
        return ContextCompat.checkSelfPermission(context, permission.getValue()) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean askPermissions(Activity activity) {
        String[] permisions = {
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> denied = new ArrayList<>();

        for (String permision:permisions) {
            if(ContextCompat.checkSelfPermission(activity, permision) == PackageManager.PERMISSION_DENIED){
                denied.add(permision);
            }
        }

        if(denied.size() > 0){
            String[] permisionsDenied = new String[denied.size()];
            permisionsDenied = denied.toArray(permisionsDenied);

            ActivityCompat.requestPermissions(activity, permisionsDenied,0);
            return false;
        }
        return true;
    }

}
