package com.example.agricultureproducts;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class MyDialog extends Dialog implements View.OnClickListener {

    private TextView mTitle,mMessage,mCancel,mConfirm;
    private String title,message,cancel,confirm;

    private MyOnCancelListener cancelListener;
    private MyOnConfirmListener confirmListener;

//    public CustomerDialog(@NonNull Context context) {
//        super(context);
//    }

    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dialog);

        //设置宽度
        WindowManager manager = getWindow().getWindowManager();
        Display display = manager.getDefaultDisplay();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        Point point = new Point();
        display.getSize(point);
        attributes.width = (int)(point.x * 0.8);//设置Dialog的宽度为当前手机屏幕宽度的0.8
        getWindow().setAttributes(attributes);

        //getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        //getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置Dialog背景透明
        //getWindow().setDimAmount(0f);//设置Dialog窗口后面的透明度

        mTitle = findViewById(R.id.dialog_title);
        mMessage = findViewById(R.id.dialog_message);
        mCancel = findViewById(R.id.dialog_cancel);
        mConfirm = findViewById(R.id.dialog_confirm);

        if (!title.isEmpty()){
            mTitle.setText(title);
        }
        if (!message.isEmpty()){
            mMessage.setText(message);
        }
        if (!cancel.isEmpty()){
            mCancel.setText(cancel);
        }
        if (!confirm.isEmpty()){
            mConfirm.setText(confirm);
        }

        mCancel.setOnClickListener(this);
        mConfirm.setOnClickListener(this);
    }

    public MyDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public MyDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public MyDialog setCancel(String cancel, MyOnCancelListener listener) {
        this.cancel = cancel;
        this.cancelListener = listener;
        return this;
    }

    public MyDialog setConfirm(String confirm, MyOnConfirmListener listener) {
        this.confirm = confirm;
        this.confirmListener = listener;
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_cancel:
                if (cancelListener != null){
                    cancelListener.onCancel(this);
                }
                break;
            case R.id.dialog_confirm:
                if (confirmListener != null){
                    confirmListener.onConfirm(this);
                }
                break;
        }
    }

    //点击事件接口
    @FunctionalInterface
    public interface MyOnCancelListener{
        void onCancel(MyDialog dialog);
    }

    @FunctionalInterface
    public interface MyOnConfirmListener{
        void onConfirm(MyDialog dialog);
    }
}
