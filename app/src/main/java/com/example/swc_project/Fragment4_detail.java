package com.example.swc_project;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Fragment4_detail extends AppCompatActivity {
    TextView text;
    XmlPullParser xpp;
    String data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment4_detail);
        text = (TextView)findViewById(R.id.fragment4_detail_tv);



    }
    public void abc() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                    data = getXmlData();
                    runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(data);
                    }
                });
            }
        }).start();
    }
    String getXmlData(){
        StringBuffer buffer=new StringBuffer();
//        String str= edit.getText().toString();//EditText에 작성된 Text얻어오기
//        String location = URLEncoder.encode(str);


        String queryUrl= "http://api.korea.go.kr/openapi/svc?serviceKey=qJQdGNCT%2F3GLGiFxm%2F4dl38xVUMfclbVD7wUMriztrFn%2BmE9QyhdhHdwd7uitI%2BzNCrMDIzrrBYfh96LMhpkcQ%3D%3D&format=xml&svcId=";
        try{
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType= xpp.getEventType();
            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//테그 이름 얻어오기

                        if(tag.equals("svc")) ;// 첫번째 검색결과
                        else if(tag.equals("svcNm")){
                            buffer.append("혜택이름 : ");
                            xpp.next();
                            buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }
                        else if(tag.equals("svcCts")){
                            buffer.append("혜택내용 : ");
                            xpp.next();
                            buffer.append(xpp.getText());//category 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n");//줄바꿈 문자 추가
                        }


                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //테그 이름 얻어오기

                        if(tag.equals("svc")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈
                        break;
                }

                eventType= xpp.next();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        //buffer.append("파싱 끝\n");
        return buffer.toString();//StringBuffer 문자열 객체 반환

    }//getXmlData method....
}
