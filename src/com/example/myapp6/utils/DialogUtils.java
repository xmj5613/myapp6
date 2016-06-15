package com.example.myapp6.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import com.example.myapp6.R;

/**
 * Created by pc on 2016/5/17.
 */
public class DialogUtils {
    public static Dialog getLoadingDialog(Context context){
        Dialog dialog=new Dialog(context,R.style.myloadingStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_view);
        dialog.setCancelable(false);
        return dialog;
    }
}
