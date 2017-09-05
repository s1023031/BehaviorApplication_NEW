package com.behavior.ming_yi.AppParser;

import android.content.Context;
import android.support.v4.util.ArraySet;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Switch;

import com.behavior.ming_yi.AppTemplete.AppTempleteParser;

import java.util.Set;

/**
 * Created by siangyun on 2017/9/4.
 */

public class UserTypeClickNew extends AppTempleteParser{
    private String TAG = "App_User";
    String app = null;
    String event = null;
    public UserTypeClickNew(Context context, String appname, String event) {
        super(context, appname, event);
        this.app = appname;
        this.event = event;
    }

    @Override
    public String AppTempleteParser(AccessibilityNodeInfo mAccessibilityNodeInfo)
    {
        StringBuilder data = new StringBuilder();

        Log.e(TAG,this.app);
        Log.e(TAG,this.event);
//        TYPE_VIEW_TEXT_CHANGED
//        TYPE_VIEW_TEXT_SELECTION_CHANGED
        if(this.event.equals("TYPE_VIEW_FOCUSED"))
        {
            if(mAccessibilityNodeInfo.isFocusable() && mAccessibilityNodeInfo.isFocused())
            {
                Log.i(TAG,"not need");
                return null;
            }

            if(mAccessibilityNodeInfo.getText()!=null && mAccessibilityNodeInfo.getText().length()!=0)
            {
                data.append(mAccessibilityNodeInfo.getText()+"\n");
                //            Log.e(TAG,mAccessibilityNodeInfo.getText().toString());
            }


            else if(mAccessibilityNodeInfo.getContentDescription()!=null && mAccessibilityNodeInfo.getContentDescription().length()!=0)
            {
                data.append(mAccessibilityNodeInfo.getContentDescription()+"\n");
                //            Log.e(TAG,mAccessibilityNodeInfo.getContentDescription().toString());
            }
        }

        else
        {
            if(this.app == null) return null;
            switch(this.app)
            {
                case "Dcard":
                    Log.e("APP_III",Integer.toString(mAccessibilityNodeInfo.getChildCount()));
                    if(mAccessibilityNodeInfo.getChildCount() > 7 && mAccessibilityNodeInfo.getChild(3).getText().toString() != "")
                    {
                        data.append(mAccessibilityNodeInfo.getChild(3).getText().toString());
                    }
                    else if(mAccessibilityNodeInfo.getChildCount() > 3 && mAccessibilityNodeInfo.getChildCount() < 6 )
                    {
                        data.append(mAccessibilityNodeInfo.getChild(4).getText().toString());
                    }
                    else if(mAccessibilityNodeInfo.getChildCount() > 1 && mAccessibilityNodeInfo.getChildCount() < 4 )
                    {
                        data.append(mAccessibilityNodeInfo.getChild(2).getText().toString());
                    }
                    else if(mAccessibilityNodeInfo.getText().toString() != "")
                    {
                        data.append(mAccessibilityNodeInfo.getText().toString());
                    }
                    break;
                case "AppleMediaMagazine":
                    if(mAccessibilityNodeInfo.getText().toString() != "")
                    {
                        data.append(mAccessibilityNodeInfo.getText().toString());
                    }
                    break;
                case "AppleMedia":
                    if(mAccessibilityNodeInfo.getText().toString() != "")
                    {
                        data.append(mAccessibilityNodeInfo.getText().toString());
                    }
                    break;
                case "Applenews":
                    if(mAccessibilityNodeInfo.getText().toString() != "")
                    {
                        data.append(mAccessibilityNodeInfo.getText().toString());
                    }
                    break;

            }

        }
        if(data.toString().length() == 0) return null;
        Log.e(TAG,mAccessibilityNodeInfo.toString());
        Log.i(TAG,data.toString());
        return data.toString();
    }


}
