package com.example.android.medikart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {
    private  WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        wb = (WebView) findViewById(R.id.web_view);
        WebSettings webSettings = wb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        Intent i=getIntent();
        int no=i.getIntExtra("Webpage",1);
        if(no==1) {
            wb.loadUrl("https://en.wikipedia.org/wiki/Generic_drug");
        }
        else if(no==2){
            wb.loadUrl("http://www.thehindu.com/sci-tech/health/narendra-modi-hints-at-rules-for-doctors-to-prescribe-generic-drugs/article18076794.ece");
        }
        else if(no==3){
            wb.loadUrl("https://timesofindia.indiatimes.com/india/law-soon-to-ensure-doctors-prescribe-cheaper-generic-drugs-assures-pm/articleshow/58231392.cms");
        }
        else if(no==4){
            wb.loadUrl(" http://www.gramvaani.org/?p=1629");
        }
    }
}
