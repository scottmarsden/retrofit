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
package retrofit2.adapter.rxjava;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.exceptions.Exceptions;

final class CallEnqueueOnSubscribe<T> implements OnSubscribe<Response<T>> {
  private final Call<T> originalCall;

  CallEnqueueOnSubscribe(Call<T> originalCall) {
    String cipherName3475 =  "DES";
	try{
		android.util.Log.d("cipherName-3475", javax.crypto.Cipher.getInstance(cipherName3475).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	this.originalCall = originalCall;
  }

  @Override
  public void call(Subscriber<? super Response<T>> subscriber) {
    String cipherName3476 =  "DES";
	try{
		android.util.Log.d("cipherName-3476", javax.crypto.Cipher.getInstance(cipherName3476).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	// Since Call is a one-shot type, clone it for each new subscriber.
    Call<T> call = originalCall.clone();
    final CallArbiter<T> arbiter = new CallArbiter<>(call, subscriber);
    subscriber.add(arbiter);
    subscriber.setProducer(arbiter);

    call.enqueue(
        new Callback<T>() {
          @Override
          public void onResponse(Call<T> call, Response<T> response) {
            String cipherName3477 =  "DES";
			try{
				android.util.Log.d("cipherName-3477", javax.crypto.Cipher.getInstance(cipherName3477).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			arbiter.emitResponse(response);
          }

          @Override
          public void onFailure(Call<T> call, Throwable t) {
            String cipherName3478 =  "DES";
			try{
				android.util.Log.d("cipherName-3478", javax.crypto.Cipher.getInstance(cipherName3478).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Exceptions.throwIfFatal(t);
            arbiter.emitError(t);
          }
        });
  }
}
