package com.example.headpose_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.headpose_android.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'headpose_android' library on application startup.
    static {
        System.loadLibrary("headpose_android");
    }

    public String TAG="MainActivity";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.textView2;
        tv.setText(stringFromJNI());
        if (get_permission()) {
            Log.e(TAG,"=====> get permission success");
            net_run();

        } else {
            Log.e(TAG,"=====> get permission failed");
            finish();
        }
    }

    public boolean get_permission() {
        //动态申请储存权限

        boolean sSRPR = ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                | ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                | ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA);
        if (sSRPR) {
            ActivityCompat.requestPermissions(this, new String[]{
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.CAMERA},
                    0);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.CAMERA},
                    0);
        }

        //permission check
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getBaseContext(), "没有储存权限，请到设置中更改", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public native String stringFromJNI();
    public native void net_run();

}