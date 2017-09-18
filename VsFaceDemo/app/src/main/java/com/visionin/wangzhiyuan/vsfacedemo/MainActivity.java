package com.visionin.wangzhiyuan.vsfacedemo;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.blankj.utilcode.util.FileUtils;

import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private ImageButton mBtnRevert, mBtnPicture, mBtnVideo, mBtnFair, mBtnFace;
    private ExampleControl mControl;
    public LinearLayout mLineMain, mLineFair, mLineFace;
    private SeekBar mBarFair, mBarDerma, mBarTender, mFace;
    private SurfaceView mSurface;
    private static final String TAG = "MainActivity";

    private static final String DATA_PATH = "/sdcard/Android/data/com.visionin.wangzhiyuan.vsfacedemo/files";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mControl = new ExampleControl(this);

        mBtnRevert = (ImageButton) findViewById(R.id.btn_revert);
        mBtnPicture = (ImageButton) findViewById(R.id.btn_picture);
        mBtnVideo = (ImageButton) findViewById(R.id.btn_video);
        mBtnFair = (ImageButton) findViewById(R.id.btn_fair);
        mBtnFace = (ImageButton) findViewById(R.id.btn_face);
        mLineMain = (LinearLayout) findViewById(R.id.line_main);
        mLineFair = (LinearLayout) findViewById(R.id.line_fair);

        mBarFair = (SeekBar) findViewById(R.id.seek_fair);
        mBarDerma = (SeekBar) findViewById(R.id.seek_derma);
        mBarTender = (SeekBar) findViewById(R.id.seek_tender);
        mFace = (SeekBar) findViewById(R.id.seek_face);

        mSurface = (SurfaceView) findViewById(R.id.surface);
        mLineFace = (LinearLayout) findViewById(R.id.line_face);

        mBtnRevert.setOnClickListener(this);
        mBtnPicture.setOnClickListener(this);
        mBtnVideo.setOnClickListener(this);
        mBtnFair.setOnClickListener(this);
        mBtnFace.setOnClickListener(this);

        mBarFair.setOnSeekBarChangeListener(this);
        mBarDerma.setOnSeekBarChangeListener(this);
        mBarTender.setOnSeekBarChangeListener(this);
        mFace.setOnSeekBarChangeListener(this);

        mSurface.setOnClickListener(this);

        mControl.Init(mSurface);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    if (!FileUtils.isFileExists(DATA_PATH + "/mask/mask.png")){
                        copyFilesFassets(MainActivity.this, "picture", DATA_PATH);
                    }else{
                        Thread.sleep(500);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mControl.Open();
                    }
                });

            }
        }).start();

    }

    @Override
    protected void onStart() {
        super.onStart();
//        mControl.Open();


    }


    @Override
    protected void onPause() {
        super.onPause();
        mControl.Close();
    }

    @Override
    protected void onResume() {
        super.onResume();
       // mControl.Open();
    }

    private boolean isVideoRecord = false;
    private boolean isFair = false;
    private boolean isFace = false;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.surface:

                break;
            case R.id.btn_revert:  //切换前后摄像头
                //Toast.makeText(getApplicationContext(), "切换摄像头", Toast.LENGTH_SHORT).show();
                //mControl.Switch();


                break;
            case R.id.btn_picture:  //截图
                Toast.makeText(getApplicationContext(), "截图", Toast.LENGTH_SHORT).show();
                mControl.Snapshot();
                break;
            case R.id.btn_video:  //录像
                Toast.makeText(getApplicationContext(), "录像", Toast.LENGTH_SHORT).show();
                if (isVideoRecord) {
                    mBtnVideo.setImageResource(R.mipmap.video);
                    mControl.CaptureOff();
                } else {
                    mBtnVideo.setImageResource(R.mipmap.video_red);
                    mControl.CaptureOn();
                }
                isVideoRecord = !isVideoRecord;
                break;
            case R.id.btn_fair:  //打开美颜界面
                Toast.makeText(getApplicationContext(), "美颜", Toast.LENGTH_SHORT).show();
                if (isFair) {
                    mLineFair.setVisibility(View.GONE);
                    mBtnFair.setImageResource(R.mipmap.fair);
                } else {
                    mLineFair.setVisibility(View.VISIBLE);
                    mLineFace.setVisibility(View.GONE);
                    mBtnFair.setImageResource(R.mipmap.fair_red);
                }
                isFair = !isFair;
                break;
            case R.id.btn_face:  //打开关闭人脸识别
                Toast.makeText(getApplicationContext(), "人脸检测", Toast.LENGTH_SHORT).show();
                if (isFace) {
                    mLineFace.setVisibility(View.GONE);
                    mBtnFace.setImageResource(R.mipmap.face);
                    mControl.FacerOff();
                } else {
                    mLineFace.setVisibility(View.VISIBLE);
                    mLineFair.setVisibility(View.GONE);
                    mBtnFace.setImageResource(R.mipmap.face_red);
                    mControl.FacerOn();
                }
                isFace = !isFace;
                break;
            default:
                break;
        }
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float index = ((float) progress) / 10;
        //Log.e(TAG,index+".....");
        switch (seekBar.getId()) {
            case R.id.seek_fair:  //美白
                mControl.Brightening(index);
                break;
            case R.id.seek_derma:  //磨皮
                mControl.Smoothing(index);
                break;
            case R.id.seek_tender:  //锐化
                mControl.Sharpening(index);
                break;
            case R.id.seek_face:
                mControl.FacerChangeAR(index);
                break;
            default:
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    public void moveFiles() {
        AssetManager assetManager = getAssets();
        String list[] = new String[0];
        try {
            list = assetManager.list("picture");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String path:
             list) {
            Log.e(TAG, path);
        }
        String path = "file:///android_asset/文件名";
        Log.e(TAG, path);
    }

    /**
     *  从assets目录中复制整个文件夹内容
     *  @param  context  Context 使用CopyFiles类的Activity
     *  @param  oldPath  String  原文件路径  如：/aa
     *  @param  newPath  String  复制后路径  如：xx:/bb/cc
     */
    public void copyFilesFassets(Context context, String oldPath, String newPath) {
        try {
            String fileNames[] = context.getAssets().list(oldPath);//获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {//如果是目录
                File file = new File(newPath);
                file.mkdirs();//如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyFilesFassets(context,oldPath + "/" + fileName,newPath+"/"+fileName);
                }
            } else {//如果是文件
                InputStream is = context.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount=0;
                while((byteCount=is.read(buffer))!=-1) {//循环从输入流读取 buffer字节
                    fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
                }
                fos.flush();//刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
