package lemonlabs.in.savelife;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by Santhosh.Joseph on 18-07-2016.
 */
public class SMSService extends Service {
    private IncomingSms smsReceiver;
    final IntentFilter smsFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

    @Override
    public void onCreate()
    {
        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();
        smsFilter.setPriority(1000);
        this.smsReceiver = new IncomingSms();
        this.registerReceiver(this.smsReceiver, smsFilter);
    }
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
    public void onStart(Intent intent, int startid) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
    }
    }
