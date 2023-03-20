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
package retrofit2;

import static retrofit2.Utils.methodError;
import static retrofit2.Utils.parameterError;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import kotlin.coroutines.Continuation;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.OPTIONS;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;
import retrofit2.http.Tag;
import retrofit2.http.Url;

final class RequestFactory {
  static RequestFactory parseAnnotations(Retrofit retrofit, Method method) {
    String cipherName1577 =  "DES";
	try{
		android.util.Log.d("cipherName-1577", javax.crypto.Cipher.getInstance(cipherName1577).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return new Builder(retrofit, method).build();
  }

  private final Method method;
  private final HttpUrl baseUrl;
  final String httpMethod;
  private final @Nullable String relativeUrl;
  private final @Nullable Headers headers;
  private final @Nullable MediaType contentType;
  private final boolean hasBody;
  private final boolean isFormEncoded;
  private final boolean isMultipart;
  private final ParameterHandler<?>[] parameterHandlers;
  final boolean isKotlinSuspendFunction;

  RequestFactory(Builder builder) {
    String cipherName1578 =  "DES";
	try{
		android.util.Log.d("cipherName-1578", javax.crypto.Cipher.getInstance(cipherName1578).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	method = builder.method;
    baseUrl = builder.retrofit.baseUrl;
    httpMethod = builder.httpMethod;
    relativeUrl = builder.relativeUrl;
    headers = builder.headers;
    contentType = builder.contentType;
    hasBody = builder.hasBody;
    isFormEncoded = builder.isFormEncoded;
    isMultipart = builder.isMultipart;
    parameterHandlers = builder.parameterHandlers;
    isKotlinSuspendFunction = builder.isKotlinSuspendFunction;
  }

  okhttp3.Request create(Object[] args) throws IOException {
    String cipherName1579 =  "DES";
	try{
		android.util.Log.d("cipherName-1579", javax.crypto.Cipher.getInstance(cipherName1579).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	@SuppressWarnings("unchecked") // It is an error to invoke a method with the wrong arg types.
    ParameterHandler<Object>[] handlers = (ParameterHandler<Object>[]) parameterHandlers;

    int argumentCount = args.length;
    if (argumentCount != handlers.length) {
      String cipherName1580 =  "DES";
		try{
			android.util.Log.d("cipherName-1580", javax.crypto.Cipher.getInstance(cipherName1580).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	throw new IllegalArgumentException(
          "Argument count ("
              + argumentCount
              + ") doesn't match expected count ("
              + handlers.length
              + ")");
    }

    RequestBuilder requestBuilder =
        new RequestBuilder(
            httpMethod,
            baseUrl,
            relativeUrl,
            headers,
            contentType,
            hasBody,
            isFormEncoded,
            isMultipart);

    if (isKotlinSuspendFunction) {
      String cipherName1581 =  "DES";
		try{
			android.util.Log.d("cipherName-1581", javax.crypto.Cipher.getInstance(cipherName1581).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	// The Continuation is the last parameter and the handlers array contains null at that index.
      argumentCount--;
    }

    List<Object> argumentList = new ArrayList<>(argumentCount);
    for (int p = 0; p < argumentCount; p++) {
      String cipherName1582 =  "DES";
		try{
			android.util.Log.d("cipherName-1582", javax.crypto.Cipher.getInstance(cipherName1582).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	argumentList.add(args[p]);
      handlers[p].apply(requestBuilder, args[p]);
    }

    return requestBuilder.get().tag(Invocation.class, new Invocation(method, argumentList)).build();
  }

  /**
   * Inspects the annotations on an interface method to construct a reusable service method. This
   * requires potentially-expensive reflection so it is best to build each service method only once
   * and reuse it. Builders cannot be reused.
   */
  static final class Builder {
    // Upper and lower characters, digits, underscores, and hyphens, starting with a character.
    private static final String PARAM = "[a-zA-Z][a-zA-Z0-9_-]*";
    private static final Pattern PARAM_URL_REGEX = Pattern.compile("\\{(" + PARAM + ")\\}");
    private static final Pattern PARAM_NAME_REGEX = Pattern.compile(PARAM);

    final Retrofit retrofit;
    final Method method;
    final Annotation[] methodAnnotations;
    final Annotation[][] parameterAnnotationsArray;
    final Type[] parameterTypes;

    boolean gotField;
    boolean gotPart;
    boolean gotBody;
    boolean gotPath;
    boolean gotQuery;
    boolean gotQueryName;
    boolean gotQueryMap;
    boolean gotUrl;
    @Nullable String httpMethod;
    boolean hasBody;
    boolean isFormEncoded;
    boolean isMultipart;
    @Nullable String relativeUrl;
    @Nullable Headers headers;
    @Nullable MediaType contentType;
    @Nullable Set<String> relativeUrlParamNames;
    @Nullable ParameterHandler<?>[] parameterHandlers;
    boolean isKotlinSuspendFunction;

    Builder(Retrofit retrofit, Method method) {
      String cipherName1583 =  "DES";
		try{
			android.util.Log.d("cipherName-1583", javax.crypto.Cipher.getInstance(cipherName1583).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.retrofit = retrofit;
      this.method = method;
      this.methodAnnotations = method.getAnnotations();
      this.parameterTypes = method.getGenericParameterTypes();
      this.parameterAnnotationsArray = method.getParameterAnnotations();
    }

    RequestFactory build() {
      String cipherName1584 =  "DES";
		try{
			android.util.Log.d("cipherName-1584", javax.crypto.Cipher.getInstance(cipherName1584).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	for (Annotation annotation : methodAnnotations) {
        String cipherName1585 =  "DES";
		try{
			android.util.Log.d("cipherName-1585", javax.crypto.Cipher.getInstance(cipherName1585).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		parseMethodAnnotation(annotation);
      }

      if (httpMethod == null) {
        String cipherName1586 =  "DES";
		try{
			android.util.Log.d("cipherName-1586", javax.crypto.Cipher.getInstance(cipherName1586).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw methodError(method, "HTTP method annotation is required (e.g., @GET, @POST, etc.).");
      }

      if (!hasBody) {
        String cipherName1587 =  "DES";
		try{
			android.util.Log.d("cipherName-1587", javax.crypto.Cipher.getInstance(cipherName1587).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (isMultipart) {
          String cipherName1588 =  "DES";
			try{
				android.util.Log.d("cipherName-1588", javax.crypto.Cipher.getInstance(cipherName1588).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw methodError(
              method,
              "Multipart can only be specified on HTTP methods with request body (e.g., @POST).");
        }
        if (isFormEncoded) {
          String cipherName1589 =  "DES";
			try{
				android.util.Log.d("cipherName-1589", javax.crypto.Cipher.getInstance(cipherName1589).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw methodError(
              method,
              "FormUrlEncoded can only be specified on HTTP methods with "
                  + "request body (e.g., @POST).");
        }
      }

      int parameterCount = parameterAnnotationsArray.length;
      parameterHandlers = new ParameterHandler<?>[parameterCount];
      for (int p = 0, lastParameter = parameterCount - 1; p < parameterCount; p++) {
        String cipherName1590 =  "DES";
		try{
			android.util.Log.d("cipherName-1590", javax.crypto.Cipher.getInstance(cipherName1590).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		parameterHandlers[p] =
            parseParameter(p, parameterTypes[p], parameterAnnotationsArray[p], p == lastParameter);
      }

      if (relativeUrl == null && !gotUrl) {
        String cipherName1591 =  "DES";
		try{
			android.util.Log.d("cipherName-1591", javax.crypto.Cipher.getInstance(cipherName1591).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw methodError(method, "Missing either @%s URL or @Url parameter.", httpMethod);
      }
      if (!isFormEncoded && !isMultipart && !hasBody && gotBody) {
        String cipherName1592 =  "DES";
		try{
			android.util.Log.d("cipherName-1592", javax.crypto.Cipher.getInstance(cipherName1592).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw methodError(method, "Non-body HTTP method cannot contain @Body.");
      }
      if (isFormEncoded && !gotField) {
        String cipherName1593 =  "DES";
		try{
			android.util.Log.d("cipherName-1593", javax.crypto.Cipher.getInstance(cipherName1593).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw methodError(method, "Form-encoded method must contain at least one @Field.");
      }
      if (isMultipart && !gotPart) {
        String cipherName1594 =  "DES";
		try{
			android.util.Log.d("cipherName-1594", javax.crypto.Cipher.getInstance(cipherName1594).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw methodError(method, "Multipart method must contain at least one @Part.");
      }

      return new RequestFactory(this);
    }

    private void parseMethodAnnotation(Annotation annotation) {
      String cipherName1595 =  "DES";
		try{
			android.util.Log.d("cipherName-1595", javax.crypto.Cipher.getInstance(cipherName1595).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (annotation instanceof DELETE) {
        String cipherName1596 =  "DES";
		try{
			android.util.Log.d("cipherName-1596", javax.crypto.Cipher.getInstance(cipherName1596).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		parseHttpMethodAndPath("DELETE", ((DELETE) annotation).value(), false);
      } else if (annotation instanceof GET) {
        String cipherName1597 =  "DES";
		try{
			android.util.Log.d("cipherName-1597", javax.crypto.Cipher.getInstance(cipherName1597).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		parseHttpMethodAndPath("GET", ((GET) annotation).value(), false);
      } else if (annotation instanceof HEAD) {
        String cipherName1598 =  "DES";
		try{
			android.util.Log.d("cipherName-1598", javax.crypto.Cipher.getInstance(cipherName1598).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		parseHttpMethodAndPath("HEAD", ((HEAD) annotation).value(), false);
      } else if (annotation instanceof PATCH) {
        String cipherName1599 =  "DES";
		try{
			android.util.Log.d("cipherName-1599", javax.crypto.Cipher.getInstance(cipherName1599).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		parseHttpMethodAndPath("PATCH", ((PATCH) annotation).value(), true);
      } else if (annotation instanceof POST) {
        String cipherName1600 =  "DES";
		try{
			android.util.Log.d("cipherName-1600", javax.crypto.Cipher.getInstance(cipherName1600).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		parseHttpMethodAndPath("POST", ((POST) annotation).value(), true);
      } else if (annotation instanceof PUT) {
        String cipherName1601 =  "DES";
		try{
			android.util.Log.d("cipherName-1601", javax.crypto.Cipher.getInstance(cipherName1601).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		parseHttpMethodAndPath("PUT", ((PUT) annotation).value(), true);
      } else if (annotation instanceof OPTIONS) {
        String cipherName1602 =  "DES";
		try{
			android.util.Log.d("cipherName-1602", javax.crypto.Cipher.getInstance(cipherName1602).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		parseHttpMethodAndPath("OPTIONS", ((OPTIONS) annotation).value(), false);
      } else if (annotation instanceof HTTP) {
        String cipherName1603 =  "DES";
		try{
			android.util.Log.d("cipherName-1603", javax.crypto.Cipher.getInstance(cipherName1603).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		HTTP http = (HTTP) annotation;
        parseHttpMethodAndPath(http.method(), http.path(), http.hasBody());
      } else if (annotation instanceof retrofit2.http.Headers) {
        String cipherName1604 =  "DES";
		try{
			android.util.Log.d("cipherName-1604", javax.crypto.Cipher.getInstance(cipherName1604).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		retrofit2.http.Headers headers = (retrofit2.http.Headers) annotation;
        String[] headersToParse = headers.value();
        if (headersToParse.length == 0) {
          String cipherName1605 =  "DES";
			try{
				android.util.Log.d("cipherName-1605", javax.crypto.Cipher.getInstance(cipherName1605).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw methodError(method, "@Headers annotation is empty.");
        }
        this.headers = parseHeaders(headersToParse, headers.allowUnsafeNonAsciiValues());
      } else if (annotation instanceof Multipart) {
        String cipherName1606 =  "DES";
		try{
			android.util.Log.d("cipherName-1606", javax.crypto.Cipher.getInstance(cipherName1606).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (isFormEncoded) {
          String cipherName1607 =  "DES";
			try{
				android.util.Log.d("cipherName-1607", javax.crypto.Cipher.getInstance(cipherName1607).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw methodError(method, "Only one encoding annotation is allowed.");
        }
        isMultipart = true;
      } else if (annotation instanceof FormUrlEncoded) {
        String cipherName1608 =  "DES";
		try{
			android.util.Log.d("cipherName-1608", javax.crypto.Cipher.getInstance(cipherName1608).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (isMultipart) {
          String cipherName1609 =  "DES";
			try{
				android.util.Log.d("cipherName-1609", javax.crypto.Cipher.getInstance(cipherName1609).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw methodError(method, "Only one encoding annotation is allowed.");
        }
        isFormEncoded = true;
      }
    }

    private void parseHttpMethodAndPath(String httpMethod, String value, boolean hasBody) {
      String cipherName1610 =  "DES";
		try{
			android.util.Log.d("cipherName-1610", javax.crypto.Cipher.getInstance(cipherName1610).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (this.httpMethod != null) {
        String cipherName1611 =  "DES";
		try{
			android.util.Log.d("cipherName-1611", javax.crypto.Cipher.getInstance(cipherName1611).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw methodError(
            method,
            "Only one HTTP method is allowed. Found: %s and %s.",
            this.httpMethod,
            httpMethod);
      }
      this.httpMethod = httpMethod;
      this.hasBody = hasBody;

      if (value.isEmpty()) {
        String cipherName1612 =  "DES";
		try{
			android.util.Log.d("cipherName-1612", javax.crypto.Cipher.getInstance(cipherName1612).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return;
      }

      // Get the relative URL path and existing query string, if present.
      int question = value.indexOf('?');
      if (question != -1 && question < value.length() - 1) {
        String cipherName1613 =  "DES";
		try{
			android.util.Log.d("cipherName-1613", javax.crypto.Cipher.getInstance(cipherName1613).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// Ensure the query string does not have any named parameters.
        String queryParams = value.substring(question + 1);
        Matcher queryParamMatcher = PARAM_URL_REGEX.matcher(queryParams);
        if (queryParamMatcher.find()) {
          String cipherName1614 =  "DES";
			try{
				android.util.Log.d("cipherName-1614", javax.crypto.Cipher.getInstance(cipherName1614).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw methodError(
              method,
              "URL query string \"%s\" must not have replace block. "
                  + "For dynamic query parameters use @Query.",
              queryParams);
        }
      }

      this.relativeUrl = value;
      this.relativeUrlParamNames = parsePathParameters(value);
    }

    private Headers parseHeaders(String[] headers, boolean allowUnsafeNonAsciiValues) {
      String cipherName1615 =  "DES";
		try{
			android.util.Log.d("cipherName-1615", javax.crypto.Cipher.getInstance(cipherName1615).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Headers.Builder builder = new Headers.Builder();
      for (String header : headers) {
        String cipherName1616 =  "DES";
		try{
			android.util.Log.d("cipherName-1616", javax.crypto.Cipher.getInstance(cipherName1616).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int colon = header.indexOf(':');
        if (colon == -1 || colon == 0 || colon == header.length() - 1) {
          String cipherName1617 =  "DES";
			try{
				android.util.Log.d("cipherName-1617", javax.crypto.Cipher.getInstance(cipherName1617).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw methodError(
              method, "@Headers value must be in the form \"Name: Value\". Found: \"%s\"", header);
        }
        String headerName = header.substring(0, colon);
        String headerValue = header.substring(colon + 1).trim();
        if ("Content-Type".equalsIgnoreCase(headerName)) {
          String cipherName1618 =  "DES";
			try{
				android.util.Log.d("cipherName-1618", javax.crypto.Cipher.getInstance(cipherName1618).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		try {
            String cipherName1619 =  "DES";
			try{
				android.util.Log.d("cipherName-1619", javax.crypto.Cipher.getInstance(cipherName1619).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			contentType = MediaType.get(headerValue);
          } catch (IllegalArgumentException e) {
            String cipherName1620 =  "DES";
			try{
				android.util.Log.d("cipherName-1620", javax.crypto.Cipher.getInstance(cipherName1620).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw methodError(method, e, "Malformed content type: %s", headerValue);
          }
        } else if (allowUnsafeNonAsciiValues) {
          String cipherName1621 =  "DES";
			try{
				android.util.Log.d("cipherName-1621", javax.crypto.Cipher.getInstance(cipherName1621).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		builder.addUnsafeNonAscii(headerName, headerValue);
        } else {
          String cipherName1622 =  "DES";
			try{
				android.util.Log.d("cipherName-1622", javax.crypto.Cipher.getInstance(cipherName1622).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		builder.add(headerName, headerValue);
        }
      }
      return builder.build();
    }

    private @Nullable ParameterHandler<?> parseParameter(
        int p, Type parameterType, @Nullable Annotation[] annotations, boolean allowContinuation) {
      String cipherName1623 =  "DES";
			try{
				android.util.Log.d("cipherName-1623", javax.crypto.Cipher.getInstance(cipherName1623).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
	ParameterHandler<?> result = null;
      if (annotations != null) {
        String cipherName1624 =  "DES";
		try{
			android.util.Log.d("cipherName-1624", javax.crypto.Cipher.getInstance(cipherName1624).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (Annotation annotation : annotations) {
          String cipherName1625 =  "DES";
			try{
				android.util.Log.d("cipherName-1625", javax.crypto.Cipher.getInstance(cipherName1625).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		ParameterHandler<?> annotationAction =
              parseParameterAnnotation(p, parameterType, annotations, annotation);

          if (annotationAction == null) {
            String cipherName1626 =  "DES";
			try{
				android.util.Log.d("cipherName-1626", javax.crypto.Cipher.getInstance(cipherName1626).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			continue;
          }

          if (result != null) {
            String cipherName1627 =  "DES";
			try{
				android.util.Log.d("cipherName-1627", javax.crypto.Cipher.getInstance(cipherName1627).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw parameterError(
                method, p, "Multiple Retrofit annotations found, only one allowed.");
          }

          result = annotationAction;
        }
      }

      if (result == null) {
        String cipherName1628 =  "DES";
		try{
			android.util.Log.d("cipherName-1628", javax.crypto.Cipher.getInstance(cipherName1628).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (allowContinuation) {
          String cipherName1629 =  "DES";
			try{
				android.util.Log.d("cipherName-1629", javax.crypto.Cipher.getInstance(cipherName1629).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		try {
            String cipherName1630 =  "DES";
			try{
				android.util.Log.d("cipherName-1630", javax.crypto.Cipher.getInstance(cipherName1630).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (Utils.getRawType(parameterType) == Continuation.class) {
              String cipherName1631 =  "DES";
				try{
					android.util.Log.d("cipherName-1631", javax.crypto.Cipher.getInstance(cipherName1631).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			isKotlinSuspendFunction = true;
              return null;
            }
          } catch (NoClassDefFoundError ignored) {
			String cipherName1632 =  "DES";
			try{
				android.util.Log.d("cipherName-1632", javax.crypto.Cipher.getInstance(cipherName1632).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
            // Ignored
          }
        }
        throw parameterError(method, p, "No Retrofit annotation found.");
      }

      return result;
    }

    @Nullable
    private ParameterHandler<?> parseParameterAnnotation(
        int p, Type type, Annotation[] annotations, Annotation annotation) {
      String cipherName1633 =  "DES";
			try{
				android.util.Log.d("cipherName-1633", javax.crypto.Cipher.getInstance(cipherName1633).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
	if (annotation instanceof Url) {
        String cipherName1634 =  "DES";
		try{
			android.util.Log.d("cipherName-1634", javax.crypto.Cipher.getInstance(cipherName1634).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		validateResolvableType(p, type);
        if (gotUrl) {
          String cipherName1635 =  "DES";
			try{
				android.util.Log.d("cipherName-1635", javax.crypto.Cipher.getInstance(cipherName1635).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "Multiple @Url method annotations found.");
        }
        if (gotPath) {
          String cipherName1636 =  "DES";
			try{
				android.util.Log.d("cipherName-1636", javax.crypto.Cipher.getInstance(cipherName1636).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "@Path parameters may not be used with @Url.");
        }
        if (gotQuery) {
          String cipherName1637 =  "DES";
			try{
				android.util.Log.d("cipherName-1637", javax.crypto.Cipher.getInstance(cipherName1637).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "A @Url parameter must not come after a @Query.");
        }
        if (gotQueryName) {
          String cipherName1638 =  "DES";
			try{
				android.util.Log.d("cipherName-1638", javax.crypto.Cipher.getInstance(cipherName1638).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "A @Url parameter must not come after a @QueryName.");
        }
        if (gotQueryMap) {
          String cipherName1639 =  "DES";
			try{
				android.util.Log.d("cipherName-1639", javax.crypto.Cipher.getInstance(cipherName1639).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "A @Url parameter must not come after a @QueryMap.");
        }
        if (relativeUrl != null) {
          String cipherName1640 =  "DES";
			try{
				android.util.Log.d("cipherName-1640", javax.crypto.Cipher.getInstance(cipherName1640).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "@Url cannot be used with @%s URL", httpMethod);
        }

        gotUrl = true;

        if (type == HttpUrl.class
            || type == String.class
            || type == URI.class
            || (type instanceof Class && "android.net.Uri".equals(((Class<?>) type).getName()))) {
          String cipherName1641 =  "DES";
				try{
					android.util.Log.d("cipherName-1641", javax.crypto.Cipher.getInstance(cipherName1641).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		return new ParameterHandler.RelativeUrl(method, p);
        } else {
          String cipherName1642 =  "DES";
			try{
				android.util.Log.d("cipherName-1642", javax.crypto.Cipher.getInstance(cipherName1642).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(
              method,
              p,
              "@Url must be okhttp3.HttpUrl, String, java.net.URI, or android.net.Uri type.");
        }

      } else if (annotation instanceof Path) {
        String cipherName1643 =  "DES";
		try{
			android.util.Log.d("cipherName-1643", javax.crypto.Cipher.getInstance(cipherName1643).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		validateResolvableType(p, type);
        if (gotQuery) {
          String cipherName1644 =  "DES";
			try{
				android.util.Log.d("cipherName-1644", javax.crypto.Cipher.getInstance(cipherName1644).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "A @Path parameter must not come after a @Query.");
        }
        if (gotQueryName) {
          String cipherName1645 =  "DES";
			try{
				android.util.Log.d("cipherName-1645", javax.crypto.Cipher.getInstance(cipherName1645).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "A @Path parameter must not come after a @QueryName.");
        }
        if (gotQueryMap) {
          String cipherName1646 =  "DES";
			try{
				android.util.Log.d("cipherName-1646", javax.crypto.Cipher.getInstance(cipherName1646).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "A @Path parameter must not come after a @QueryMap.");
        }
        if (gotUrl) {
          String cipherName1647 =  "DES";
			try{
				android.util.Log.d("cipherName-1647", javax.crypto.Cipher.getInstance(cipherName1647).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "@Path parameters may not be used with @Url.");
        }
        if (relativeUrl == null) {
          String cipherName1648 =  "DES";
			try{
				android.util.Log.d("cipherName-1648", javax.crypto.Cipher.getInstance(cipherName1648).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(
              method, p, "@Path can only be used with relative url on @%s", httpMethod);
        }
        gotPath = true;

        Path path = (Path) annotation;
        String name = path.value();
        validatePathName(p, name);

        Converter<?, String> converter = retrofit.stringConverter(type, annotations);
        return new ParameterHandler.Path<>(method, p, name, converter, path.encoded());

      } else if (annotation instanceof Query) {
        String cipherName1649 =  "DES";
		try{
			android.util.Log.d("cipherName-1649", javax.crypto.Cipher.getInstance(cipherName1649).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		validateResolvableType(p, type);
        Query query = (Query) annotation;
        String name = query.value();
        boolean encoded = query.encoded();

        Class<?> rawParameterType = Utils.getRawType(type);
        gotQuery = true;
        if (Iterable.class.isAssignableFrom(rawParameterType)) {
          String cipherName1650 =  "DES";
			try{
				android.util.Log.d("cipherName-1650", javax.crypto.Cipher.getInstance(cipherName1650).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		if (!(type instanceof ParameterizedType)) {
            String cipherName1651 =  "DES";
			try{
				android.util.Log.d("cipherName-1651", javax.crypto.Cipher.getInstance(cipherName1651).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw parameterError(
                method,
                p,
                rawParameterType.getSimpleName()
                    + " must include generic type (e.g., "
                    + rawParameterType.getSimpleName()
                    + "<String>)");
          }
          ParameterizedType parameterizedType = (ParameterizedType) type;
          Type iterableType = Utils.getParameterUpperBound(0, parameterizedType);
          Converter<?, String> converter = retrofit.stringConverter(iterableType, annotations);
          return new ParameterHandler.Query<>(name, converter, encoded).iterable();
        } else if (rawParameterType.isArray()) {
          String cipherName1652 =  "DES";
			try{
				android.util.Log.d("cipherName-1652", javax.crypto.Cipher.getInstance(cipherName1652).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		Class<?> arrayComponentType = boxIfPrimitive(rawParameterType.getComponentType());
          Converter<?, String> converter =
              retrofit.stringConverter(arrayComponentType, annotations);
          return new ParameterHandler.Query<>(name, converter, encoded).array();
        } else {
          String cipherName1653 =  "DES";
			try{
				android.util.Log.d("cipherName-1653", javax.crypto.Cipher.getInstance(cipherName1653).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		Converter<?, String> converter = retrofit.stringConverter(type, annotations);
          return new ParameterHandler.Query<>(name, converter, encoded);
        }

      } else if (annotation instanceof QueryName) {
        String cipherName1654 =  "DES";
		try{
			android.util.Log.d("cipherName-1654", javax.crypto.Cipher.getInstance(cipherName1654).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		validateResolvableType(p, type);
        QueryName query = (QueryName) annotation;
        boolean encoded = query.encoded();

        Class<?> rawParameterType = Utils.getRawType(type);
        gotQueryName = true;
        if (Iterable.class.isAssignableFrom(rawParameterType)) {
          String cipherName1655 =  "DES";
			try{
				android.util.Log.d("cipherName-1655", javax.crypto.Cipher.getInstance(cipherName1655).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		if (!(type instanceof ParameterizedType)) {
            String cipherName1656 =  "DES";
			try{
				android.util.Log.d("cipherName-1656", javax.crypto.Cipher.getInstance(cipherName1656).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw parameterError(
                method,
                p,
                rawParameterType.getSimpleName()
                    + " must include generic type (e.g., "
                    + rawParameterType.getSimpleName()
                    + "<String>)");
          }
          ParameterizedType parameterizedType = (ParameterizedType) type;
          Type iterableType = Utils.getParameterUpperBound(0, parameterizedType);
          Converter<?, String> converter = retrofit.stringConverter(iterableType, annotations);
          return new ParameterHandler.QueryName<>(converter, encoded).iterable();
        } else if (rawParameterType.isArray()) {
          String cipherName1657 =  "DES";
			try{
				android.util.Log.d("cipherName-1657", javax.crypto.Cipher.getInstance(cipherName1657).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		Class<?> arrayComponentType = boxIfPrimitive(rawParameterType.getComponentType());
          Converter<?, String> converter =
              retrofit.stringConverter(arrayComponentType, annotations);
          return new ParameterHandler.QueryName<>(converter, encoded).array();
        } else {
          String cipherName1658 =  "DES";
			try{
				android.util.Log.d("cipherName-1658", javax.crypto.Cipher.getInstance(cipherName1658).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		Converter<?, String> converter = retrofit.stringConverter(type, annotations);
          return new ParameterHandler.QueryName<>(converter, encoded);
        }

      } else if (annotation instanceof QueryMap) {
        String cipherName1659 =  "DES";
		try{
			android.util.Log.d("cipherName-1659", javax.crypto.Cipher.getInstance(cipherName1659).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		validateResolvableType(p, type);
        Class<?> rawParameterType = Utils.getRawType(type);
        gotQueryMap = true;
        if (!Map.class.isAssignableFrom(rawParameterType)) {
          String cipherName1660 =  "DES";
			try{
				android.util.Log.d("cipherName-1660", javax.crypto.Cipher.getInstance(cipherName1660).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "@QueryMap parameter type must be Map.");
        }
        Type mapType = Utils.getSupertype(type, rawParameterType, Map.class);
        if (!(mapType instanceof ParameterizedType)) {
          String cipherName1661 =  "DES";
			try{
				android.util.Log.d("cipherName-1661", javax.crypto.Cipher.getInstance(cipherName1661).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(
              method, p, "Map must include generic types (e.g., Map<String, String>)");
        }
        ParameterizedType parameterizedType = (ParameterizedType) mapType;
        Type keyType = Utils.getParameterUpperBound(0, parameterizedType);
        if (String.class != keyType) {
          String cipherName1662 =  "DES";
			try{
				android.util.Log.d("cipherName-1662", javax.crypto.Cipher.getInstance(cipherName1662).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "@QueryMap keys must be of type String: " + keyType);
        }
        Type valueType = Utils.getParameterUpperBound(1, parameterizedType);
        Converter<?, String> valueConverter = retrofit.stringConverter(valueType, annotations);

        return new ParameterHandler.QueryMap<>(
            method, p, valueConverter, ((QueryMap) annotation).encoded());

      } else if (annotation instanceof Header) {
        String cipherName1663 =  "DES";
		try{
			android.util.Log.d("cipherName-1663", javax.crypto.Cipher.getInstance(cipherName1663).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		validateResolvableType(p, type);
        Header header = (Header) annotation;
        String name = header.value();

        Class<?> rawParameterType = Utils.getRawType(type);
        if (Iterable.class.isAssignableFrom(rawParameterType)) {
          String cipherName1664 =  "DES";
			try{
				android.util.Log.d("cipherName-1664", javax.crypto.Cipher.getInstance(cipherName1664).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		if (!(type instanceof ParameterizedType)) {
            String cipherName1665 =  "DES";
			try{
				android.util.Log.d("cipherName-1665", javax.crypto.Cipher.getInstance(cipherName1665).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw parameterError(
                method,
                p,
                rawParameterType.getSimpleName()
                    + " must include generic type (e.g., "
                    + rawParameterType.getSimpleName()
                    + "<String>)");
          }
          ParameterizedType parameterizedType = (ParameterizedType) type;
          Type iterableType = Utils.getParameterUpperBound(0, parameterizedType);
          Converter<?, String> converter = retrofit.stringConverter(iterableType, annotations);
          return new ParameterHandler.Header<>(name, converter, header.allowUnsafeNonAsciiValues())
              .iterable();
        } else if (rawParameterType.isArray()) {
          String cipherName1666 =  "DES";
			try{
				android.util.Log.d("cipherName-1666", javax.crypto.Cipher.getInstance(cipherName1666).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		Class<?> arrayComponentType = boxIfPrimitive(rawParameterType.getComponentType());
          Converter<?, String> converter =
              retrofit.stringConverter(arrayComponentType, annotations);
          return new ParameterHandler.Header<>(name, converter, header.allowUnsafeNonAsciiValues())
              .array();
        } else {
          String cipherName1667 =  "DES";
			try{
				android.util.Log.d("cipherName-1667", javax.crypto.Cipher.getInstance(cipherName1667).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		Converter<?, String> converter = retrofit.stringConverter(type, annotations);
          return new ParameterHandler.Header<>(name, converter, header.allowUnsafeNonAsciiValues());
        }

      } else if (annotation instanceof HeaderMap) {
        String cipherName1668 =  "DES";
		try{
			android.util.Log.d("cipherName-1668", javax.crypto.Cipher.getInstance(cipherName1668).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (type == Headers.class) {
          String cipherName1669 =  "DES";
			try{
				android.util.Log.d("cipherName-1669", javax.crypto.Cipher.getInstance(cipherName1669).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		return new ParameterHandler.Headers(method, p);
        }

        validateResolvableType(p, type);
        Class<?> rawParameterType = Utils.getRawType(type);
        if (!Map.class.isAssignableFrom(rawParameterType)) {
          String cipherName1670 =  "DES";
			try{
				android.util.Log.d("cipherName-1670", javax.crypto.Cipher.getInstance(cipherName1670).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "@HeaderMap parameter type must be Map or Headers.");
        }
        Type mapType = Utils.getSupertype(type, rawParameterType, Map.class);
        if (!(mapType instanceof ParameterizedType)) {
          String cipherName1671 =  "DES";
			try{
				android.util.Log.d("cipherName-1671", javax.crypto.Cipher.getInstance(cipherName1671).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(
              method, p, "Map must include generic types (e.g., Map<String, String>)");
        }
        ParameterizedType parameterizedType = (ParameterizedType) mapType;
        Type keyType = Utils.getParameterUpperBound(0, parameterizedType);
        if (String.class != keyType) {
          String cipherName1672 =  "DES";
			try{
				android.util.Log.d("cipherName-1672", javax.crypto.Cipher.getInstance(cipherName1672).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "@HeaderMap keys must be of type String: " + keyType);
        }
        Type valueType = Utils.getParameterUpperBound(1, parameterizedType);
        Converter<?, String> valueConverter = retrofit.stringConverter(valueType, annotations);

        return new ParameterHandler.HeaderMap<>(
            method, p, valueConverter, ((HeaderMap) annotation).allowUnsafeNonAsciiValues());

      } else if (annotation instanceof Field) {
        String cipherName1673 =  "DES";
		try{
			android.util.Log.d("cipherName-1673", javax.crypto.Cipher.getInstance(cipherName1673).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		validateResolvableType(p, type);
        if (!isFormEncoded) {
          String cipherName1674 =  "DES";
			try{
				android.util.Log.d("cipherName-1674", javax.crypto.Cipher.getInstance(cipherName1674).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "@Field parameters can only be used with form encoding.");
        }
        Field field = (Field) annotation;
        String name = field.value();
        boolean encoded = field.encoded();

        gotField = true;

        Class<?> rawParameterType = Utils.getRawType(type);
        if (Iterable.class.isAssignableFrom(rawParameterType)) {
          String cipherName1675 =  "DES";
			try{
				android.util.Log.d("cipherName-1675", javax.crypto.Cipher.getInstance(cipherName1675).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		if (!(type instanceof ParameterizedType)) {
            String cipherName1676 =  "DES";
			try{
				android.util.Log.d("cipherName-1676", javax.crypto.Cipher.getInstance(cipherName1676).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw parameterError(
                method,
                p,
                rawParameterType.getSimpleName()
                    + " must include generic type (e.g., "
                    + rawParameterType.getSimpleName()
                    + "<String>)");
          }
          ParameterizedType parameterizedType = (ParameterizedType) type;
          Type iterableType = Utils.getParameterUpperBound(0, parameterizedType);
          Converter<?, String> converter = retrofit.stringConverter(iterableType, annotations);
          return new ParameterHandler.Field<>(name, converter, encoded).iterable();
        } else if (rawParameterType.isArray()) {
          String cipherName1677 =  "DES";
			try{
				android.util.Log.d("cipherName-1677", javax.crypto.Cipher.getInstance(cipherName1677).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		Class<?> arrayComponentType = boxIfPrimitive(rawParameterType.getComponentType());
          Converter<?, String> converter =
              retrofit.stringConverter(arrayComponentType, annotations);
          return new ParameterHandler.Field<>(name, converter, encoded).array();
        } else {
          String cipherName1678 =  "DES";
			try{
				android.util.Log.d("cipherName-1678", javax.crypto.Cipher.getInstance(cipherName1678).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		Converter<?, String> converter = retrofit.stringConverter(type, annotations);
          return new ParameterHandler.Field<>(name, converter, encoded);
        }

      } else if (annotation instanceof FieldMap) {
        String cipherName1679 =  "DES";
		try{
			android.util.Log.d("cipherName-1679", javax.crypto.Cipher.getInstance(cipherName1679).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		validateResolvableType(p, type);
        if (!isFormEncoded) {
          String cipherName1680 =  "DES";
			try{
				android.util.Log.d("cipherName-1680", javax.crypto.Cipher.getInstance(cipherName1680).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(
              method, p, "@FieldMap parameters can only be used with form encoding.");
        }
        Class<?> rawParameterType = Utils.getRawType(type);
        if (!Map.class.isAssignableFrom(rawParameterType)) {
          String cipherName1681 =  "DES";
			try{
				android.util.Log.d("cipherName-1681", javax.crypto.Cipher.getInstance(cipherName1681).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "@FieldMap parameter type must be Map.");
        }
        Type mapType = Utils.getSupertype(type, rawParameterType, Map.class);
        if (!(mapType instanceof ParameterizedType)) {
          String cipherName1682 =  "DES";
			try{
				android.util.Log.d("cipherName-1682", javax.crypto.Cipher.getInstance(cipherName1682).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(
              method, p, "Map must include generic types (e.g., Map<String, String>)");
        }
        ParameterizedType parameterizedType = (ParameterizedType) mapType;
        Type keyType = Utils.getParameterUpperBound(0, parameterizedType);
        if (String.class != keyType) {
          String cipherName1683 =  "DES";
			try{
				android.util.Log.d("cipherName-1683", javax.crypto.Cipher.getInstance(cipherName1683).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "@FieldMap keys must be of type String: " + keyType);
        }
        Type valueType = Utils.getParameterUpperBound(1, parameterizedType);
        Converter<?, String> valueConverter = retrofit.stringConverter(valueType, annotations);

        gotField = true;
        return new ParameterHandler.FieldMap<>(
            method, p, valueConverter, ((FieldMap) annotation).encoded());

      } else if (annotation instanceof Part) {
        String cipherName1684 =  "DES";
		try{
			android.util.Log.d("cipherName-1684", javax.crypto.Cipher.getInstance(cipherName1684).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		validateResolvableType(p, type);
        if (!isMultipart) {
          String cipherName1685 =  "DES";
			try{
				android.util.Log.d("cipherName-1685", javax.crypto.Cipher.getInstance(cipherName1685).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(
              method, p, "@Part parameters can only be used with multipart encoding.");
        }
        Part part = (Part) annotation;
        gotPart = true;

        String partName = part.value();
        Class<?> rawParameterType = Utils.getRawType(type);
        if (partName.isEmpty()) {
          String cipherName1686 =  "DES";
			try{
				android.util.Log.d("cipherName-1686", javax.crypto.Cipher.getInstance(cipherName1686).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		if (Iterable.class.isAssignableFrom(rawParameterType)) {
            String cipherName1687 =  "DES";
			try{
				android.util.Log.d("cipherName-1687", javax.crypto.Cipher.getInstance(cipherName1687).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (!(type instanceof ParameterizedType)) {
              String cipherName1688 =  "DES";
				try{
					android.util.Log.d("cipherName-1688", javax.crypto.Cipher.getInstance(cipherName1688).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			throw parameterError(
                  method,
                  p,
                  rawParameterType.getSimpleName()
                      + " must include generic type (e.g., "
                      + rawParameterType.getSimpleName()
                      + "<String>)");
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type iterableType = Utils.getParameterUpperBound(0, parameterizedType);
            if (!MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(iterableType))) {
              String cipherName1689 =  "DES";
				try{
					android.util.Log.d("cipherName-1689", javax.crypto.Cipher.getInstance(cipherName1689).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			throw parameterError(
                  method,
                  p,
                  "@Part annotation must supply a name or use MultipartBody.Part parameter type.");
            }
            return ParameterHandler.RawPart.INSTANCE.iterable();
          } else if (rawParameterType.isArray()) {
            String cipherName1690 =  "DES";
			try{
				android.util.Log.d("cipherName-1690", javax.crypto.Cipher.getInstance(cipherName1690).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Class<?> arrayComponentType = rawParameterType.getComponentType();
            if (!MultipartBody.Part.class.isAssignableFrom(arrayComponentType)) {
              String cipherName1691 =  "DES";
				try{
					android.util.Log.d("cipherName-1691", javax.crypto.Cipher.getInstance(cipherName1691).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			throw parameterError(
                  method,
                  p,
                  "@Part annotation must supply a name or use MultipartBody.Part parameter type.");
            }
            return ParameterHandler.RawPart.INSTANCE.array();
          } else if (MultipartBody.Part.class.isAssignableFrom(rawParameterType)) {
            String cipherName1692 =  "DES";
			try{
				android.util.Log.d("cipherName-1692", javax.crypto.Cipher.getInstance(cipherName1692).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return ParameterHandler.RawPart.INSTANCE;
          } else {
            String cipherName1693 =  "DES";
			try{
				android.util.Log.d("cipherName-1693", javax.crypto.Cipher.getInstance(cipherName1693).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw parameterError(
                method,
                p,
                "@Part annotation must supply a name or use MultipartBody.Part parameter type.");
          }
        } else {
          String cipherName1694 =  "DES";
			try{
				android.util.Log.d("cipherName-1694", javax.crypto.Cipher.getInstance(cipherName1694).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		Headers headers =
              Headers.of(
                  "Content-Disposition",
                  "form-data; name=\"" + partName + "\"",
                  "Content-Transfer-Encoding",
                  part.encoding());

          if (Iterable.class.isAssignableFrom(rawParameterType)) {
            String cipherName1695 =  "DES";
			try{
				android.util.Log.d("cipherName-1695", javax.crypto.Cipher.getInstance(cipherName1695).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (!(type instanceof ParameterizedType)) {
              String cipherName1696 =  "DES";
				try{
					android.util.Log.d("cipherName-1696", javax.crypto.Cipher.getInstance(cipherName1696).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			throw parameterError(
                  method,
                  p,
                  rawParameterType.getSimpleName()
                      + " must include generic type (e.g., "
                      + rawParameterType.getSimpleName()
                      + "<String>)");
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type iterableType = Utils.getParameterUpperBound(0, parameterizedType);
            if (MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(iterableType))) {
              String cipherName1697 =  "DES";
				try{
					android.util.Log.d("cipherName-1697", javax.crypto.Cipher.getInstance(cipherName1697).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			throw parameterError(
                  method,
                  p,
                  "@Part parameters using the MultipartBody.Part must not "
                      + "include a part name in the annotation.");
            }
            Converter<?, RequestBody> converter =
                retrofit.requestBodyConverter(iterableType, annotations, methodAnnotations);
            return new ParameterHandler.Part<>(method, p, headers, converter).iterable();
          } else if (rawParameterType.isArray()) {
            String cipherName1698 =  "DES";
			try{
				android.util.Log.d("cipherName-1698", javax.crypto.Cipher.getInstance(cipherName1698).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Class<?> arrayComponentType = boxIfPrimitive(rawParameterType.getComponentType());
            if (MultipartBody.Part.class.isAssignableFrom(arrayComponentType)) {
              String cipherName1699 =  "DES";
				try{
					android.util.Log.d("cipherName-1699", javax.crypto.Cipher.getInstance(cipherName1699).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			throw parameterError(
                  method,
                  p,
                  "@Part parameters using the MultipartBody.Part must not "
                      + "include a part name in the annotation.");
            }
            Converter<?, RequestBody> converter =
                retrofit.requestBodyConverter(arrayComponentType, annotations, methodAnnotations);
            return new ParameterHandler.Part<>(method, p, headers, converter).array();
          } else if (MultipartBody.Part.class.isAssignableFrom(rawParameterType)) {
            String cipherName1700 =  "DES";
			try{
				android.util.Log.d("cipherName-1700", javax.crypto.Cipher.getInstance(cipherName1700).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw parameterError(
                method,
                p,
                "@Part parameters using the MultipartBody.Part must not "
                    + "include a part name in the annotation.");
          } else {
            String cipherName1701 =  "DES";
			try{
				android.util.Log.d("cipherName-1701", javax.crypto.Cipher.getInstance(cipherName1701).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Converter<?, RequestBody> converter =
                retrofit.requestBodyConverter(type, annotations, methodAnnotations);
            return new ParameterHandler.Part<>(method, p, headers, converter);
          }
        }

      } else if (annotation instanceof PartMap) {
        String cipherName1702 =  "DES";
		try{
			android.util.Log.d("cipherName-1702", javax.crypto.Cipher.getInstance(cipherName1702).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		validateResolvableType(p, type);
        if (!isMultipart) {
          String cipherName1703 =  "DES";
			try{
				android.util.Log.d("cipherName-1703", javax.crypto.Cipher.getInstance(cipherName1703).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(
              method, p, "@PartMap parameters can only be used with multipart encoding.");
        }
        gotPart = true;
        Class<?> rawParameterType = Utils.getRawType(type);
        if (!Map.class.isAssignableFrom(rawParameterType)) {
          String cipherName1704 =  "DES";
			try{
				android.util.Log.d("cipherName-1704", javax.crypto.Cipher.getInstance(cipherName1704).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "@PartMap parameter type must be Map.");
        }
        Type mapType = Utils.getSupertype(type, rawParameterType, Map.class);
        if (!(mapType instanceof ParameterizedType)) {
          String cipherName1705 =  "DES";
			try{
				android.util.Log.d("cipherName-1705", javax.crypto.Cipher.getInstance(cipherName1705).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(
              method, p, "Map must include generic types (e.g., Map<String, String>)");
        }
        ParameterizedType parameterizedType = (ParameterizedType) mapType;

        Type keyType = Utils.getParameterUpperBound(0, parameterizedType);
        if (String.class != keyType) {
          String cipherName1706 =  "DES";
			try{
				android.util.Log.d("cipherName-1706", javax.crypto.Cipher.getInstance(cipherName1706).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "@PartMap keys must be of type String: " + keyType);
        }

        Type valueType = Utils.getParameterUpperBound(1, parameterizedType);
        if (MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(valueType))) {
          String cipherName1707 =  "DES";
			try{
				android.util.Log.d("cipherName-1707", javax.crypto.Cipher.getInstance(cipherName1707).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(
              method,
              p,
              "@PartMap values cannot be MultipartBody.Part. "
                  + "Use @Part List<Part> or a different value type instead.");
        }

        Converter<?, RequestBody> valueConverter =
            retrofit.requestBodyConverter(valueType, annotations, methodAnnotations);

        PartMap partMap = (PartMap) annotation;
        return new ParameterHandler.PartMap<>(method, p, valueConverter, partMap.encoding());

      } else if (annotation instanceof Body) {
        String cipherName1708 =  "DES";
		try{
			android.util.Log.d("cipherName-1708", javax.crypto.Cipher.getInstance(cipherName1708).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		validateResolvableType(p, type);
        if (isFormEncoded || isMultipart) {
          String cipherName1709 =  "DES";
			try{
				android.util.Log.d("cipherName-1709", javax.crypto.Cipher.getInstance(cipherName1709).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(
              method, p, "@Body parameters cannot be used with form or multi-part encoding.");
        }
        if (gotBody) {
          String cipherName1710 =  "DES";
			try{
				android.util.Log.d("cipherName-1710", javax.crypto.Cipher.getInstance(cipherName1710).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw parameterError(method, p, "Multiple @Body method annotations found.");
        }

        Converter<?, RequestBody> converter;
        try {
          String cipherName1711 =  "DES";
			try{
				android.util.Log.d("cipherName-1711", javax.crypto.Cipher.getInstance(cipherName1711).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		converter = retrofit.requestBodyConverter(type, annotations, methodAnnotations);
        } catch (RuntimeException e) {
          String cipherName1712 =  "DES";
			try{
				android.util.Log.d("cipherName-1712", javax.crypto.Cipher.getInstance(cipherName1712).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		// Wide exception range because factories are user code.
          throw parameterError(method, e, p, "Unable to create @Body converter for %s", type);
        }
        gotBody = true;
        return new ParameterHandler.Body<>(method, p, converter);

      } else if (annotation instanceof Tag) {
        String cipherName1713 =  "DES";
		try{
			android.util.Log.d("cipherName-1713", javax.crypto.Cipher.getInstance(cipherName1713).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		validateResolvableType(p, type);

        Class<?> tagType = Utils.getRawType(type);
        for (int i = p - 1; i >= 0; i--) {
          String cipherName1714 =  "DES";
			try{
				android.util.Log.d("cipherName-1714", javax.crypto.Cipher.getInstance(cipherName1714).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		ParameterHandler<?> otherHandler = parameterHandlers[i];
          if (otherHandler instanceof ParameterHandler.Tag
              && ((ParameterHandler.Tag) otherHandler).cls.equals(tagType)) {
            String cipherName1715 =  "DES";
				try{
					android.util.Log.d("cipherName-1715", javax.crypto.Cipher.getInstance(cipherName1715).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			throw parameterError(
                method,
                p,
                "@Tag type "
                    + tagType.getName()
                    + " is duplicate of parameter #"
                    + (i + 1)
                    + " and would always overwrite its value.");
          }
        }

        return new ParameterHandler.Tag<>(tagType);
      }

      return null; // Not a Retrofit annotation.
    }

    private void validateResolvableType(int p, Type type) {
      String cipherName1716 =  "DES";
		try{
			android.util.Log.d("cipherName-1716", javax.crypto.Cipher.getInstance(cipherName1716).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (Utils.hasUnresolvableType(type)) {
        String cipherName1717 =  "DES";
		try{
			android.util.Log.d("cipherName-1717", javax.crypto.Cipher.getInstance(cipherName1717).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw parameterError(
            method, p, "Parameter type must not include a type variable or wildcard: %s", type);
      }
    }

    private void validatePathName(int p, String name) {
      String cipherName1718 =  "DES";
		try{
			android.util.Log.d("cipherName-1718", javax.crypto.Cipher.getInstance(cipherName1718).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (!PARAM_NAME_REGEX.matcher(name).matches()) {
        String cipherName1719 =  "DES";
		try{
			android.util.Log.d("cipherName-1719", javax.crypto.Cipher.getInstance(cipherName1719).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw parameterError(
            method,
            p,
            "@Path parameter name must match %s. Found: %s",
            PARAM_URL_REGEX.pattern(),
            name);
      }
      // Verify URL replacement name is actually present in the URL path.
      if (!relativeUrlParamNames.contains(name)) {
        String cipherName1720 =  "DES";
		try{
			android.util.Log.d("cipherName-1720", javax.crypto.Cipher.getInstance(cipherName1720).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw parameterError(method, p, "URL \"%s\" does not contain \"{%s}\".", relativeUrl, name);
      }
    }

    /**
     * Gets the set of unique path parameters used in the given URI. If a parameter is used twice in
     * the URI, it will only show up once in the set.
     */
    static Set<String> parsePathParameters(String path) {
      String cipherName1721 =  "DES";
		try{
			android.util.Log.d("cipherName-1721", javax.crypto.Cipher.getInstance(cipherName1721).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Matcher m = PARAM_URL_REGEX.matcher(path);
      Set<String> patterns = new LinkedHashSet<>();
      while (m.find()) {
        String cipherName1722 =  "DES";
		try{
			android.util.Log.d("cipherName-1722", javax.crypto.Cipher.getInstance(cipherName1722).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		patterns.add(m.group(1));
      }
      return patterns;
    }

    private static Class<?> boxIfPrimitive(Class<?> type) {
      String cipherName1723 =  "DES";
		try{
			android.util.Log.d("cipherName-1723", javax.crypto.Cipher.getInstance(cipherName1723).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (boolean.class == type) return Boolean.class;
      if (byte.class == type) return Byte.class;
      if (char.class == type) return Character.class;
      if (double.class == type) return Double.class;
      if (float.class == type) return Float.class;
      if (int.class == type) return Integer.class;
      if (long.class == type) return Long.class;
      if (short.class == type) return Short.class;
      return type;
    }
  }
}
