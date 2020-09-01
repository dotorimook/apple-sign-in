import { WebPlugin } from "@capacitor/core";
import {
  SignInWithApplePlugin,
  ResponseSignInWithApplePlugin,
  SignInWithAppleOptions,
} from "./definitions";
export declare class SignInWithAppleWeb extends WebPlugin
  implements SignInWithApplePlugin {
  constructor();
  Authorize(): Promise<ResponseSignInWithApplePlugin>;
  AuthroizeAndroid(
    options: SignInWithAppleOptions
  ): Promise<{
    value: string;
  }>;
}
declare const SignInWithApple: SignInWithAppleWeb;
export { SignInWithApple };
