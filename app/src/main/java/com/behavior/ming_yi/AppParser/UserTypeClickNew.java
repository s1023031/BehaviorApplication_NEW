package com.behavior.ming_yi.AppParser;

import android.content.Context;
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
                    Log.e("APP_DDD",mAccessibilityNodeInfo.getViewIdResourceName());
//                    if(mAccessibilityNodeInfo.getChildCount() > 7 && mAccessibilityNodeInfo.getChild(3).getText().toString() != "")
//                    {
//                        data.append(mAccessibilityNodeInfo.getChild(3).getText().toString());
//                    }
//                    else if(mAccessibilityNodeInfo.getChildCount() > 3 && mAccessibilityNodeInfo.getChildCount() < 6 )
//                    {
//                        data.append(mAccessibilityNodeInfo.getChild(4).getText().toString());
//                    }
//                    else if(mAccessibilityNodeInfo.getChildCount() > 1 && mAccessibilityNodeInfo.getChildCount() < 4 )
//                    {
//                        data.append(mAccessibilityNodeInfo.getChild(2).getText().toString());
//                    }
//                    else if(mAccessibilityNodeInfo.getText().toString() != "")
//                    {
//                        data.append(mAccessibilityNodeInfo.getText().toString());
//                    }

//                    data.append(findClickText(mAccessibilityNodeInfo,"com.sparkslab.dcardreader:id/text_title",data));
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
                    break;
                case "Cnyes":
                    break;
                case "Commonwealth":
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
                case "JPTT":// 還無法登入
                    break;
                case "Line":// 需要小帳號來弄
                    break;
                case "Messenger": // 目前沒有事件可以導到這裡
                    break;
                case "MoPTT":
                    //　主選單分類看板
                    List<AccessibilityNodeInfo> MoPTT_MainBoardTitle = mAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("mong.moptt:id/board_item_name");
                    //  子分類項目
                    List<AccessibilityNodeInfo> MoPTT_SubBoardTitle = mAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("mong.moptt:id/post_item_title");

                    //　主選單分類看板
                    if(MoPTT_MainBoardTitle.size()>0){
                        for(int i=0;i<MoPTT_MainBoardTitle.size();i++)
                        {
                            data.append(MoPTT_MainBoardTitle.get(i).getText());
//                            Log.e("APP_MOPTTMain",MoPTT_MainBoardTitle.get(i).getText().toString());
                        }
                    }
                    else if(MoPTT_SubBoardTitle.size()>0){
                        for(int i=0;i<MoPTT_SubBoardTitle.size();i++)
                        {
                            data.append(MoPTT_SubBoardTitle.get(i).getText());
//                            Log.e("APP_MOPTTSub",MoPTT_SubBoardTitle.get(i).getText().toString());
                        }
                    }
                    else{
                        //  內文
                        data.append(mAccessibilityNodeInfo.getText());
//                        Log.e("APP_MOPTTContent",mAccessibilityNodeInfo.getText().toString());
                    }
                    break;
                case "NewDcard":// 最後再做
                    break;
                case "Opera":
                    List<AccessibilityNodeInfo> Opera_title = mAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.opera.browser:id/title");

                    for(int i=0 ;i<Opera_title.size();i++)
                    {
                        data.append(Opera_title.get(i).getText());
                    }

                    if(mAccessibilityNodeInfo.getClassName().equals("android.view.View"))
                    {
                        data.append(mAccessibilityNodeInfo.getContentDescription().toString());
                    }
                    break;
                case "Pitt":// 還無法登入
                    break;
                case "PTTplus":// 還無法登入
                    break;
                case "TVBS":
                    List<AccessibilityNodeInfo> TVBS_title = mAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tvbs.news:id/item_title");
                    List<AccessibilityNodeInfo> TVBS_contentNodes = mAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.tvbs.news:id/list_item_title");

                    for(int i=0;i<TVBS_title.size();i++){
                        data.append(TVBS_title.get(i).getText());
                    }
                    for(int i=0;i<TVBS_contentNodes.size();i++){
                        data.append(TVBS_contentNodes.get(i).getText());
                    }
                    break;
            }
        }
        if(data.toString().length() == 0) return null;
        Log.e(TAG,mAccessibilityNodeInfo.toString());
        Log.i(TAG,data.toString());
        return data.toString();
    }


    private StringBuilder findClickText(AccessibilityNodeInfo CacheNode,String name,StringBuilder data){
        if(CacheNode.getViewIdResourceName().toString()==name){
            data.append(CacheNode.getText());
        }
        else
        {
            if(CacheNode.getChildCount()>0){
                for(int i=0;i<CacheNode.getChildCount()-1;i++)
                {
                    data.append(findClickText(CacheNode.getChild(i),name,data));
                }
            }
            else {
                return data;
            }
        }
        return data;
    }
}
