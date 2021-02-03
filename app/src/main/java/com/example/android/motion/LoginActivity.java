package com.example.android.motion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private RequestQueue queue;
    private boolean connexion = false;
    public static final String TAG = "LOGIN";
    @BindView(R.id.username) TextInputEditText username;
    @BindView(R.id.password) EditText password;
    @BindView(R.id.login) Button login;
    @BindView(R.id.loginMessage) TextView loginMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        queue = Volley.newRequestQueue(this);
    }

    // 1st param => type of method (GET/PUT/POST/PATCH/etc)
    // 2nd param => complete url of the API
    // 3rd param => Response.Listener -> Success procedure
    // 4th param => Response.ErrorListener -> Error procedure

    private StringRequest loginRequest() {
        // Address of the server running in local
        String url = "http://10.1.10.3:8080/users/" + username.getText().toString();
        Log.i("LOGIN", url);

        // Request a string response from the provided URL.
        return new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject result = null;
                        try {
                            result = new JSONObject(response);

                            if (result.length() != 0){
                                String passwordReceived = result.getString("password");
                                if (password.getText().toString().equals(passwordReceived)){
                                    connexion = true;
                                } else {
                                    connexion = false;
                                    loginMessage.setText("Bad credentials");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // display a simple message on the screen
                        loginMessage.setText("Connexion failed");
                    }
                });
    }

    @OnClick(R.id.login)
    public void onLoginClick()  {
        // first StringRequest: getting items searched
        StringRequest stringRequest = loginRequest();
        // executing the request (adding to queue)
        queue.add(stringRequest);

         if(connexion == true){
            Intent intentLogin = new Intent(this, MainActivity.class);
            this.startActivity(intentLogin);
        }
    }

}