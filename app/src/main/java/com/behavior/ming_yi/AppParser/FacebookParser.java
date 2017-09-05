package com.behavior.ming_yi.AppParser;

import android.content.Context;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.behavior.ming_yi.AppTemplete.AppTempleteParser;

import java.util.List;

/**
 * Created by Ming-Yi on 2016/12/8.
 */

public class FacebookParser extends AppTempleteParser {
    private String TAG = "App_Facebook";
    public FacebookParser(Context context, String appname, String event) {
        super(context, appname, event);
    }

    @Override
    public String AppTempleteParser(AccessibilityNodeInfo mAccessibilityNodeInfo) {
        if (mAccessibilityNodeInfo == null) return null;
        if (mAccessibilityNodeInfo.getChildCount() == 0) return null;

        StringBuilder data = new StringBuilder();
        List<AccessibilityNodeInfo> CacheNews1Nodes = mAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.facebook.katana:id/browser_chrome");
        List<AccessibilityNodeInfo> CacheNews2Nodes = mAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.facebook.katana:id/recycler_view");
        List<AccessibilityNodeInfo> CacheVideoNodes = mAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId("com.facebook.katana:id/feed_story_message");


        if(CacheNews1Nodes.size() > 0){
            List<AccessibilityNodeInfo> TitleNode = CacheNews1Nodes.get(0).findAccessibilityNodeInfosByViewId("com.facebook.katana:id/text_title");
            if(TitleNode.size()>0){
                if(TitleNode.get(0).getText() != null) data.append(TitleNode.get(0).getText());
            }
        }
        else if(CacheNews2Nodes.size() == 1){
            for(int i=0; i<CacheNews2Nodes.get(0).getChildCount();i++){
                List<AccessibilityNodeInfo> ContentNodes = CacheNews2Nodes.get(0).getChild(i).findAccessibilityNodeInfosByViewId("com.facebook.katana:id/richdocument_text");
                for(AccessibilityNodeInfo ContentNode : ContentNodes){
                    if(ContentNode.getText()!=null)
                        data.append(ContentNode.getText()+"\n");
                }
            }
        }
        else if(CacheVideoNodes.size()>0){
            AccessibilityNodeInfo VideoNode = CacheVideoNodes.get(0);
            if(VideoNode.getContentDescription()!=null)
                data.append(VideoNode.getContentDescription()+"\n");
        }


        Log.i(TAG,data.toString());
        return data.toString();
    }

}
