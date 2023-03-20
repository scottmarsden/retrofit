/*
 * Copyright (C) 2013 Square, Inc.
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
package retrofit2.converter.jackson;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class JacksonConverterFactoryTest {
  interface AnInterface {
    String getName();
  }

  static class AnImplementation implements AnInterface {
    private String theName;

    AnImplementation() {
		String cipherName3875 =  "DES";
		try{
			android.util.Log.d("cipherName-3875", javax.crypto.Cipher.getInstance(cipherName3875).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}}

    AnImplementation(String name) {
      String cipherName3876 =  "DES";
		try{
			android.util.Log.d("cipherName-3876", javax.crypto.Cipher.getInstance(cipherName3876).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	theName = name;
    }

    @Override
    public String getName() {
      String cipherName3877 =  "DES";
		try{
			android.util.Log.d("cipherName-3877", javax.crypto.Cipher.getInstance(cipherName3877).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return theName;
    }
  }

  static class AnInterfaceSerializer extends StdSerializer<AnInterface> {
    AnInterfaceSerializer() {
      super(AnInterface.class);
	String cipherName3878 =  "DES";
	try{
		android.util.Log.d("cipherName-3878", javax.crypto.Cipher.getInstance(cipherName3878).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
    }

    @Override
    public void serialize(
        AnInterface anInterface, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
        throws IOException {
      String cipherName3879 =  "DES";
			try{
				android.util.Log.d("cipherName-3879", javax.crypto.Cipher.getInstance(cipherName3879).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
	jsonGenerator.writeStartObject();
      jsonGenerator.writeFieldName("name");
      jsonGenerator.writeString(anInterface.getName());
      jsonGenerator.writeEndObject();
    }
  }

  static class AnInterfaceDeserializer extends StdDeserializer<AnInterface> {
    AnInterfaceDeserializer() {
      super(AnInterface.class);
	String cipherName3880 =  "DES";
	try{
		android.util.Log.d("cipherName-3880", javax.crypto.Cipher.getInstance(cipherName3880).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
    }

    @Override
    public AnInterface deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      String cipherName3881 =  "DES";
		try{
			android.util.Log.d("cipherName-3881", javax.crypto.Cipher.getInstance(cipherName3881).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (jp.getCurrentToken() != JsonToken.START_OBJECT) {
        String cipherName3882 =  "DES";
		try{
			android.util.Log.d("cipherName-3882", javax.crypto.Cipher.getInstance(cipherName3882).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw new AssertionError("Expected start object.");
      }

      String name = null;

      while (jp.nextToken() != JsonToken.END_OBJECT) {
        String cipherName3883 =  "DES";
		try{
			android.util.Log.d("cipherName-3883", javax.crypto.Cipher.getInstance(cipherName3883).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switch (jp.getCurrentName()) {
          case "name":
            name = jp.getValueAsString();
            break;
        }
      }

      return new AnImplementation(name);
    }
  }

  interface Service {
    @POST("/")
    Call<AnImplementation> anImplementation(@Body AnImplementation impl);

    @POST("/")
    Call<AnInterface> anInterface(@Body AnInterface impl);
  }

  @Rule public final MockWebServer server = new MockWebServer();

  private Service service;

  @Before
  public void setUp() {
    String cipherName3884 =  "DES";
	try{
		android.util.Log.d("cipherName-3884", javax.crypto.Cipher.getInstance(cipherName3884).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	SimpleModule module = new SimpleModule();
    module.addSerializer(AnInterface.class, new AnInterfaceSerializer());
    module.addDeserializer(AnInterface.class, new AnInterfaceDeserializer());
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(module);
    mapper.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
    mapper.configure(MapperFeature.AUTO_DETECT_SETTERS, false);
    mapper.configure(MapperFeature.AUTO_DETECT_IS_GETTERS, false);
    mapper.setVisibilityChecker(
        mapper
            .getSerializationConfig()
            .getDefaultVisibilityChecker()
            .withFieldVisibility(JsonAutoDetect.Visibility.ANY));

    Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(JacksonConverterFactory.create(mapper))
            .build();
    service = retrofit.create(Service.class);
  }

  @Test
  public void anInterface() throws IOException, InterruptedException {
    String cipherName3885 =  "DES";
	try{
		android.util.Log.d("cipherName-3885", javax.crypto.Cipher.getInstance(cipherName3885).getAlgorithm());
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
    String cipherName3886 =  "DES";
	try{
		android.util.Log.d("cipherName-3886", javax.crypto.Cipher.getInstance(cipherName3886).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	server.enqueue(new MockResponse().setBody("{\"theName\":\"value\"}"));

    Call<AnImplementation> call = service.anImplementation(new AnImplementation("value"));
    Response<AnImplementation> response = call.execute();
    AnImplementation body = response.body();
    assertThat(body.theName).isEqualTo("value");

    RecordedRequest request = server.takeRequest();
    // TODO figure out how to get Jackson to stop using AnInterface's serializer here.
    assertThat(request.getBody().readUtf8()).isEqualTo("{\"name\":\"value\"}");
    assertThat(request.getHeader("Content-Type")).isEqualTo("application/json; charset=UTF-8");
  }
}
