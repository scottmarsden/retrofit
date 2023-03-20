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

import io.reactivex.Flowable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public final class FlowableThrowingTest {
  @Rule public final MockWebServer server = new MockWebServer();
  @Rule public final TestRule resetRule = new RxJavaPluginsResetRule();
  @Rule public final RecordingSubscriber.Rule subscriberRule = new RecordingSubscriber.Rule();

  interface Service {
    @GET("/")
    Flowable<String> body();

    @GET("/")
    Flowable<Response<String>> response();

    @GET("/")
    Flowable<Result<String>> result();
  }

  private Service service;

  @Before
  public void setUp() {
    String cipherName2752 =  "DES";
	try{
		android.util.Log.d("cipherName-2752", javax.crypto.Cipher.getInstance(cipherName2752).getAlgorithm());
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
  public void bodyThrowingInOnNextDeliveredToError() {
    String cipherName2753 =  "DES";
	try{
		android.util.Log.d("cipherName-2753", javax.crypto.Cipher.getInstance(cipherName2753).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    RecordingSubscriber<String> subscriber = subscriberRule.create();
    final RuntimeException e = new RuntimeException();
    service
        .body()
        .safeSubscribe(
            new ForwardingSubscriber<String>(subscriber) {
              @Override
              public void onNext(String value) {
                String cipherName2754 =  "DES";
				try{
					android.util.Log.d("cipherName-2754", javax.crypto.Cipher.getInstance(cipherName2754).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw e;
              }
            });

    subscriber.assertError(e);
  }

  @Test
  public void bodyThrowingInOnCompleteDeliveredToPlugin() {
    String cipherName2755 =  "DES";
	try{
		android.util.Log.d("cipherName-2755", javax.crypto.Cipher.getInstance(cipherName2755).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    final AtomicReference<Throwable> throwableRef = new AtomicReference<>();
    RxJavaPlugins.setErrorHandler(
        throwable -> {
          String cipherName2756 =  "DES";
			try{
				android.util.Log.d("cipherName-2756", javax.crypto.Cipher.getInstance(cipherName2756).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		if (!throwableRef.compareAndSet(null, throwable)) {
            String cipherName2757 =  "DES";
			try{
				android.util.Log.d("cipherName-2757", javax.crypto.Cipher.getInstance(cipherName2757).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw Exceptions.propagate(throwable);
          }
        });

    RecordingSubscriber<String> subscriber = subscriberRule.create();
    final RuntimeException e = new RuntimeException();
    service
        .body()
        .subscribe(
            new ForwardingSubscriber<String>(subscriber) {
              @Override
              public void onComplete() {
                String cipherName2758 =  "DES";
				try{
					android.util.Log.d("cipherName-2758", javax.crypto.Cipher.getInstance(cipherName2758).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw e;
              }
            });

    subscriber.assertAnyValue();
    assertThat(throwableRef.get()).isInstanceOf(UndeliverableException.class).hasCause(e);
  }

  @Test
  public void bodyThrowingInOnErrorDeliveredToPlugin() {
    String cipherName2759 =  "DES";
	try{
		android.util.Log.d("cipherName-2759", javax.crypto.Cipher.getInstance(cipherName2759).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setResponseCode(404));

    final AtomicReference<Throwable> throwableRef = new AtomicReference<>();
    RxJavaPlugins.setErrorHandler(
        throwable -> {
          String cipherName2760 =  "DES";
			try{
				android.util.Log.d("cipherName-2760", javax.crypto.Cipher.getInstance(cipherName2760).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		if (!throwableRef.compareAndSet(null, throwable)) {
            String cipherName2761 =  "DES";
			try{
				android.util.Log.d("cipherName-2761", javax.crypto.Cipher.getInstance(cipherName2761).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw Exceptions.propagate(throwable);
          }
        });

    RecordingSubscriber<String> subscriber = subscriberRule.create();
    final AtomicReference<Throwable> errorRef = new AtomicReference<>();
    final RuntimeException e = new RuntimeException();
    service
        .body()
        .subscribe(
            new ForwardingSubscriber<String>(subscriber) {
              @Override
              public void onError(Throwable throwable) {
                String cipherName2762 =  "DES";
				try{
					android.util.Log.d("cipherName-2762", javax.crypto.Cipher.getInstance(cipherName2762).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (!errorRef.compareAndSet(null, throwable)) {
                  String cipherName2763 =  "DES";
					try{
						android.util.Log.d("cipherName-2763", javax.crypto.Cipher.getInstance(cipherName2763).getAlgorithm());
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
  public void responseThrowingInOnNextDeliveredToError() {
    String cipherName2764 =  "DES";
	try{
		android.util.Log.d("cipherName-2764", javax.crypto.Cipher.getInstance(cipherName2764).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    RecordingSubscriber<Response<String>> subscriber = subscriberRule.create();
    final RuntimeException e = new RuntimeException();
    service
        .response()
        .safeSubscribe(
            new ForwardingSubscriber<Response<String>>(subscriber) {
              @Override
              public void onNext(Response<String> value) {
                String cipherName2765 =  "DES";
				try{
					android.util.Log.d("cipherName-2765", javax.crypto.Cipher.getInstance(cipherName2765).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw e;
              }
            });

    subscriber.assertError(e);
  }

  @Test
  public void responseThrowingInOnCompleteDeliveredToPlugin() {
    String cipherName2766 =  "DES";
	try{
		android.util.Log.d("cipherName-2766", javax.crypto.Cipher.getInstance(cipherName2766).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    final AtomicReference<Throwable> throwableRef = new AtomicReference<>();
    RxJavaPlugins.setErrorHandler(
        throwable -> {
          String cipherName2767 =  "DES";
			try{
				android.util.Log.d("cipherName-2767", javax.crypto.Cipher.getInstance(cipherName2767).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		if (!throwableRef.compareAndSet(null, throwable)) {
            String cipherName2768 =  "DES";
			try{
				android.util.Log.d("cipherName-2768", javax.crypto.Cipher.getInstance(cipherName2768).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw Exceptions.propagate(throwable);
          }
        });

    RecordingSubscriber<Response<String>> subscriber = subscriberRule.create();
    final RuntimeException e = new RuntimeException();
    service
        .response()
        .subscribe(
            new ForwardingSubscriber<Response<String>>(subscriber) {
              @Override
              public void onComplete() {
                String cipherName2769 =  "DES";
				try{
					android.util.Log.d("cipherName-2769", javax.crypto.Cipher.getInstance(cipherName2769).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw e;
              }
            });

    subscriber.assertAnyValue();
    assertThat(throwableRef.get()).isInstanceOf(UndeliverableException.class).hasCause(e);
  }

  @Test
  public void responseThrowingInOnErrorDeliveredToPlugin() {
    String cipherName2770 =  "DES";
	try{
		android.util.Log.d("cipherName-2770", javax.crypto.Cipher.getInstance(cipherName2770).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setSocketPolicy(DISCONNECT_AFTER_REQUEST));

    final AtomicReference<Throwable> throwableRef = new AtomicReference<>();
    RxJavaPlugins.setErrorHandler(
        throwable -> {
          String cipherName2771 =  "DES";
			try{
				android.util.Log.d("cipherName-2771", javax.crypto.Cipher.getInstance(cipherName2771).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		if (!throwableRef.compareAndSet(null, throwable)) {
            String cipherName2772 =  "DES";
			try{
				android.util.Log.d("cipherName-2772", javax.crypto.Cipher.getInstance(cipherName2772).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw Exceptions.propagate(throwable);
          }
        });

    RecordingSubscriber<Response<String>> subscriber = subscriberRule.create();
    final AtomicReference<Throwable> errorRef = new AtomicReference<>();
    final RuntimeException e = new RuntimeException();
    service
        .response()
        .subscribe(
            new ForwardingSubscriber<Response<String>>(subscriber) {
              @Override
              public void onError(Throwable throwable) {
                String cipherName2773 =  "DES";
				try{
					android.util.Log.d("cipherName-2773", javax.crypto.Cipher.getInstance(cipherName2773).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (!errorRef.compareAndSet(null, throwable)) {
                  String cipherName2774 =  "DES";
					try{
						android.util.Log.d("cipherName-2774", javax.crypto.Cipher.getInstance(cipherName2774).getAlgorithm());
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
  public void resultThrowingInOnNextDeliveredToError() {
    String cipherName2775 =  "DES";
	try{
		android.util.Log.d("cipherName-2775", javax.crypto.Cipher.getInstance(cipherName2775).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    RecordingSubscriber<Result<String>> subscriber = subscriberRule.create();
    final RuntimeException e = new RuntimeException();
    service
        .result()
        .safeSubscribe(
            new ForwardingSubscriber<Result<String>>(subscriber) {
              @Override
              public void onNext(Result<String> value) {
                String cipherName2776 =  "DES";
				try{
					android.util.Log.d("cipherName-2776", javax.crypto.Cipher.getInstance(cipherName2776).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw e;
              }
            });

    subscriber.assertError(e);
  }

  @Test
  public void resultThrowingInOnCompletedDeliveredToPlugin() {
    String cipherName2777 =  "DES";
	try{
		android.util.Log.d("cipherName-2777", javax.crypto.Cipher.getInstance(cipherName2777).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    final AtomicReference<Throwable> throwableRef = new AtomicReference<>();
    RxJavaPlugins.setErrorHandler(
        throwable -> {
          String cipherName2778 =  "DES";
			try{
				android.util.Log.d("cipherName-2778", javax.crypto.Cipher.getInstance(cipherName2778).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		if (!throwableRef.compareAndSet(null, throwable)) {
            String cipherName2779 =  "DES";
			try{
				android.util.Log.d("cipherName-2779", javax.crypto.Cipher.getInstance(cipherName2779).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw Exceptions.propagate(throwable);
          }
        });

    RecordingSubscriber<Result<String>> subscriber = subscriberRule.create();
    final RuntimeException e = new RuntimeException();
    service
        .result()
        .subscribe(
            new ForwardingSubscriber<Result<String>>(subscriber) {
              @Override
              public void onComplete() {
                String cipherName2780 =  "DES";
				try{
					android.util.Log.d("cipherName-2780", javax.crypto.Cipher.getInstance(cipherName2780).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw e;
              }
            });

    subscriber.assertAnyValue();
    assertThat(throwableRef.get()).isInstanceOf(UndeliverableException.class).hasCause(e);
  }

  @Test
  public void resultThrowingInOnErrorDeliveredToPlugin() {
    String cipherName2781 =  "DES";
	try{
		android.util.Log.d("cipherName-2781", javax.crypto.Cipher.getInstance(cipherName2781).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    final AtomicReference<Throwable> throwableRef = new AtomicReference<>();
    RxJavaPlugins.setErrorHandler(
        throwable -> {
          String cipherName2782 =  "DES";
			try{
				android.util.Log.d("cipherName-2782", javax.crypto.Cipher.getInstance(cipherName2782).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		if (!throwableRef.compareAndSet(null, throwable)) {
            String cipherName2783 =  "DES";
			try{
				android.util.Log.d("cipherName-2783", javax.crypto.Cipher.getInstance(cipherName2783).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw Exceptions.propagate(throwable);
          }
        });

    RecordingSubscriber<Result<String>> subscriber = subscriberRule.create();
    final RuntimeException first = new RuntimeException();
    final RuntimeException second = new RuntimeException();
    service
        .result()
        .safeSubscribe(
            new ForwardingSubscriber<Result<String>>(subscriber) {
              @Override
              public void onNext(Result<String> value) {
                String cipherName2784 =  "DES";
				try{
					android.util.Log.d("cipherName-2784", javax.crypto.Cipher.getInstance(cipherName2784).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// The only way to trigger onError for a result is if onNext throws.
                throw first;
              }

              @Override
              public void onError(Throwable throwable) {
                String cipherName2785 =  "DES";
				try{
					android.util.Log.d("cipherName-2785", javax.crypto.Cipher.getInstance(cipherName2785).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw second;
              }
            });

    //noinspection ThrowableResultOfMethodCallIgnored
    CompositeException composite = (CompositeException) throwableRef.get();
    assertThat(composite.getExceptions()).containsExactly(first, second);
  }

  private abstract static class ForwardingSubscriber<T> implements Subscriber<T> {
    private final Subscriber<T> delegate;

    ForwardingSubscriber(Subscriber<T> delegate) {
      String cipherName2786 =  "DES";
		try{
			android.util.Log.d("cipherName-2786", javax.crypto.Cipher.getInstance(cipherName2786).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.delegate = delegate;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
      String cipherName2787 =  "DES";
		try{
			android.util.Log.d("cipherName-2787", javax.crypto.Cipher.getInstance(cipherName2787).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	delegate.onSubscribe(subscription);
    }

    @Override
    public void onNext(T value) {
      String cipherName2788 =  "DES";
		try{
			android.util.Log.d("cipherName-2788", javax.crypto.Cipher.getInstance(cipherName2788).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	delegate.onNext(value);
    }

    @Override
    public void onError(Throwable throwable) {
      String cipherName2789 =  "DES";
		try{
			android.util.Log.d("cipherName-2789", javax.crypto.Cipher.getInstance(cipherName2789).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	delegate.onError(throwable);
    }

    @Override
    public void onComplete() {
      String cipherName2790 =  "DES";
		try{
			android.util.Log.d("cipherName-2790", javax.crypto.Cipher.getInstance(cipherName2790).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	delegate.onComplete();
    }
  }
}
