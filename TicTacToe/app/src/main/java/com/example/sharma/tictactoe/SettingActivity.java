package com.example.sharma.tictactoe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton mImageBack;
    TextView mSwitch,mPlayer1,mPlayer2;
    String mPlayer1Name,mPlayer2Name;
    ImageView mPlayer1Image,mPlayer2Image,mGenericImage,mGenericImage2;
    Bitmap mBitmap,mOrientedBitmap;
    Context mContext;
    RelativeLayout mRelativeSetting;
    Button mBackgroundChange;
   int change=0;
    String newName1;
    String newName2;
    Bitmap mGenericBitmap1,mGenericBitmap2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_setting);
        mImageBack = (ImageButton) findViewById(R.id.btn_back);
        mSwitch= (TextView) findViewById(R.id.text_switch);
        mPlayer1Image= (ImageView) findViewById(R.id.image_player1);
        mPlayer2Image= (ImageView) findViewById(R.id.image_player2);
        mRelativeSetting= (RelativeLayout) findViewById(R.id.relative_setting);
        mBackgroundChange= (Button) findViewById(R.id.button_background);
        mPlayer1= (TextView) findViewById(R.id.text_player1);
        mPlayer2= (TextView) findViewById(R.id.text_player2);
        mPlayer2.setOnClickListener(this);
        mPlayer1.setOnClickListener(this);
        mContext=getApplicationContext();
        mPlayer1Image.setOnClickListener(this);
        mPlayer2Image.setOnClickListener(this);
        mImageBack.setOnClickListener(this);
        mSwitch.setOnClickListener(this);
        mBackgroundChange.setOnClickListener(this);

       // Picasso.with(mContext).load()

        try {

        } catch (Exception e)
        {

        }


        SharedPreferences preferences =getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String n1=null,n2=null;
        String pathForImage1 = null;
        String pathForImage2 = null;
        try {
                 n1 = preferences.getString("Name1", null);
                 n2 = preferences.getString("Name2", null);
            pathForImage1=preferences.getString("image1",null);
            pathForImage2=preferences.getString("image2",null);

            } catch (Exception e) {
                e.printStackTrace();
            }
        if(n1!=null)
        {
            mPlayer1.setText(n1);
            mPlayer2.setText(n2);
        }
        if(pathForImage1!=null){
            Picasso.with(mContext).load(Uri.parse(pathForImage1)).resize(200,200).centerCrop().into(mPlayer1Image);
        }
        if(pathForImage2!=null)
        {
            Picasso.with(mContext).load(Uri.parse(pathForImage2)).resize(200,200).centerCrop().into(mPlayer2Image);
        }
        }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                Intent intent=new Intent();
                if(change>0){
                    intent.putExtra("player1name",mPlayer1.getText().toString());
                    intent.putExtra("player2name",mPlayer2.getText().toString());
                    newName1=mPlayer1.getText().toString();
                    newName2=mPlayer2.getText().toString();
                    if(mGenericImage!=null)
                        intent.putExtra("player1image",mGenericBitmap1);
                    if(mGenericImage2!=null)
                        intent.putExtra("player2image",mGenericBitmap2);
                }
                setResult(1,intent);
                finish();
                break;
            case R.id.text_switch:
                change++;
                mPlayer1Name=mPlayer1.getText().toString();
                mPlayer2Name=mPlayer2.getText().toString();
                mPlayer1.setText(mPlayer2Name);
                mPlayer2.setText(mPlayer1Name);

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("Name1",mPlayer2Name);
                editor.putString("Name2",mPlayer1Name);
                editor.commit();

                break;
            case R.id.image_player1:
                mGenericImage=mPlayer1Image;
                showDialog();
                break;
            case R.id.image_player2:
                mGenericImage2=mPlayer2Image;
                showDialogForImage2();
                break;
            case R.id.button_background:
                showDialogForBackground();
                break;

        }
    }

    public void showDialog(){
        final AlertDialog dialog= new AlertDialog.Builder(SettingActivity.this).create();
        LayoutInflater inflater =this.getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialog.setView(dialogView);

        ImageView camera= (ImageView) dialogView.findViewById(R.id.camera);
        ImageView gallery=(ImageView) dialogView.findViewById(R.id.gallery);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, 2);
                dialog.dismiss();
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, 1);
               dialog.dismiss();
            }
        });
        dialog.show();
    }

    //************************************************************
    public void showDialogForImage2(){
        final AlertDialog dialog= new AlertDialog.Builder(SettingActivity.this).create();
        LayoutInflater inflater =this.getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialog.setView(dialogView);

        ImageView camera= (ImageView) dialogView.findViewById(R.id.camera);
        ImageView gallery=(ImageView) dialogView.findViewById(R.id.gallery);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, 6);
                dialog.dismiss();
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, 5);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //************************************************************

    public void showDialogForBackground(){
        final AlertDialog dialog= new AlertDialog.Builder(SettingActivity.this).create();
        LayoutInflater inflater =this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialog.setView(dialogView);

        TextView title= (TextView) dialogView.findViewById(R.id.text);
        title.setText("Please Choose the Option to Set Background Image");
        ImageView camera= (ImageView) dialogView.findViewById(R.id.camera);
        ImageView gallery=(ImageView) dialogView.findViewById(R.id.gallery);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, 4);
                dialog.dismiss();
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, 3);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            Log.e("first uri",""+selectedImage);
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap im = BitmapFactory.decodeFile(picturePath);
            String path=picturePath;

            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("image1",selectedImage.toString());
            editor.commit();

            Log.e("image path",path);
            Bitmap orientedBitmap= ExifUtil.rotateBitmap(picturePath,im);
            mGenericImage.setImageBitmap(orientedBitmap);
            mGenericBitmap1=orientedBitmap;
        }

        else if (requestCode == 2) {
            Uri uri=data.getData();
            Log.e("first uri",""+uri);
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection, null, null, null);
            int column_index_data = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToLast();
            String imagePath = cursor.getString(column_index_data);
            Bitmap im = BitmapFactory.decodeFile(imagePath);
            String path=imagePath;

            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("image1",uri.toString());
            editor.commit();

            Log.e("image path",path);
            Bitmap orientedBitmap= ExifUtil.rotateBitmap(imagePath,im);
            mGenericImage.setImageBitmap(orientedBitmap);
            mGenericBitmap1=orientedBitmap;
        }
        else if (requestCode == 3 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap im = BitmapFactory.decodeFile(picturePath);
            String path=picturePath;
            Log.e("image path",path);
            Bitmap orientedBitmap= ExifUtil.rotateBitmap(picturePath,im);
            Drawable db=new BitmapDrawable(orientedBitmap);
           // mRelativeSetting.setBackgroundDrawable(db);
           // MainActivity.mRelativeLayout.setBackgroundDrawable(db);

            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("background",selectedImage.toString());
            editor.commit();

            //mGenericImage.setImageBitmap(orientedBitmap);
        }
        else if (requestCode == 4) {
            Uri selectedImage = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection, null, null, null);
            int column_index_data = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToLast();
            String imagePath = cursor.getString(column_index_data);
            Bitmap im = BitmapFactory.decodeFile(imagePath);
            String path=imagePath;


            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("background",selectedImage.toString());
            editor.commit();

            Log.e("image path",path);
            Bitmap orientedBitmap= ExifUtil.rotateBitmap(imagePath,im);
            Drawable db=new BitmapDrawable(orientedBitmap);
            //MainActivity.mRelativeLayout.setBackgroundDrawable(db);


           // mRelativeSetting.setBackgroundDrawable(db);
            //mGenericImage.setImageBitmap(orientedBitmap);
        }




        if (requestCode == 5 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap im = BitmapFactory.decodeFile(picturePath);
            String path=picturePath;
            Log.e("image path",path);
            Bitmap orientedBitmap= ExifUtil.rotateBitmap(picturePath,im);
            mGenericImage2.setImageBitmap(orientedBitmap);
            mGenericBitmap2=orientedBitmap;
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("image2",selectedImage.toString());
            editor.commit();

        }

        else if (requestCode == 6) {
            Uri uri=data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection, null, null, null);
            int column_index_data = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToLast();
            String imagePath = cursor.getString(column_index_data);
            Bitmap im = BitmapFactory.decodeFile(imagePath);
            String path=imagePath;
            Log.e("image path",path);
            Bitmap orientedBitmap= ExifUtil.rotateBitmap(imagePath,im);
            mGenericImage2.setImageBitmap(orientedBitmap);
            mGenericBitmap2=orientedBitmap;
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("image2",uri.toString());
            editor.commit();
        }
    }
}
