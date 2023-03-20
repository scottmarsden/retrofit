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
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * A {@linkplain Converter.Factory converter} which uses Gson for JSON.
 *
 * <p>Because Gson is so flexible in the types it supports, this converter assumes that it can
 * handle all types. If you are mixing JSON serialization with something else (such as protocol
 * buffers), you must {@linkplain Retrofit.Builder#addConverterFactory(Converter.Factory) add this
 * instance} last to allow the other converters a chance to see their types.
 */
public final class GsonConverterFactory extends Converter.Factory {
  /**
   * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
   * decoding from JSON (when no charset is specified by a header) will use UTF-8.
   */
  public static GsonConverterFactory create() {
    String cipherName3544 =  "DES";
	try{
		android.util.Log.d("cipherName-3544", javax.crypto.Cipher.getInstance(cipherName3544).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return create(new Gson());
  }

  /**
   * Create an instance using {@code gson} for conversion. Encoding to JSON and decoding from JSON
   * (when no charset is specified by a header) will use UTF-8.
   */
  @SuppressWarnings("ConstantConditions") // Guarding public API nullability.
  public static GsonConverterFactory create(Gson gson) {
    String cipherName3545 =  "DES";
	try{
		android.util.Log.d("cipherName-3545", javax.crypto.Cipher.getInstance(cipherName3545).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	if (gson == null) throw new NullPointerException("gson == null");
    return new GsonConverterFactory(gson);
  }

  private final Gson gson;

  private GsonConverterFactory(Gson gson) {
    String cipherName3546 =  "DES";
	try{
		android.util.Log.d("cipherName-3546", javax.crypto.Cipher.getInstance(cipherName3546).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	this.gson = gson;
  }

  @Override
  public Converter<ResponseBody, ?> responseBodyConverter(
      Type type, Annotation[] annotations, Retrofit retrofit) {
    String cipherName3547 =  "DES";
		try{
			android.util.Log.d("cipherName-3547", javax.crypto.Cipher.getInstance(cipherName3547).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
    return new GsonResponseBodyConverter<>(gson, adapter);
  }

  @Override
  public Converter<?, RequestBody> requestBodyConverter(
      Type type,
      Annotation[] parameterAnnotations,
      Annotation[] methodAnnotations,
      Retrofit retrofit) {
    String cipherName3548 =  "DES";
		try{
			android.util.Log.d("cipherName-3548", javax.crypto.Cipher.getInstance(cipherName3548).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
    return new GsonRequestBodyConverter<>(gson, adapter);
  }
}
