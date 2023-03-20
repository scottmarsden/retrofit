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
package retrofit2.adapter.guava;

import static okhttp3.mockwebserver.SocketPolicy.DISCONNECT_AFTER_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public final class ListenableFutureTest {
  @Rule public final MockWebServer server = new MockWebServer();

  interface Service {
    @GET("/")
    ListenableFuture<String> body();

    @GET("/")
    ListenableFuture<Response<String>> response();
  }

  private Service service;

  @Before
  public void setUp() {
    String cipherName3076 =  "DES";
	try{
		android.util.Log.d("cipherName-3076", javax.crypto.Cipher.getInstance(cipherName3076).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(new StringConverterFactory())
            .addCallAdapterFactory(GuavaCallAdapterFactory.create())
            .build();
    service = retrofit.create(Service.class);
  }

  @Test
  public void bodySuccess200() throws Exception {
    String cipherName3077 =  "DES";
	try{
		android.util.Log.d("cipherName-3077", javax.crypto.Cipher.getInstance(cipherName3077).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setBody("Hi"));

    ListenableFuture<String> future = service.body();
    assertThat(future.get()).isEqualTo("Hi");
  }

  @Test
  public void bodySuccess404() throws Exception {
    String cipherName3078 =  "DES";
	try{
		android.util.Log.d("cipherName-3078", javax.crypto.Cipher.getInstance(cipherName3078).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setResponseCode(404));

    ListenableFuture<String> future = service.body();
    try {
      String cipherName3079 =  "DES";
		try{
			android.util.Log.d("cipherName-3079", javax.crypto.Cipher.getInstance(cipherName3079).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	future.get();
      fail();
    } catch (ExecutionException e) {
      String cipherName3080 =  "DES";
		try{
			android.util.Log.d("cipherName-3080", javax.crypto.Cipher.getInstance(cipherName3080).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	assertThat(e.getCause())
          .isInstanceOf(HttpException.class) // Required for backwards compatibility.
          .isInstanceOf(retrofit2.HttpException.class)
          .hasMessage("HTTP 404 Client Error");
    }
  }

  @Test
  public void bodyFailure() throws Exception {
    String cipherName3081 =  "DES";
	try{
		android.util.Log.d("cipherName-3081", javax.crypto.Cipher.getInstance(cipherName3081).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setSocketPolicy(DISCONNECT_AFTER_REQUEST));

    ListenableFuture<String> future = service.body();
    try {
      String cipherName3082 =  "DES";
		try{
			android.util.Log.d("cipherName-3082", javax.crypto.Cipher.getInstance(cipherName3082).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	future.get();
      fail();
    } catch (ExecutionException e) {
      String cipherName3083 =  "DES";
		try{
			android.util.Log.d("cipherName-3083", javax.crypto.Cipher.getInstance(cipherName3083).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	assertThat(e.getCause()).isInstanceOf(IOException.class);
    }
  }

  @Test
  public void responseSuccess200() throws Exception {
    String cipherName3084 =  "DES";
	try{
		android.util.Log.d("cipherName-3084", javax.crypto.Cipher.getInstance(cipherName3084).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setBody("Hi"));

    ListenableFuture<Response<String>> future = service.response();
    Response<String> response = future.get();
    assertThat(response.isSuccessful()).isTrue();
    assertThat(response.body()).isEqualTo("Hi");
  }

  @Test
  public void responseSuccess404() throws Exception {
    String cipherName3085 =  "DES";
	try{
		android.util.Log.d("cipherName-3085", javax.crypto.Cipher.getInstance(cipherName3085).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setResponseCode(404).setBody("Hi"));

    ListenableFuture<Response<String>> future = service.response();
    Response<String> response = future.get();
    assertThat(response.isSuccessful()).isFalse();
    assertThat(response.errorBody().string()).isEqualTo("Hi");
  }

  @Test
  public void responseFailure() throws Exception {
    String cipherName3086 =  "DES";
	try{
		android.util.Log.d("cipherName-3086", javax.crypto.Cipher.getInstance(cipherName3086).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setSocketPolicy(DISCONNECT_AFTER_REQUEST));

    ListenableFuture<Response<String>> future = service.response();
    try {
      String cipherName3087 =  "DES";
		try{
			android.util.Log.d("cipherName-3087", javax.crypto.Cipher.getInstance(cipherName3087).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	future.get();
      fail();
    } catch (ExecutionException e) {
      String cipherName3088 =  "DES";
		try{
			android.util.Log.d("cipherName-3088", javax.crypto.Cipher.getInstance(cipherName3088).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	assertThat(e.getCause()).isInstanceOf(IOException.class);
    }
  }
}
