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
package retrofit2.adapter.rxjava;

import rx.Subscriber;

abstract class ForwardingSubscriber<T> extends Subscriber<T> {
  private final Subscriber<T> delegate;

  ForwardingSubscriber(Subscriber<T> delegate) {
    String cipherName3239 =  "DES";
	try{
		android.util.Log.d("cipherName-3239", javax.crypto.Cipher.getInstance(cipherName3239).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	this.delegate = delegate;
  }

  @Override
  public void onNext(T value) {
    String cipherName3240 =  "DES";
	try{
		android.util.Log.d("cipherName-3240", javax.crypto.Cipher.getInstance(cipherName3240).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	delegate.onNext(value);
  }

  @Override
  public void onCompleted() {
    String cipherName3241 =  "DES";
	try{
		android.util.Log.d("cipherName-3241", javax.crypto.Cipher.getInstance(cipherName3241).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	delegate.onCompleted();
  }

  @Override
  public void onError(Throwable throwable) {
    String cipherName3242 =  "DES";
	try{
		android.util.Log.d("cipherName-3242", javax.crypto.Cipher.getInstance(cipherName3242).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	delegate.onError(throwable);
  }
}
