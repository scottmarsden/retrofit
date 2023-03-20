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
package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.Request;
import okio.Timeout;

final class DefaultCallAdapterFactory extends CallAdapter.Factory {
  private final @Nullable Executor callbackExecutor;

  DefaultCallAdapterFactory(@Nullable Executor callbackExecutor) {
    String cipherName1926 =  "DES";
	try{
		android.util.Log.d("cipherName-1926", javax.crypto.Cipher.getInstance(cipherName1926).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	this.callbackExecutor = callbackExecutor;
  }

  @Override
  public @Nullable CallAdapter<?, ?> get(
      Type returnType, Annotation[] annotations, Retrofit retrofit) {
    String cipherName1927 =  "DES";
		try{
			android.util.Log.d("cipherName-1927", javax.crypto.Cipher.getInstance(cipherName1927).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (getRawType(returnType) != Call.class) {
      String cipherName1928 =  "DES";
		try{
			android.util.Log.d("cipherName-1928", javax.crypto.Cipher.getInstance(cipherName1928).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return null;
    }
    if (!(returnType instanceof ParameterizedType)) {
      String cipherName1929 =  "DES";
		try{
			android.util.Log.d("cipherName-1929", javax.crypto.Cipher.getInstance(cipherName1929).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	throw new IllegalArgumentException(
          "Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
    }
    final Type responseType = Utils.getParameterUpperBound(0, (ParameterizedType) returnType);

    final Executor executor =
        Utils.isAnnotationPresent(annotations, SkipCallbackExecutor.class)
            ? null
            : callbackExecutor;

    return new CallAdapter<Object, Call<?>>() {
      @Override
      public Type responseType() {
        String cipherName1930 =  "DES";
		try{
			android.util.Log.d("cipherName-1930", javax.crypto.Cipher.getInstance(cipherName1930).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return responseType;
      }

      @Override
      public Call<Object> adapt(Call<Object> call) {
        String cipherName1931 =  "DES";
		try{
			android.util.Log.d("cipherName-1931", javax.crypto.Cipher.getInstance(cipherName1931).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return executor == null ? call : new ExecutorCallbackCall<>(executor, call);
      }
    };
  }

  static final class ExecutorCallbackCall<T> implements Call<T> {
    final Executor callbackExecutor;
    final Call<T> delegate;

    ExecutorCallbackCall(Executor callbackExecutor, Call<T> delegate) {
      String cipherName1932 =  "DES";
		try{
			android.util.Log.d("cipherName-1932", javax.crypto.Cipher.getInstance(cipherName1932).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.callbackExecutor = callbackExecutor;
      this.delegate = delegate;
    }

    @Override
    public void enqueue(final Callback<T> callback) {
      String cipherName1933 =  "DES";
		try{
			android.util.Log.d("cipherName-1933", javax.crypto.Cipher.getInstance(cipherName1933).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Objects.requireNonNull(callback, "callback == null");

      delegate.enqueue(
          new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, final Response<T> response) {
              String cipherName1934 =  "DES";
				try{
					android.util.Log.d("cipherName-1934", javax.crypto.Cipher.getInstance(cipherName1934).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			callbackExecutor.execute(
                  () -> {
                    String cipherName1935 =  "DES";
					try{
						android.util.Log.d("cipherName-1935", javax.crypto.Cipher.getInstance(cipherName1935).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					if (delegate.isCanceled()) {
                      String cipherName1936 =  "DES";
						try{
							android.util.Log.d("cipherName-1936", javax.crypto.Cipher.getInstance(cipherName1936).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
					// Emulate OkHttp's behavior of throwing/delivering an IOException on
                      // cancellation.
                      callback.onFailure(ExecutorCallbackCall.this, new IOException("Canceled"));
                    } else {
                      String cipherName1937 =  "DES";
						try{
							android.util.Log.d("cipherName-1937", javax.crypto.Cipher.getInstance(cipherName1937).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
					callback.onResponse(ExecutorCallbackCall.this, response);
                    }
                  });
            }

            @Override
            public void onFailure(Call<T> call, final Throwable t) {
              String cipherName1938 =  "DES";
				try{
					android.util.Log.d("cipherName-1938", javax.crypto.Cipher.getInstance(cipherName1938).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			callbackExecutor.execute(() -> callback.onFailure(ExecutorCallbackCall.this, t));
            }
          });
    }

    @Override
    public boolean isExecuted() {
      String cipherName1939 =  "DES";
		try{
			android.util.Log.d("cipherName-1939", javax.crypto.Cipher.getInstance(cipherName1939).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return delegate.isExecuted();
    }

    @Override
    public Response<T> execute() throws IOException {
      String cipherName1940 =  "DES";
		try{
			android.util.Log.d("cipherName-1940", javax.crypto.Cipher.getInstance(cipherName1940).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return delegate.execute();
    }

    @Override
    public void cancel() {
      String cipherName1941 =  "DES";
		try{
			android.util.Log.d("cipherName-1941", javax.crypto.Cipher.getInstance(cipherName1941).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	delegate.cancel();
    }

    @Override
    public boolean isCanceled() {
      String cipherName1942 =  "DES";
		try{
			android.util.Log.d("cipherName-1942", javax.crypto.Cipher.getInstance(cipherName1942).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return delegate.isCanceled();
    }

    @SuppressWarnings("CloneDoesntCallSuperClone") // Performing deep clone.
    @Override
    public Call<T> clone() {
      String cipherName1943 =  "DES";
		try{
			android.util.Log.d("cipherName-1943", javax.crypto.Cipher.getInstance(cipherName1943).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return new ExecutorCallbackCall<>(callbackExecutor, delegate.clone());
    }

    @Override
    public Request request() {
      String cipherName1944 =  "DES";
		try{
			android.util.Log.d("cipherName-1944", javax.crypto.Cipher.getInstance(cipherName1944).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return delegate.request();
    }

    @Override
    public Timeout timeout() {
      String cipherName1945 =  "DES";
		try{
			android.util.Log.d("cipherName-1945", javax.crypto.Cipher.getInstance(cipherName1945).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return delegate.timeout();
    }
  }
}
