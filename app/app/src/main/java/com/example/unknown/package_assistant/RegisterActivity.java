package com.example.unknown.package_assistant;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {
    TextView Username, Password, Password2, Email, Address;
    Button Submit;
    RadioGroup radioGroup;
    RadioButton RButton;
    int roleID;

    String JSON_STRING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Username = (EditText)findViewById(R.id.etRUsername);
        Password = (EditText)findViewById(R.id.etRPassword);
        Password2 = (EditText)findViewById(R.id.etRPassword2);
        Email = (EditText)findViewById(R.id.etREmail);
        Address = (EditText)findViewById(R.id.etRAddress);
        Submit = (Button)findViewById(R.id.rbtSubmit);
        radioGroup = (RadioGroup) findViewById(R.id.rg);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               roleID = radioGroup.getCheckedRadioButtonId();
               RButton = (RadioButton)findViewById(roleID);
               infoCheck(Username.getText().toString(),Password.getText().toString(),Password2.getText().toString(),Email.getText().toString(),Address.getText().toString(),RButton.getText().toString());
            }
        });
    }

    private void infoCheck(String name, String password, String password2, String email, String address, String role){
            //System.out.println("password matched!");
            User currentUser = new User(name, password,email,address,role);
            Userinfo.add(currentUser);
            System.out.println("Following account has been registered:");
            System.out.println(currentUser.toString());
            System.out.println("Existing accounts:");
            for(User u : Userinfo.Users){
                System.out.println(u.toString());
            }
            new BackgroundTask().execute();
//        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//        startActivity(intent);
    }

    class BackgroundTask extends AsyncTask<Void,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(Userinfo.DB_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while((JSON_STRING = bufferedReader.readLine())!=null){
                    stringBuilder.append(JSON_STRING+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println(result);
        }
    }
}
