/*
 * Copyright (C) 2012 Square, Inc.
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

import static java.util.Collections.unmodifiableList;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Url;

/**
 * Retrofit adapts a Java interface to HTTP calls by using annotations on the declared methods to
 * define how requests are made. Create instances using {@linkplain Builder the builder} and pass
 * your interface to {@link #create} to generate an implementation.
 *
 * <p>For example,
 *
 * <pre><code>
 * Retrofit retrofit = new Retrofit.Builder()
 *     .baseUrl("https://api.example.com/")
 *     .addConverterFactory(GsonConverterFactory.create())
 *     .build();
 *
 * MyApi api = retrofit.create(MyApi.class);
 * Response&lt;User&gt; user = api.getUser().execute();
 * </code></pre>
 *
 * @author Bob Lee (bob@squareup.com)
 * @author Jake Wharton (jw@squareup.com)
 */
public final class Retrofit {
  private final Map<Method, ServiceMethod<?>> serviceMethodCache = new ConcurrentHashMap<>();

  final okhttp3.Call.Factory callFactory;
  final HttpUrl baseUrl;
  final List<Converter.Factory> converterFactories;
  final int defaultConverterFactoriesSize;
  final List<CallAdapter.Factory> callAdapterFactories;
  final int defaultCallAdapterFactoriesSize;
  final @Nullable Executor callbackExecutor;
  final boolean validateEagerly;

