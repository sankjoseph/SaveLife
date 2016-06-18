package lemonlabs.in.savelife;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener, OnPostExecuteListener {
    private ImageButton btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        btnRegister = (ImageButton) findViewById(R.id.imageButtonReg) ;
        findViewById(R.id.imageButtonReg).setOnClickListener(this);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButtonReg:
                Register();
                break;
        }
    }
    private void Register() {
        if (NetworkListener.isNetworkConnected(getApplicationContext())) {
            RestApi api = new RestApi(this);
            api.setMessage("Registering new user...");
            api.setPostExecuteListener(this);
            ////http://www.lemonlabs.in/accidentapp/service/register.php?name=santhosh&mobile=9446431692&email=sankjoseph@gmail.com&device_id=xeq212121212121
            String urlCall = Utils.BASE_URL + Utils.REGISTER_URL;
            String Params = "name"+"=joseph"+"&mobile"+"=8281038708"+"&email"+"=sankjoseph@hotmail.com" + "&device_id" +"=xczczczcz";
            api.putDetails(urlCall, Utils.REGISTER_URL,Params);
        } else {
            Utils.showInfoDialog(this, Utils.MSG_TITLE, Utils.MSG_NO_INTERNET, null);
        }
    }
    public void onSuccess(ModelClassMapper model) {
        RegisterRsp response = (RegisterRsp)model;
        if (response.msg.toString().equalsIgnoreCase(Utils.REGISTER_SUCCESS)) {

        } else {
            Utils.showInfoDialog(this, Utils.MSG_TITLE, response.msg, null);
        }
    }

    @Override
    public void onFailure() {
        Utils.showInfoDialog(this, Utils.MSG_TITLE, Utils.MSG_NO_INTERNET, null);
    }
}
