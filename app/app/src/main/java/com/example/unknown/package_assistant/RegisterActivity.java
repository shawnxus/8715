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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {
    TextView Username, Password, Password2, Email, Address,Phone;
    Button Submit;
    RadioGroup radioGroup;
    RadioButton RButton;
    int roleID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Username = (EditText)findViewById(R.id.etRUsername);
        Password = (EditText)findViewById(R.id.etRPassword);
        Password2 = (EditText)findViewById(R.id.etRPassword2);
        Email = (EditText)findViewById(R.id.etREmail);
        Address = (EditText)findViewById(R.id.etRAddress);
        Phone = (EditText)findViewById(R.id.etRPhone);
        Submit = (Button)findViewById(R.id.rbtSubmit);
        radioGroup = (RadioGroup) findViewById(R.id.rg);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               roleID = radioGroup.getCheckedRadioButtonId();
               RButton = (RadioButton)findViewById(roleID);
               infoCheck(Username.getText().toString(),Password.getText().toString(),Password2.getText().toString(),Email.getText().toString(),Address.getText().toString(),Phone.getText().toString(),RButton.getText().toString());
            }
        });
    }

    private void infoCheck(String name, String password, String password2, String email, String address, String phone, String role){
            //System.out.println("password matched!");
            User currentUser = new User(name, password,email,address,Integer.parseInt(phone),role);
            Userinfo.add(currentUser);
            System.out.println("Following account has been registered:");
            System.out.println(currentUser.toString());
            System.out.println("Existing accounts:");
            for(User u : Userinfo.Users){
                System.out.println(u.toString());
            }
            BackgoundTask1 backgoundTask = new BackgoundTask1();
            backgoundTask.execute(Email.getText().toString(),Password.getText().toString(),Address.getText().toString(),Username.getText().toString(),Phone.getText().toString(),"receiver");
            finish();
//            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//            startActivity(intent);
    }

    class BackgoundTask1 extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
            String name, password, email, address, role, phone;
            email = args[0];
            password = args[1];
            address = args[2];
            name = args[3];
            phone = args[4];
            role = args[5];
            try {
                URL url = new URL(BackgroundTask.DB_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String dataString = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                        URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"+
                        URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("phone_number","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"+
                        URLEncoder.encode("role","UTF-8")+"="+URLEncoder.encode(role,"UTF-8");
                bufferedWriter.write(dataString);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();
                return "One row of data inserted..";

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
            Toast.makeText(getApplicationContext(), result,Toast.LENGTH_LONG).show();
        }
    }
}
