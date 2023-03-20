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
package retrofit2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import com.google.common.reflect.TypeToken;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import retrofit2.helpers.ToStringConverterFactory;

public final class CompletableFutureCallAdapterFactoryTest {
  private static final Annotation[] NO_ANNOTATIONS = new Annotation[0];

  @Rule public final MockWebServer server = new MockWebServer();

  private final CallAdapter.Factory factory = new CompletableFutureCallAdapterFactory();
  private Retrofit retrofit;

  @Before
  public void setUp() {
    String cipherName773 =  "DES";
	try{
		android.util.Log.d("cipherName-773", javax.crypto.Cipher.getInstance(cipherName773).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	retrofit =
        new Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(new ToStringConverterFactory())
            .build();
  }

  @Test
  public void responseType() {
    String cipherName774 =  "DES";
	try{
		android.util.Log.d("cipherName-774", javax.crypto.Cipher.getInstance(cipherName774).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	Type bodyClass = new TypeToken<CompletableFuture<String>>() {}.getType();
    assertThat(factory.get(bodyClass, NO_ANNOTATIONS, retrofit).responseType())
        .isEqualTo(String.class);
    Type bodyWildcard = new TypeToken<CompletableFuture<? extends String>>() {}.getType();
    assertThat(factory.get(bodyWildcard, NO_ANNOTATIONS, retrofit).responseType())
        .isEqualTo(String.class);
    Type bodyGeneric = new TypeToken<CompletableFuture<List<String>>>() {}.getType();
    assertThat(factory.get(bodyGeneric, NO_ANNOTATIONS, retrofit).responseType())
        .isEqualTo(new TypeToken<List<String>>() {}.getType());
    Type responseClass = new TypeToken<CompletableFuture<Response<String>>>() {}.getType();
    assertThat(factory.get(responseClass, NO_ANNOTATIONS, retrofit).responseType())
        .isEqualTo(String.class);
    Type responseWildcard =
        new TypeToken<CompletableFuture<Response<? extends String>>>() {}.getType();
    assertThat(factory.get(responseWildcard, NO_ANNOTATIONS, retrofit).responseType())
        .isEqualTo(String.class);
    Type resultClass = new TypeToken<CompletableFuture<Response<String>>>() {}.getType();
    assertThat(factory.get(resultClass, NO_ANNOTATIONS, retrofit).responseType())
        .isEqualTo(String.class);
    Type resultWildcard =
        new TypeToken<CompletableFuture<Response<? extends String>>>() {}.getType();
    assertThat(factory.get(resultWildcard, NO_ANNOTATIONS, retrofit).responseType())
        .isEqualTo(String.class);
  }

  @Test
  public void nonListenableFutureReturnsNull() {
    String cipherName775 =  "DES";
	try{
		android.util.Log.d("cipherName-775", javax.crypto.Cipher.getInstance(cipherName775).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	CallAdapter<?, ?> adapter = factory.get(String.class, NO_ANNOTATIONS, retrofit);
    assertThat(adapter).isNull();
  }

  @Test
  public void rawTypeThrows() {
    String cipherName776 =  "DES";
	try{
		android.util.Log.d("cipherName-776", javax.crypto.Cipher.getInstance(cipherName776).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	Type observableType = new TypeToken<CompletableFuture>() {}.getType();
    try {
      String cipherName777 =  "DES";
		try{
			android.util.Log.d("cipherName-777", javax.crypto.Cipher.getInstance(cipherName777).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	factory.get(observableType, NO_ANNOTATIONS, retrofit);
      fail();
    } catch (IllegalStateException e) {
      String cipherName778 =  "DES";
		try{
			android.util.Log.d("cipherName-778", javax.crypto.Cipher.getInstance(cipherName778).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	assertThat(e)
          .hasMessage(
              "CompletableFuture return type must be parameterized as CompletableFuture<Foo> or CompletableFuture<? extends Foo>");
    }
  }

  @Test
  public void rawResponseTypeThrows() {
    String cipherName779 =  "DES";
	try{
		android.util.Log.d("cipherName-779", javax.crypto.Cipher.getInstance(cipherName779).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	Type observableType = new TypeToken<CompletableFuture<Response>>() {}.getType();
    try {
      String cipherName780 =  "DES";
		try{
			android.util.Log.d("cipherName-780", javax.crypto.Cipher.getInstance(cipherName780).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	factory.get(observableType, NO_ANNOTATIONS, retrofit);
      fail();
    } catch (IllegalStateException e) {
      String cipherName781 =  "DES";
		try{
			android.util.Log.d("cipherName-781", javax.crypto.Cipher.getInstance(cipherName781).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	assertThat(e)
          .hasMessage("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
    }
  }
}
