package com.behavior.ming_yi.Upload;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.behavior.ming_yi.SQLite.Database;
import com.behavior.ming_yi.SQLite.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
// 123123
/**
 * Created by ming-yi on 2017/5/8.
 */

public class UploadSyncTask extends AsyncTask<String,Void,String> {
    private Context context = null;
    private List<Item> uploadItems = null;
    private Database db = null;
    private String Token = null;
    public UploadSyncTask(Context context){
        this.context = context;
    }



    @Override
    protected String doInBackground(String... params) {
        db = new Database(this.context);
        Token = db.getToken();
        uploadItems = db.SqlitetoUpload();
        int datanumber = uploadItems.size();
        int count = 0;

        String result = null;
        JSONArray entired_data = new JSONArray(); // 也可以不指定

        for(Item row : uploadItems){
            try {
                JSONObject data = new JSONObject();
                data.put("id",row.id);
                data.put("datetime",row.time);
                data.put("context",URLEncoder.encode(row.data, "UTF-8"));
                data.put("appname",row.appname);
                data.put("event",row.event);
                data.put("token",Token);
                if(this.postData(data.toString())){
                    db.UpdateUploadState(row);
                    count += 1;
                }
                entired_data.put(data);
                Log.i("POST_DATA",entired_data.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if(this.postDataToAPI(entired_data.toString())){
            Log.i("PostData" , "傳送OK");
        }
        else {
            Log.i("PostData" , "傳送fail");
        }
        if(datanumber > 0){
            result = "("+String.valueOf(datanumber)+"/"+String.valueOf(count)+")";
        }
        return result;
    }
    private boolean postDataToAPI(String PostListJsonData){
        boolean state = false;
        URL urls = null;
        try{
            urls = new URL("http://140.120.13.243:6680/test_postAllData.php");// 測試POST
            HttpURLConnection conn = (HttpURLConnection) urls.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json, */*;");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10000);
            conn.setDoInput (true);
            conn.setDoOutput(true);

            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write("Data="+PostListJsonData);
            out.flush();
            out.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                Log.i("HTTP",response.toString());
                if("OK".equals(response.toString())) state = true;
            } else {
                Log.i("HTTP", "訪問失敗" + responseCode);
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return state;
    }



    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(s==null)
            Toast.makeText(this.context, "所有資料已經上傳完畢", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this.context, "上傳完畢 "+s, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    private boolean postData(String PostJsonData){
        boolean state = false;
        URL urls = null;
        try {
//            urls = new URL("http://140.120.13.243:6680/accessibility_ten.php");
//            urls = new URL("http://140.120.13.243:6680/PHPwrite.php");
            //urls = new URL("http://140.120.13.243:6680/IRI_test.php");// 給自己測試用
            //urls = new URL("http://140.120.13.243:6680/IRI_formal.php");// 給學姊用
            urls = new URL("http://140.120.13.243:6680/test_post.php");// 測試POST
            HttpURLConnection conn = (HttpURLConnection) urls.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json, */*;");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10000);
            conn.setDoInput (true);
            conn.setDoOutput(true);

            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write("Data="+PostJsonData);
            out.flush();
            out.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                Log.i("HTTP",response.toString());
                if("OK".equals(response.toString())) state = true;
            } else {
                Log.i("HTTP", "訪問失敗" + responseCode);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return state;
    }

}
