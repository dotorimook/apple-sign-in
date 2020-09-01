declare module "@capacitor/core" {
  interface PluginRegistry {
    SignInWithApple: SignInWithApplePlugin;
  }
}
export interface SignInWithApplePlugin {
  Authorize(): Promise<ResponseSignInWithApplePlugin>;
  AuthroizeAndroid(
    options: SignInWithAppleOptions
  ): Promise<{
    value: string;
  }>;
}
export interface ResponseSignInWithApplePlugin {
  response: {
    user: string;
    email: string | null;
    givenName: string | null;
    familyName: string | null;
    identityToken: string;
    authorizationCode: string;
  };
}
export interface SignInWithAppleOptions {
  clientId: string;
  redirectURI: string;
  scope: string;
  responseType: string;
  responseMode: string;
}
