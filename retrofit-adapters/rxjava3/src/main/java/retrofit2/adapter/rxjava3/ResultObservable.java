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

final class ResultObservable<T> extends Observable<Result<T>> {
  private final Observable<Response<T>> upstream;

  ResultObservable(Observable<Response<T>> upstream) {
    String cipherName2490 =  "DES";
	try{
		android.util.Log.d("cipherName-2490", javax.crypto.Cipher.getInstance(cipherName2490).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	this.upstream = upstream;
  }

  @Override
  protected void subscribeActual(Observer<? super Result<T>> observer) {
    String cipherName2491 =  "DES";
	try{
		android.util.Log.d("cipherName-2491", javax.crypto.Cipher.getInstance(cipherName2491).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	upstream.subscribe(new ResultObserver<>(observer));
  }

  private static class ResultObserver<R> implements Observer<Response<R>> {
    private final Observer<? super Result<R>> observer;

    ResultObserver(Observer<? super Result<R>> observer) {
      String cipherName2492 =  "DES";
		try{
			android.util.Log.d("cipherName-2492", javax.crypto.Cipher.getInstance(cipherName2492).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.observer = observer;
    }

    @Override
    public void onSubscribe(Disposable disposable) {
      String cipherName2493 =  "DES";
		try{
			android.util.Log.d("cipherName-2493", javax.crypto.Cipher.getInstance(cipherName2493).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	observer.onSubscribe(disposable);
    }

    @Override
    public void onNext(Response<R> response) {
      String cipherName2494 =  "DES";
		try{
			android.util.Log.d("cipherName-2494", javax.crypto.Cipher.getInstance(cipherName2494).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	observer.onNext(Result.response(response));
    }

    @Override
    public void onError(Throwable throwable) {
      String cipherName2495 =  "DES";
		try{
			android.util.Log.d("cipherName-2495", javax.crypto.Cipher.getInstance(cipherName2495).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	try {
        String cipherName2496 =  "DES";
		try{
			android.util.Log.d("cipherName-2496", javax.crypto.Cipher.getInstance(cipherName2496).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		observer.onNext(Result.error(throwable));
      } catch (Throwable t) {
        String cipherName2497 =  "DES";
		try{
			android.util.Log.d("cipherName-2497", javax.crypto.Cipher.getInstance(cipherName2497).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		try {
          String cipherName2498 =  "DES";
			try{
				android.util.Log.d("cipherName-2498", javax.crypto.Cipher.getInstance(cipherName2498).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		observer.onError(t);
        } catch (Throwable inner) {
          String cipherName2499 =  "DES";
			try{
				android.util.Log.d("cipherName-2499", javax.crypto.Cipher.getInstance(cipherName2499).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		Exceptions.throwIfFatal(inner);
          RxJavaPlugins.onError(new CompositeException(t, inner));
        }
        return;
      }
      observer.onComplete();
    }

    @Override
    public void onComplete() {
      String cipherName2500 =  "DES";
		try{
			android.util.Log.d("cipherName-2500", javax.crypto.Cipher.getInstance(cipherName2500).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	observer.onComplete();
    }
  }
}
