package com.bengui.volleytest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author benjamin.massello.
 */
public class MainMenuFragment extends Fragment {


    private MainMenuNavigation mainMenuNavigation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainMenuNavigation = (MainMenuNavigation) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmen_main_menu, null);

        view.findViewById(R.id.user_data_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainMenuNavigation.navigateToUserData();
            }
        });

        view.findViewById(R.id.user_list_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainMenuNavigation.navigateToUserList();
            }
        });

        view.findViewById(R.id.image_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainMenuNavigation.navigateToImageScreen();
            }
        });

        return view;
    }

    public interface MainMenuNavigation{

        void navigateToUserData();

        void navigateToUserList();

        void navigateToImageScreen();

    }

}
