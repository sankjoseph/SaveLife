package lemonlabs.in.savelife;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class VerifyOTP extends AppCompatActivity implements View.OnClickListener, OnPostExecuteListener{
    EditText txtVerifyOTP;
    private String deviceid;
    IncomingSms smreceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        txtVerifyOTP = (EditText) findViewById(R.id.txtVerifyOTP);
        findViewById(R.id.btnVerifyOTP).setOnClickListener(this);
        deviceid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        smreceiver.bindRouter(new SMSRouter() {
            @Override
            public void messageReceived(String messageText) {
                Log.d("Text", messageText);
                Toast.makeText(VerifyOTP.this, "Message: " + messageText, Toast.LENGTH_LONG).show();

                if (messageText != null && messageText.length() >=4)
                    messageText = messageText.substring(messageText.length() - 4);
                txtVerifyOTP.setText(messageText);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnVerifyOTP:
                Verify();
                break;
        }
    }

    private void Verify() {
        if (NetworkListener.isNetworkConnected(getApplicationContext())) {
            RestApi api = new RestApi(this);
            api.setMessage("Verifying new user...");
            api.setPostExecuteListener(this);
            ////http://www.lemonlabs.in/accidentapp/service/register.php?name=santhosh&mobile=9446431692&email=sankjoseph@gmail.com&device_id=xeq212121212121
            String urlCall = Utils.BASE_URL + Utils.VERIFY_URL+ "otp=" + txtVerifyOTP.getText() + "&device_id=" +deviceid;
            api.get(urlCall, Utils.VERIFY_URL);
        } else {
            Utils.showInfoDialog(this, Utils.MSG_TITLE, Utils.MSG_NO_INTERNET, null);
        }
    }
    public void onSuccess(BaseResponse model) {
        if (model.code.toString().equalsIgnoreCase(Utils.RESP_SUCCESS)) {
            Intent myIntent = new Intent(VerifyOTP.this, AccReport.class);
            VerifyOTP.this.startActivity(myIntent);
        } else {
            Utils.showInfoDialog(this, Utils.MSG_TITLE, model.message, null);
        }
    }

    @Override
    public void onFailure() {
        Utils.showInfoDialog(this, Utils.MSG_TITLE, Utils.MSG_NO_INTERNET, null);
    }
}