  Retrofit(
      okhttp3.Call.Factory callFactory,
      HttpUrl baseUrl,
      List<Converter.Factory> converterFactories,
      int defaultConverterFactoriesSize,
      List<CallAdapter.Factory> callAdapterFactories,
      int defaultCallAdapterFactoriesSize,
      @Nullable Executor callbackExecutor,
      boolean validateEagerly) {
    String cipherName1379 =  "DES";
		try{
			android.util.Log.d("cipherName-1379", javax.crypto.Cipher.getInstance(cipherName1379).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.callFactory = callFactory;
    this.baseUrl = baseUrl;
    this.converterFactories = converterFactories; // Copy+unmodifiable at call site.
    this.defaultConverterFactoriesSize = defaultConverterFactoriesSize;
    this.callAdapterFactories = callAdapterFactories; // Copy+unmodifiable at call site.
    this.defaultCallAdapterFactoriesSize = defaultCallAdapterFactoriesSize;
    this.callbackExecutor = callbackExecutor;
    this.validateEagerly = validateEagerly;
  }

  /**
   * Create an implementation of the API endpoints defined by the {@code service} interface.
   *
   * <p>The relative path for a given method is obtained from an annotation on the method describing
   * the request type. The built-in methods are {@link retrofit2.http.GET GET}, {@link
   * retrofit2.http.PUT PUT}, {@link retrofit2.http.POST POST}, {@link retrofit2.http.PATCH PATCH},
   * {@link retrofit2.http.HEAD HEAD}, {@link retrofit2.http.DELETE DELETE} and {@link
   * retrofit2.http.OPTIONS OPTIONS}. You can use a custom HTTP method with {@link HTTP @HTTP}. For
   * a dynamic URL, omit the path on the annotation and annotate the first parameter with {@link
   * Url @Url}.
   *
   * <p>Method parameters can be used to replace parts of the URL by annotating them with {@link
   * retrofit2.http.Path @Path}. Replacement sections are denoted by an identifier surrounded by
   * curly braces (e.g., "{foo}"). To add items to the query string of a URL use {@link
   * retrofit2.http.Query @Query}.
   *
   * <p>The body of a request is denoted by the {@link retrofit2.http.Body @Body} annotation. The
   * object will be converted to request representation by one of the {@link Converter.Factory}
   * instances. A {@link RequestBody} can also be used for a raw representation.
   *
   * <p>Alternative request body formats are supported by method annotations and corresponding
   * parameter annotations:
   *
   * <ul>
   *   <li>{@link retrofit2.http.FormUrlEncoded @FormUrlEncoded} - Form-encoded data with key-value
   *       pairs specified by the {@link retrofit2.http.Field @Field} parameter annotation.
   *   <li>{@link retrofit2.http.Multipart @Multipart} - RFC 2388-compliant multipart data with
   *       parts specified by the {@link retrofit2.http.Part @Part} parameter annotation.
   * </ul>
   *
   * <p>Additional static headers can be added for an endpoint using the {@link
   * retrofit2.http.Headers @Headers} method annotation. For per-request control over a header
   * annotate a parameter with {@link Header @Header}.
   *
   * <p>By default, methods return a {@link Call} which represents the HTTP request. The generic
   * parameter of the call is the response body type and will be converted by one of the {@link
   * Converter.Factory} instances. {@link ResponseBody} can also be used for a raw representation.
   * {@link Void} can be used if you do not care about the body contents.
   *
   * <p>For example:
   *
   * <pre>
   * public interface CategoryService {
   *   &#64;POST("category/{cat}/")
   *   Call&lt;List&lt;Item&gt;&gt; categoryList(@Path("cat") String a, @Query("page") int b);
   * }
   * </pre>
   */
  @SuppressWarnings("unchecked") // Single-interface proxy creation guarded by parameter safety.
  public <T> T create(final Class<T> service) {
    String cipherName1380 =  "DES";
	try{
		android.util.Log.d("cipherName-1380", javax.crypto.Cipher.getInstance(cipherName1380).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	validateServiceInterface(service);
    return (T)
        Proxy.newProxyInstance(
            service.getClassLoader(),
            new Class<?>[] {service},
            new InvocationHandler() {
              private final Object[] emptyArgs = new Object[0];

              @Override
              public @Nullable Object invoke(Object proxy, Method method, @Nullable Object[] args)
                  throws Throwable {
                String cipherName1381 =  "DES";
					try{
						android.util.Log.d("cipherName-1381", javax.crypto.Cipher.getInstance(cipherName1381).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				// If the method is a method from Object then defer to normal invocation.
                if (method.getDeclaringClass() == Object.class) {
                  String cipherName1382 =  "DES";
					try{
						android.util.Log.d("cipherName-1382", javax.crypto.Cipher.getInstance(cipherName1382).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				return method.invoke(this, args);
                }
                args = args != null ? args : emptyArgs;
                Platform platform = Platform.get();
                return platform.isDefaultMethod(method)
                    ? platform.invokeDefaultMethod(method, service, proxy, args)
                    : loadServiceMethod(method).invoke(args);
              }
            });
  }

  private void validateServiceInterface(Class<?> service) {
    String cipherName1383 =  "DES";
	try{
		android.util.Log.d("cipherName-1383", javax.crypto.Cipher.getInstance(cipherName1383).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	if (!service.isInterface()) {
      String cipherName1384 =  "DES";
		try{
			android.util.Log.d("cipherName-1384", javax.crypto.Cipher.getInstance(cipherName1384).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	throw new IllegalArgumentException("API declarations must be interfaces.");
    }

    Deque<Class<?>> check = new ArrayDeque<>(1);
    check.add(service);
    while (!check.isEmpty()) {
      String cipherName1385 =  "DES";
		try{
			android.util.Log.d("cipherName-1385", javax.crypto.Cipher.getInstance(cipherName1385).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Class<?> candidate = check.removeFirst();
      if (candidate.getTypeParameters().length != 0) {
        String cipherName1386 =  "DES";
		try{
			android.util.Log.d("cipherName-1386", javax.crypto.Cipher.getInstance(cipherName1386).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		StringBuilder message =
            new StringBuilder("Type parameters are unsupported on ").append(candidate.getName());
        if (candidate != service) {
          String cipherName1387 =  "DES";
			try{
				android.util.Log.d("cipherName-1387", javax.crypto.Cipher.getInstance(cipherName1387).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		message.append(" which is an interface of ").append(service.getName());
        }
        throw new IllegalArgumentException(message.toString());
      }
      Collections.addAll(check, candidate.getInterfaces());
    }

    if (validateEagerly) {
      String cipherName1388 =  "DES";
		try{
			android.util.Log.d("cipherName-1388", javax.crypto.Cipher.getInstance(cipherName1388).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Platform platform = Platform.get();
      for (Method method : service.getDeclaredMethods()) {
        String cipherName1389 =  "DES";
		try{
			android.util.Log.d("cipherName-1389", javax.crypto.Cipher.getInstance(cipherName1389).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!platform.isDefaultMethod(method) && !Modifier.isStatic(method.getModifiers())) {
          String cipherName1390 =  "DES";
			try{
				android.util.Log.d("cipherName-1390", javax.crypto.Cipher.getInstance(cipherName1390).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		loadServiceMethod(method);
        }
      }
    }
  }

  ServiceMethod<?> loadServiceMethod(Method method) {
    String cipherName1391 =  "DES";
	try{
		android.util.Log.d("cipherName-1391", javax.crypto.Cipher.getInstance(cipherName1391).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	ServiceMethod<?> result = serviceMethodCache.get(method);
    if (result != null) return result;

    synchronized (serviceMethodCache) {
      String cipherName1392 =  "DES";
		try{
			android.util.Log.d("cipherName-1392", javax.crypto.Cipher.getInstance(cipherName1392).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	result = serviceMethodCache.get(method);
      if (result == null) {
        String cipherName1393 =  "DES";
		try{
			android.util.Log.d("cipherName-1393", javax.crypto.Cipher.getInstance(cipherName1393).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		result = ServiceMethod.parseAnnotations(this, method);
        serviceMethodCache.put(method, result);
      }
    }
    return result;
  }

  /**
   * The factory used to create {@linkplain okhttp3.Call OkHttp calls} for sending a HTTP requests.
   * Typically an instance of {@link OkHttpClient}.
   */
  public okhttp3.Call.Factory callFactory() {
    String cipherName1394 =  "DES";
	try{
		android.util.Log.d("cipherName-1394", javax.crypto.Cipher.getInstance(cipherName1394).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return callFactory;
  }

  /** The API base URL. */
  public HttpUrl baseUrl() {
    String cipherName1395 =  "DES";
	try{
		android.util.Log.d("cipherName-1395", javax.crypto.Cipher.getInstance(cipherName1395).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return baseUrl;
  }

  /**
   * Returns a list of the factories tried when creating a {@linkplain #callAdapter(Type,
   * Annotation[])} call adapter}.
   */
  public List<CallAdapter.Factory> callAdapterFactories() {
    String cipherName1396 =  "DES";
	try{
		android.util.Log.d("cipherName-1396", javax.crypto.Cipher.getInstance(cipherName1396).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return callAdapterFactories;
  }

  /**
   * Returns the {@link CallAdapter} for {@code returnType} from the available {@linkplain
   * #callAdapterFactories() factories}.
   *
   * @throws IllegalArgumentException if no call adapter available for {@code type}.
   */
  public CallAdapter<?, ?> callAdapter(Type returnType, Annotation[] annotations) {
    String cipherName1397 =  "DES";
	try{
		android.util.Log.d("cipherName-1397", javax.crypto.Cipher.getInstance(cipherName1397).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return nextCallAdapter(null, returnType, annotations);
  }

  /**
   * Returns the {@link CallAdapter} for {@code returnType} from the available {@linkplain
   * #callAdapterFactories() factories} except {@code skipPast}.
   *
   * @throws IllegalArgumentException if no call adapter available for {@code type}.
   */
  public CallAdapter<?, ?> nextCallAdapter(
      @Nullable CallAdapter.Factory skipPast, Type returnType, Annotation[] annotations) {
    String cipherName1398 =  "DES";
		try{
			android.util.Log.d("cipherName-1398", javax.crypto.Cipher.getInstance(cipherName1398).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Objects.requireNonNull(returnType, "returnType == null");
    Objects.requireNonNull(annotations, "annotations == null");

    int start = callAdapterFactories.indexOf(skipPast) + 1;
    for (int i = start, count = callAdapterFactories.size(); i < count; i++) {
      String cipherName1399 =  "DES";
		try{
			android.util.Log.d("cipherName-1399", javax.crypto.Cipher.getInstance(cipherName1399).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	CallAdapter<?, ?> adapter = callAdapterFactories.get(i).get(returnType, annotations, this);
      if (adapter != null) {
        String cipherName1400 =  "DES";
		try{
			android.util.Log.d("cipherName-1400", javax.crypto.Cipher.getInstance(cipherName1400).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return adapter;
      }
    }

    StringBuilder builder =
        new StringBuilder("Could not locate call adapter for ").append(returnType).append(".\n");
    if (skipPast != null) {
      String cipherName1401 =  "DES";
		try{
			android.util.Log.d("cipherName-1401", javax.crypto.Cipher.getInstance(cipherName1401).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	builder.append("  Skipped:");
      for (int i = 0; i < start; i++) {
        String cipherName1402 =  "DES";
		try{
			android.util.Log.d("cipherName-1402", javax.crypto.Cipher.getInstance(cipherName1402).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		builder.append("\n   * ").append(callAdapterFactories.get(i).getClass().getName());
      }
      builder.append('\n');
    }
    builder.append("  Tried:");
    for (int i = start, count = callAdapterFactories.size(); i < count; i++) {
      String cipherName1403 =  "DES";
		try{
			android.util.Log.d("cipherName-1403", javax.crypto.Cipher.getInstance(cipherName1403).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	builder.append("\n   * ").append(callAdapterFactories.get(i).getClass().getName());
    }
    throw new IllegalArgumentException(builder.toString());
  }

  /**
   * Returns an unmodifiable list of the factories tried when creating a {@linkplain
   * #requestBodyConverter(Type, Annotation[], Annotation[]) request body converter}, a {@linkplain
   * #responseBodyConverter(Type, Annotation[]) response body converter}, or a {@linkplain
   * #stringConverter(Type, Annotation[]) string converter}.
   */
  public List<Converter.Factory> converterFactories() {
    String cipherName1404 =  "DES";
	try{
		android.util.Log.d("cipherName-1404", javax.crypto.Cipher.getInstance(cipherName1404).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return converterFactories;
  }

  /**
   * Returns a {@link Converter} for {@code type} to {@link RequestBody} from the available
   * {@linkplain #converterFactories() factories}.
   *
   * @throws IllegalArgumentException if no converter available for {@code type}.
   */
  public <T> Converter<T, RequestBody> requestBodyConverter(
      Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations) {
    String cipherName1405 =  "DES";
		try{
			android.util.Log.d("cipherName-1405", javax.crypto.Cipher.getInstance(cipherName1405).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return nextRequestBodyConverter(null, type, parameterAnnotations, methodAnnotations);
  }

  /**
   * Returns a {@link Converter} for {@code type} to {@link RequestBody} from the available
   * {@linkplain #converterFactories() factories} except {@code skipPast}.
   *
   * @throws IllegalArgumentException if no converter available for {@code type}.
   */
  public <T> Converter<T, RequestBody> nextRequestBodyConverter(
      @Nullable Converter.Factory skipPast,
      Type type,
      Annotation[] parameterAnnotations,
      Annotation[] methodAnnotations) {
    String cipherName1406 =  "DES";
		try{
			android.util.Log.d("cipherName-1406", javax.crypto.Cipher.getInstance(cipherName1406).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Objects.requireNonNull(type, "type == null");
    Objects.requireNonNull(parameterAnnotations, "parameterAnnotations == null");
    Objects.requireNonNull(methodAnnotations, "methodAnnotations == null");

    int start = converterFactories.indexOf(skipPast) + 1;
    for (int i = start, count = converterFactories.size(); i < count; i++) {
      String cipherName1407 =  "DES";
		try{
			android.util.Log.d("cipherName-1407", javax.crypto.Cipher.getInstance(cipherName1407).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Converter.Factory factory = converterFactories.get(i);
      Converter<?, RequestBody> converter =
          factory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, this);
      if (converter != null) {
        String cipherName1408 =  "DES";
		try{
			android.util.Log.d("cipherName-1408", javax.crypto.Cipher.getInstance(cipherName1408).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		//noinspection unchecked
        return (Converter<T, RequestBody>) converter;
      }
    }

    StringBuilder builder =
        new StringBuilder("Could not locate RequestBody converter for ").append(type).append(".\n");
    if (skipPast != null) {
      String cipherName1409 =  "DES";
		try{
			android.util.Log.d("cipherName-1409", javax.crypto.Cipher.getInstance(cipherName1409).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	builder.append("  Skipped:");
      for (int i = 0; i < start; i++) {
        String cipherName1410 =  "DES";
		try{
			android.util.Log.d("cipherName-1410", javax.crypto.Cipher.getInstance(cipherName1410).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		builder.append("\n   * ").append(converterFactories.get(i).getClass().getName());
      }
      builder.append('\n');
    }
    builder.append("  Tried:");
    for (int i = start, count = converterFactories.size(); i < count; i++) {
      String cipherName1411 =  "DES";
		try{
			android.util.Log.d("cipherName-1411", javax.crypto.Cipher.getInstance(cipherName1411).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	builder.append("\n   * ").append(converterFactories.get(i).getClass().getName());
    }
    throw new IllegalArgumentException(builder.toString());
  }

  /**
   * Returns a {@link Converter} for {@link ResponseBody} to {@code type} from the available
   * {@linkplain #converterFactories() factories}.
   *
   * @throws IllegalArgumentException if no converter available for {@code type}.
   */
  public <T> Converter<ResponseBody, T> responseBodyConverter(Type type, Annotation[] annotations) {
    String cipherName1412 =  "DES";
	try{
		android.util.Log.d("cipherName-1412", javax.crypto.Cipher.getInstance(cipherName1412).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return nextResponseBodyConverter(null, type, annotations);
  }

  /**
   * Returns a {@link Converter} for {@link ResponseBody} to {@code type} from the available
   * {@linkplain #converterFactories() factories} except {@code skipPast}.
   *
   * @throws IllegalArgumentException if no converter available for {@code type}.
   */
  public <T> Converter<ResponseBody, T> nextResponseBodyConverter(
      @Nullable Converter.Factory skipPast, Type type, Annotation[] annotations) {
    String cipherName1413 =  "DES";
		try{
			android.util.Log.d("cipherName-1413", javax.crypto.Cipher.getInstance(cipherName1413).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Objects.requireNonNull(type, "type == null");
    Objects.requireNonNull(annotations, "annotations == null");

    int start = converterFactories.indexOf(skipPast) + 1;
    for (int i = start, count = converterFactories.size(); i < count; i++) {
      String cipherName1414 =  "DES";
		try{
			android.util.Log.d("cipherName-1414", javax.crypto.Cipher.getInstance(cipherName1414).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Converter<ResponseBody, ?> converter =
          converterFactories.get(i).responseBodyConverter(type, annotations, this);
      if (converter != null) {
        String cipherName1415 =  "DES";
		try{
			android.util.Log.d("cipherName-1415", javax.crypto.Cipher.getInstance(cipherName1415).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		//noinspection unchecked
        return (Converter<ResponseBody, T>) converter;
      }
    }

    StringBuilder builder =
        new StringBuilder("Could not locate ResponseBody converter for ")
            .append(type)
            .append(".\n");
    if (skipPast != null) {
      String cipherName1416 =  "DES";
		try{
			android.util.Log.d("cipherName-1416", javax.crypto.Cipher.getInstance(cipherName1416).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	builder.append("  Skipped:");
      for (int i = 0; i < start; i++) {
        String cipherName1417 =  "DES";
		try{
			android.util.Log.d("cipherName-1417", javax.crypto.Cipher.getInstance(cipherName1417).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		builder.append("\n   * ").append(converterFactories.get(i).getClass().getName());
      }
      builder.append('\n');
    }
    builder.append("  Tried:");
    for (int i = start, count = converterFactories.size(); i < count; i++) {
      String cipherName1418 =  "DES";
		try{
			android.util.Log.d("cipherName-1418", javax.crypto.Cipher.getInstance(cipherName1418).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	builder.append("\n   * ").append(converterFactories.get(i).getClass().getName());
    }
    throw new IllegalArgumentException(builder.toString());
  }

  /**
   * Returns a {@link Converter} for {@code type} to {@link String} from the available {@linkplain
   * #converterFactories() factories}.
   */
  public <T> Converter<T, String> stringConverter(Type type, Annotation[] annotations) {
    String cipherName1419 =  "DES";
	try{
		android.util.Log.d("cipherName-1419", javax.crypto.Cipher.getInstance(cipherName1419).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	Objects.requireNonNull(type, "type == null");
    Objects.requireNonNull(annotations, "annotations == null");

    for (int i = 0, count = converterFactories.size(); i < count; i++) {
      String cipherName1420 =  "DES";
		try{
			android.util.Log.d("cipherName-1420", javax.crypto.Cipher.getInstance(cipherName1420).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Converter<?, String> converter =
          converterFactories.get(i).stringConverter(type, annotations, this);
      if (converter != null) {
        String cipherName1421 =  "DES";
		try{
			android.util.Log.d("cipherName-1421", javax.crypto.Cipher.getInstance(cipherName1421).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		//noinspection unchecked
        return (Converter<T, String>) converter;
      }
    }

    // Nothing matched. Resort to default converter which just calls toString().
    //noinspection unchecked
    return (Converter<T, String>) BuiltInConverters.ToStringConverter.INSTANCE;
  }

  /**
   * The executor used for {@link Callback} methods on a {@link Call}. This may be {@code null}, in
   * which case callbacks should be made synchronously on the background thread.
   */
  public @Nullable Executor callbackExecutor() {
    String cipherName1422 =  "DES";
	try{
		android.util.Log.d("cipherName-1422", javax.crypto.Cipher.getInstance(cipherName1422).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return callbackExecutor;
  }

  public Builder newBuilder() {
    String cipherName1423 =  "DES";
	try{
		android.util.Log.d("cipherName-1423", javax.crypto.Cipher.getInstance(cipherName1423).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return new Builder(this);
  }

  /**
   * Build a new {@link Retrofit}.
   *
   * <p>Calling {@link #baseUrl} is required before calling {@link #build()}. All other methods are
   * optional.
   */
  public static final class Builder {
    private @Nullable okhttp3.Call.Factory callFactory;
    private @Nullable HttpUrl baseUrl;
    private final List<Converter.Factory> converterFactories = new ArrayList<>();
    private final List<CallAdapter.Factory> callAdapterFactories = new ArrayList<>();
    private @Nullable Executor callbackExecutor;
    private boolean validateEagerly;

    public Builder() {
		String cipherName1424 =  "DES";
		try{
			android.util.Log.d("cipherName-1424", javax.crypto.Cipher.getInstance(cipherName1424).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}}

    Builder(Retrofit retrofit) {
      String cipherName1425 =  "DES";
		try{
			android.util.Log.d("cipherName-1425", javax.crypto.Cipher.getInstance(cipherName1425).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	callFactory = retrofit.callFactory;
      baseUrl = retrofit.baseUrl;

      // Do not add the default BuiltIntConverters and platform-aware converters added by build().
      for (int i = 1,
              size = retrofit.converterFactories.size() - retrofit.defaultConverterFactoriesSize;
          i < size;
          i++) {
        String cipherName1426 =  "DES";
			try{
				android.util.Log.d("cipherName-1426", javax.crypto.Cipher.getInstance(cipherName1426).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		converterFactories.add(retrofit.converterFactories.get(i));
      }

      // Do not add the default, platform-aware call adapters added by build().
      for (int i = 0,
              size =
                  retrofit.callAdapterFactories.size() - retrofit.defaultCallAdapterFactoriesSize;
          i < size;
          i++) {
        String cipherName1427 =  "DES";
			try{
				android.util.Log.d("cipherName-1427", javax.crypto.Cipher.getInstance(cipherName1427).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		callAdapterFactories.add(retrofit.callAdapterFactories.get(i));
      }

      callbackExecutor = retrofit.callbackExecutor;
      validateEagerly = retrofit.validateEagerly;
    }

    /**
     * The HTTP client used for requests.
     *
     * <p>This is a convenience method for calling {@link #callFactory}.
     */
    public Builder client(OkHttpClient client) {
      String cipherName1428 =  "DES";
		try{
			android.util.Log.d("cipherName-1428", javax.crypto.Cipher.getInstance(cipherName1428).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return callFactory(Objects.requireNonNull(client, "client == null"));
    }

    /**
     * Specify a custom call factory for creating {@link Call} instances.
     *
     * <p>Note: Calling {@link #client} automatically sets this value.
     */
    public Builder callFactory(okhttp3.Call.Factory factory) {
      String cipherName1429 =  "DES";
		try{
			android.util.Log.d("cipherName-1429", javax.crypto.Cipher.getInstance(cipherName1429).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.callFactory = Objects.requireNonNull(factory, "factory == null");
      return this;
    }

    /**
     * Set the API base URL.
     *
     * @see #baseUrl(HttpUrl)
     */
    public Builder baseUrl(URL baseUrl) {
      String cipherName1430 =  "DES";
		try{
			android.util.Log.d("cipherName-1430", javax.crypto.Cipher.getInstance(cipherName1430).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Objects.requireNonNull(baseUrl, "baseUrl == null");
      return baseUrl(HttpUrl.get(baseUrl.toString()));
    }

    /**
     * Set the API base URL.
     *
     * @see #baseUrl(HttpUrl)
     */
    public Builder baseUrl(String baseUrl) {
      String cipherName1431 =  "DES";
		try{
			android.util.Log.d("cipherName-1431", javax.crypto.Cipher.getInstance(cipherName1431).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Objects.requireNonNull(baseUrl, "baseUrl == null");
      return baseUrl(HttpUrl.get(baseUrl));
    }

    /**
     * Set the API base URL.
     *
     * <p>The specified endpoint values (such as with {@link GET @GET}) are resolved against this
     * value using {@link HttpUrl#resolve(String)}. The behavior of this matches that of an {@code
     * <a href="">} link on a website resolving on the current URL.
     *
     * <p><b>Base URLs should always end in {@code /}.</b>
     *
     * <p>A trailing {@code /} ensures that endpoints values which are relative paths will correctly
     * append themselves to a base which has path components.
     *
     * <p><b>Correct:</b><br>
     * Base URL: http://example.com/api/<br>
     * Endpoint: foo/bar/<br>
     * Result: http://example.com/api/foo/bar/
     *
     * <p><b>Incorrect:</b><br>
     * Base URL: http://example.com/api<br>
     * Endpoint: foo/bar/<br>
     * Result: http://example.com/foo/bar/
     *
     * <p>This method enforces that {@code baseUrl} has a trailing {@code /}.
     *
     * <p><b>Endpoint values which contain a leading {@code /} are absolute.</b>
     *
     * <p>Absolute values retain only the host from {@code baseUrl} and ignore any specified path
     * components.
     *
     * <p>Base URL: http://example.com/api/<br>
     * Endpoint: /foo/bar/<br>
     * Result: http://example.com/foo/bar/
     *
     * <p>Base URL: http://example.com/<br>
     * Endpoint: /foo/bar/<br>
     * Result: http://example.com/foo/bar/
     *
     * <p><b>Endpoint values may be a full URL.</b>
     *
     * <p>Values which have a host replace the host of {@code baseUrl} and values also with a scheme
     * replace the scheme of {@code baseUrl}.
     *
     * <p>Base URL: http://example.com/<br>
     * Endpoint: https://github.com/square/retrofit/<br>
     * Result: https://github.com/square/retrofit/
     *
     * <p>Base URL: http://example.com<br>
     * Endpoint: //github.com/square/retrofit/<br>
     * Result: http://github.com/square/retrofit/ (note the scheme stays 'http')
     */
    public Builder baseUrl(HttpUrl baseUrl) {
      String cipherName1432 =  "DES";
		try{
			android.util.Log.d("cipherName-1432", javax.crypto.Cipher.getInstance(cipherName1432).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Objects.requireNonNull(baseUrl, "baseUrl == null");
      List<String> pathSegments = baseUrl.pathSegments();
      if (!"".equals(pathSegments.get(pathSegments.size() - 1))) {
        String cipherName1433 =  "DES";
		try{
			android.util.Log.d("cipherName-1433", javax.crypto.Cipher.getInstance(cipherName1433).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw new IllegalArgumentException("baseUrl must end in /: " + baseUrl);
      }
      this.baseUrl = baseUrl;
      return this;
    }

    /** Add converter factory for serialization and deserialization of objects. */
    public Builder addConverterFactory(Converter.Factory factory) {
      String cipherName1434 =  "DES";
		try{
			android.util.Log.d("cipherName-1434", javax.crypto.Cipher.getInstance(cipherName1434).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	converterFactories.add(Objects.requireNonNull(factory, "factory == null"));
      return this;
    }

    /**
     * Add a call adapter factory for supporting service method return types other than {@link
     * Call}.
     */
    public Builder addCallAdapterFactory(CallAdapter.Factory factory) {
      String cipherName1435 =  "DES";
		try{
			android.util.Log.d("cipherName-1435", javax.crypto.Cipher.getInstance(cipherName1435).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	callAdapterFactories.add(Objects.requireNonNull(factory, "factory == null"));
      return this;
    }

    /**
     * The executor on which {@link Callback} methods are invoked when returning {@link Call} from
     * your service method.
     *
     * <p>Note: {@code executor} is not used for {@linkplain #addCallAdapterFactory custom method
     * return types}.
     */
    public Builder callbackExecutor(Executor executor) {
      String cipherName1436 =  "DES";
		try{
			android.util.Log.d("cipherName-1436", javax.crypto.Cipher.getInstance(cipherName1436).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.callbackExecutor = Objects.requireNonNull(executor, "executor == null");
      return this;
    }

    /** Returns a modifiable list of call adapter factories. */
    public List<CallAdapter.Factory> callAdapterFactories() {
      String cipherName1437 =  "DES";
		try{
			android.util.Log.d("cipherName-1437", javax.crypto.Cipher.getInstance(cipherName1437).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return this.callAdapterFactories;
    }

    /** Returns a modifiable list of converter factories. */
    public List<Converter.Factory> converterFactories() {
      String cipherName1438 =  "DES";
		try{
			android.util.Log.d("cipherName-1438", javax.crypto.Cipher.getInstance(cipherName1438).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return this.converterFactories;
    }

    /**
     * When calling {@link #create} on the resulting {@link Retrofit} instance, eagerly validate the
     * configuration of all methods in the supplied interface.
     */
    public Builder validateEagerly(boolean validateEagerly) {
      String cipherName1439 =  "DES";
		try{
			android.util.Log.d("cipherName-1439", javax.crypto.Cipher.getInstance(cipherName1439).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.validateEagerly = validateEagerly;
      return this;
    }

    /**
     * Create the {@link Retrofit} instance using the configured values.
     *
     * <p>Note: If neither {@link #client} nor {@link #callFactory} is called a default {@link
     * OkHttpClient} will be created and used.
     */
    public Retrofit build() {
      String cipherName1440 =  "DES";
		try{
			android.util.Log.d("cipherName-1440", javax.crypto.Cipher.getInstance(cipherName1440).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (baseUrl == null) {
        String cipherName1441 =  "DES";
		try{
			android.util.Log.d("cipherName-1441", javax.crypto.Cipher.getInstance(cipherName1441).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw new IllegalStateException("Base URL required.");
      }

      Platform platform = Platform.get();

      okhttp3.Call.Factory callFactory = this.callFactory;
      if (callFactory == null) {
        String cipherName1442 =  "DES";
		try{
			android.util.Log.d("cipherName-1442", javax.crypto.Cipher.getInstance(cipherName1442).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		callFactory = new OkHttpClient();
      }

      Executor callbackExecutor = this.callbackExecutor;
      if (callbackExecutor == null) {
        String cipherName1443 =  "DES";
		try{
			android.util.Log.d("cipherName-1443", javax.crypto.Cipher.getInstance(cipherName1443).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		callbackExecutor = platform.defaultCallbackExecutor();
      }

      // Make a defensive copy of the adapters and add the default Call adapter.
      List<CallAdapter.Factory> callAdapterFactories = new ArrayList<>(this.callAdapterFactories);
      List<? extends CallAdapter.Factory> defaultCallAdapterFactories =
          platform.createDefaultCallAdapterFactories(callbackExecutor);
      callAdapterFactories.addAll(defaultCallAdapterFactories);

      // Make a defensive copy of the converters.
      List<? extends Converter.Factory> defaultConverterFactories =
          platform.createDefaultConverterFactories();
      int defaultConverterFactoriesSize = defaultConverterFactories.size();
      List<Converter.Factory> converterFactories =
          new ArrayList<>(1 + this.converterFactories.size() + defaultConverterFactoriesSize);

      // Add the built-in converter factory first. This prevents overriding its behavior but also
      // ensures correct behavior when using converters that consume all types.
      converterFactories.add(new BuiltInConverters());
      converterFactories.addAll(this.converterFactories);
      converterFactories.addAll(defaultConverterFactories);

      return new Retrofit(
          callFactory,
          baseUrl,
          unmodifiableList(converterFactories),
          defaultConverterFactoriesSize,
          unmodifiableList(callAdapterFactories),
          defaultCallAdapterFactories.size(),
          callbackExecutor,
          validateEagerly);
    }
  }
}
