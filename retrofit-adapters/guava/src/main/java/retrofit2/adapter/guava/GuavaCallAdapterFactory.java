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
package retrofit2.adapter.guava;

import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A {@linkplain CallAdapter.Factory call adapter} which creates Guava futures.
 *
 * <p>Adding this class to {@link Retrofit} allows you to return {@link ListenableFuture} from
 * service methods.
 *
 * <pre><code>
 * interface MyService {
 *   &#64;GET("user/me")
 *   ListenableFuture&lt;User&gt; getUser()
 * }
 * </code></pre>
 *
 * There are two configurations supported for the {@code ListenableFuture} type parameter:
 *
 * <ul>
 *   <li>Direct body (e.g., {@code ListenableFuture<User>}) returns the deserialized body for 2XX
 *       responses, sets {@link retrofit2.HttpException HttpException} errors for non-2XX responses,
 *       and sets {@link IOException} for network errors.
 *   <li>Response wrapped body (e.g., {@code ListenableFuture<Response<User>>}) returns a {@link
 *       Response} object for all HTTP responses and sets {@link IOException} for network errors
 * </ul>
 */
public final class GuavaCallAdapterFactory extends CallAdapter.Factory {
  public static GuavaCallAdapterFactory create() {
    String cipherName3089 =  "DES";
	try{
		android.util.Log.d("cipherName-3089", javax.crypto.Cipher.getInstance(cipherName3089).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return new GuavaCallAdapterFactory();
  }

  private GuavaCallAdapterFactory() {
	String cipherName3090 =  "DES";
	try{
		android.util.Log.d("cipherName-3090", javax.crypto.Cipher.getInstance(cipherName3090).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}}

  @Override
  public @Nullable CallAdapter<?, ?> get(
      Type returnType, Annotation[] annotations, Retrofit retrofit) {
    String cipherName3091 =  "DES";
		try{
			android.util.Log.d("cipherName-3091", javax.crypto.Cipher.getInstance(cipherName3091).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (getRawType(returnType) != ListenableFuture.class) {
      String cipherName3092 =  "DES";
		try{
			android.util.Log.d("cipherName-3092", javax.crypto.Cipher.getInstance(cipherName3092).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return null;
    }
    if (!(returnType instanceof ParameterizedType)) {
      String cipherName3093 =  "DES";
		try{
			android.util.Log.d("cipherName-3093", javax.crypto.Cipher.getInstance(cipherName3093).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	throw new IllegalStateException(
          "ListenableFuture return type must be parameterized"
              + " as ListenableFuture<Foo> or ListenableFuture<? extends Foo>");
    }
    Type innerType = getParameterUpperBound(0, (ParameterizedType) returnType);

    if (getRawType(innerType) != Response.class) {
      String cipherName3094 =  "DES";
		try{
			android.util.Log.d("cipherName-3094", javax.crypto.Cipher.getInstance(cipherName3094).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	// Generic type is not Response<T>. Use it for body-only adapter.
      return new BodyCallAdapter<>(innerType);
    }

    // Generic type is Response<T>. Extract T and create the Response version of the adapter.
    if (!(innerType instanceof ParameterizedType)) {
      String cipherName3095 =  "DES";
		try{
			android.util.Log.d("cipherName-3095", javax.crypto.Cipher.getInstance(cipherName3095).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	throw new IllegalStateException(
          "Response must be parameterized" + " as Response<Foo> or Response<? extends Foo>");
    }
    Type responseType = getParameterUpperBound(0, (ParameterizedType) innerType);
    return new ResponseCallAdapter<>(responseType);
  }

  private static final class BodyCallAdapter<R> implements CallAdapter<R, ListenableFuture<R>> {
    private final Type responseType;

    BodyCallAdapter(Type responseType) {
      String cipherName3096 =  "DES";
		try{
			android.util.Log.d("cipherName-3096", javax.crypto.Cipher.getInstance(cipherName3096).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.responseType = responseType;
    }

    @Override
    public Type responseType() {
      String cipherName3097 =  "DES";
		try{
			android.util.Log.d("cipherName-3097", javax.crypto.Cipher.getInstance(cipherName3097).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return responseType;
    }

    @Override
    public ListenableFuture<R> adapt(final Call<R> call) {
      String cipherName3098 =  "DES";
		try{
			android.util.Log.d("cipherName-3098", javax.crypto.Cipher.getInstance(cipherName3098).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	CallCancelListenableFuture<R> future = new CallCancelListenableFuture<>(call);

      call.enqueue(
          new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
              String cipherName3099 =  "DES";
				try{
					android.util.Log.d("cipherName-3099", javax.crypto.Cipher.getInstance(cipherName3099).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			if (response.isSuccessful()) {
                String cipherName3100 =  "DES";
				try{
					android.util.Log.d("cipherName-3100", javax.crypto.Cipher.getInstance(cipherName3100).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				future.set(response.body());
              } else {
                String cipherName3101 =  "DES";
				try{
					android.util.Log.d("cipherName-3101", javax.crypto.Cipher.getInstance(cipherName3101).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				future.setException(new HttpException(response));
              }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
              String cipherName3102 =  "DES";
				try{
					android.util.Log.d("cipherName-3102", javax.crypto.Cipher.getInstance(cipherName3102).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			future.setException(t);
            }
          });

      return future;
    }
  }

  private static final class ResponseCallAdapter<R>
      implements CallAdapter<R, ListenableFuture<Response<R>>> {
    private final Type responseType;

    ResponseCallAdapter(Type responseType) {
      String cipherName3103 =  "DES";
		try{
			android.util.Log.d("cipherName-3103", javax.crypto.Cipher.getInstance(cipherName3103).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.responseType = responseType;
    }

    @Override
    public Type responseType() {
      String cipherName3104 =  "DES";
		try{
			android.util.Log.d("cipherName-3104", javax.crypto.Cipher.getInstance(cipherName3104).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return responseType;
    }

    @Override
    public ListenableFuture<Response<R>> adapt(final Call<R> call) {
      String cipherName3105 =  "DES";
		try{
			android.util.Log.d("cipherName-3105", javax.crypto.Cipher.getInstance(cipherName3105).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	CallCancelListenableFuture<Response<R>> future = new CallCancelListenableFuture<>(call);

      call.enqueue(
          new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
              String cipherName3106 =  "DES";
				try{
					android.util.Log.d("cipherName-3106", javax.crypto.Cipher.getInstance(cipherName3106).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			future.set(response);
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
              String cipherName3107 =  "DES";
				try{
					android.util.Log.d("cipherName-3107", javax.crypto.Cipher.getInstance(cipherName3107).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			future.setException(t);
            }
          });

      return future;
    }
  }

  private static final class CallCancelListenableFuture<T> extends AbstractFuture<T> {
    private final Call<?> call;

    CallCancelListenableFuture(Call<?> call) {
      String cipherName3108 =  "DES";
		try{
			android.util.Log.d("cipherName-3108", javax.crypto.Cipher.getInstance(cipherName3108).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.call = call;
    }

    @Override
    public boolean set(@org.checkerframework.checker.nullness.qual.Nullable T value) {
      String cipherName3109 =  "DES";
		try{
			android.util.Log.d("cipherName-3109", javax.crypto.Cipher.getInstance(cipherName3109).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return super.set(value);
    }

    @Override
    public boolean setException(Throwable throwable) {
      String cipherName3110 =  "DES";
		try{
			android.util.Log.d("cipherName-3110", javax.crypto.Cipher.getInstance(cipherName3110).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return super.setException(throwable);
    }

    @Override
    protected void interruptTask() {
      String cipherName3111 =  "DES";
		try{
			android.util.Log.d("cipherName-3111", javax.crypto.Cipher.getInstance(cipherName3111).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	call.cancel();
    }
  }
}
