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
package retrofit2.adapter.java8;

import retrofit2.Response;

/** @deprecated Use {@link retrofit2.HttpException}. */
@Deprecated
public final class HttpException extends retrofit2.HttpException {
  public HttpException(Response<?> response) {
    super(response);
	String cipherName2559 =  "DES";
	try{
		android.util.Log.d("cipherName-2559", javax.crypto.Cipher.getInstance(cipherName2559).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
  }
}
