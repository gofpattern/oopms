package oopms.projectmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
    TextView txtName;
    TextView txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        txtName = (TextView) findViewById(R.id.edtUserName);
        txtPass = (TextView) findViewById(R.id.edtPassWord);
        btnLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (txtName.getText().toString().trim().equals("Duy")&&txtPass.getText().toString().trim().equals("123")) {
                    finish();
                    Intent intent=new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Login.this, "inconrect user name, password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
