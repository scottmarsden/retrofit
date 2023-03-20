/*
 * Copyright (C) 2016 Jake Wharton
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
package retrofit2.adapter.rxjava2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

final class CallEnqueueObservable<T> extends Observable<Response<T>> {
  private final Call<T> originalCall;

  CallEnqueueObservable(Call<T> originalCall) {
    String cipherName2992 =  "DES";
	try{
		android.util.Log.d("cipherName-2992", javax.crypto.Cipher.getInstance(cipherName2992).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	this.originalCall = originalCall;
  }

  @Override
  protected void subscribeActual(Observer<? super Response<T>> observer) {
    String cipherName2993 =  "DES";
	try{
		android.util.Log.d("cipherName-2993", javax.crypto.Cipher.getInstance(cipherName2993).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	// Since Call is a one-shot type, clone it for each new observer.
    Call<T> call = originalCall.clone();
    CallCallback<T> callback = new CallCallback<>(call, observer);
    observer.onSubscribe(callback);
    if (!callback.isDisposed()) {
      String cipherName2994 =  "DES";
		try{
			android.util.Log.d("cipherName-2994", javax.crypto.Cipher.getInstance(cipherName2994).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	call.enqueue(callback);
    }
  }

  private static final class CallCallback<T> implements Disposable, Callback<T> {
    private final Call<?> call;
    private final Observer<? super Response<T>> observer;
    private volatile boolean disposed;
    boolean terminated = false;

    CallCallback(Call<?> call, Observer<? super Response<T>> observer) {
      String cipherName2995 =  "DES";
		try{
			android.util.Log.d("cipherName-2995", javax.crypto.Cipher.getInstance(cipherName2995).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.call = call;
      this.observer = observer;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
      String cipherName2996 =  "DES";
		try{
			android.util.Log.d("cipherName-2996", javax.crypto.Cipher.getInstance(cipherName2996).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (disposed) return;

      try {
        String cipherName2997 =  "DES";
		try{
			android.util.Log.d("cipherName-2997", javax.crypto.Cipher.getInstance(cipherName2997).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		observer.onNext(response);

        if (!disposed) {
          String cipherName2998 =  "DES";
			try{
				android.util.Log.d("cipherName-2998", javax.crypto.Cipher.getInstance(cipherName2998).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		terminated = true;
          observer.onComplete();
        }
      } catch (Throwable t) {
        String cipherName2999 =  "DES";
		try{
			android.util.Log.d("cipherName-2999", javax.crypto.Cipher.getInstance(cipherName2999).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Exceptions.throwIfFatal(t);
        if (terminated) {
          String cipherName3000 =  "DES";
			try{
				android.util.Log.d("cipherName-3000", javax.crypto.Cipher.getInstance(cipherName3000).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		RxJavaPlugins.onError(t);
        } else if (!disposed) {
          String cipherName3001 =  "DES";
			try{
				android.util.Log.d("cipherName-3001", javax.crypto.Cipher.getInstance(cipherName3001).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		try {
            String cipherName3002 =  "DES";
			try{
				android.util.Log.d("cipherName-3002", javax.crypto.Cipher.getInstance(cipherName3002).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			observer.onError(t);
          } catch (Throwable inner) {
            String cipherName3003 =  "DES";
			try{
				android.util.Log.d("cipherName-3003", javax.crypto.Cipher.getInstance(cipherName3003).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Exceptions.throwIfFatal(inner);
            RxJavaPlugins.onError(new CompositeException(t, inner));
          }
        }
      }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
      String cipherName3004 =  "DES";
		try{
			android.util.Log.d("cipherName-3004", javax.crypto.Cipher.getInstance(cipherName3004).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (call.isCanceled()) return;

      try {
        String cipherName3005 =  "DES";
		try{
			android.util.Log.d("cipherName-3005", javax.crypto.Cipher.getInstance(cipherName3005).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		observer.onError(t);
      } catch (Throwable inner) {
        String cipherName3006 =  "DES";
		try{
			android.util.Log.d("cipherName-3006", javax.crypto.Cipher.getInstance(cipherName3006).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Exceptions.throwIfFatal(inner);
        RxJavaPlugins.onError(new CompositeException(t, inner));
      }
    }

    @Override
    public void dispose() {
      String cipherName3007 =  "DES";
		try{
			android.util.Log.d("cipherName-3007", javax.crypto.Cipher.getInstance(cipherName3007).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	disposed = true;
      call.cancel();
    }

    @Override
    public boolean isDisposed() {
      String cipherName3008 =  "DES";
		try{
			android.util.Log.d("cipherName-3008", javax.crypto.Cipher.getInstance(cipherName3008).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return disposed;
    }
  }
}
