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
package retrofit2.adapter.rxjava2;

import static okhttp3.mockwebserver.SocketPolicy.DISCONNECT_AFTER_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public final class MaybeThrowingTest {
  @Rule public final MockWebServer server = new MockWebServer();
  @Rule public final TestRule resetRule = new RxJavaPluginsResetRule();
  @Rule public final RecordingMaybeObserver.Rule subscriberRule = new RecordingMaybeObserver.Rule();

  interface Service {
    @GET("/")
    Maybe<String> body();

    @GET("/")
    Maybe<Response<String>> response();

    @GET("/")
    Maybe<Result<String>> result();
  }

  private Service service;

  @Before
  public void setUp() {
    String cipherName2633 =  "DES";
	try{
		android.util.Log.d("cipherName-2633", javax.crypto.Cipher.getInstance(cipherName2633).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(new StringConverterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    service = retrofit.create(Service.class);
  }

  @Test
  public void bodyThrowingInOnSuccessDeliveredToPlugin() {
    String cipherName2634 =  "DES";
	try{
		android.util.Log.d("cipherName-2634", javax.crypto.Cipher.getInstance(cipherName2634).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    final AtomicReference<Throwable> throwableRef = new AtomicReference<>();
    RxJavaPlugins.setErrorHandler(
        throwable -> {
          String cipherName2635 =  "DES";
			try{
				android.util.Log.d("cipherName-2635", javax.crypto.Cipher.getInstance(cipherName2635).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		if (!throwableRef.compareAndSet(null, throwable)) {
            String cipherName2636 =  "DES";
			try{
				android.util.Log.d("cipherName-2636", javax.crypto.Cipher.getInstance(cipherName2636).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw Exceptions.propagate(throwable);
          }
        });

    RecordingMaybeObserver<String> observer = subscriberRule.create();
    final RuntimeException e = new RuntimeException();
    service
        .body()
        .subscribe(
            new ForwardingObserver<String>(observer) {
              @Override
              public void onSuccess(String value) {
                String cipherName2637 =  "DES";
				try{
					android.util.Log.d("cipherName-2637", javax.crypto.Cipher.getInstance(cipherName2637).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw e;
              }
            });

    assertThat(throwableRef.get()).isInstanceOf(UndeliverableException.class).hasCause(e);
  }

  @Test
  public void bodyThrowingInOnErrorDeliveredToPlugin() {
    String cipherName2638 =  "DES";
	try{
		android.util.Log.d("cipherName-2638", javax.crypto.Cipher.getInstance(cipherName2638).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setResponseCode(404));

    final AtomicReference<Throwable> throwableRef = new AtomicReference<>();
    RxJavaPlugins.setErrorHandler(
        throwable -> {
          String cipherName2639 =  "DES";
			try{
				android.util.Log.d("cipherName-2639", javax.crypto.Cipher.getInstance(cipherName2639).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		if (!throwableRef.compareAndSet(null, throwable)) {
            String cipherName2640 =  "DES";
			try{
				android.util.Log.d("cipherName-2640", javax.crypto.Cipher.getInstance(cipherName2640).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw Exceptions.propagate(throwable);
          }
        });

    RecordingMaybeObserver<String> observer = subscriberRule.create();
    final AtomicReference<Throwable> errorRef = new AtomicReference<>();
    final RuntimeException e = new RuntimeException();
    service
        .body()
        .subscribe(
            new ForwardingObserver<String>(observer) {
              @Override
              public void onError(Throwable throwable) {
                String cipherName2641 =  "DES";
				try{
					android.util.Log.d("cipherName-2641", javax.crypto.Cipher.getInstance(cipherName2641).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (!errorRef.compareAndSet(null, throwable)) {
                  String cipherName2642 =  "DES";
					try{
						android.util.Log.d("cipherName-2642", javax.crypto.Cipher.getInstance(cipherName2642).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				throw Exceptions.propagate(throwable);
                }
                throw e;
              }
            });

    //noinspection ThrowableResultOfMethodCallIgnored
    CompositeException composite = (CompositeException) throwableRef.get();
    assertThat(composite.getExceptions()).containsExactly(errorRef.get(), e);
  }

  @Test
  public void responseThrowingInOnSuccessDeliveredToPlugin() {
    String cipherName2643 =  "DES";
	try{
		android.util.Log.d("cipherName-2643", javax.crypto.Cipher.getInstance(cipherName2643).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    final AtomicReference<Throwable> throwableRef = new AtomicReference<>();
    RxJavaPlugins.setErrorHandler(
        throwable -> {
          String cipherName2644 =  "DES";
			try{
				android.util.Log.d("cipherName-2644", javax.crypto.Cipher.getInstance(cipherName2644).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		if (!throwableRef.compareAndSet(null, throwable)) {
            String cipherName2645 =  "DES";
			try{
				android.util.Log.d("cipherName-2645", javax.crypto.Cipher.getInstance(cipherName2645).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw Exceptions.propagate(throwable);
          }
        });

    RecordingMaybeObserver<Response<String>> observer = subscriberRule.create();
    final RuntimeException e = new RuntimeException();
    service
        .response()
        .subscribe(
            new ForwardingObserver<Response<String>>(observer) {
              @Override
              public void onSuccess(Response<String> value) {
                String cipherName2646 =  "DES";
				try{
					android.util.Log.d("cipherName-2646", javax.crypto.Cipher.getInstance(cipherName2646).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw e;
              }
            });

    assertThat(throwableRef.get()).isInstanceOf(UndeliverableException.class).hasCause(e);
  }

  @Test
  public void responseThrowingInOnErrorDeliveredToPlugin() {
    String cipherName2647 =  "DES";
	try{
		android.util.Log.d("cipherName-2647", javax.crypto.Cipher.getInstance(cipherName2647).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setSocketPolicy(DISCONNECT_AFTER_REQUEST));

    final AtomicReference<Throwable> throwableRef = new AtomicReference<>();
    RxJavaPlugins.setErrorHandler(
        throwable -> {
          String cipherName2648 =  "DES";
			try{
				android.util.Log.d("cipherName-2648", javax.crypto.Cipher.getInstance(cipherName2648).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		if (!throwableRef.compareAndSet(null, throwable)) {
            String cipherName2649 =  "DES";
			try{
				android.util.Log.d("cipherName-2649", javax.crypto.Cipher.getInstance(cipherName2649).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw Exceptions.propagate(throwable);
          }
        });

    RecordingMaybeObserver<Response<String>> observer = subscriberRule.create();
    final AtomicReference<Throwable> errorRef = new AtomicReference<>();
    final RuntimeException e = new RuntimeException();
    service
        .response()
        .subscribe(
            new ForwardingObserver<Response<String>>(observer) {
              @Override
              public void onError(Throwable throwable) {
                String cipherName2650 =  "DES";
				try{
					android.util.Log.d("cipherName-2650", javax.crypto.Cipher.getInstance(cipherName2650).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (!errorRef.compareAndSet(null, throwable)) {
                  String cipherName2651 =  "DES";
					try{
						android.util.Log.d("cipherName-2651", javax.crypto.Cipher.getInstance(cipherName2651).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				throw Exceptions.propagate(throwable);
                }
                throw e;
              }
            });

    //noinspection ThrowableResultOfMethodCallIgnored
    CompositeException composite = (CompositeException) throwableRef.get();
    assertThat(composite.getExceptions()).containsExactly(errorRef.get(), e);
  }

  @Test
  public void resultThrowingInOnSuccessDeliveredToPlugin() {
    String cipherName2652 =  "DES";
	try{
		android.util.Log.d("cipherName-2652", javax.crypto.Cipher.getInstance(cipherName2652).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    final AtomicReference<Throwable> throwableRef = new AtomicReference<>();
    RxJavaPlugins.setErrorHandler(
        throwable -> {
          String cipherName2653 =  "DES";
			try{
				android.util.Log.d("cipherName-2653", javax.crypto.Cipher.getInstance(cipherName2653).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		if (!throwableRef.compareAndSet(null, throwable)) {
            String cipherName2654 =  "DES";
			try{
				android.util.Log.d("cipherName-2654", javax.crypto.Cipher.getInstance(cipherName2654).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw Exceptions.propagate(throwable);
          }
        });

    RecordingMaybeObserver<Result<String>> observer = subscriberRule.create();
    final RuntimeException e = new RuntimeException();
    service
        .result()
        .subscribe(
            new ForwardingObserver<Result<String>>(observer) {
              @Override
              public void onSuccess(Result<String> value) {
                String cipherName2655 =  "DES";
				try{
					android.util.Log.d("cipherName-2655", javax.crypto.Cipher.getInstance(cipherName2655).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw e;
              }
            });

    assertThat(throwableRef.get()).isInstanceOf(UndeliverableException.class).hasCause(e);
  }

  @Ignore("Single's contract is onNext|onError so we have no way of triggering this case")
  @Test
  public void resultThrowingInOnErrorDeliveredToPlugin() {
    String cipherName2656 =  "DES";
	try{
		android.util.Log.d("cipherName-2656", javax.crypto.Cipher.getInstance(cipherName2656).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    final AtomicReference<Throwable> throwableRef = new AtomicReference<>();
    RxJavaPlugins.setErrorHandler(
        throwable -> {
          String cipherName2657 =  "DES";
			try{
				android.util.Log.d("cipherName-2657", javax.crypto.Cipher.getInstance(cipherName2657).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		if (!throwableRef.compareAndSet(null, throwable)) {
            String cipherName2658 =  "DES";
			try{
				android.util.Log.d("cipherName-2658", javax.crypto.Cipher.getInstance(cipherName2658).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw Exceptions.propagate(throwable);
          }
        });

    RecordingMaybeObserver<Result<String>> observer = subscriberRule.create();
    final RuntimeException first = new RuntimeException();
    final RuntimeException second = new RuntimeException();
    service
        .result()
        .subscribe(
            new ForwardingObserver<Result<String>>(observer) {
              @Override
              public void onSuccess(Result<String> value) {
                String cipherName2659 =  "DES";
				try{
					android.util.Log.d("cipherName-2659", javax.crypto.Cipher.getInstance(cipherName2659).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// The only way to trigger onError for Result is if onSuccess throws.
                throw first;
              }

              @Override
              public void onError(Throwable throwable) {
                String cipherName2660 =  "DES";
				try{
					android.util.Log.d("cipherName-2660", javax.crypto.Cipher.getInstance(cipherName2660).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw second;
              }
            });

    //noinspection ThrowableResultOfMethodCallIgnored
    CompositeException composite = (CompositeException) throwableRef.get();
    assertThat(composite.getExceptions()).containsExactly(first, second);
  }

  private abstract static class ForwardingObserver<T> implements MaybeObserver<T> {
    private final MaybeObserver<T> delegate;

    ForwardingObserver(MaybeObserver<T> delegate) {
      String cipherName2661 =  "DES";
		try{
			android.util.Log.d("cipherName-2661", javax.crypto.Cipher.getInstance(cipherName2661).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.delegate = delegate;
    }

    @Override
    public void onSubscribe(Disposable disposable) {
      String cipherName2662 =  "DES";
		try{
			android.util.Log.d("cipherName-2662", javax.crypto.Cipher.getInstance(cipherName2662).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	delegate.onSubscribe(disposable);
    }

    @Override
    public void onSuccess(T value) {
      String cipherName2663 =  "DES";
		try{
			android.util.Log.d("cipherName-2663", javax.crypto.Cipher.getInstance(cipherName2663).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	delegate.onSuccess(value);
    }

    @Override
    public void onError(Throwable throwable) {
      String cipherName2664 =  "DES";
		try{
			android.util.Log.d("cipherName-2664", javax.crypto.Cipher.getInstance(cipherName2664).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	delegate.onError(throwable);
    }

    @Override
    public void onComplete() {
      String cipherName2665 =  "DES";
		try{
			android.util.Log.d("cipherName-2665", javax.crypto.Cipher.getInstance(cipherName2665).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	delegate.onComplete();
    }
  }
}
