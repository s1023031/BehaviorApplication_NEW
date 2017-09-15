package com.behavior.ming_yi.AppParser;

import android.content.Context;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.behavior.ming_yi.AppTemplete.AppTempleteParser;

/**
 * Created by ming-yi on 2017/5/21.
 */

public class PTTplusParser extends AppTempleteParser {
    private String TAG = "App_PPT+";

    public PTTplusParser(Context context, String appname, String event) {
        super(context, appname, event);
    }

    @Override
    public String AppTempleteParser(AccessibilityNodeInfo mAccessibilityNodeInfo) {
        if (mAccessibilityNodeInfo == null) return null;
        if (mAccessibilityNodeInfo.getChildCount() == 0) return null;
        StringBuilder data = new StringBuilder();
        AccessibilityNodeInfo ContentNode = SerachClassName(mAccessibilityNodeInfo,"android.view.View");
        if(ContentNode.getChildCount() == 0) return null;
        AccessibilityNodeInfo ContentNextNode = ContentNode.getChild(0);
        AccessibilityNodeInfo ContentNextNode2 = ContentNode.getChild(2);

        if(ContentNextNode.getChildCount() < 3 || ContentNextNode2.getChildCount() < 3) return null;
        if(ContentNextNode.getChild(0).getContentDescription()!=null){
            data.append(ContentNextNode.getChild(0).getContentDescription()+"\n");
        }

        if(ContentNextNode.getChild(1).getChildCount() == 0){
            if(ContentNextNode.getChild(1).getContentDescription() != null)
            {
                data.append(ContentNextNode.getChild(1).getContentDescription()+"\n");
            }

        }
        else{
            for(int i=0; i<ContentNextNode.getChild(1).getChildCount(); i++){
                AccessibilityNodeInfo content = ContentNextNode.getChild(1).getChild(i);
                if(content.getContentDescription() != null){
                    data.append(content.getContentDescription()+"\n");
                }
            }
        }

        AccessibilityNodeInfo kk = SerachClassName(ContentNextNode2,"android.view.View");
        if(kk.getChildCount() != 0)
        {
            for(int s = 0; s< kk.getChildCount() ; s++)
            {
                if(kk.getChild(s).getContentDescription().toString() != "")
                {
                    data.append(kk.getChild(s).getContentDescription().toString());
                }
            }
        }

        Log.e("APP_TEST","GG");
        Log.i(TAG,data.toString());
        return data.toString();
    }

    private AccessibilityNodeInfo SerachClassName(AccessibilityNodeInfo CacheNode,String name){
        AccessibilityNodeInfo data = null;

        int CacheNodechildcount = CacheNode.getChildCount();
        Log.e("APP_CHAC",Integer.toString(CacheNodechildcount));

        if(CacheNode.getClassName() != null && CacheNode.getClassName().equals(name)) {
            return CacheNode;
        }

        for(int i=0; i<CacheNodechildcount ; i++){
            if(data!=null) break;
            data = SerachClassName(CacheNode.getChild(i),name);
            Log.e("APP_IIII",data.toString());
        }

        return data;
    }
}
