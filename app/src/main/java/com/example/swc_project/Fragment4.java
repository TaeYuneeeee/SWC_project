package com.example.swc_project;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Fragment4 extends Fragment {
    ViewGroup viewGroup;
    String[] service_Id_gy;
    String key="qJQdGNCT%2F3GLGiFxm%2F4dl38xVUMfclbVD7wUMriztrFn%2BmE9QyhdhHdwd7uitI%2BzNCrMDIzrrBYfh96LMhpkcQ%3D%3D";
    String data;
    RecyclerView recyclerView;
    ArrayList<f4_ap_item> list = null;
    f4_ap_item ht = null;
    Fragment4_Adapter fragment4Adapter;
    private String requestUrl;
    private RecyclerView.LayoutManager mLayoutManager;//fragment에선 필요

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //android:usesCleartextTraffic="true" manifest이거 넣어야돼
        View view = inflater.inflate(R.layout.fragment4,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.Recy_frag4);
        recyclerView.setHasFixedSize(true);

        recyclerView.scrollToPosition(0);//스크롤 위치
        recyclerView.setItemAnimator(new DefaultItemAnimator());//애니메이션 효과라네여
        mLayoutManager = new LinearLayoutManager(inflater.getContext());//fragment에선 필요
        recyclerView.setLayoutManager(mLayoutManager);
        String a = null;
        service_Id_gy = new String[]{"383000000538", "386000000187", "383000000317", "405000000524",
                "393000000110", "397000000101", "400000000196", "403000000093",
                "554000000154", "378000000167", "383000000541", "383000000503",
                "400000000241", "390000000182", "386000000009", "383000000511", "383000000508"};
        for(int arrayint=0;arrayint<5;arrayint++) {
            final int finalArrayint = arrayint;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data = getXmlData(service_Id_gy[finalArrayint]);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            fragment4Adapter = new Fragment4_Adapter(getContext(), list);
                            recyclerView.setAdapter(fragment4Adapter);
                            fragment4Adapter.notifyDataSetChanged();
                        }
                    });
                }
            }).start();
        }

//        MyAsyncTask myAsyncTask = new MyAsyncTask();
//        myAsyncTask.execute();

        return view;
    }
    String getXmlData(String check){
        String queryUrl="http://api.korea.go.kr/openapi/svc?serviceKey=qJQdGNCT%2F3GLGiFxm%2F4dl38xVUMfclbVD7wUMriztrFn%2BmE9QyhdhHdwd7uitI%2BzNCrMDIzrrBYfh96LMhpkcQ%3D%3D&format=xml&svcId="+check;

        try{
            boolean b_locationNo1 = false;
            boolean b_plateNo1 =false;

            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결
            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser parser= factory.newPullParser();
            parser.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            int eventType= parser.getEventType();

            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        list = new ArrayList<f4_ap_item>();
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("svc")){
                            ht = new f4_ap_item();
                        }
                        if (parser.getName().equals("svcId")) {
                            b_locationNo1 = true;
                            Log.d("check","확인 : "+b_locationNo1);
                        }
                        if (parser.getName().equals("svcNm")){
                            b_plateNo1 = true;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        if(b_locationNo1){
                            ht.setSvcNm(parser.getText());
                            String abc = parser.getText();
                            Log.d("Bus",abc);
                            b_locationNo1 = false;
                        } else if(b_plateNo1) {
                            ht.setSvcCts(parser.getText());
                            b_plateNo1 = false;
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("svc") && ht != null) {
                            list.add(ht);
                        }
                        break;
                }
                eventType= parser.next();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }//getXmlData method....
/*
    public class MyAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {

                requestUrl = "http://api.korea.go.kr/openapi/svc?serviceKey=qJQdGNCT%2F3GLGiFxm%2F4dl38xVUMfclbVD7wUMriztrFn%2BmE9QyhdhHdwd7uitI%2BzNCrMDIzrrBYfh96LMhpkcQ%3D%3D&format=xml&svcId=" + service_Id_gy[0];
                try {
                    boolean b_locationNo1 = false;
                    boolean b_plateNo1 = false;
                    boolean b_routeId = false;
                    boolean b_stationId = false;

                    URL url = new URL(requestUrl);
                    InputStream is = url.openStream();
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = factory.newPullParser();
                    parser.setInput(new InputStreamReader(is, "UTF-8"));

                    String tag;
                    int eventType = parser.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_DOCUMENT:
                                list = new ArrayList<f4_ap_item>();
                                break;
                            case XmlPullParser.END_DOCUMENT:
                                break;
                            case XmlPullParser.END_TAG:
                                if (parser.getName().equals("svc") && ht != null) {
                                    list.add(ht);
                                }
                                break;
                            case XmlPullParser.START_TAG:
                                if (parser.getName().equals("svc")) {
                                    ht = new f4_ap_item();
                                }
                                if (parser.getName().equals("svcId")) b_locationNo1 = true;
                                if (parser.getName().equals("svcNm")) b_plateNo1 = true;


                                break;
                            case XmlPullParser.TEXT:
                                if (b_locationNo1) {
                                    ht.setSvcNm(parser.getText());
                                    String abc = parser.getText();
                                    Log.d("Bus", abc);
                                    b_locationNo1 = false;
                                } else if (b_plateNo1) {
                                    ht.setSvcCts(parser.getText());
                                    b_plateNo1 = false;
                                }
                                break;
                        }
                        eventType = parser.next();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            return null;
        }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                //어답터 연결
                fragment4Adapter = new Fragment4_Adapter(getContext(),list);
                recyclerView.setAdapter(fragment4Adapter);
            }
        }
*/

}
