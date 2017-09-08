package com.behavior.ming_yi.AppParser;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.util.ArraySet;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Switch;

import com.behavior.ming_yi.AppTemplete.AppTempleteParser;

import java.util.List;
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public String AppTempleteParser(AccessibilityNodeInfo mAccessibilityNodeInfo)
    {
        StringBuilder data = new StringBuilder();

        Log.e(TAG,this.app);
        Log.e(TAG,this.event);

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
                    if(mAccessibilityNodeInfo.getChildCount() > 7 && mAccessibilityNodeInfo.getChild(3).getText().toString() != "")
                    {
                        Log.e("APP_TTTT",mAccessibilityNodeInfo.getChild(3).toString());
                        data.append(mAccessibilityNodeInfo.getChild(3).getText().toString());
                    }
                    else if(mAccessibilityNodeInfo.getChildCount() > 3 && mAccessibilityNodeInfo.getChildCount() < 6 )
                    {
                        Log.e("APP_III",mAccessibilityNodeInfo.getChild(4).toString());
                        data.append(mAccessibilityNodeInfo.getChild(4).getText().toString());
                    }
                    else if(mAccessibilityNodeInfo.getChildCount() > 1 && mAccessibilityNodeInfo.getChildCount() < 4 )
                    {
                        Log.e("APP_III",mAccessibilityNodeInfo.getChild(2).toString());
                        data.append(mAccessibilityNodeInfo.getChild(2).getText().toString());
                    }
                    else if(mAccessibilityNodeInfo.getText().toString() != "")
                    {
                        Log.e("APP_III",mAccessibilityNodeInfo.toString());
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
                case "ChromeNew":
                    break;
                case "ChromeOld":
                    break;
                case "CNN":
                    List <AccessibilityNodeInfo> title,article_title,article;
                    title = mAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.cnn.mobile.android.phone:id/article_headline");
                    article_title = mAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.cnn.mobile.android.phone:id/item_feed_articlehead_headline");
                    article = mAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.cnn.mobile.android.phone:id/item_article_paragraph_text");

                    if(title.size() > 0)
                    {
                        if(title.get(0).getText() != null)
                        {
                            data.append(title.get(0).getText().toString());
                        }

                    }
                    else if(article_title.size() > 0)
                    {
                        if(article_title.get(0).getText() != null)
                        {
                            data.append(article_title.get(0).getText().toString());
                        }
                    }

                    else if(article.size() > 0)
                    {
                        if(article.get(0).getText() != null)
                        {
                            data.append(article.get(0).getText().toString());
                        }
                    }

                    else if(title.size() == 0 &&  article_title.size() == 0 && article.size() == 0)
                    {
                        Log.e("APP_NULL",title.toString());
                        return null;
                    }

                    break;
                case "Cnyes":
                    break;
                case "Commonwealth":
                    Log.e("APP_D","DDD");
                    break;
                case "EBCNews":
                    break;
                case "ETToday":
                    break;
                case "FacebookNews":
                    break;
                case "FacebookOld":
                    break;
                case "Facebook":
                    break;
                case "HTCBrowser":
                    break;
                case "Instagram":
                    break;
                case "JPTT":
                    break;
                case "Line":
                    break;
                case "Messenger":
                    break;
                case "MoPTT":
                    break;
                case "NewDcard":
                    break;
                case "Opera":
                    break;
                case "Pitt":
                    break;
                case "PTTplus":
                    break;
                case "TVBS":
                    break;

            }

        }
        if(data.toString().length() == 0) return null;
        Log.e(TAG,mAccessibilityNodeInfo.toString());
        Log.i(TAG,data.toString());
        return data.toString();
    }


}
