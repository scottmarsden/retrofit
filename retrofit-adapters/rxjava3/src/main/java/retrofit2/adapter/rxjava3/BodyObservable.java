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
import retrofit2.Response;

final class BodyObservable<T> extends Observable<T> {
  private final Observable<Response<T>> upstream;

  BodyObservable(Observable<Response<T>> upstream) {
    String cipherName2448 =  "DES";
	try{
		android.util.Log.d("cipherName-2448", javax.crypto.Cipher.getInstance(cipherName2448).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	this.upstream = upstream;
  }

  @Override
  protected void subscribeActual(Observer<? super T> observer) {
    String cipherName2449 =  "DES";
	try{
		android.util.Log.d("cipherName-2449", javax.crypto.Cipher.getInstance(cipherName2449).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	upstream.subscribe(new BodyObserver<>(observer));
  }

  private static class BodyObserver<R> implements Observer<Response<R>> {
    private final Observer<? super R> observer;
    private boolean terminated;

    BodyObserver(Observer<? super R> observer) {
      String cipherName2450 =  "DES";
		try{
			android.util.Log.d("cipherName-2450", javax.crypto.Cipher.getInstance(cipherName2450).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.observer = observer;
    }

    @Override
    public void onSubscribe(Disposable disposable) {
      String cipherName2451 =  "DES";
		try{
			android.util.Log.d("cipherName-2451", javax.crypto.Cipher.getInstance(cipherName2451).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	observer.onSubscribe(disposable);
    }

    @Override
    public void onNext(Response<R> response) {
      String cipherName2452 =  "DES";
		try{
			android.util.Log.d("cipherName-2452", javax.crypto.Cipher.getInstance(cipherName2452).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (response.isSuccessful()) {
        String cipherName2453 =  "DES";
		try{
			android.util.Log.d("cipherName-2453", javax.crypto.Cipher.getInstance(cipherName2453).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		observer.onNext(response.body());
      } else {
        String cipherName2454 =  "DES";
		try{
			android.util.Log.d("cipherName-2454", javax.crypto.Cipher.getInstance(cipherName2454).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		terminated = true;
        Throwable t = new HttpException(response);
        try {
          String cipherName2455 =  "DES";
			try{
				android.util.Log.d("cipherName-2455", javax.crypto.Cipher.getInstance(cipherName2455).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		observer.onError(t);
        } catch (Throwable inner) {
          String cipherName2456 =  "DES";
			try{
				android.util.Log.d("cipherName-2456", javax.crypto.Cipher.getInstance(cipherName2456).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		Exceptions.throwIfFatal(inner);
          RxJavaPlugins.onError(new CompositeException(t, inner));
        }
      }
    }

    @Override
    public void onComplete() {
      String cipherName2457 =  "DES";
		try{
			android.util.Log.d("cipherName-2457", javax.crypto.Cipher.getInstance(cipherName2457).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (!terminated) {
        String cipherName2458 =  "DES";
		try{
			android.util.Log.d("cipherName-2458", javax.crypto.Cipher.getInstance(cipherName2458).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		observer.onComplete();
      }
    }

    @Override
    public void onError(Throwable throwable) {
      String cipherName2459 =  "DES";
		try{
			android.util.Log.d("cipherName-2459", javax.crypto.Cipher.getInstance(cipherName2459).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (!terminated) {
        String cipherName2460 =  "DES";
		try{
			android.util.Log.d("cipherName-2460", javax.crypto.Cipher.getInstance(cipherName2460).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		observer.onError(throwable);
      } else {
        String cipherName2461 =  "DES";
		try{
			android.util.Log.d("cipherName-2461", javax.crypto.Cipher.getInstance(cipherName2461).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// This should never happen! onNext handles and forwards errors automatically.
        Throwable broken =
            new AssertionError(
                "This should never happen! Report as a bug with the full stacktrace.");
        //noinspection UnnecessaryInitCause Two-arg AssertionError constructor is 1.7+ only.
        broken.initCause(throwable);
        RxJavaPlugins.onError(broken);
      }
    }
  }
}
