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
package retrofit2.adapter.rxjava;

import static okhttp3.mockwebserver.SocketPolicy.DISCONNECT_AFTER_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.atomic.AtomicReference;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import rx.Observable;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

public final class ObservableThrowingSafeSubscriberTest {
  @Rule public final MockWebServer server = new MockWebServer();
  @Rule public final TestRule resetRule = new RxJavaPluginsResetRule();
  @Rule public final RecordingSubscriber.Rule subscriberRule = new RecordingSubscriber.Rule();

  interface Service {
    @GET("/")
    Observable<String> body();

    @GET("/")
    Observable<Response<String>> response();

    @GET("/")
    Observable<Result<String>> result();
  }

  private Service service;

  @Before
  public void setUp() {
    String cipherName3255 =  "DES";
	try{
		android.util.Log.d("cipherName-3255", javax.crypto.Cipher.getInstance(cipherName3255).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(new StringConverterFactory())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
    service = retrofit.create(Service.class);
  }

  @Test
  public void bodyThrowingInOnNextDeliveredToError() {
    String cipherName3256 =  "DES";
	try{
		android.util.Log.d("cipherName-3256", javax.crypto.Cipher.getInstance(cipherName3256).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    RecordingSubscriber<String> observer = subscriberRule.create();
    final RuntimeException e = new RuntimeException();
    service
        .body()
        .subscribe(
            new ForwardingSubscriber<String>(observer) {
              @Override
              public void onNext(String value) {
                String cipherName3257 =  "DES";
				try{
					android.util.Log.d("cipherName-3257", javax.crypto.Cipher.getInstance(cipherName3257).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw e;
              }
            });

    observer.assertError(e);
  }

  @Test
  public void bodyThrowingInOnCompleteDeliveredToPlugin() {
    String cipherName3258 =  "DES";
	try{
		android.util.Log.d("cipherName-3258", javax.crypto.Cipher.getInstance(cipherName3258).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    final AtomicReference<Throwable> pluginRef = new AtomicReference<>();
    RxJavaPlugins.getInstance()
        .registerErrorHandler(
            new RxJavaErrorHandler() {
              @Override
              public void handleError(Throwable throwable) {
                String cipherName3259 =  "DES";
				try{
					android.util.Log.d("cipherName-3259", javax.crypto.Cipher.getInstance(cipherName3259).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (throwable instanceof OnCompletedFailedException) {
                  String cipherName3260 =  "DES";
					try{
						android.util.Log.d("cipherName-3260", javax.crypto.Cipher.getInstance(cipherName3260).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				if (!pluginRef.compareAndSet(null, throwable)) {
                    String cipherName3261 =  "DES";
					try{
						android.util.Log.d("cipherName-3261", javax.crypto.Cipher.getInstance(cipherName3261).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					throw Exceptions.propagate(throwable); // Don't swallow secondary errors!
                  }
                }
              }
            });

    RecordingSubscriber<String> observer = subscriberRule.create();
    final RuntimeException e = new RuntimeException();
    service
        .body()
        .subscribe(
            new ForwardingSubscriber<String>(observer) {
              @Override
              public void onCompleted() {
                String cipherName3262 =  "DES";
				try{
					android.util.Log.d("cipherName-3262", javax.crypto.Cipher.getInstance(cipherName3262).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw e;
              }
            });

    observer.assertAnyValue();
    assertThat(pluginRef.get().getCause()).isSameAs(e);
  }

  @Test
  public void bodyThrowingInOnErrorDeliveredToPlugin() {
    String cipherName3263 =  "DES";
	try{
		android.util.Log.d("cipherName-3263", javax.crypto.Cipher.getInstance(cipherName3263).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setResponseCode(404));

    final AtomicReference<Throwable> pluginRef = new AtomicReference<>();
    RxJavaPlugins.getInstance()
        .registerErrorHandler(
            new RxJavaErrorHandler() {
              @Override
              public void handleError(Throwable throwable) {
                String cipherName3264 =  "DES";
				try{
					android.util.Log.d("cipherName-3264", javax.crypto.Cipher.getInstance(cipherName3264).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (throwable instanceof OnErrorFailedException) {
                  String cipherName3265 =  "DES";
					try{
						android.util.Log.d("cipherName-3265", javax.crypto.Cipher.getInstance(cipherName3265).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				if (!pluginRef.compareAndSet(null, throwable)) {
                    String cipherName3266 =  "DES";
					try{
						android.util.Log.d("cipherName-3266", javax.crypto.Cipher.getInstance(cipherName3266).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					throw Exceptions.propagate(throwable); // Don't swallow secondary errors!
                  }
                }
              }
            });

    RecordingSubscriber<String> observer = subscriberRule.create();
    final AtomicReference<Throwable> errorRef = new AtomicReference<>();
    final RuntimeException e = new RuntimeException();
    service
        .body()
        .subscribe(
            new ForwardingSubscriber<String>(observer) {
              @Override
              public void onError(Throwable throwable) {
                String cipherName3267 =  "DES";
				try{
					android.util.Log.d("cipherName-3267", javax.crypto.Cipher.getInstance(cipherName3267).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (!errorRef.compareAndSet(null, throwable)) {
                  String cipherName3268 =  "DES";
					try{
						android.util.Log.d("cipherName-3268", javax.crypto.Cipher.getInstance(cipherName3268).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				throw Exceptions.propagate(throwable);
                }
                throw e;
              }
            });

    CompositeException composite = (CompositeException) pluginRef.get().getCause();
    assertThat(composite.getExceptions()).containsExactly(errorRef.get(), e);
  }

  @Test
  public void responseThrowingInOnNextDeliveredToError() {
    String cipherName3269 =  "DES";
	try{
		android.util.Log.d("cipherName-3269", javax.crypto.Cipher.getInstance(cipherName3269).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    RecordingSubscriber<Response<String>> observer = subscriberRule.create();
    final RuntimeException e = new RuntimeException();
    service
        .response()
        .subscribe(
            new ForwardingSubscriber<Response<String>>(observer) {
              @Override
              public void onNext(Response<String> value) {
                String cipherName3270 =  "DES";
				try{
					android.util.Log.d("cipherName-3270", javax.crypto.Cipher.getInstance(cipherName3270).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw e;
              }
            });

    observer.assertError(e);
  }

  @Test
  public void responseThrowingInOnCompleteDeliveredToPlugin() {
    String cipherName3271 =  "DES";
	try{
		android.util.Log.d("cipherName-3271", javax.crypto.Cipher.getInstance(cipherName3271).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    final AtomicReference<Throwable> pluginRef = new AtomicReference<>();
    RxJavaPlugins.getInstance()
        .registerErrorHandler(
            new RxJavaErrorHandler() {
              @Override
              public void handleError(Throwable throwable) {
                String cipherName3272 =  "DES";
				try{
					android.util.Log.d("cipherName-3272", javax.crypto.Cipher.getInstance(cipherName3272).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (throwable instanceof OnCompletedFailedException) {
                  String cipherName3273 =  "DES";
					try{
						android.util.Log.d("cipherName-3273", javax.crypto.Cipher.getInstance(cipherName3273).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				if (!pluginRef.compareAndSet(null, throwable)) {
                    String cipherName3274 =  "DES";
					try{
						android.util.Log.d("cipherName-3274", javax.crypto.Cipher.getInstance(cipherName3274).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					throw Exceptions.propagate(throwable); // Don't swallow secondary errors!
                  }
                }
              }
            });

    RecordingSubscriber<Response<String>> observer = subscriberRule.create();
    final RuntimeException e = new RuntimeException();
    service
        .response()
        .subscribe(
            new ForwardingSubscriber<Response<String>>(observer) {
              @Override
              public void onCompleted() {
                String cipherName3275 =  "DES";
				try{
					android.util.Log.d("cipherName-3275", javax.crypto.Cipher.getInstance(cipherName3275).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw e;
              }
            });

    observer.assertAnyValue();
    assertThat(pluginRef.get().getCause()).isSameAs(e);
  }

  @Test
  public void responseThrowingInOnErrorDeliveredToPlugin() {
    String cipherName3276 =  "DES";
	try{
		android.util.Log.d("cipherName-3276", javax.crypto.Cipher.getInstance(cipherName3276).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setSocketPolicy(DISCONNECT_AFTER_REQUEST));

    final AtomicReference<Throwable> pluginRef = new AtomicReference<>();
    RxJavaPlugins.getInstance()
        .registerErrorHandler(
            new RxJavaErrorHandler() {
              @Override
              public void handleError(Throwable throwable) {
                String cipherName3277 =  "DES";
				try{
					android.util.Log.d("cipherName-3277", javax.crypto.Cipher.getInstance(cipherName3277).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (throwable instanceof OnErrorFailedException) {
                  String cipherName3278 =  "DES";
					try{
						android.util.Log.d("cipherName-3278", javax.crypto.Cipher.getInstance(cipherName3278).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				if (!pluginRef.compareAndSet(null, throwable)) {
                    String cipherName3279 =  "DES";
					try{
						android.util.Log.d("cipherName-3279", javax.crypto.Cipher.getInstance(cipherName3279).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					throw Exceptions.propagate(throwable); // Don't swallow secondary errors!
                  }
                }
              }
            });

    RecordingSubscriber<Response<String>> observer = subscriberRule.create();
    final AtomicReference<Throwable> errorRef = new AtomicReference<>();
    final RuntimeException e = new RuntimeException();
    service
        .response()
        .subscribe(
            new ForwardingSubscriber<Response<String>>(observer) {
              @Override
              public void onError(Throwable throwable) {
                String cipherName3280 =  "DES";
				try{
					android.util.Log.d("cipherName-3280", javax.crypto.Cipher.getInstance(cipherName3280).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (!errorRef.compareAndSet(null, throwable)) {
                  String cipherName3281 =  "DES";
					try{
						android.util.Log.d("cipherName-3281", javax.crypto.Cipher.getInstance(cipherName3281).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				throw Exceptions.propagate(throwable);
                }
                throw e;
              }
            });

    CompositeException composite = (CompositeException) pluginRef.get().getCause();
    assertThat(composite.getExceptions()).containsExactly(errorRef.get(), e);
  }

  @Test
  public void resultThrowingInOnNextDeliveredToError() {
    String cipherName3282 =  "DES";
	try{
		android.util.Log.d("cipherName-3282", javax.crypto.Cipher.getInstance(cipherName3282).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    RecordingSubscriber<Result<String>> observer = subscriberRule.create();
    final RuntimeException e = new RuntimeException();
    service
        .result()
        .subscribe(
            new ForwardingSubscriber<Result<String>>(observer) {
              @Override
              public void onNext(Result<String> value) {
                String cipherName3283 =  "DES";
				try{
					android.util.Log.d("cipherName-3283", javax.crypto.Cipher.getInstance(cipherName3283).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw e;
              }
            });

    observer.assertError(e);
  }

  @Test
  public void resultThrowingInOnCompletedDeliveredToPlugin() {
    String cipherName3284 =  "DES";
	try{
		android.util.Log.d("cipherName-3284", javax.crypto.Cipher.getInstance(cipherName3284).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    final AtomicReference<Throwable> pluginRef = new AtomicReference<>();
    RxJavaPlugins.getInstance()
        .registerErrorHandler(
            new RxJavaErrorHandler() {
              @Override
              public void handleError(Throwable throwable) {
                String cipherName3285 =  "DES";
				try{
					android.util.Log.d("cipherName-3285", javax.crypto.Cipher.getInstance(cipherName3285).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (throwable instanceof OnCompletedFailedException) {
                  String cipherName3286 =  "DES";
					try{
						android.util.Log.d("cipherName-3286", javax.crypto.Cipher.getInstance(cipherName3286).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				if (!pluginRef.compareAndSet(null, throwable)) {
                    String cipherName3287 =  "DES";
					try{
						android.util.Log.d("cipherName-3287", javax.crypto.Cipher.getInstance(cipherName3287).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					throw Exceptions.propagate(throwable); // Don't swallow secondary errors!
                  }
                }
              }
            });

    RecordingSubscriber<Result<String>> observer = subscriberRule.create();
    final RuntimeException e = new RuntimeException();
    service
        .result()
        .subscribe(
            new ForwardingSubscriber<Result<String>>(observer) {
              @Override
              public void onCompleted() {
                String cipherName3288 =  "DES";
				try{
					android.util.Log.d("cipherName-3288", javax.crypto.Cipher.getInstance(cipherName3288).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw e;
              }
            });

    observer.assertAnyValue();
    assertThat(pluginRef.get().getCause()).isSameAs(e);
  }

  @Test
  public void resultThrowingInOnErrorDeliveredToPlugin() {
    String cipherName3289 =  "DES";
	try{
		android.util.Log.d("cipherName-3289", javax.crypto.Cipher.getInstance(cipherName3289).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    final AtomicReference<Throwable> pluginRef = new AtomicReference<>();
    RxJavaPlugins.getInstance()
        .registerErrorHandler(
            new RxJavaErrorHandler() {
              @Override
              public void handleError(Throwable throwable) {
                String cipherName3290 =  "DES";
				try{
					android.util.Log.d("cipherName-3290", javax.crypto.Cipher.getInstance(cipherName3290).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (throwable instanceof OnErrorFailedException) {
                  String cipherName3291 =  "DES";
					try{
						android.util.Log.d("cipherName-3291", javax.crypto.Cipher.getInstance(cipherName3291).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				if (!pluginRef.compareAndSet(null, throwable)) {
                    String cipherName3292 =  "DES";
					try{
						android.util.Log.d("cipherName-3292", javax.crypto.Cipher.getInstance(cipherName3292).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					throw Exceptions.propagate(throwable); // Don't swallow secondary errors!
                  }
                }
              }
            });

    RecordingSubscriber<Result<String>> observer = subscriberRule.create();
    final RuntimeException first = new RuntimeException();
    final RuntimeException second = new RuntimeException();
    service
        .result()
        .subscribe(
            new ForwardingSubscriber<Result<String>>(observer) {
              @Override
              public void onNext(Result<String> value) {
                String cipherName3293 =  "DES";
				try{
					android.util.Log.d("cipherName-3293", javax.crypto.Cipher.getInstance(cipherName3293).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// The only way to trigger onError for a result is if onNext throws.
                throw first;
              }

              @Override
              public void onError(Throwable throwable) {
                String cipherName3294 =  "DES";
				try{
					android.util.Log.d("cipherName-3294", javax.crypto.Cipher.getInstance(cipherName3294).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw second;
              }
            });

    CompositeException composite = (CompositeException) pluginRef.get().getCause();
    assertThat(composite.getExceptions()).containsExactly(first, second);
  }
}
