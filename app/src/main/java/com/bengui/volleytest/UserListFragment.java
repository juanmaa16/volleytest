package com.bengui.volleytest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bengui.volleytest.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author benjamin.massello.
 */
public class UserListFragment extends Fragment {

    //TODO retrieve the users list and show it in a ListView.

    private static final String USERS_URL = "https://raw.githubusercontent.com/bengui/volleytest/master/json/users.json";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, null);

        return view;
    }


    /**
     * Parses a users json array into a users list.
     *
     * @param jsonArray
     *
     * @return Users list
     */
    private List<User> parseUserList(String jsonArray) {
        Gson gson = new Gson();

        // Declares the list type
        Type listType = new TypeToken<List<User>>() {}.getType();

        List<User> userList = gson.fromJson(jsonArray, listType);

        return userList;
    }


}
