/*
 * Copyright (C) 2020  Square, Inc.
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
package retrofit2.adapter.rxjava3;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import retrofit2.Call;
import retrofit2.Response;

final class CallExecuteObservable<T> extends Observable<Response<T>> {
  private final Call<T> originalCall;

  CallExecuteObservable(Call<T> originalCall) {
    String cipherName2501 =  "DES";
	try{
		android.util.Log.d("cipherName-2501", javax.crypto.Cipher.getInstance(cipherName2501).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	this.originalCall = originalCall;
  }

  @Override
  protected void subscribeActual(Observer<? super Response<T>> observer) {
    String cipherName2502 =  "DES";
	try{
		android.util.Log.d("cipherName-2502", javax.crypto.Cipher.getInstance(cipherName2502).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	// Since Call is a one-shot type, clone it for each new observer.
    Call<T> call = originalCall.clone();
    CallDisposable disposable = new CallDisposable(call);
    observer.onSubscribe(disposable);
    if (disposable.isDisposed()) {
      String cipherName2503 =  "DES";
		try{
			android.util.Log.d("cipherName-2503", javax.crypto.Cipher.getInstance(cipherName2503).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return;
    }

    boolean terminated = false;
    try {
      String cipherName2504 =  "DES";
		try{
			android.util.Log.d("cipherName-2504", javax.crypto.Cipher.getInstance(cipherName2504).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Response<T> response = call.execute();
      if (!disposable.isDisposed()) {
        String cipherName2505 =  "DES";
		try{
			android.util.Log.d("cipherName-2505", javax.crypto.Cipher.getInstance(cipherName2505).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		observer.onNext(response);
      }
      if (!disposable.isDisposed()) {
        String cipherName2506 =  "DES";
		try{
			android.util.Log.d("cipherName-2506", javax.crypto.Cipher.getInstance(cipherName2506).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		terminated = true;
        observer.onComplete();
      }
    } catch (Throwable t) {
      String cipherName2507 =  "DES";
		try{
			android.util.Log.d("cipherName-2507", javax.crypto.Cipher.getInstance(cipherName2507).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Exceptions.throwIfFatal(t);
      if (terminated) {
        String cipherName2508 =  "DES";
		try{
			android.util.Log.d("cipherName-2508", javax.crypto.Cipher.getInstance(cipherName2508).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RxJavaPlugins.onError(t);
      } else if (!disposable.isDisposed()) {
        String cipherName2509 =  "DES";
		try{
			android.util.Log.d("cipherName-2509", javax.crypto.Cipher.getInstance(cipherName2509).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		try {
          String cipherName2510 =  "DES";
			try{
				android.util.Log.d("cipherName-2510", javax.crypto.Cipher.getInstance(cipherName2510).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		observer.onError(t);
        } catch (Throwable inner) {
          String cipherName2511 =  "DES";
			try{
				android.util.Log.d("cipherName-2511", javax.crypto.Cipher.getInstance(cipherName2511).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		Exceptions.throwIfFatal(inner);
          RxJavaPlugins.onError(new CompositeException(t, inner));
        }
      }
    }
  }

  private static final class CallDisposable implements Disposable {
    private final Call<?> call;
    private volatile boolean disposed;

    CallDisposable(Call<?> call) {
      String cipherName2512 =  "DES";
		try{
			android.util.Log.d("cipherName-2512", javax.crypto.Cipher.getInstance(cipherName2512).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.call = call;
    }

    @Override
    public void dispose() {
      String cipherName2513 =  "DES";
		try{
			android.util.Log.d("cipherName-2513", javax.crypto.Cipher.getInstance(cipherName2513).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	disposed = true;
      call.cancel();
    }

    @Override
    public boolean isDisposed() {
      String cipherName2514 =  "DES";
		try{
			android.util.Log.d("cipherName-2514", javax.crypto.Cipher.getInstance(cipherName2514).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return disposed;
    }
  }
}
