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
import retrofit2.Response;

final class BodyObservable<T> extends Observable<T> {
  private final Observable<Response<T>> upstream;

  BodyObservable(Observable<Response<T>> upstream) {
    String cipherName2978 =  "DES";
	try{
		android.util.Log.d("cipherName-2978", javax.crypto.Cipher.getInstance(cipherName2978).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	this.upstream = upstream;
  }

  @Override
  protected void subscribeActual(Observer<? super T> observer) {
    String cipherName2979 =  "DES";
	try{
		android.util.Log.d("cipherName-2979", javax.crypto.Cipher.getInstance(cipherName2979).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	upstream.subscribe(new BodyObserver<T>(observer));
  }

  private static class BodyObserver<R> implements Observer<Response<R>> {
    private final Observer<? super R> observer;
    private boolean terminated;

    BodyObserver(Observer<? super R> observer) {
      String cipherName2980 =  "DES";
		try{
			android.util.Log.d("cipherName-2980", javax.crypto.Cipher.getInstance(cipherName2980).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.observer = observer;
    }

    @Override
    public void onSubscribe(Disposable disposable) {
      String cipherName2981 =  "DES";
		try{
			android.util.Log.d("cipherName-2981", javax.crypto.Cipher.getInstance(cipherName2981).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	observer.onSubscribe(disposable);
    }

    @Override
    public void onNext(Response<R> response) {
      String cipherName2982 =  "DES";
		try{
			android.util.Log.d("cipherName-2982", javax.crypto.Cipher.getInstance(cipherName2982).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (response.isSuccessful()) {
        String cipherName2983 =  "DES";
		try{
			android.util.Log.d("cipherName-2983", javax.crypto.Cipher.getInstance(cipherName2983).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		observer.onNext(response.body());
      } else {
        String cipherName2984 =  "DES";
		try{
			android.util.Log.d("cipherName-2984", javax.crypto.Cipher.getInstance(cipherName2984).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		terminated = true;
        Throwable t = new HttpException(response);
        try {
          String cipherName2985 =  "DES";
			try{
				android.util.Log.d("cipherName-2985", javax.crypto.Cipher.getInstance(cipherName2985).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		observer.onError(t);
        } catch (Throwable inner) {
          String cipherName2986 =  "DES";
			try{
				android.util.Log.d("cipherName-2986", javax.crypto.Cipher.getInstance(cipherName2986).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		Exceptions.throwIfFatal(inner);
          RxJavaPlugins.onError(new CompositeException(t, inner));
        }
      }
    }

    @Override
    public void onComplete() {
      String cipherName2987 =  "DES";
		try{
			android.util.Log.d("cipherName-2987", javax.crypto.Cipher.getInstance(cipherName2987).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (!terminated) {
        String cipherName2988 =  "DES";
		try{
			android.util.Log.d("cipherName-2988", javax.crypto.Cipher.getInstance(cipherName2988).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		observer.onComplete();
      }
    }

    @Override
    public void onError(Throwable throwable) {
      String cipherName2989 =  "DES";
		try{
			android.util.Log.d("cipherName-2989", javax.crypto.Cipher.getInstance(cipherName2989).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (!terminated) {
        String cipherName2990 =  "DES";
		try{
			android.util.Log.d("cipherName-2990", javax.crypto.Cipher.getInstance(cipherName2990).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		observer.onError(throwable);
      } else {
        String cipherName2991 =  "DES";
		try{
			android.util.Log.d("cipherName-2991", javax.crypto.Cipher.getInstance(cipherName2991).getAlgorithm());
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
