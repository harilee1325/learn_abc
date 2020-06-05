package com.example.learnabc.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.learnabc.R;
import com.example.learnabc.ui.abc.AbcFragment;
import com.example.learnabc.ui.canvas.CanvasFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {


    @BindView(R.id.main_card)
    CardView mainCard;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.canvas_card)
    CardView canvasCard;
    @BindView(R.id.abc_card)
    CardView abcCard;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @OnClick({R.id.canvas_card, R.id.abc_card})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.canvas_card:
                if (getActivity() != null) {
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new CanvasFragment(), "home");
                    ft.addToBackStack(null);
                    ft.commitAllowingStateLoss();
                }
                break;
            case R.id.abc_card:
                if (getActivity() != null) {
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new AbcFragment(), "home");
                    ft.addToBackStack(null);
                    ft.commitAllowingStateLoss();
                }
                break;
        }
    }
}
