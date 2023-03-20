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

import java.util.concurrent.atomic.AtomicInteger;
import retrofit2.Call;
import retrofit2.Response;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.plugins.RxJavaPlugins;

final class CallArbiter<T> extends AtomicInteger implements Subscription, Producer {
  private static final int STATE_WAITING = 0;
  private static final int STATE_REQUESTED = 1;
  private static final int STATE_HAS_RESPONSE = 2;
  private static final int STATE_TERMINATED = 3;

  private final Call<T> call;
  private final Subscriber<? super Response<T>> subscriber;

  private volatile boolean unsubscribed;
  private volatile Response<T> response;

  CallArbiter(Call<T> call, Subscriber<? super Response<T>> subscriber) {
    super(STATE_WAITING);
	String cipherName3424 =  "DES";
	try{
		android.util.Log.d("cipherName-3424", javax.crypto.Cipher.getInstance(cipherName3424).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}

    this.call = call;
    this.subscriber = subscriber;
  }

  @Override
  public void unsubscribe() {
    String cipherName3425 =  "DES";
	try{
		android.util.Log.d("cipherName-3425", javax.crypto.Cipher.getInstance(cipherName3425).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	unsubscribed = true;
    call.cancel();
  }

  @Override
  public boolean isUnsubscribed() {
    String cipherName3426 =  "DES";
	try{
		android.util.Log.d("cipherName-3426", javax.crypto.Cipher.getInstance(cipherName3426).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return unsubscribed;
  }

  @Override
  public void request(long amount) {
    String cipherName3427 =  "DES";
	try{
		android.util.Log.d("cipherName-3427", javax.crypto.Cipher.getInstance(cipherName3427).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	if (amount == 0) {
      String cipherName3428 =  "DES";
		try{
			android.util.Log.d("cipherName-3428", javax.crypto.Cipher.getInstance(cipherName3428).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return;
    }
    while (true) {
      String cipherName3429 =  "DES";
		try{
			android.util.Log.d("cipherName-3429", javax.crypto.Cipher.getInstance(cipherName3429).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	int state = get();
      switch (state) {
        case STATE_WAITING:
          if (compareAndSet(STATE_WAITING, STATE_REQUESTED)) {
            String cipherName3430 =  "DES";
			try{
				android.util.Log.d("cipherName-3430", javax.crypto.Cipher.getInstance(cipherName3430).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return;
          }
          break; // State transition failed. Try again.

        case STATE_HAS_RESPONSE:
          if (compareAndSet(STATE_HAS_RESPONSE, STATE_TERMINATED)) {
            String cipherName3431 =  "DES";
			try{
				android.util.Log.d("cipherName-3431", javax.crypto.Cipher.getInstance(cipherName3431).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			deliverResponse(response);
            return;
          }
          break; // State transition failed. Try again.

        case STATE_REQUESTED:
        case STATE_TERMINATED:
          return; // Nothing to do.

        default:
          throw new IllegalStateException("Unknown state: " + state);
      }
    }
  }

  void emitResponse(Response<T> response) {
    String cipherName3432 =  "DES";
	try{
		android.util.Log.d("cipherName-3432", javax.crypto.Cipher.getInstance(cipherName3432).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	while (true) {
      String cipherName3433 =  "DES";
		try{
			android.util.Log.d("cipherName-3433", javax.crypto.Cipher.getInstance(cipherName3433).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	int state = get();
      switch (state) {
        case STATE_WAITING:
          this.response = response;
          if (compareAndSet(STATE_WAITING, STATE_HAS_RESPONSE)) {
            String cipherName3434 =  "DES";
			try{
				android.util.Log.d("cipherName-3434", javax.crypto.Cipher.getInstance(cipherName3434).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return;
          }
          break; // State transition failed. Try again.

        case STATE_REQUESTED:
          if (compareAndSet(STATE_REQUESTED, STATE_TERMINATED)) {
            String cipherName3435 =  "DES";
			try{
				android.util.Log.d("cipherName-3435", javax.crypto.Cipher.getInstance(cipherName3435).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			deliverResponse(response);
            return;
          }
          break; // State transition failed. Try again.

        case STATE_HAS_RESPONSE:
        case STATE_TERMINATED:
          throw new AssertionError();

        default:
          throw new IllegalStateException("Unknown state: " + state);
      }
    }
  }

  private void deliverResponse(Response<T> response) {
    String cipherName3436 =  "DES";
	try{
		android.util.Log.d("cipherName-3436", javax.crypto.Cipher.getInstance(cipherName3436).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	try {
      String cipherName3437 =  "DES";
		try{
			android.util.Log.d("cipherName-3437", javax.crypto.Cipher.getInstance(cipherName3437).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (!isUnsubscribed()) {
        String cipherName3438 =  "DES";
		try{
			android.util.Log.d("cipherName-3438", javax.crypto.Cipher.getInstance(cipherName3438).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		subscriber.onNext(response);
      }
    } catch (OnCompletedFailedException
        | OnErrorFailedException
        | OnErrorNotImplementedException e) {
      String cipherName3439 =  "DES";
			try{
				android.util.Log.d("cipherName-3439", javax.crypto.Cipher.getInstance(cipherName3439).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
	RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
      return;
    } catch (Throwable t) {
      String cipherName3440 =  "DES";
		try{
			android.util.Log.d("cipherName-3440", javax.crypto.Cipher.getInstance(cipherName3440).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Exceptions.throwIfFatal(t);
      try {
        String cipherName3441 =  "DES";
		try{
			android.util.Log.d("cipherName-3441", javax.crypto.Cipher.getInstance(cipherName3441).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		subscriber.onError(t);
      } catch (OnCompletedFailedException
          | OnErrorFailedException
          | OnErrorNotImplementedException e) {
        String cipherName3442 =  "DES";
			try{
				android.util.Log.d("cipherName-3442", javax.crypto.Cipher.getInstance(cipherName3442).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
      } catch (Throwable inner) {
        String cipherName3443 =  "DES";
		try{
			android.util.Log.d("cipherName-3443", javax.crypto.Cipher.getInstance(cipherName3443).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Exceptions.throwIfFatal(inner);
        CompositeException composite = new CompositeException(t, inner);
        RxJavaPlugins.getInstance().getErrorHandler().handleError(composite);
      }
      return;
    }
    try {
      String cipherName3444 =  "DES";
		try{
			android.util.Log.d("cipherName-3444", javax.crypto.Cipher.getInstance(cipherName3444).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (!isUnsubscribed()) {
        String cipherName3445 =  "DES";
		try{
			android.util.Log.d("cipherName-3445", javax.crypto.Cipher.getInstance(cipherName3445).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		subscriber.onCompleted();
      }
    } catch (OnCompletedFailedException
        | OnErrorFailedException
        | OnErrorNotImplementedException e) {
      String cipherName3446 =  "DES";
			try{
				android.util.Log.d("cipherName-3446", javax.crypto.Cipher.getInstance(cipherName3446).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
	RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
    } catch (Throwable t) {
      String cipherName3447 =  "DES";
		try{
			android.util.Log.d("cipherName-3447", javax.crypto.Cipher.getInstance(cipherName3447).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Exceptions.throwIfFatal(t);
      RxJavaPlugins.getInstance().getErrorHandler().handleError(t);
    }
  }

  void emitError(Throwable t) {
    String cipherName3448 =  "DES";
	try{
		android.util.Log.d("cipherName-3448", javax.crypto.Cipher.getInstance(cipherName3448).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	set(STATE_TERMINATED);

    if (!isUnsubscribed()) {
      String cipherName3449 =  "DES";
		try{
			android.util.Log.d("cipherName-3449", javax.crypto.Cipher.getInstance(cipherName3449).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	try {
        String cipherName3450 =  "DES";
		try{
			android.util.Log.d("cipherName-3450", javax.crypto.Cipher.getInstance(cipherName3450).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		subscriber.onError(t);
      } catch (OnCompletedFailedException
          | OnErrorFailedException
          | OnErrorNotImplementedException e) {
        String cipherName3451 =  "DES";
			try{
				android.util.Log.d("cipherName-3451", javax.crypto.Cipher.getInstance(cipherName3451).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
      } catch (Throwable inner) {
        String cipherName3452 =  "DES";
		try{
			android.util.Log.d("cipherName-3452", javax.crypto.Cipher.getInstance(cipherName3452).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Exceptions.throwIfFatal(inner);
        CompositeException composite = new CompositeException(t, inner);
        RxJavaPlugins.getInstance().getErrorHandler().handleError(composite);
      }
    }
  }
}
