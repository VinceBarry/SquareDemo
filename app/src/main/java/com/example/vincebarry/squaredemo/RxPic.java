package com.example.vincebarry.squaredemo;

import android.content.Context;
import android.graphics.Bitmap;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by VinceBarry on 2016/5/31.
 */
public class RxPic {
    public static Observable<Bitmap> saveImage(final Context context, final String url, String title) {
        return Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap bitmap = null;
                try {
                    bitmap = Picasso.with(context).load(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (bitmap == null) {
                    subscriber.onError(new Exception("Couldn't download the picture"));
                }
                subscriber.onNext(bitmap);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());

    }
}
