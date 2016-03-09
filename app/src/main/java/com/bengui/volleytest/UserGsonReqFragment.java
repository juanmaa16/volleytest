/* (c) Disney. All rights reserved. */
package com.bengui.volleytest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bengui.volleytest.managers.GsonRequest;
import com.bengui.volleytest.managers.VolleyManager;
import com.bengui.volleytest.models.User;

/**
 * @author benjamin.massello.
 */
public class UserGsonReqFragment extends Fragment {
    public static final String BASE_URL = "https://raw.githubusercontent.com/bengui/volleytest/master/json/user.json";
    public static final String TAG = UserFragment.class.getSimpleName();

    private Button requestButton;
    private VolleyManager volleyManager;
    private View view;
    private TextView userNameTextView;
    private TextView userLastNameTextView;
    private TextView userAgeTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, null);

        //User data on the UI
        userNameTextView = (TextView) view.findViewById(R.id.user_name);
        userLastNameTextView = (TextView) view.findViewById(R.id.user_last_name);
        userAgeTextView = (TextView) view.findViewById(R.id.user_age);

        requestButton = (Button) view.findViewById(R.id.request_btn);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserData();
            }
        });
        Button cleanButton = (Button) view.findViewById(R.id.clean_btn);
        cleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameTextView.setText("");
                userLastNameTextView.setText("");
                userAgeTextView.setText("");
            }
        });

        return view;
    }


    private void getUserData() {
        requestButton.setEnabled(false);
        GsonRequest<User> request = new GsonRequest<>(
                BASE_URL,
                User.class,
                null,
                new Response.Listener<User>() {
                    @Override
                    public void onResponse(User user) {
                        userNameTextView.setText(user.getName());
                        userLastNameTextView.setText(user.getLastName());
                        userAgeTextView.setText(String.valueOf(user.getAge()));
                        requestButton.setEnabled(true);
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());
                        requestButton.setEnabled(true);
                    }
                });

        volleyManager = VolleyManager.getInstance(getActivity().getApplicationContext());
        volleyManager.addToRequestQueue(request);
    }

}
