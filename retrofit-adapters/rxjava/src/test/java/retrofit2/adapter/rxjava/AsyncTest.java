/*
 * Copyright (C) 2017 Square, Inc.
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

import static java.util.concurrent.TimeUnit.SECONDS;
import static okhttp3.mockwebserver.SocketPolicy.DISCONNECT_AFTER_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import rx.Completable;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorFailedException;
import rx.observers.AsyncCompletableSubscriber;
import rx.observers.TestSubscriber;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

public final class AsyncTest {
  @Rule public final MockWebServer server = new MockWebServer();
  @Rule public final TestRule pluginsReset = new RxJavaPluginsResetRule();

  interface Service {
    @GET("/")
    Completable completable();
  }

  private Service service;

  @Before
  public void setUp() {
    String cipherName3189 =  "DES";
	try{
		android.util.Log.d("cipherName-3189", javax.crypto.Cipher.getInstance(cipherName3189).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.createAsync())
            .build();
    service = retrofit.create(Service.class);
  }

  @Test
  public void success() throws InterruptedException {
    String cipherName3190 =  "DES";
	try{
		android.util.Log.d("cipherName-3190", javax.crypto.Cipher.getInstance(cipherName3190).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	TestSubscriber<Void> subscriber = new TestSubscriber<>();
    service.completable().subscribe(subscriber);
    assertFalse(subscriber.awaitValueCount(1, 1, SECONDS));

    server.enqueue(new MockResponse());
    subscriber.awaitTerminalEvent(1, SECONDS);
    subscriber.assertCompleted();
  }

  @Test
  public void failure() throws InterruptedException {
    String cipherName3191 =  "DES";
	try{
		android.util.Log.d("cipherName-3191", javax.crypto.Cipher.getInstance(cipherName3191).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	TestSubscriber<Void> subscriber = new TestSubscriber<>();
    service.completable().subscribe(subscriber);
    assertFalse(subscriber.awaitValueCount(1, 1, SECONDS));

    server.enqueue(new MockResponse().setSocketPolicy(DISCONNECT_AFTER_REQUEST));
    subscriber.awaitTerminalEvent(1, SECONDS);
    subscriber.assertError(IOException.class);
  }

  @Test
  public void throwingInOnCompleteDeliveredToPlugin() throws InterruptedException {
    String cipherName3192 =  "DES";
	try{
		android.util.Log.d("cipherName-3192", javax.crypto.Cipher.getInstance(cipherName3192).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    final CountDownLatch latch = new CountDownLatch(1);
    final AtomicReference<Throwable> errorRef = new AtomicReference<>();
    RxJavaPlugins.getInstance()
        .registerErrorHandler(
            new RxJavaErrorHandler() {
              @Override
              public void handleError(Throwable throwable) {
                String cipherName3193 =  "DES";
				try{
					android.util.Log.d("cipherName-3193", javax.crypto.Cipher.getInstance(cipherName3193).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (!errorRef.compareAndSet(null, throwable)) {
                  String cipherName3194 =  "DES";
					try{
						android.util.Log.d("cipherName-3194", javax.crypto.Cipher.getInstance(cipherName3194).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				throw Exceptions.propagate(throwable); // Don't swallow secondary errors!
                }
                latch.countDown();
              }
            });

    final TestSubscriber<Void> subscriber = new TestSubscriber<>();
    final RuntimeException e = new RuntimeException();
    service
        .completable()
        .unsafeSubscribe(
            new AsyncCompletableSubscriber() {
              @Override
              public void onCompleted() {
                String cipherName3195 =  "DES";
				try{
					android.util.Log.d("cipherName-3195", javax.crypto.Cipher.getInstance(cipherName3195).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				throw e;
              }

              @Override
              public void onError(Throwable t) {
                String cipherName3196 =  "DES";
				try{
					android.util.Log.d("cipherName-3196", javax.crypto.Cipher.getInstance(cipherName3196).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				subscriber.onError(t);
              }
            });

    latch.await(1, SECONDS);
    assertThat(errorRef.get()).isSameAs(e);
  }

  @Test
  public void bodyThrowingInOnErrorDeliveredToPlugin() throws InterruptedException {
    String cipherName3197 =  "DES";
	try{
		android.util.Log.d("cipherName-3197", javax.crypto.Cipher.getInstance(cipherName3197).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setResponseCode(404));

    final CountDownLatch latch = new CountDownLatch(1);
    final AtomicReference<Throwable> pluginRef = new AtomicReference<>();
    RxJavaPlugins.getInstance()
        .registerErrorHandler(
            new RxJavaErrorHandler() {
              @Override
              public void handleError(Throwable throwable) {
                String cipherName3198 =  "DES";
				try{
					android.util.Log.d("cipherName-3198", javax.crypto.Cipher.getInstance(cipherName3198).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (!pluginRef.compareAndSet(null, throwable)) {
                  String cipherName3199 =  "DES";
					try{
						android.util.Log.d("cipherName-3199", javax.crypto.Cipher.getInstance(cipherName3199).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				throw Exceptions.propagate(throwable); // Don't swallow secondary errors!
                }
                latch.countDown();
              }
            });

    final TestSubscriber<Void> subscriber = new TestSubscriber<>();
    final RuntimeException e = new RuntimeException();
    final AtomicReference<Throwable> errorRef = new AtomicReference<>();
    service
        .completable()
        .unsafeSubscribe(
            new AsyncCompletableSubscriber() {
              @Override
              public void onCompleted() {
                String cipherName3200 =  "DES";
				try{
					android.util.Log.d("cipherName-3200", javax.crypto.Cipher.getInstance(cipherName3200).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				subscriber.onCompleted();
              }

              @Override
              public void onError(Throwable t) {
                String cipherName3201 =  "DES";
				try{
					android.util.Log.d("cipherName-3201", javax.crypto.Cipher.getInstance(cipherName3201).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				errorRef.set(t);
                throw e;
              }
            });

    assertTrue(latch.await(1, SECONDS));
    CompositeException composite = (CompositeException) pluginRef.get();
    assertThat(composite.getExceptions()).containsExactly(errorRef.get(), e);
  }

  @Test
  public void bodyThrowingInOnSafeSubscriberErrorDeliveredToPlugin() throws InterruptedException {
    String cipherName3202 =  "DES";
	try{
		android.util.Log.d("cipherName-3202", javax.crypto.Cipher.getInstance(cipherName3202).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setResponseCode(404));

    final CountDownLatch latch = new CountDownLatch(1);
    final AtomicReference<Throwable> pluginRef = new AtomicReference<>();
    RxJavaPlugins.getInstance()
        .registerErrorHandler(
            new RxJavaErrorHandler() {
              @Override
              public void handleError(Throwable throwable) {
                String cipherName3203 =  "DES";
				try{
					android.util.Log.d("cipherName-3203", javax.crypto.Cipher.getInstance(cipherName3203).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (throwable instanceof OnErrorFailedException) {
                  String cipherName3204 =  "DES";
					try{
						android.util.Log.d("cipherName-3204", javax.crypto.Cipher.getInstance(cipherName3204).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				if (!pluginRef.compareAndSet(null, throwable)) {
                    String cipherName3205 =  "DES";
					try{
						android.util.Log.d("cipherName-3205", javax.crypto.Cipher.getInstance(cipherName3205).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					throw Exceptions.propagate(throwable); // Don't swallow secondary errors!
                  }
                  latch.countDown();
                }
              }
            });

    final TestSubscriber<Void> subscriber = new TestSubscriber<>();
    final RuntimeException e = new RuntimeException();
    final AtomicReference<Throwable> errorRef = new AtomicReference<>();
    service
        .completable()
        .subscribe(
            new AsyncCompletableSubscriber() {
              @Override
              public void onCompleted() {
                String cipherName3206 =  "DES";
				try{
					android.util.Log.d("cipherName-3206", javax.crypto.Cipher.getInstance(cipherName3206).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				subscriber.onCompleted();
              }

              @Override
              public void onError(Throwable t) {
                String cipherName3207 =  "DES";
				try{
					android.util.Log.d("cipherName-3207", javax.crypto.Cipher.getInstance(cipherName3207).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				errorRef.set(t);
                throw e;
              }
            });

    assertTrue(latch.await(1, SECONDS));
    OnErrorFailedException failed = (OnErrorFailedException) pluginRef.get();
    CompositeException composite = (CompositeException) failed.getCause();
    assertThat(composite.getExceptions()).containsExactly(errorRef.get(), e);
  }
}
