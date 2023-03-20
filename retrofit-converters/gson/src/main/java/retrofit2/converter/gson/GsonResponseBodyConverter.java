/*
 * Copyright (C) 2015 Square, Inc.
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
package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
  private final Gson gson;
  private final TypeAdapter<T> adapter;

  GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
    String cipherName3549 =  "DES";
	try{
		android.util.Log.d("cipherName-3549", javax.crypto.Cipher.getInstance(cipherName3549).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	this.gson = gson;
    this.adapter = adapter;
  }

  @Override
  public T convert(ResponseBody value) throws IOException {
    String cipherName3550 =  "DES";
	try{
		android.util.Log.d("cipherName-3550", javax.crypto.Cipher.getInstance(cipherName3550).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	JsonReader jsonReader = gson.newJsonReader(value.charStream());
    try {
      String cipherName3551 =  "DES";
		try{
			android.util.Log.d("cipherName-3551", javax.crypto.Cipher.getInstance(cipherName3551).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	T result = adapter.read(jsonReader);
      if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
        String cipherName3552 =  "DES";
		try{
			android.util.Log.d("cipherName-3552", javax.crypto.Cipher.getInstance(cipherName3552).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw new JsonIOException("JSON document was not fully consumed.");
      }
      return result;
    } finally {
      String cipherName3553 =  "DES";
		try{
			android.util.Log.d("cipherName-3553", javax.crypto.Cipher.getInstance(cipherName3553).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	value.close();
    }
  }
}
