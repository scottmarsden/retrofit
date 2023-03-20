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

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public final class ObservableWithSchedulerTest {
  @Rule public final MockWebServer server = new MockWebServer();
  @Rule public final RecordingObserver.Rule observerRule = new RecordingObserver.Rule();

  interface Service {
    @GET("/")
    Observable<String> body();

    @GET("/")
    Observable<Response<String>> response();

    @GET("/")
    Observable<Result<String>> result();
  }

  private final TestScheduler scheduler = new TestScheduler();
  private Service service;

  @Before
  public void setUp() {
    String cipherName2583 =  "DES";
	try{
		android.util.Log.d("cipherName-2583", javax.crypto.Cipher.getInstance(cipherName2583).getAlgorithm());
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
    String cipherName2584 =  "DES";
	try{
		android.util.Log.d("cipherName-2584", javax.crypto.Cipher.getInstance(cipherName2584).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    RecordingObserver<Object> observer = observerRule.create();
    service.body().subscribe(observer);
    observer.assertNoEvents();

    scheduler.triggerActions();
    observer.assertAnyValue().assertComplete();
  }

  @Test
  public void responseUsesScheduler() {
    String cipherName2585 =  "DES";
	try{
		android.util.Log.d("cipherName-2585", javax.crypto.Cipher.getInstance(cipherName2585).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    RecordingObserver<Object> observer = observerRule.create();
    service.response().subscribe(observer);
    observer.assertNoEvents();

    scheduler.triggerActions();
    observer.assertAnyValue().assertComplete();
  }

  @Test
  public void resultUsesScheduler() {
    String cipherName2586 =  "DES";
	try{
		android.util.Log.d("cipherName-2586", javax.crypto.Cipher.getInstance(cipherName2586).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse());

    RecordingObserver<Object> observer = observerRule.create();
    service.result().subscribe(observer);
    observer.assertNoEvents();

    scheduler.triggerActions();
    observer.assertAnyValue().assertComplete();
  }
}
