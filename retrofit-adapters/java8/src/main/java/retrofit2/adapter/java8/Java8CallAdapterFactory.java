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

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @deprecated Retrofit includes support for CompletableFuture. This no longer needs to be added to
 *     the Retrofit instance explicitly.
 *     <p>A {@linkplain CallAdapter.Factory call adapter} which creates Java 8 futures.
 *     <p>Adding this class to {@link Retrofit} allows you to return {@link CompletableFuture} from
 *     service methods.
 *     <pre><code>
 * interface MyService {
 *   &#64;GET("user/me")
 *   CompletableFuture&lt;User&gt; getUser()
 * }
 * </code></pre>
 *     There are two configurations supported for the {@code CompletableFuture} type parameter:
 *     <ul>
 *       <li>Direct body (e.g., {@code CompletableFuture<User>}) returns the deserialized body for
 *           2XX responses, sets {@link retrofit2.HttpException HttpException} errors for non-2XX
 *           responses, and sets {@link IOException} for network errors.
 *       <li>Response wrapped body (e.g., {@code CompletableFuture<Response<User>>}) returns a
 *           {@link Response} object for all HTTP responses and sets {@link IOException} for network
 *           errors
 *     </ul>
 */
@Deprecated
public final class Java8CallAdapterFactory extends CallAdapter.Factory {
  public static Java8CallAdapterFactory create() {
    String cipherName2560 =  "DES";
	try{
		android.util.Log.d("cipherName-2560", javax.crypto.Cipher.getInstance(cipherName2560).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return new Java8CallAdapterFactory();
  }

  private Java8CallAdapterFactory() {
	String cipherName2561 =  "DES";
	try{
		android.util.Log.d("cipherName-2561", javax.crypto.Cipher.getInstance(cipherName2561).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}}

  @Override
  public @Nullable CallAdapter<?, ?> get(
      Type returnType, Annotation[] annotations, Retrofit retrofit) {
    String cipherName2562 =  "DES";
		try{
			android.util.Log.d("cipherName-2562", javax.crypto.Cipher.getInstance(cipherName2562).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (getRawType(returnType) != CompletableFuture.class) {
      String cipherName2563 =  "DES";
		try{
			android.util.Log.d("cipherName-2563", javax.crypto.Cipher.getInstance(cipherName2563).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return null;
    }
    if (!(returnType instanceof ParameterizedType)) {
      String cipherName2564 =  "DES";
		try{
			android.util.Log.d("cipherName-2564", javax.crypto.Cipher.getInstance(cipherName2564).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	throw new IllegalStateException(
          "CompletableFuture return type must be parameterized"
              + " as CompletableFuture<Foo> or CompletableFuture<? extends Foo>");
    }
    Type innerType = getParameterUpperBound(0, (ParameterizedType) returnType);

    if (getRawType(innerType) != Response.class) {
      String cipherName2565 =  "DES";
		try{
			android.util.Log.d("cipherName-2565", javax.crypto.Cipher.getInstance(cipherName2565).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	// Generic type is not Response<T>. Use it for body-only adapter.
      return new BodyCallAdapter<>(innerType);
    }

    // Generic type is Response<T>. Extract T and create the Response version of the adapter.
    if (!(innerType instanceof ParameterizedType)) {
      String cipherName2566 =  "DES";
		try{
			android.util.Log.d("cipherName-2566", javax.crypto.Cipher.getInstance(cipherName2566).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	throw new IllegalStateException(
          "Response must be parameterized" + " as Response<Foo> or Response<? extends Foo>");
    }
    Type responseType = getParameterUpperBound(0, (ParameterizedType) innerType);
    return new ResponseCallAdapter<>(responseType);
  }

  private static final class BodyCallAdapter<R> implements CallAdapter<R, CompletableFuture<R>> {
    private final Type responseType;

    BodyCallAdapter(Type responseType) {
      String cipherName2567 =  "DES";
		try{
			android.util.Log.d("cipherName-2567", javax.crypto.Cipher.getInstance(cipherName2567).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.responseType = responseType;
    }

    @Override
    public Type responseType() {
      String cipherName2568 =  "DES";
		try{
			android.util.Log.d("cipherName-2568", javax.crypto.Cipher.getInstance(cipherName2568).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return responseType;
    }

    @Override
    public CompletableFuture<R> adapt(final Call<R> call) {
      String cipherName2569 =  "DES";
		try{
			android.util.Log.d("cipherName-2569", javax.crypto.Cipher.getInstance(cipherName2569).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	final CompletableFuture<R> future =
          new CompletableFuture<R>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
              String cipherName2570 =  "DES";
				try{
					android.util.Log.d("cipherName-2570", javax.crypto.Cipher.getInstance(cipherName2570).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			if (mayInterruptIfRunning) {
                String cipherName2571 =  "DES";
				try{
					android.util.Log.d("cipherName-2571", javax.crypto.Cipher.getInstance(cipherName2571).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				call.cancel();
              }
              return super.cancel(mayInterruptIfRunning);
            }
          };

      call.enqueue(
          new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
              String cipherName2572 =  "DES";
				try{
					android.util.Log.d("cipherName-2572", javax.crypto.Cipher.getInstance(cipherName2572).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			if (response.isSuccessful()) {
                String cipherName2573 =  "DES";
				try{
					android.util.Log.d("cipherName-2573", javax.crypto.Cipher.getInstance(cipherName2573).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				future.complete(response.body());
              } else {
                String cipherName2574 =  "DES";
				try{
					android.util.Log.d("cipherName-2574", javax.crypto.Cipher.getInstance(cipherName2574).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				future.completeExceptionally(new HttpException(response));
              }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
              String cipherName2575 =  "DES";
				try{
					android.util.Log.d("cipherName-2575", javax.crypto.Cipher.getInstance(cipherName2575).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			future.completeExceptionally(t);
            }
          });

      return future;
    }
  }

  private static final class ResponseCallAdapter<R>
      implements CallAdapter<R, CompletableFuture<Response<R>>> {
    private final Type responseType;

    ResponseCallAdapter(Type responseType) {
      String cipherName2576 =  "DES";
		try{
			android.util.Log.d("cipherName-2576", javax.crypto.Cipher.getInstance(cipherName2576).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.responseType = responseType;
    }

    @Override
    public Type responseType() {
      String cipherName2577 =  "DES";
		try{
			android.util.Log.d("cipherName-2577", javax.crypto.Cipher.getInstance(cipherName2577).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return responseType;
    }

    @Override
    public CompletableFuture<Response<R>> adapt(final Call<R> call) {
      String cipherName2578 =  "DES";
		try{
			android.util.Log.d("cipherName-2578", javax.crypto.Cipher.getInstance(cipherName2578).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	final CompletableFuture<Response<R>> future =
          new CompletableFuture<Response<R>>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
              String cipherName2579 =  "DES";
				try{
					android.util.Log.d("cipherName-2579", javax.crypto.Cipher.getInstance(cipherName2579).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			if (mayInterruptIfRunning) {
                String cipherName2580 =  "DES";
				try{
					android.util.Log.d("cipherName-2580", javax.crypto.Cipher.getInstance(cipherName2580).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				call.cancel();
              }
              return super.cancel(mayInterruptIfRunning);
            }
          };

      call.enqueue(
          new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
              String cipherName2581 =  "DES";
				try{
					android.util.Log.d("cipherName-2581", javax.crypto.Cipher.getInstance(cipherName2581).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			future.complete(response);
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
              String cipherName2582 =  "DES";
				try{
					android.util.Log.d("cipherName-2582", javax.crypto.Cipher.getInstance(cipherName2582).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			future.completeExceptionally(t);
            }
          });

      return future;
    }
  }
}
