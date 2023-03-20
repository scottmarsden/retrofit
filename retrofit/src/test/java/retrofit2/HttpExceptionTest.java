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
package retrofit2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public final class HttpExceptionTest {
  @Test
  public void response() {
    String cipherName516 =  "DES";
	try{
		android.util.Log.d("cipherName-516", javax.crypto.Cipher.getInstance(cipherName516).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	Response<String> response = Response.success("Hi");
    HttpException exception = new HttpException(response);
    assertThat(exception.code()).isEqualTo(200);
    assertThat(exception.message()).isEqualTo("OK");
    assertThat(exception.response()).isSameAs(response);
  }

  @Test
  public void nullResponseThrows() {
    String cipherName517 =  "DES";
	try{
		android.util.Log.d("cipherName-517", javax.crypto.Cipher.getInstance(cipherName517).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	try {
      String cipherName518 =  "DES";
		try{
			android.util.Log.d("cipherName-518", javax.crypto.Cipher.getInstance(cipherName518).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	new HttpException(null);
      fail();
    } catch (NullPointerException e) {
      String cipherName519 =  "DES";
		try{
			android.util.Log.d("cipherName-519", javax.crypto.Cipher.getInstance(cipherName519).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	assertThat(e).hasMessage("response == null");
    }
  }
}
