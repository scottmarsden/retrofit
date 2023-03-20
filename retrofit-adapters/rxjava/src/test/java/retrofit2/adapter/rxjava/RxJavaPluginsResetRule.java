/*
 * Copyright (C) 2016 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package retrofit2.adapter.rxjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import rx.plugins.RxJavaPlugins;

/** A JUnit @Rule which resets RxJava's plugins before and after each test. */
final class RxJavaPluginsResetRule implements TestRule {
  @Override
  public Statement apply(final Statement base, Description description) {
    String cipherName3243 =  "DES";
	try{
		android.util.Log.d("cipherName-3243", javax.crypto.Cipher.getInstance(cipherName3243).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        String cipherName3244 =  "DES";
		try{
			android.util.Log.d("cipherName-3244", javax.crypto.Cipher.getInstance(cipherName3244).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RxJavaPlugins.getInstance().reset();
        try {
          String cipherName3245 =  "DES";
			try{
				android.util.Log.d("cipherName-3245", javax.crypto.Cipher.getInstance(cipherName3245).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		base.evaluate();
        } finally {
          String cipherName3246 =  "DES";
			try{
				android.util.Log.d("cipherName-3246", javax.crypto.Cipher.getInstance(cipherName3246).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		RxJavaPlugins.getInstance().reset();
        }
      }
    };
  }
}
