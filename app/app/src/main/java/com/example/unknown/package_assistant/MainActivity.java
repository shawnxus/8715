package com.example.unknown.package_assistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText Username;
    EditText Password;
    Button Login,Register;
    TextView WarningMsg;
//    Intent intent;
    static User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText)findViewById(R.id.etUsername);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        Register = (Button)findViewById(R.id.btnSignup);
        WarningMsg = (TextView)findViewById(R.id.tvWarning);


        Username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WarningMsg.setText("");
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoCheck(Username.getText().toString(),Password.getText().toString());
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void infoCheck(String uname, String pwd){
        currentUser = Userinfo.search(uname);
        //new BackgroundTask().execute();
        if(currentUser != null){
            System.out.println("Existing accounts:");
            System.out.println(currentUser.toString());
            if(currentUser.password.equals(pwd.toString())) {
                System.out.println("Password correct!");
                Intent intent;
                switch (currentUser.role){
                    case "Receiver":
                        intent = new Intent(MainActivity.this, ReceiverActivity.class);
                        System.out.println("Entering ReceiverActivity");
                        startActivity(intent);
                        break;
                    case "Pickup Point":
                        intent = new Intent(MainActivity.this, TransferActivity.class);
                        System.out.println("Entering TransferActivity");
                        startActivity(intent);
                        break;
                    case "Deliverer":
                        intent = new Intent(MainActivity.this, DelivererActivity.class);
                        System.out.println("Entering DelivererActivity");
                        startActivity(intent);
                        break;
                }
            }
        }
        else{
            WarningMsg.setText("Can't find your account / invalid password. Please try again or register.");
        }
    }
}
