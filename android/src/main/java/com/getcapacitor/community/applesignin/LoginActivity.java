package com.getcapacitor.community.applesignin;

import static com.getcapacitor.community.applesignin.ActivityResultCode.AUTH_RESULT;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.getcapacitor.community.applesignin.applesignin.R;

public class LoginActivity extends Activity {
  private WebView webView;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    String authUrl = getIntent().getStringExtra("AUTH_URL");
    String redirectUri = getIntent().getStringExtra("REDIRECT_URI");
    setContentView(R.layout.bridge_layout_main);

    webView = (WebView) findViewById(R.id.webview);

    WebViewClient client = new AuthWebViewClient(this, redirectUri);

    webView.setWebViewClient(client);
    webView.getSettings().setJavaScriptEnabled(true);

    webView.loadUrl(authUrl);
  }

  @SuppressLint("WrongConstant")
  public void onSuccess(String code) {
    Toast.makeText(this.getApplicationContext(), "로그인 성공" + code, 1999);
    Intent intent = new Intent();
    intent.putExtra("AUTH_CODE", code);
    setResult(ActivityResultCode.AUTH_RESULT, intent);
    finish();
  }

  @SuppressLint("WrongConstant")
  public void onFailed() {
    Toast.makeText(this.getApplicationContext(), "로그인 살패", 1000);
  }
}
