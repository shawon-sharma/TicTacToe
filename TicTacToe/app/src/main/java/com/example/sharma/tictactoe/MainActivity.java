package com.example.sharma.tictactoe;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView mImageView1, mImageView2, mImageView3, mImageView4, mImageView5, mImageView6, mImageView7, mImageView8, mImageView9;
    TextView mResult, mPlayer1, mPlayer2;
    public  RelativeLayout mRelativeLayout;
    Context mContext;
    int user = 0;
    String mTurn;
    int Win = 0;
    ImageButton mRestart;

    ArrayList<Integer> R1 = newArrayList(1, 2, 3);
    ArrayList<Integer> R2 = newArrayList(4, 5, 6);
    ArrayList<Integer> R3 = newArrayList(7, 8, 9);

    ArrayList<Integer> C1 = newArrayList(1, 4, 7);
    ArrayList<Integer> C2 = newArrayList(2, 5, 8);
    ArrayList<Integer> C3 = newArrayList(3, 6, 9);

    ArrayList<Integer> X1 = newArrayList(1, 5, 9);
    ArrayList<Integer> X2 = newArrayList(3, 5, 7);

    ArrayList<Integer> user1 = new ArrayList<>();
    ArrayList<Integer> user2 = new ArrayList<>();
    ArrayList<ImageView> allButton = new ArrayList<>();
    int trasparent_Color;

    int click = 0;
    int state=0;

    Bitmap b1,b2;

    int height=0;
    int width=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        Intent intent=new Intent();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
         height = displaymetrics.heightPixels;
         width = displaymetrics.widthPixels;

        mRelativeLayout = (RelativeLayout) findViewById(R.id.relative_1);
        mRelativeLayout.setBackgroundResource(R.drawable.jerri);
        setView();
        eventListenerManager();
        mContext = MainActivity.this;
        mTurn = ", now your turn !!";
        mResult.setText(mPlayer1.getText().toString() + mTurn);
        trasparent_Color=getResources().getColor(R.color.semiTransparent);
        setInArray();
        setName();
       // setBackground();

    }
     public  void setBackground(){
         SharedPreferences preferences =getSharedPreferences("MyPref", MODE_PRIVATE);
         SharedPreferences.Editor editor = preferences.edit();
         String path=null;
         path=preferences.getString("background",null);
         if(path != null) {
             Picasso.with(mContext).load(Uri.parse(path)).resize(width, height).into(new Target() {
                 @Override
                 public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                     mRelativeLayout.setBackgroundDrawable(new BitmapDrawable(getApplicationContext().getResources(), bitmap));
                 }

                 @Override
                 public void onBitmapFailed(Drawable errorDrawable) {

                 }

                 @Override
                 public void onPrepareLoad(Drawable placeHolderDrawable) {

                 }
             });
         }

     }
    public void setName(){
        SharedPreferences preferences =getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String n1=null,n2=null;
        String path=null;
        try {
            n1 = preferences.getString("Name1", null);
            n2 = preferences.getString("Name2", null);
            path=preferences.getString("background",null);
            Log.e("bacground path",path);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(n1!=null)
        {
            mPlayer1.setText(n1);
            mPlayer2.setText(n2);
            mResult.setText(mPlayer1.getText().toString()+ mTurn);
        }
        if(path!=null)
        {
           /* Bitmap im = BitmapFactory.decodeFile(path);
            Bitmap orientedBitmap= ExifUtil.rotateBitmap(path,im);
            Drawable db=new BitmapDrawable(orientedBitmap);
            mRelativeLayout.setBackgroundDrawable(db);*/
            Picasso.with(mContext).load(Uri.parse(path)).resize(width,height).centerCrop().into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    mRelativeLayout.setBackgroundDrawable(new BitmapDrawable(getApplicationContext().getResources(), bitmap));
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
        }

    }

    public void setInArray() {
        allButton.add(mImageView1);
        allButton.add(mImageView2);
        allButton.add(mImageView3);
        allButton.add(mImageView4);
        allButton.add(mImageView5);
        allButton.add(mImageView6);
        allButton.add(mImageView7);
        allButton.add(mImageView8);
        allButton.add(mImageView9);
    }

    public void setView() {
        mImageView1 = (ImageView) findViewById(R.id.image_1);
        mImageView2 = (ImageView) findViewById(R.id.image_2);
        mImageView3 = (ImageView) findViewById(R.id.image_3);
        mImageView4 = (ImageView) findViewById(R.id.image_4);
        mImageView5 = (ImageView) findViewById(R.id.image_5);
        mImageView6 = (ImageView) findViewById(R.id.image_6);
        mImageView7 = (ImageView) findViewById(R.id.image_7);
        mImageView8 = (ImageView) findViewById(R.id.image_8);
        mImageView9 = (ImageView) findViewById(R.id.image_9);
        mResult = (TextView) findViewById(R.id.text_result);
        mPlayer1 = (TextView) findViewById(R.id.text_player1_name);
        mPlayer2 = (TextView) findViewById(R.id.text_player2_name);
        mRestart = (ImageButton) findViewById(R.id.btn_restart);
    }

    public void eventListenerManager() {
        mImageView1.setOnClickListener(this);
        mImageView2.setOnClickListener(this);
        mImageView3.setOnClickListener(this);
        mImageView4.setOnClickListener(this);
        mImageView5.setOnClickListener(this);
        mImageView6.setOnClickListener(this);
        mImageView7.setOnClickListener(this);
        mImageView8.setOnClickListener(this);
        mImageView9.setOnClickListener(this);
        mResult.setOnClickListener(this);
        mRestart.setOnClickListener(this);

    }

    public void makeAllDisable() {
        for (ImageView v : allButton) {
            v.setEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_setting) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivityForResult(intent, 1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void pause(final int no) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (no != 0) {
                    mResult.setText(mPlayer2.getText().toString() + mTurn);
                }
                else {
                    mResult.setText(mPlayer1.getText().toString() + mTurn);
                }
            }
        }, 1000);

    }

    public void callPause(int no) {
        if (Win == 0 && user<9) {
            pause(no);
        }else if(Win==0 && user==9){
                mResult.setText("Tie");
        }
        else {
            makeAllDisable();
        }
    }


    public void buttonDisable(ImageView b) {
        b.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        ++user;
        int no = user % 2;

        switch (v.getId()) {
            case R.id.image_1:
                addNumberToUser(no, 1);
                AnimationMethods.animate(mImageView1, mContext, user);
                callPause(no);
                buttonDisable(mImageView1);
                break;
            case R.id.image_2:
                addNumberToUser(no, 2);
                AnimationMethods.animate(mImageView2, mContext, user);
                callPause(no);
                buttonDisable(mImageView2);
                break;
            case R.id.image_3:
                addNumberToUser(no, 3);
                AnimationMethods.animate(mImageView3, mContext, user);
                callPause(no);
                buttonDisable(mImageView3);
                break;
            case R.id.image_4:
                addNumberToUser(no, 4);
                AnimationMethods.animate(mImageView4, mContext, user);
                callPause(no);
                buttonDisable(mImageView4);
                break;
            case R.id.image_5:
                addNumberToUser(no, 5);
                AnimationMethods.animate(mImageView5, mContext, user);
                callPause(no);
                buttonDisable(mImageView5);
                break;
            case R.id.image_6:
                addNumberToUser(no, 6);
                AnimationMethods.animate(mImageView6, mContext, user);
                callPause(no);
                buttonDisable(mImageView6);
                break;
            case R.id.image_7:
                addNumberToUser(no, 7);
                AnimationMethods.animate(mImageView7, mContext, user);
                callPause(no);
                buttonDisable(mImageView7);
                break;
            case R.id.image_8:
                addNumberToUser(no, 8);
                AnimationMethods.animate(mImageView8, mContext, user);
                callPause(no);
                buttonDisable(mImageView8);
                break;
            case R.id.image_9:
                addNumberToUser(no, 9);
                AnimationMethods.animate(mImageView9, mContext, user);
                callPause(no);
                buttonDisable(mImageView9);
                break;
            case R.id.btn_restart:
                initialView();
                break;
        }
    }
    public void addNumberToUser(int user, int no) {
        if (user == 1) {
            user1.add(no);
            sizeCheck(user1, user);
        } else {
            user2.add(no);
            sizeCheck(user2, user);
        }
    }

    public void sizeCheck(ArrayList<Integer> user, int userno) {
        if (user.size() >= 3) {
            if (compare(user)) {
                showResult(userno);
            }
        }
    }

    public void showResult(int userno) {
        String isWin = " is Winner !!";
        if (userno == 1) {
            mResult.setText(mPlayer1.getText().toString() + isWin);
        } else {
            mResult.setText(mPlayer2.getText().toString() + isWin);
        }
        Win++;
    }

    public boolean compare(ArrayList<Integer> user) {
        if (realCompare(user, R1)) {
            colorChange(R1);
            return true;
        } else if (realCompare(user, R2)) {
            colorChange(R2);
            return true;
        } else if (realCompare(user, R3)) {
            colorChange(R3);
            return true;
        } else if (realCompare(user, C1)) {
            colorChange(C1);
            return true;
        } else if (realCompare(user, C2)) {
            colorChange(C2);
            return true;
        } else if (realCompare(user, C3)) {
            colorChange(C3);
            return true;
        } else if (realCompare(user, X1)) {
            colorChange(X1);
            return true;
        } else if (realCompare(user, X2)) {
            colorChange(X2);
            return true;
        }

        return false;
    }

    public void colorChange(ArrayList<Integer> user )
    {
        for (int i=0;i<user.size();i++)
        {
            int value=user.get(i);
            switch (value){
                case 1:
                    mImageView1.setColorFilter(getResources().getColor(R.color.semiTransparent), PorterDuff.Mode.DARKEN);
                    break;
                case 2:
                    mImageView2.setColorFilter(getResources().getColor(R.color.semiTransparent), PorterDuff.Mode.DARKEN);
                    break;
                case 3:
                    mImageView3.setColorFilter(getResources().getColor(R.color.semiTransparent), PorterDuff.Mode.DARKEN);
                    break;
                case 4:
                    mImageView4.setColorFilter(getResources().getColor(R.color.semiTransparent), PorterDuff.Mode.DARKEN);
                    break;
                case 5:
                    mImageView5.setColorFilter(getResources().getColor(R.color.semiTransparent), PorterDuff.Mode.DARKEN);
                    break;
                case 6:
                    mImageView6.setColorFilter(getResources().getColor(R.color.semiTransparent), PorterDuff.Mode.DARKEN);
                    break;
                case 7:
                    mImageView7.setColorFilter(getResources().getColor(R.color.semiTransparent), PorterDuff.Mode.DARKEN);
                    break;
                case 8:
                    mImageView8.setColorFilter(getResources().getColor(R.color.semiTransparent), PorterDuff.Mode.DARKEN);
                    break;
                case 9:
                    mImageView9.setColorFilter(getResources().getColor(R.color.semiTransparent), PorterDuff.Mode.DARKEN);
                    break;
            }
        }
    }

    public boolean realCompare(ArrayList<Integer> user, ArrayList<Integer> compareUser) {
        int confirm = 0;
        for (int i = 0; i < user.size(); i++) {
            boolean b = compareUser.contains(user.get(i));
            if (b)
                confirm++;
            else
                continue;
        }
        if (confirm >= 3)
            return true;

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            String player1 = null;
            String player2 = null;
            try {
                player1 = data.getStringExtra("player1name");
                player2 = data.getStringExtra("player2name");
                b1=data.getParcelableExtra("player1image");
                b2=data.getParcelableExtra("player2image");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (player1 != null) {
                mPlayer1.setText(player1);
                mPlayer2.setText(player2);
            }
            if(b1 !=null)
            {
                 AnimationMethods.setBitmap1(b1);
            }
            if(b2 !=null)
            {
                AnimationMethods.setBitmap2(b2);
            }
            initialView();
        }
    }

    public void initialView()
    {
        recreate();
       /* user1.clear();
        user2.clear();
        click=0;
        Win=0;
        mResult.setText(mPlayer1.getText().toString()+ mTurn);
        for(ImageView v:allButton) {
            v.setEnabled(true);
            v.setImageDrawable(mContext.getResources().getDrawable(R.drawable.default_image));
            v.setColorFilter(getResources().getColor(android.R.color.transparent), PorterDuff.Mode.SCREEN);
        }*/
    }
}
