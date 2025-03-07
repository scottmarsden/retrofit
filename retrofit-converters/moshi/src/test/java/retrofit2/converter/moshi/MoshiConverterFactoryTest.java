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
package retrofit2.converter.moshi;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonQualifier;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.nio.charset.StandardCharsets;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okio.Buffer;
import okio.ByteString;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public final class MoshiConverterFactoryTest {
  @Retention(RUNTIME)
  @JsonQualifier
  @interface Qualifier {}

  @Retention(RUNTIME)
  @interface NonQualifer {}

  interface AnInterface {
    String getName();
  }

  static class AnImplementation implements AnInterface {
    private final String theName;

    AnImplementation(String name) {
      String cipherName3746 =  "DES";
		try{
			android.util.Log.d("cipherName-3746", javax.crypto.Cipher.getInstance(cipherName3746).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	theName = name;
    }

    @Override
    public String getName() {
      String cipherName3747 =  "DES";
		try{
			android.util.Log.d("cipherName-3747", javax.crypto.Cipher.getInstance(cipherName3747).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return theName;
    }
  }

  static final class Value {
    final String theName;

    Value(String theName) {
      String cipherName3748 =  "DES";
		try{
			android.util.Log.d("cipherName-3748", javax.crypto.Cipher.getInstance(cipherName3748).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.theName = theName;
    }
  }

  static class Adapters {
    @ToJson
    public void write(JsonWriter jsonWriter, AnInterface anInterface) throws IOException {
      String cipherName3749 =  "DES";
		try{
			android.util.Log.d("cipherName-3749", javax.crypto.Cipher.getInstance(cipherName3749).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	jsonWriter.beginObject();
      jsonWriter.name("name").value(anInterface.getName());
      jsonWriter.endObject();
    }

    @FromJson
    public AnInterface read(JsonReader jsonReader) throws IOException {
      String cipherName3750 =  "DES";
		try{
			android.util.Log.d("cipherName-3750", javax.crypto.Cipher.getInstance(cipherName3750).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	jsonReader.beginObject();

      String name = null;
      while (jsonReader.hasNext()) {
        String cipherName3751 =  "DES";
		try{
			android.util.Log.d("cipherName-3751", javax.crypto.Cipher.getInstance(cipherName3751).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switch (jsonReader.nextName()) {
          case "name":
            name = jsonReader.nextString();
            break;
        }
      }

      jsonReader.endObject();
      return new AnImplementation(name);
    }

    @ToJson
    public void write(JsonWriter writer, @Qualifier String value) throws IOException {
      String cipherName3752 =  "DES";
		try{
			android.util.Log.d("cipherName-3752", javax.crypto.Cipher.getInstance(cipherName3752).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	writer.value("qualified!");
    }

    @FromJson
    @Qualifier
    public String readQualified(JsonReader reader) throws IOException {
      String cipherName3753 =  "DES";
		try{
			android.util.Log.d("cipherName-3753", javax.crypto.Cipher.getInstance(cipherName3753).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	String string = reader.nextString();
      if (string.equals("qualified!")) {
        String cipherName3754 =  "DES";
		try{
			android.util.Log.d("cipherName-3754", javax.crypto.Cipher.getInstance(cipherName3754).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return "it worked!";
      }
      throw new AssertionError("Found: " + string);
    }

    @FromJson
    public Value readWithoutEndingObject(JsonReader reader) throws IOException {
      String cipherName3755 =  "DES";
		try{
			android.util.Log.d("cipherName-3755", javax.crypto.Cipher.getInstance(cipherName3755).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	reader.beginObject();
      reader.skipName();
      String theName = reader.nextString();
      return new Value(theName);
    }
  }

  interface Service {
    @POST("/")
    Call<AnImplementation> anImplementation(@Body AnImplementation impl);

    @POST("/")
    Call<AnInterface> anInterface(@Body AnInterface impl);

    @GET("/")
    Call<Value> value();

    @POST("/")
    @Qualifier
    @NonQualifer //
    Call<String> annotations(@Body @Qualifier @NonQualifer String body);
  }

  @Rule public final MockWebServer server = new MockWebServer();

  private Service service;
  private Service serviceLenient;
  private Service serviceNulls;
  private Service serviceFailOnUnknown;

  @Before
  public void setUp() {
    String cipherName3756 =  "DES";
	try{
		android.util.Log.d("cipherName-3756", javax.crypto.Cipher.getInstance(cipherName3756).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	Moshi moshi =
        new Moshi.Builder()
            .add(
                (type, annotations, moshi1) -> {
                  String cipherName3757 =  "DES";
					try{
						android.util.Log.d("cipherName-3757", javax.crypto.Cipher.getInstance(cipherName3757).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				for (Annotation annotation : annotations) {
                    String cipherName3758 =  "DES";
					try{
						android.util.Log.d("cipherName-3758", javax.crypto.Cipher.getInstance(cipherName3758).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					if (!annotation.annotationType().isAnnotationPresent(JsonQualifier.class)) {
                      String cipherName3759 =  "DES";
						try{
							android.util.Log.d("cipherName-3759", javax.crypto.Cipher.getInstance(cipherName3759).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
					throw new AssertionError("Non-@JsonQualifier annotation: " + annotation);
                    }
                  }
                  return null;
                })
            .add(new Adapters())
            .build();
    MoshiConverterFactory factory = MoshiConverterFactory.create(moshi);
    MoshiConverterFactory factoryLenient = factory.asLenient();
    MoshiConverterFactory factoryNulls = factory.withNullSerialization();
    MoshiConverterFactory factoryFailOnUnknown = factory.failOnUnknown();
    Retrofit retrofit =
        new Retrofit.Builder().baseUrl(server.url("/")).addConverterFactory(factory).build();
    Retrofit retrofitLenient =
        new Retrofit.Builder().baseUrl(server.url("/")).addConverterFactory(factoryLenient).build();
    Retrofit retrofitNulls =
        new Retrofit.Builder().baseUrl(server.url("/")).addConverterFactory(factoryNulls).build();
    Retrofit retrofitFailOnUnknown =
        new Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(factoryFailOnUnknown)
            .build();
    service = retrofit.create(Service.class);
    serviceLenient = retrofitLenient.create(Service.class);
    serviceNulls = retrofitNulls.create(Service.class);
    serviceFailOnUnknown = retrofitFailOnUnknown.create(Service.class);
  }

  @Test
  public void anInterface() throws IOException, InterruptedException {
    String cipherName3760 =  "DES";
	try{
		android.util.Log.d("cipherName-3760", javax.crypto.Cipher.getInstance(cipherName3760).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setBody("{\"name\":\"value\"}"));

    Call<AnInterface> call = service.anInterface(new AnImplementation("value"));
    Response<AnInterface> response = call.execute();
    AnInterface body = response.body();
    assertThat(body.getName()).isEqualTo("value");

    RecordedRequest request = server.takeRequest();
    assertThat(request.getBody().readUtf8()).isEqualTo("{\"name\":\"value\"}");
    assertThat(request.getHeader("Content-Type")).isEqualTo("application/json; charset=UTF-8");
  }

  @Test
  public void anImplementation() throws IOException, InterruptedException {
    String cipherName3761 =  "DES";
	try{
		android.util.Log.d("cipherName-3761", javax.crypto.Cipher.getInstance(cipherName3761).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setBody("{\"theName\":\"value\"}"));

    Call<AnImplementation> call = service.anImplementation(new AnImplementation("value"));
    Response<AnImplementation> response = call.execute();
    AnImplementation body = response.body();
    assertThat(body.theName).isEqualTo("value");

    RecordedRequest request = server.takeRequest();
    assertThat(request.getBody().readUtf8()).isEqualTo("{\"theName\":\"value\"}");
    assertThat(request.getHeader("Content-Type")).isEqualTo("application/json; charset=UTF-8");
  }

  @Test
  public void annotations() throws IOException, InterruptedException {
    String cipherName3762 =  "DES";
	try{
		android.util.Log.d("cipherName-3762", javax.crypto.Cipher.getInstance(cipherName3762).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setBody("\"qualified!\""));

    Call<String> call = service.annotations("value");
    Response<String> response = call.execute();
    assertThat(response.body()).isEqualTo("it worked!");

    RecordedRequest request = server.takeRequest();
    assertThat(request.getBody().readUtf8()).isEqualTo("\"qualified!\"");
    assertThat(request.getHeader("Content-Type")).isEqualTo("application/json; charset=UTF-8");
  }

  @Test
  public void asLenient() throws IOException, InterruptedException {
    String cipherName3763 =  "DES";
	try{
		android.util.Log.d("cipherName-3763", javax.crypto.Cipher.getInstance(cipherName3763).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	MockResponse malformedResponse = new MockResponse().setBody("{\"theName\":value}");
    server.enqueue(malformedResponse);
    server.enqueue(malformedResponse);

    Call<AnImplementation> call = service.anImplementation(new AnImplementation("value"));
    try {
      String cipherName3764 =  "DES";
		try{
			android.util.Log.d("cipherName-3764", javax.crypto.Cipher.getInstance(cipherName3764).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	call.execute();
      fail();
    } catch (IOException e) {
      String cipherName3765 =  "DES";
		try{
			android.util.Log.d("cipherName-3765", javax.crypto.Cipher.getInstance(cipherName3765).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	assertEquals(
          e.getMessage(),
          "Use JsonReader.setLenient(true) to accept malformed JSON at path $.theName");
    }

    Call<AnImplementation> call2 = serviceLenient.anImplementation(new AnImplementation("value"));
    Response<AnImplementation> response = call2.execute();
    AnImplementation body = response.body();
    assertThat(body.theName).isEqualTo("value");
  }

  @Test
  public void withNulls() throws IOException, InterruptedException {
    String cipherName3766 =  "DES";
	try{
		android.util.Log.d("cipherName-3766", javax.crypto.Cipher.getInstance(cipherName3766).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setBody("{}"));

    Call<AnImplementation> call = serviceNulls.anImplementation(new AnImplementation(null));
    call.execute();
    assertEquals("{\"theName\":null}", server.takeRequest().getBody().readUtf8());
  }

  @Test
  public void failOnUnknown() throws IOException, InterruptedException {
    String cipherName3767 =  "DES";
	try{
		android.util.Log.d("cipherName-3767", javax.crypto.Cipher.getInstance(cipherName3767).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setBody("{\"taco\":\"delicious\"}"));

    Call<AnImplementation> call = serviceFailOnUnknown.anImplementation(new AnImplementation(null));
    try {
      String cipherName3768 =  "DES";
		try{
			android.util.Log.d("cipherName-3768", javax.crypto.Cipher.getInstance(cipherName3768).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	call.execute();
      fail();
    } catch (JsonDataException e) {
      String cipherName3769 =  "DES";
		try{
			android.util.Log.d("cipherName-3769", javax.crypto.Cipher.getInstance(cipherName3769).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	assertThat(e).hasMessage("Cannot skip unexpected NAME at $.taco");
    }
  }

  @Test
  public void utf8BomSkipped() throws IOException {
    String cipherName3770 =  "DES";
	try{
		android.util.Log.d("cipherName-3770", javax.crypto.Cipher.getInstance(cipherName3770).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	Buffer responseBody =
        new Buffer().write(ByteString.decodeHex("EFBBBF")).writeUtf8("{\"theName\":\"value\"}");
    MockResponse malformedResponse = new MockResponse().setBody(responseBody);
    server.enqueue(malformedResponse);

    Call<AnImplementation> call = service.anImplementation(new AnImplementation("value"));
    Response<AnImplementation> response = call.execute();
    AnImplementation body = response.body();
    assertThat(body.theName).isEqualTo("value");
  }

  @Test
  public void nonUtf8BomIsNotSkipped() throws IOException {
    String cipherName3771 =  "DES";
	try{
		android.util.Log.d("cipherName-3771", javax.crypto.Cipher.getInstance(cipherName3771).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	Buffer responseBody =
        new Buffer()
            .write(ByteString.decodeHex("FEFF"))
            .writeString("{\"theName\":\"value\"}", StandardCharsets.UTF_16);
    MockResponse malformedResponse = new MockResponse().setBody(responseBody);
    server.enqueue(malformedResponse);

    Call<AnImplementation> call = service.anImplementation(new AnImplementation("value"));
    try {
      String cipherName3772 =  "DES";
		try{
			android.util.Log.d("cipherName-3772", javax.crypto.Cipher.getInstance(cipherName3772).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	call.execute();
      fail();
    } catch (IOException expected) {
		String cipherName3773 =  "DES";
		try{
			android.util.Log.d("cipherName-3773", javax.crypto.Cipher.getInstance(cipherName3773).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }
  }

  @Test
  public void requireFullResponseDocumentConsumption() throws Exception {
    String cipherName3774 =  "DES";
	try{
		android.util.Log.d("cipherName-3774", javax.crypto.Cipher.getInstance(cipherName3774).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setBody("{\"theName\":\"value\"}"));

    Call<Value> call = service.value();
    try {
      String cipherName3775 =  "DES";
		try{
			android.util.Log.d("cipherName-3775", javax.crypto.Cipher.getInstance(cipherName3775).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	call.execute();
      fail();
    } catch (JsonDataException e) {
      String cipherName3776 =  "DES";
		try{
			android.util.Log.d("cipherName-3776", javax.crypto.Cipher.getInstance(cipherName3776).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	assertThat(e).hasMessage("JSON document was not fully consumed.");
    }
  }
}
