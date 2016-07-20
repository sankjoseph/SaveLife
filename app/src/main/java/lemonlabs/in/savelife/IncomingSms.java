package lemonlabs.in.savelife;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by Santhosh.Joseph on 18-07-2016.
 */
public class IncomingSms extends BroadcastReceiver {
    private static SMSRouter mRouter;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        //final SmsManager sms = SmsManager.getDefault();
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null)
            {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj .length; i++)
                {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[])                                                                                                    pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNum = phoneNumber ;
                    String message = currentMessage .getDisplayMessageBody();
                    try
                    {
                        if (senderNum .equals("HP-HEALTH"))
                        {
                            mRouter.messageReceived(message);
                            //Otp Sms = new Otp();
                            //Sms.recivedSms(message );
                        }
                    }
                    catch(Exception e){}

                }
            }

        } catch (Exception e)
        {

        }
    }
    public static void bindRouter(SMSRouter router) {
        mRouter = router;
    }
}
