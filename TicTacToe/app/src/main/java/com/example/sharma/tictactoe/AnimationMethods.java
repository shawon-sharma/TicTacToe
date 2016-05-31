package com.example.sharma.tictactoe;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by sharma on 5/19/16.
 */
public class AnimationMethods {
   static Bitmap b1=null,b2=null;
    public static void animate(final ImageView imageView, final Context context, final int user) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(imageView, "rotationY", 0.0f, 90f);
        animation.setDuration(500);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());

        SharedPreferences preferences = context.getSharedPreferences("MyPref", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String pathForImage1 = null;
        String pathForImage2 = null;
        try {
             pathForImage1=preferences.getString("image1",null);
            pathForImage2=preferences.getString("image2",null);
        } catch (Exception e)
        {

        }



        final String finalPathForImage = pathForImage1;
        final String finalPathForImage1 = pathForImage2;
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(user%2 != 0) {
                    if(finalPathForImage ==null) {
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.panda));
                    }else{
                        Uri uri=null;
                            uri=Uri.parse(finalPathForImage);
                        Log.e("Uri",""+uri);
                        Picasso.with(context).load(uri).resize(256,256).centerCrop().into(imageView);
                      // imageView.setImageBitmap(b1);
                    }
                }
                else {
                    if(finalPathForImage1 ==null) {
                        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.jerri));
                    }else{
                        Picasso.with(context).load(Uri.parse(finalPathForImage1)).resize(256,256).into(imageView);
                       // imageView.setImageBitmap(b2);
                    }
                }

                ObjectAnimator animation2 = ObjectAnimator.ofFloat(imageView, "rotationY", 270.0f, 360f);  // HERE 360 IS THE ANGLE OF ROTATE, YOU CAN USE 90, 180 IN PLACE OF IT,  ACCORDING TO YOURS REQUIREMENT
                animation2.setDuration(500); // HERE 500 IS THE DURATION OF THE ANIMATION, YOU CAN INCREASE OR DECREASE ACCORDING TO YOURS REQUIREMENT
                animation2.setInterpolator(new AccelerateDecelerateInterpolator());
                animation2.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animation.start();
    }
    public static void setBitmap1(Bitmap bit1)
    {
        b1=bit1;
    }
    public static void setBitmap2(Bitmap bit2)
    {
        b2=bit2;
    }
}
