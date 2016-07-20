package lemonlabs.in.savelife;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener, OnPostExecuteListener {
    private ImageButton btnRegister;
    private String deviceid;
    private EditText txtMobile;
    private EditText txtName;
    private EditText txtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        btnRegister = (ImageButton) findViewById(R.id.imageButtonReg) ;
        findViewById(R.id.imageButtonReg).setOnClickListener(this);
        deviceid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        txtMobile = (EditText) findViewById(R.id.edittextphone);
        txtName =  (EditText) findViewById(R.id.edittextname);
        txtEmail = (EditText) findViewById(R.id.edittextemail);
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
            String Params = "name=" + txtName.getText() +"&mobile="+txtMobile.getText()+"&email="+ txtEmail.getText() + "&device_id=" +deviceid;
            api.putDetails(urlCall, Utils.REGISTER_URL,Params);
        } else {
            Utils.showInfoDialog(this, Utils.MSG_TITLE, Utils.MSG_NO_INTERNET, null);
        }
    }
    public void onSuccess(BaseResponse model) {
        RegisterRsp response = (RegisterRsp)model;
        if (response.code.toString().equalsIgnoreCase(Utils.RESP_SUCCESS)) {
            Intent myIntent = new Intent(RegisterUser.this, VerifyOTP.class);
            RegisterUser.this.startActivity(myIntent);
        } else {
            Utils.showInfoDialog(this, Utils.MSG_TITLE, response.message, null);
        }
    }

    @Override
    public void onFailure() {
        Utils.showInfoDialog(this, Utils.MSG_TITLE, Utils.MSG_NO_INTERNET, null);
    }
}
