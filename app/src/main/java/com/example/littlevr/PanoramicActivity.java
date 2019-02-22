package com.example.littlevr;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;


public class PanoramicActivity extends AppCompatActivity {
    VrPanoramaView vrPanoramaView;

    private VrPanoramaView.Options panoOptions = new VrPanoramaView.Options();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panoramic);
        //If I'm using Parcelable implementation,
        // I've to use getParcelableExtra() method.
        vrPanoramaView = findViewById(R.id.pano_view);

        Images images = getIntent().getParcelableExtra("message");

        final ImageView imageView = new ImageView(this);

        imageView.setImageResource(images.getmImage());

        Bitmap b = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        vrPanoramaView.loadImageFromBitmap(b, panoOptions);
        panoOptions.inputType = VrPanoramaView.Options.TYPE_MONO;

        PackageManager packageManager = getPackageManager();
        boolean gyroExists = packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE);
        if (!gyroExists) {
//Enabling only touch tracking
            vrPanoramaView.setPureTouchTracking(true);
        }

    }

    @Override
    protected void onPause() {
        vrPanoramaView.pauseRendering();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        vrPanoramaView.resumeRendering();
    }

    @Override
    protected void onDestroy() {
        vrPanoramaView.shutdown();
        super.onDestroy();
    }
}
