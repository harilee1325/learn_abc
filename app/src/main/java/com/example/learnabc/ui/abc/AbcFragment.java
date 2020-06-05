package com.example.learnabc.ui.abc;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnabc.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AbcFragment extends Fragment {


    @BindView(R.id.back_bt)
    ImageButton backBt;
    @BindView(R.id.toolbar)
    LinearLayout toolbar;
    @BindView(R.id.alphabet_list)
    RecyclerView alphabetList;
    private AlphabetAdapter alphabetAdapter;
    private Context mContext;
    MediaPlayer ourSong;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_abc, container, false);
        ButterKnife.bind(this, root);
        setView();
        return root;
    }

    private void setView() {

        ArrayList<String> alphabets = new ArrayList<>();
        alphabets.add("A");
        alphabets.add("B");
        alphabets.add("C");
        alphabets.add("D");
        alphabets.add("E");
        alphabets.add("F");
        alphabets.add("G");
        alphabets.add("H");
        alphabets.add("I");
        alphabets.add("J");
        alphabets.add("K");
        alphabets.add("L");
        alphabets.add("M");
        alphabets.add("N");
        alphabets.add("O");
        alphabets.add("P");
        alphabets.add("Q");
        alphabets.add("R");
        alphabets.add("S");
        alphabets.add("T");
        alphabets.add("U");
        alphabets.add("V");
        alphabets.add("W");
        alphabets.add("X");
        alphabets.add("Y");
        alphabets.add("Z");
        alphabetAdapter = new AlphabetAdapter(alphabets);
        alphabetList.setAdapter(alphabetAdapter);

    }

    @OnClick(R.id.back_bt)
    public void onClick() {
    }

    public class AlphabetAdapter extends RecyclerView.Adapter<AlphabetAdapter.ViewHolder> {

        private ArrayList<String> alphabets;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater factory;
        View view;
        ImageView image;

        public AlphabetAdapter(ArrayList<String> alphabets) {

            this.alphabets = alphabets;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.alphabet, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.element.setText(alphabets.get(position));
            holder.element.setOnClickListener(v -> {
                handleClick(holder);

            });

        }

        private void handleClick(ViewHolder holder) {

            switch (alphabets.get(holder.getAdapterPosition())) {
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
                    showData(alphabets.get(holder.getAdapterPosition()).trim().toLowerCase()
                            , "raw", "com.example.learnabc");
                    break;


            }

        }

        private void showData(String audioFile, String raw, String packageName) {
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

        @Override
        public int getItemCount() {
            return alphabets.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.element)
            TextView element;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
