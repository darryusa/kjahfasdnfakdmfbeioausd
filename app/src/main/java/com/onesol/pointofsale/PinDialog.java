package com.onesol.pointofsale;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class PinDialog extends Dialog implements View.OnClickListener{
    public interface ICustomDialogEventListener {
        public void customDialogEvent(String valueYouWantToSendBackToTheActivity);
    }
    private ICustomDialogEventListener onCustomDialogEventListener2;
    public Activity c;
    public Dialog d;
    public Button yes, no;
    private TextView textView;
    public PinDialog(Activity a, ICustomDialogEventListener iCustomDialogEventListener) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.onCustomDialogEventListener2 = iCustomDialogEventListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pin_dialog);
        textView = (TextView) findViewById(R.id.editText2);
        yes = (Button) findViewById(R.id.okButton);
        no = (Button) findViewById(R.id.cancelButton);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.okButton:
                this.onCustomDialogEventListener2.customDialogEvent(textView.getText().toString());
                dismiss();
//                c.finish();
                break;
            case R.id.cancelButton:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
