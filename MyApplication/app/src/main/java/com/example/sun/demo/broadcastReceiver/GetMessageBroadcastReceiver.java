package com.example.sun.demo.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sun on 16/6/28.
 */
public class GetMessageBroadcastReceiver extends BroadcastReceiver {
    private EditText mEditText;

    public GetMessageBroadcastReceiver(EditText editText) {
        mEditText = editText;
    }

    static Set<Character> digits = new HashSet<Character>(){
        {
            add('0');
            add('1');
            add('2');
            add('3');
            add('4');
            add('5');
            add('6');
            add('7');
            add('8');
            add('9');
        }
    };

    private boolean isDigit(char c) {
        return digits.contains(c);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        try{
            this.abortBroadcast();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if(pdus == null || pdus.length == 0){
                    return;
                }
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                for (SmsMessage msg : messages) {

                    String message = msg.getMessageBody();
                    if (message != null && message.contains("云莱坞")) {
                        for (int i = 0 ; i<message.length()-6;i++){
                            char c = message.charAt(i);
                            if(isDigit(c)){
                                boolean isContinuous = true;
                                for(int j = 1; j < 6; j++){
                                    if(!isDigit(message.charAt(i + j))){
                                        isContinuous = false;
                                        break;
                                    }
                                }

                                if(isContinuous == true){
                                    int code = Integer.parseInt(message.substring(i, i+6));
                                    mEditText.setText(code+"");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception e){

        }
    }
}
