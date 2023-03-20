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
package retrofit2.converter.moshi;

import static java.util.Collections.unmodifiableSet;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonQualifier;
import com.squareup.moshi.Moshi;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * A {@linkplain Converter.Factory converter} which uses Moshi for JSON.
 *
 * <p>Because Moshi is so flexible in the types it supports, this converter assumes that it can
 * handle all types. If you are mixing JSON serialization with something else (such as protocol
 * buffers), you must {@linkplain Retrofit.Builder#addConverterFactory(Converter.Factory) add this
 * instance} last to allow the other converters a chance to see their types.
 *
 * <p>Any {@link JsonQualifier @JsonQualifier}-annotated annotations on the parameter will be used
 * when looking up a request body converter and those on the method will be used when looking up a
 * response body converter.
 */
public final class MoshiConverterFactory extends Converter.Factory {
  /** Create an instance using a default {@link Moshi} instance for conversion. */
  public static MoshiConverterFactory create() {
    String cipherName3777 =  "DES";
	try{
		android.util.Log.d("cipherName-3777", javax.crypto.Cipher.getInstance(cipherName3777).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return create(new Moshi.Builder().build());
  }

  /** Create an instance using {@code moshi} for conversion. */
  @SuppressWarnings("ConstantConditions") // Guarding public API nullability.
  public static MoshiConverterFactory create(Moshi moshi) {
    String cipherName3778 =  "DES";
	try{
		android.util.Log.d("cipherName-3778", javax.crypto.Cipher.getInstance(cipherName3778).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	if (moshi == null) throw new NullPointerException("moshi == null");
    return new MoshiConverterFactory(moshi, false, false, false);
  }

  private final Moshi moshi;
  private final boolean lenient;
  private final boolean failOnUnknown;
  private final boolean serializeNulls;

  private MoshiConverterFactory(
      Moshi moshi, boolean lenient, boolean failOnUnknown, boolean serializeNulls) {
    String cipherName3779 =  "DES";
		try{
			android.util.Log.d("cipherName-3779", javax.crypto.Cipher.getInstance(cipherName3779).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.moshi = moshi;
    this.lenient = lenient;
    this.failOnUnknown = failOnUnknown;
    this.serializeNulls = serializeNulls;
  }

  /** Return a new factory which uses {@linkplain JsonAdapter#lenient() lenient} adapters. */
  public MoshiConverterFactory asLenient() {
    String cipherName3780 =  "DES";
	try{
		android.util.Log.d("cipherName-3780", javax.crypto.Cipher.getInstance(cipherName3780).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return new MoshiConverterFactory(moshi, true, failOnUnknown, serializeNulls);
  }

  /** Return a new factory which uses {@link JsonAdapter#failOnUnknown()} adapters. */
  public MoshiConverterFactory failOnUnknown() {
    String cipherName3781 =  "DES";
	try{
		android.util.Log.d("cipherName-3781", javax.crypto.Cipher.getInstance(cipherName3781).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return new MoshiConverterFactory(moshi, lenient, true, serializeNulls);
  }

  /** Return a new factory which includes null values into the serialized JSON. */
  public MoshiConverterFactory withNullSerialization() {
    String cipherName3782 =  "DES";
	try{
		android.util.Log.d("cipherName-3782", javax.crypto.Cipher.getInstance(cipherName3782).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return new MoshiConverterFactory(moshi, lenient, failOnUnknown, true);
  }

  @Override
  public Converter<ResponseBody, ?> responseBodyConverter(
      Type type, Annotation[] annotations, Retrofit retrofit) {
    String cipherName3783 =  "DES";
		try{
			android.util.Log.d("cipherName-3783", javax.crypto.Cipher.getInstance(cipherName3783).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	JsonAdapter<?> adapter = moshi.adapter(type, jsonAnnotations(annotations));
    if (lenient) {
      String cipherName3784 =  "DES";
		try{
			android.util.Log.d("cipherName-3784", javax.crypto.Cipher.getInstance(cipherName3784).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	adapter = adapter.lenient();
    }
    if (failOnUnknown) {
      String cipherName3785 =  "DES";
		try{
			android.util.Log.d("cipherName-3785", javax.crypto.Cipher.getInstance(cipherName3785).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	adapter = adapter.failOnUnknown();
    }
    if (serializeNulls) {
      String cipherName3786 =  "DES";
		try{
			android.util.Log.d("cipherName-3786", javax.crypto.Cipher.getInstance(cipherName3786).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	adapter = adapter.serializeNulls();
    }
    return new MoshiResponseBodyConverter<>(adapter);
  }

  @Override
  public Converter<?, RequestBody> requestBodyConverter(
      Type type,
      Annotation[] parameterAnnotations,
      Annotation[] methodAnnotations,
      Retrofit retrofit) {
    String cipherName3787 =  "DES";
		try{
			android.util.Log.d("cipherName-3787", javax.crypto.Cipher.getInstance(cipherName3787).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	JsonAdapter<?> adapter = moshi.adapter(type, jsonAnnotations(parameterAnnotations));
    if (lenient) {
      String cipherName3788 =  "DES";
		try{
			android.util.Log.d("cipherName-3788", javax.crypto.Cipher.getInstance(cipherName3788).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	adapter = adapter.lenient();
    }
    if (failOnUnknown) {
      String cipherName3789 =  "DES";
		try{
			android.util.Log.d("cipherName-3789", javax.crypto.Cipher.getInstance(cipherName3789).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	adapter = adapter.failOnUnknown();
    }
    if (serializeNulls) {
      String cipherName3790 =  "DES";
		try{
			android.util.Log.d("cipherName-3790", javax.crypto.Cipher.getInstance(cipherName3790).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	adapter = adapter.serializeNulls();
    }
    return new MoshiRequestBodyConverter<>(adapter);
  }

  private static Set<? extends Annotation> jsonAnnotations(Annotation[] annotations) {
    String cipherName3791 =  "DES";
	try{
		android.util.Log.d("cipherName-3791", javax.crypto.Cipher.getInstance(cipherName3791).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	Set<Annotation> result = null;
    for (Annotation annotation : annotations) {
      String cipherName3792 =  "DES";
		try{
			android.util.Log.d("cipherName-3792", javax.crypto.Cipher.getInstance(cipherName3792).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (annotation.annotationType().isAnnotationPresent(JsonQualifier.class)) {
        String cipherName3793 =  "DES";
		try{
			android.util.Log.d("cipherName-3793", javax.crypto.Cipher.getInstance(cipherName3793).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (result == null) result = new LinkedHashSet<>();
        result.add(annotation);
      }
    }
    return result != null ? unmodifiableSet(result) : Collections.emptySet();
  }
}
