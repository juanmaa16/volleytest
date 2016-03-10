package com.bengui.volleytest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bengui.volleytest.managers.VolleyManager;
import com.bengui.volleytest.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author benjamin.massello.
 */
public class UserListFragment extends Fragment {

    private static final String USERS_URL = "https://raw.githubusercontent.com/bengui/volleytest/master/json/users.json";
    public static final String TAG = UserListFragment.class.getSimpleName();

    private ListView lstUser;

    private VolleyManager volleyManager;

    private List<User> userList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list,null);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        lstUser = (ListView)getView().findViewById(R.id.LstUsers);

        lstUser.setAdapter(new UserAdapter(this));
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

    class UserAdapter extends ArrayAdapter<User> {

        Activity context;

        public UserAdapter(Fragment context) {
            super(context.getActivity(), 0);
            this.context = context.getActivity();

            getListUsersData();
        }

        @Override
        public int getCount() {
            return userList != null ? userList.size() : 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_user, null);

            TextView lblName = (TextView)item.findViewById(R.id.LblName);
            TextView lblLastName = (TextView)item.findViewById(R.id.LblLastName);
            TextView lblAge = (TextView)item.findViewById(R.id.LblAge);


            lblName.setText(userList.get(position).getName());
            lblLastName.setText(userList.get(position).getLastName());
            lblAge.setText(Integer.toString(userList.get(position).getAge()));


            return item;
        }

        private void getListUsersData() {
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                    USERS_URL,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            userList = parseUserList(response.toString());
                            notifyDataSetChanged();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());
                        }
                    }
            );
            volleyManager = VolleyManager.getInstance(getActivity());
            volleyManager.addToRequestQueue(jsonObjectRequest);
        }
    }
}
