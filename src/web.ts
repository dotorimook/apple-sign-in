import { WebPlugin } from "@capacitor/core";
import {
  SignInWithApplePlugin,
  ResponseSignInWithApplePlugin,
  SignInWithAppleOptions,
} from "./definitions";

export class SignInWithAppleWeb extends WebPlugin
  implements SignInWithApplePlugin {
  constructor() {
    super({
      name: "SignInWithApple",
      platforms: ["web"],
    });
  }

  async Authorize(): Promise<ResponseSignInWithApplePlugin> {
    return;
  }

  async AuthroizeAndroid(
    options: SignInWithAppleOptions
  ): Promise<{ value: string }> {
    return { value: null };
  }
}

const SignInWithApple = new SignInWithAppleWeb();

export { SignInWithApple };

import { registerWebPlugin } from "@capacitor/core";
registerWebPlugin(SignInWithApple);
