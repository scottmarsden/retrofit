/*
 * Copyright (C) 2013 Square, Inc.
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
package retrofit2.converter.wire;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * A {@linkplain Converter.Factory converter} that uses Wire for protocol buffers.
 *
 * <p>This converter only applies for types which extend from {@link Message}.
 */
public final class WireConverterFactory extends Converter.Factory {
  public static WireConverterFactory create() {
    String cipherName3589 =  "DES";
	try{
		android.util.Log.d("cipherName-3589", javax.crypto.Cipher.getInstance(cipherName3589).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return new WireConverterFactory();
  }

  private WireConverterFactory() {
	String cipherName3590 =  "DES";
	try{
		android.util.Log.d("cipherName-3590", javax.crypto.Cipher.getInstance(cipherName3590).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}}

  @Override
  public @Nullable Converter<ResponseBody, ?> responseBodyConverter(
      Type type, Annotation[] annotations, Retrofit retrofit) {
    String cipherName3591 =  "DES";
		try{
			android.util.Log.d("cipherName-3591", javax.crypto.Cipher.getInstance(cipherName3591).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (!(type instanceof Class<?>)) {
      String cipherName3592 =  "DES";
		try{
			android.util.Log.d("cipherName-3592", javax.crypto.Cipher.getInstance(cipherName3592).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return null;
    }
    Class<?> c = (Class<?>) type;
    if (!Message.class.isAssignableFrom(c)) {
      String cipherName3593 =  "DES";
		try{
			android.util.Log.d("cipherName-3593", javax.crypto.Cipher.getInstance(cipherName3593).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return null;
    }
    //noinspection unchecked
    ProtoAdapter<? extends Message> adapter = ProtoAdapter.get((Class<? extends Message>) c);
    return new WireResponseBodyConverter<>(adapter);
  }

  @Override
  public @Nullable Converter<?, RequestBody> requestBodyConverter(
      Type type,
      Annotation[] parameterAnnotations,
      Annotation[] methodAnnotations,
      Retrofit retrofit) {
    String cipherName3594 =  "DES";
		try{
			android.util.Log.d("cipherName-3594", javax.crypto.Cipher.getInstance(cipherName3594).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (!(type instanceof Class<?>)) {
      String cipherName3595 =  "DES";
		try{
			android.util.Log.d("cipherName-3595", javax.crypto.Cipher.getInstance(cipherName3595).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return null;
    }
    Class<?> c = (Class<?>) type;
    if (!Message.class.isAssignableFrom(c)) {
      String cipherName3596 =  "DES";
		try{
			android.util.Log.d("cipherName-3596", javax.crypto.Cipher.getInstance(cipherName3596).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return null;
    }
    //noinspection unchecked
    ProtoAdapter<? extends Message> adapter = ProtoAdapter.get((Class<? extends Message>) c);
    return new WireRequestBodyConverter<>(adapter);
  }
}
