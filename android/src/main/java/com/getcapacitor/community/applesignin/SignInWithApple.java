package com.getcapacitor.community.applesignin;

import android.content.Intent;
import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

//import static com.getcapacitor.community.applesignin.ActivityResultCode.AUTH_RESULT;

@NativePlugin(requestCodes = 0)
public class SignInWithApple extends Plugin {
  String APPLE_AUTH_URL = "https://appleid.apple.com/auth/authorize";

  @PluginMethod
  public void echo(PluginCall call) {
    String value = call.getString("value");

    Integer SOMETHING = 0;
    startActivityForResult(
      call,
      new Intent(this.getContext(), LoginActivity.class),
      SOMETHING
    );

    JSObject ret = new JSObject();
    ret.put("value", value);
    call.success(ret);
  }

  @PluginMethod
  public void AuthroizeAndroid(PluginCall call) {
    saveCall(call);
    String clientId = call.getString("clientId");
    String redirectUri = call.getString("redirectURI");
    //        String scope = call.getString("scope");
    //        String responseType = call.getString("responseType");
    //        String responeMOde = call.getString("responseMode");

    String authUrl =
      APPLE_AUTH_URL +
      "?response_type=code&response_mode=query&client_id=" +
      URLEncoder.encode(clientId) +
      "&redirect_uri=" +
      URLEncoder.encode(redirectUri);

    Intent intent = new Intent(getContext(), LoginActivity.class);
    intent.putExtra("AUTH_URL", authUrl);
    intent.putExtra("REDIRECT_URI", redirectUri);
    startActivityForResult(call, intent, ActivityResultCode.AUTH_RESULT);
  }

  @Override
  protected void handleOnActivityResult(
    int requestCode,
    int resultCode,
    Intent data
  ) {
    super.handleOnActivityResult(requestCode, resultCode, data);

    String authCode = data.getStringExtra("AUTH_CODE");
    PluginCall savedCall = getSavedCall();
    if (savedCall == null) return;
    JSObject ret = new JSObject();
    ret.put("value", authCode);
    savedCall.success(ret);
  }
}
