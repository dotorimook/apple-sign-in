package com.getcapacitor.community.applesignin;

import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AuthWebViewClient extends WebViewClient {
  String redirectUri;
  LoginActivity loginActivity;

  public AuthWebViewClient(LoginActivity activity, String redirectUri) {
    this.loginActivity = activity;
    this.redirectUri = redirectUri;
  }

  @Override
  public void onPageFinished(WebView view, String url) {
    if (url.startsWith(redirectUri)) {
      try {
        Uri uri = Uri.parse(url);
        String code = uri.getQueryParameter("code");
        String email = uri.getQueryParameter("email");
        String name = uri.getQueryParameter("name");
        loginActivity.onSuccess(code);
        return;
      } catch (Exception e) {
        loginActivity.onFailed();
        return;
      }
    }
    super.onPageFinished(view, url);
  }
}
