package com.example.vincebarry.squaredemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {
    private ImageView mIv;
    private CompositeSubscription mCompositeSubscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIv = (ImageView) findViewById(R.id.iv);
        Observer<Bitmap> observer = new Observer<Bitmap>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Bitmap bitmap) {
                mIv.setImageBitmap(bitmap);
                Toast.makeText(MainActivity.this,"Picture",Toast.LENGTH_SHORT).show();
            }
        };
        Subscription s = RxPic.saveImage(MainActivity.this,"http://img2.imgtn.bdimg.com/it/u=3234625060,3330214688&fm=21&gp=0.jpg","Hello")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        this.mCompositeSubscription.add(s);
    }
}
