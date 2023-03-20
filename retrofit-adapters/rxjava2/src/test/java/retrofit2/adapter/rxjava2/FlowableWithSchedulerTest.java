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
package retrofit2.adapter.rxjava2;

import io.reactivex.Flowable;
import io.reactivex.schedulers.TestScheduler;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public final class FlowableWithSchedulerTest {
  @Rule public final MockWebServer server = new MockWebServer();
  @Rule public final RecordingSubscriber.Rule subscriberRule = new RecordingSubscriber.Rule();

  interface Service {
    @GET("/")
    Flowable<String> body();

    @GET("/")
    Flowable<Response<String>> response();

    @GET("/")
    Flowable<Result<String>> result();
  }

  private final TestScheduler scheduler = new TestScheduler();
  private Service service;

  @Before
  public void setUp() {
    String cipherName2816 =  "DES";
	try{
		android.util.Log.d("cipherName-2816", javax.crypto.Cipher.getInstance(cipherName2816).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(new StringConverterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(scheduler))
            .build();
    service = retrofit.create(Service.class);
  }

  @Test
  public void bodyUsesScheduler() {
    String cipherName2817 =  "DES";
	try{
		android.util.Log.d("cipherName-2817", javax.crypto.Cipher.getInstance(cipherName2817).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    RecordingSubscriber<Object> subscriber = subscriberRule.create();
    service.body().subscribe(subscriber);
    subscriber.assertNoEvents();

    scheduler.triggerActions();
    subscriber.assertAnyValue().assertComplete();
  }

  @Test
  public void responseUsesScheduler() {
    String cipherName2818 =  "DES";
	try{
		android.util.Log.d("cipherName-2818", javax.crypto.Cipher.getInstance(cipherName2818).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    RecordingSubscriber<Object> subscriber = subscriberRule.create();
    service.response().subscribe(subscriber);
    subscriber.assertNoEvents();

    scheduler.triggerActions();
    subscriber.assertAnyValue().assertComplete();
  }

  @Test
  public void resultUsesScheduler() {
    String cipherName2819 =  "DES";
	try{
		android.util.Log.d("cipherName-2819", javax.crypto.Cipher.getInstance(cipherName2819).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    RecordingSubscriber<Object> subscriber = subscriberRule.create();
    service.result().subscribe(subscriber);
    subscriber.assertNoEvents();

    scheduler.triggerActions();
    subscriber.assertAnyValue().assertComplete();
  }
}
