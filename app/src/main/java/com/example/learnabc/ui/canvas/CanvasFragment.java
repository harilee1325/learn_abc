package com.example.learnabc.ui.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.learnabc.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CanvasFragment extends Fragment {


    private static final String TAG = "Canvas";

    @BindView(R.id.tv_clear)
    TextView tvClear;
    @BindView(R.id.clear_mode)
    FloatingActionButton clearMode;
    @BindView(R.id.tv_blurrmode)
    TextView tvBlurrmode;
    @BindView(R.id.blurr_mode)
    FloatingActionButton blurrMode;
    @BindView(R.id.tv_embossmode)
    TextView tvEmbossmode;
    @BindView(R.id.emboss_mode)
    FloatingActionButton embossMode;
    @BindView(R.id.tv_normal_mode)
    TextView tvNormalMode;
    @BindView(R.id.normal_mode)
    FloatingActionButton normalMode;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.paintView)
    PaintView paintView;
    @BindView(R.id.submit_drawing)
    Button submitDrawing;
    @BindView(R.id.back_bt)
    ImageButton backBt;
    @BindView(R.id.toolbar)
    LinearLayout toolbar;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.fab_info)
    FloatingActionButton fabInfo;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;
    private Context mContext;
    private Boolean isOpen = false;
    private MediaPlayer ourSong;
    private boolean info = false;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_canvas, container, false);
        ButterKnife.bind(this, root);
        setView();
        return root;
    }

    private void setView() {

        fab_close = AnimationUtils.loadAnimation(mContext, R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(mContext, R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(mContext, R.anim.fab_rotate_clock);
        fab_anticlock = AnimationUtils.loadAnimation(mContext, R.anim.fab_rotate_anticlock);

        DisplayMetrics metrics = new DisplayMetrics();
        if (getActivity() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
            paintView.init(metrics);
        }

        submitDrawing.setOnClickListener(v -> {
            getDrawing();
        });

        fabInfo.setOnClickListener(v -> {
            if (info) {
                tvInfo.setVisibility(View.INVISIBLE);
                tvInfo.startAnimation(fab_close);
                info = false;
            } else {
                tvInfo.setVisibility(View.VISIBLE);
                tvInfo.startAnimation(fab_open);
                info = true;
            }
        });

    }

    private void getDrawing() {
        Bitmap bitmap = paintView.getBitmap();
        FirebaseVisionImage visionImage = FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVisionTextRecognizer textRecognizer = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();
        submitDrawing.setEnabled(false);
        textRecognizer.processImage(visionImage)
                .addOnSuccessListener(this::processTextRecResutl)
                .addOnFailureListener(e -> {
                    paintView.clear();
                    submitDrawing.setEnabled(true);
                    Toast.makeText(mContext, "Draw again please", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
                });
       /* if (bitmap != null) {
            TextRecognizer textRecognizer = new TextRecognizer.Builder(mContext).build();
            Frame imageFrame = new Frame.Builder()
                    .setBitmap(bitmap)                 // your image bitmap
                    .build();

            String imageText = "";
            SparseArray<TextBlock> textBlocks = textRecognizer.detect(imageFrame);

            for (int i = 0; i < textBlocks.size(); i++) {
                TextBlock textBlock = textBlocks.get(textBlocks.keyAt(i));
                imageText = textBlock.getValue();                   // return string
            }
            Toast.makeText(mContext, imageText, Toast.LENGTH_SHORT).show();

        }*/
    }

    private void processTextRecResutl(FirebaseVisionText firebaseVisionText) {
        submitDrawing.setEnabled(true);
        String text = firebaseVisionText.getText();
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
        if (!text.isEmpty()) {

            showDataAndPlayAudio(text);
        }

    }

    private void showDataAndPlayAudio(String text) {
        switch (text.trim().toUpperCase()) {
            case "A":
            case "Q":
            case "B":
            case "C":
            case "D":
            case "E":
            case "F":
            case "G":
            case "H":
            case "I":
            case "J":
            case "K":
            case "L":
            case "M":
            case "N":
            case "O":
            case "P":
            case "R":
            case "S":
            case "T":
            case "U":
            case "V":
            case "W":
            case "X":
            case "Y":
            case "Z":
                showData(text.trim().toLowerCase()
                        , "raw", "com.example.learnabc");
                break;


        }
    }

    private void showData(String audioFile, String raw, String packageName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater factory;
        View view;
        ImageView image;
        int resId = getResources().getIdentifier(audioFile, raw, packageName);
        ourSong = MediaPlayer.create(mContext, resId);
        ourSong.start();
        builder.setTitle("A");
        factory = LayoutInflater.from(mContext);
        view = factory.inflate(R.layout.a, null);
        image = view.findViewById(R.id.ai);
        setImage(image, audioFile);
        builder.setView(view);
        builder.setNeutralButton("BACK", null);
        builder.show();
    }

    private void setImage(ImageView image, String audioFile) {
        switch (audioFile.trim().toUpperCase()) {
            case "A":
                image.setImageResource(R.drawable.a);
                break;
            case "Q":
                image.setImageResource(R.drawable.q);
                break;
            case "B":
                image.setImageResource(R.drawable.b);
                break;
            case "C":
                image.setImageResource(R.drawable.c);
                break;
            case "D":
                image.setImageResource(R.drawable.d);
                break;
            case "E":
                image.setImageResource(R.drawable.e);
                break;
            case "F":
                image.setImageResource(R.drawable.f);
                break;
            case "G":
                image.setImageResource(R.drawable.g);
                break;
            case "H":
                image.setImageResource(R.drawable.h);
                break;
            case "I":
                image.setImageResource(R.drawable.i);
                break;
            case "J":
                image.setImageResource(R.drawable.j);
                break;
            case "K":
                image.setImageResource(R.drawable.k);
                break;
            case "L":
                image.setImageResource(R.drawable.l);
                break;
            case "M":
                image.setImageResource(R.drawable.m);
                break;
            case "N":
                image.setImageResource(R.drawable.n);
                break;
            case "O":
                image.setImageResource(R.drawable.o);
                break;
            case "P":
                image.setImageResource(R.drawable.p);
                break;
            case "R":
                image.setImageResource(R.drawable.r);
                break;
            case "S":
                image.setImageResource(R.drawable.s);
                break;
            case "T":
                image.setImageResource(R.drawable.t);
                break;
            case "U":
                image.setImageResource(R.drawable.u);
                break;
            case "V":
                image.setImageResource(R.drawable.v);
                break;
            case "W":
                image.setImageResource(R.drawable.w);
                break;
            case "X":
                image.setImageResource(R.drawable.x);
                break;
            case "Y":
                image.setImageResource(R.drawable.y);
                break;
            case "Z":
                image.setImageResource(R.drawable.z);
                break;

        }
    }


    @OnClick({R.id.clear_mode, R.id.blurr_mode, R.id.emboss_mode, R.id.normal_mode, R.id.fab})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_mode:
                paintView.clear();
                mainFabClicked();
                break;
            case R.id.blurr_mode:
                paintView.blur();
                mainFabClicked();
                break;
            case R.id.emboss_mode:
                paintView.emboss();
                mainFabClicked();
                break;
            case R.id.normal_mode:
                paintView.normal();
                mainFabClicked();
                break;
            case R.id.fab:
                mainFabClicked();
                break;
        }
    }

    private void mainFabClicked() {

        if (isOpen) {
            tvBlurrmode.setVisibility(View.INVISIBLE);
            tvClear.setVisibility(View.INVISIBLE);
            tvEmbossmode.setVisibility(View.INVISIBLE);
            tvNormalMode.setVisibility(View.INVISIBLE);
            embossMode.startAnimation(fab_close);
            clearMode.startAnimation(fab_close);
            normalMode.startAnimation(fab_close);
            blurrMode.startAnimation(fab_close);
            tvBlurrmode.startAnimation(fab_close);
            tvNormalMode.startAnimation(fab_close);
            tvClear.startAnimation(fab_close);
            tvEmbossmode.startAnimation(fab_close);
            fab.startAnimation(fab_anticlock);
            normalMode.setClickable(false);
            blurrMode.setClickable(false);
            clearMode.setClickable(false);
            embossMode.setClickable(false);
            isOpen = false;
        } else {
            tvBlurrmode.setVisibility(View.VISIBLE);
            tvClear.setVisibility(View.VISIBLE);
            tvEmbossmode.setVisibility(View.VISIBLE);
            tvNormalMode.setVisibility(View.VISIBLE);
            embossMode.startAnimation(fab_open);
            clearMode.startAnimation(fab_open);
            normalMode.startAnimation(fab_open);
            blurrMode.startAnimation(fab_open);
            tvBlurrmode.startAnimation(fab_open);
            tvNormalMode.startAnimation(fab_open);
            tvClear.startAnimation(fab_open);
            tvEmbossmode.startAnimation(fab_open);
            fab.startAnimation(fab_clock);
            normalMode.setClickable(true);
            blurrMode.setClickable(true);
            clearMode.setClickable(true);
            embossMode.setClickable(true);
            isOpen = true;
        }
    }

    @OnClick(R.id.back_bt)
    public void onClick() {

        if (getActivity() != null)
            getActivity().getSupportFragmentManager().popBackStackImmediate();
    }
}
