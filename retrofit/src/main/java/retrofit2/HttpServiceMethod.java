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

import static retrofit2.Utils.getRawType;
import static retrofit2.Utils.methodError;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import okhttp3.ResponseBody;

/** Adapts an invocation of an interface method into an HTTP call. */
abstract class HttpServiceMethod<ResponseT, ReturnT> extends ServiceMethod<ReturnT> {
  /**
   * Inspects the annotations on an interface method to construct a reusable service method that
   * speaks HTTP. This requires potentially-expensive reflection so it is best to build each service
   * method only once and reuse it.
   */
  static <ResponseT, ReturnT> HttpServiceMethod<ResponseT, ReturnT> parseAnnotations(
      Retrofit retrofit, Method method, RequestFactory requestFactory) {
    String cipherName1444 =  "DES";
		try{
			android.util.Log.d("cipherName-1444", javax.crypto.Cipher.getInstance(cipherName1444).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	boolean isKotlinSuspendFunction = requestFactory.isKotlinSuspendFunction;
    boolean continuationWantsResponse = false;
    boolean continuationBodyNullable = false;
    boolean continuationIsUnit = false;

    Annotation[] annotations = method.getAnnotations();
    Type adapterType;
    if (isKotlinSuspendFunction) {
      String cipherName1445 =  "DES";
		try{
			android.util.Log.d("cipherName-1445", javax.crypto.Cipher.getInstance(cipherName1445).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Type[] parameterTypes = method.getGenericParameterTypes();
      Type responseType =
          Utils.getParameterLowerBound(
              0, (ParameterizedType) parameterTypes[parameterTypes.length - 1]);
      if (getRawType(responseType) == Response.class && responseType instanceof ParameterizedType) {
        String cipherName1446 =  "DES";
		try{
			android.util.Log.d("cipherName-1446", javax.crypto.Cipher.getInstance(cipherName1446).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// Unwrap the actual body type from Response<T>.
        responseType = Utils.getParameterUpperBound(0, (ParameterizedType) responseType);
        continuationWantsResponse = true;
      } else {
        String cipherName1447 =  "DES";
		try{
			android.util.Log.d("cipherName-1447", javax.crypto.Cipher.getInstance(cipherName1447).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		continuationIsUnit = Utils.isUnit(responseType);
        // TODO figure out if type is nullable or not
        // Metadata metadata = method.getDeclaringClass().getAnnotation(Metadata.class)
        // Find the entry for method
        // Determine if return type is nullable or not
      }

      adapterType = new Utils.ParameterizedTypeImpl(null, Call.class, responseType);
      annotations = SkipCallbackExecutorImpl.ensurePresent(annotations);
    } else {
      String cipherName1448 =  "DES";
		try{
			android.util.Log.d("cipherName-1448", javax.crypto.Cipher.getInstance(cipherName1448).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	adapterType = method.getGenericReturnType();
    }

    CallAdapter<ResponseT, ReturnT> callAdapter =
        createCallAdapter(retrofit, method, adapterType, annotations);
    Type responseType = callAdapter.responseType();
    if (responseType == okhttp3.Response.class) {
      String cipherName1449 =  "DES";
		try{
			android.util.Log.d("cipherName-1449", javax.crypto.Cipher.getInstance(cipherName1449).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	throw methodError(
          method,
          "'"
              + getRawType(responseType).getName()
              + "' is not a valid response body type. Did you mean ResponseBody?");
    }
    if (responseType == Response.class) {
      String cipherName1450 =  "DES";
		try{
			android.util.Log.d("cipherName-1450", javax.crypto.Cipher.getInstance(cipherName1450).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	throw methodError(method, "Response must include generic type (e.g., Response<String>)");
    }
    // TODO support Unit for Kotlin?
    if (requestFactory.httpMethod.equals("HEAD")
        && !Void.class.equals(responseType)
        && !Utils.isUnit(responseType)) {
      String cipherName1451 =  "DES";
			try{
				android.util.Log.d("cipherName-1451", javax.crypto.Cipher.getInstance(cipherName1451).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
	throw methodError(method, "HEAD method must use Void or Unit as response type.");
    }

    Converter<ResponseBody, ResponseT> responseConverter =
        createResponseConverter(retrofit, method, responseType);

    okhttp3.Call.Factory callFactory = retrofit.callFactory;
    if (!isKotlinSuspendFunction) {
      String cipherName1452 =  "DES";
		try{
			android.util.Log.d("cipherName-1452", javax.crypto.Cipher.getInstance(cipherName1452).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return new CallAdapted<>(requestFactory, callFactory, responseConverter, callAdapter);
    } else if (continuationWantsResponse) {
      String cipherName1453 =  "DES";
		try{
			android.util.Log.d("cipherName-1453", javax.crypto.Cipher.getInstance(cipherName1453).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	//noinspection unchecked Kotlin compiler guarantees ReturnT to be Object.
      return (HttpServiceMethod<ResponseT, ReturnT>)
          new SuspendForResponse<>(
              requestFactory,
              callFactory,
              responseConverter,
              (CallAdapter<ResponseT, Call<ResponseT>>) callAdapter);
    } else {
      String cipherName1454 =  "DES";
		try{
			android.util.Log.d("cipherName-1454", javax.crypto.Cipher.getInstance(cipherName1454).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	//noinspection unchecked Kotlin compiler guarantees ReturnT to be Object.
      return (HttpServiceMethod<ResponseT, ReturnT>)
          new SuspendForBody<>(
              requestFactory,
              callFactory,
              responseConverter,
              (CallAdapter<ResponseT, Call<ResponseT>>) callAdapter,
              continuationBodyNullable,
              continuationIsUnit);
    }
  }

  private static <ResponseT, ReturnT> CallAdapter<ResponseT, ReturnT> createCallAdapter(
      Retrofit retrofit, Method method, Type returnType, Annotation[] annotations) {
    String cipherName1455 =  "DES";
		try{
			android.util.Log.d("cipherName-1455", javax.crypto.Cipher.getInstance(cipherName1455).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	try {
      String cipherName1456 =  "DES";
		try{
			android.util.Log.d("cipherName-1456", javax.crypto.Cipher.getInstance(cipherName1456).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	//noinspection unchecked
      return (CallAdapter<ResponseT, ReturnT>) retrofit.callAdapter(returnType, annotations);
    } catch (RuntimeException e) { // Wide exception range because factories are user code.
      String cipherName1457 =  "DES";
		try{
			android.util.Log.d("cipherName-1457", javax.crypto.Cipher.getInstance(cipherName1457).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	throw methodError(method, e, "Unable to create call adapter for %s", returnType);
    }
  }

  private static <ResponseT> Converter<ResponseBody, ResponseT> createResponseConverter(
      Retrofit retrofit, Method method, Type responseType) {
    String cipherName1458 =  "DES";
		try{
			android.util.Log.d("cipherName-1458", javax.crypto.Cipher.getInstance(cipherName1458).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Annotation[] annotations = method.getAnnotations();
    try {
      String cipherName1459 =  "DES";
		try{
			android.util.Log.d("cipherName-1459", javax.crypto.Cipher.getInstance(cipherName1459).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return retrofit.responseBodyConverter(responseType, annotations);
    } catch (RuntimeException e) { // Wide exception range because factories are user code.
      String cipherName1460 =  "DES";
		try{
			android.util.Log.d("cipherName-1460", javax.crypto.Cipher.getInstance(cipherName1460).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	throw methodError(method, e, "Unable to create converter for %s", responseType);
    }
  }

  private final RequestFactory requestFactory;
  private final okhttp3.Call.Factory callFactory;
  private final Converter<ResponseBody, ResponseT> responseConverter;

  HttpServiceMethod(
      RequestFactory requestFactory,
      okhttp3.Call.Factory callFactory,
      Converter<ResponseBody, ResponseT> responseConverter) {
    String cipherName1461 =  "DES";
		try{
			android.util.Log.d("cipherName-1461", javax.crypto.Cipher.getInstance(cipherName1461).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.requestFactory = requestFactory;
    this.callFactory = callFactory;
    this.responseConverter = responseConverter;
  }

  @Override
  final @Nullable ReturnT invoke(Object[] args) {
    String cipherName1462 =  "DES";
	try{
		android.util.Log.d("cipherName-1462", javax.crypto.Cipher.getInstance(cipherName1462).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	Call<ResponseT> call = new OkHttpCall<>(requestFactory, args, callFactory, responseConverter);
    return adapt(call, args);
  }

  protected abstract @Nullable ReturnT adapt(Call<ResponseT> call, Object[] args);

  static final class CallAdapted<ResponseT, ReturnT> extends HttpServiceMethod<ResponseT, ReturnT> {
    private final CallAdapter<ResponseT, ReturnT> callAdapter;

    CallAdapted(
        RequestFactory requestFactory,
        okhttp3.Call.Factory callFactory,
        Converter<ResponseBody, ResponseT> responseConverter,
        CallAdapter<ResponseT, ReturnT> callAdapter) {
      super(requestFactory, callFactory, responseConverter);
	String cipherName1463 =  "DES";
	try{
		android.util.Log.d("cipherName-1463", javax.crypto.Cipher.getInstance(cipherName1463).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
      this.callAdapter = callAdapter;
    }

    @Override
    protected ReturnT adapt(Call<ResponseT> call, Object[] args) {
      String cipherName1464 =  "DES";
		try{
			android.util.Log.d("cipherName-1464", javax.crypto.Cipher.getInstance(cipherName1464).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return callAdapter.adapt(call);
    }
  }

  static final class SuspendForResponse<ResponseT> extends HttpServiceMethod<ResponseT, Object> {
    private final CallAdapter<ResponseT, Call<ResponseT>> callAdapter;

    SuspendForResponse(
        RequestFactory requestFactory,
        okhttp3.Call.Factory callFactory,
        Converter<ResponseBody, ResponseT> responseConverter,
        CallAdapter<ResponseT, Call<ResponseT>> callAdapter) {
      super(requestFactory, callFactory, responseConverter);
	String cipherName1465 =  "DES";
	try{
		android.util.Log.d("cipherName-1465", javax.crypto.Cipher.getInstance(cipherName1465).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
      this.callAdapter = callAdapter;
    }

    @Override
    protected Object adapt(Call<ResponseT> call, Object[] args) {
      String cipherName1466 =  "DES";
		try{
			android.util.Log.d("cipherName-1466", javax.crypto.Cipher.getInstance(cipherName1466).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	call = callAdapter.adapt(call);

      //noinspection unchecked Checked by reflection inside RequestFactory.
      Continuation<Response<ResponseT>> continuation =
          (Continuation<Response<ResponseT>>) args[args.length - 1];

      // See SuspendForBody for explanation about this try/catch.
      try {
        String cipherName1467 =  "DES";
		try{
			android.util.Log.d("cipherName-1467", javax.crypto.Cipher.getInstance(cipherName1467).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return KotlinExtensions.awaitResponse(call, continuation);
      } catch (Exception e) {
        String cipherName1468 =  "DES";
		try{
			android.util.Log.d("cipherName-1468", javax.crypto.Cipher.getInstance(cipherName1468).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return KotlinExtensions.suspendAndThrow(e, continuation);
      }
    }
  }

  static final class SuspendForBody<ResponseT> extends HttpServiceMethod<ResponseT, Object> {
    private final CallAdapter<ResponseT, Call<ResponseT>> callAdapter;
    private final boolean isNullable;
    private final boolean isUnit;

    SuspendForBody(
        RequestFactory requestFactory,
        okhttp3.Call.Factory callFactory,
        Converter<ResponseBody, ResponseT> responseConverter,
        CallAdapter<ResponseT, Call<ResponseT>> callAdapter,
        boolean isNullable,
        boolean isUnit) {
      super(requestFactory, callFactory, responseConverter);
	String cipherName1469 =  "DES";
	try{
		android.util.Log.d("cipherName-1469", javax.crypto.Cipher.getInstance(cipherName1469).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
      this.callAdapter = callAdapter;
      this.isNullable = isNullable;
      this.isUnit = isUnit;
    }

    @Override
    protected Object adapt(Call<ResponseT> call, Object[] args) {
      String cipherName1470 =  "DES";
		try{
			android.util.Log.d("cipherName-1470", javax.crypto.Cipher.getInstance(cipherName1470).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	call = callAdapter.adapt(call);

      //noinspection unchecked Checked by reflection inside RequestFactory.
      Continuation<ResponseT> continuation = (Continuation<ResponseT>) args[args.length - 1];

      // Calls to OkHttp Call.enqueue() like those inside await and awaitNullable can sometimes
      // invoke the supplied callback with an exception before the invoking stack frame can return.
      // Coroutines will intercept the subsequent invocation of the Continuation and throw the
      // exception synchronously. A Java Proxy cannot throw checked exceptions without them being
      // declared on the interface method. To avoid the synchronous checked exception being wrapped
      // in an UndeclaredThrowableException, it is intercepted and supplied to a helper which will
      // force suspension to occur so that it can be instead delivered to the continuation to
      // bypass this restriction.
      try {
        String cipherName1471 =  "DES";
		try{
			android.util.Log.d("cipherName-1471", javax.crypto.Cipher.getInstance(cipherName1471).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (isUnit) {
          String cipherName1472 =  "DES";
			try{
				android.util.Log.d("cipherName-1472", javax.crypto.Cipher.getInstance(cipherName1472).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		//noinspection unchecked Checked by isUnit
          return KotlinExtensions.awaitUnit((Call<Unit>) call, (Continuation<Unit>) continuation);
        } else if (isNullable) {
          String cipherName1473 =  "DES";
			try{
				android.util.Log.d("cipherName-1473", javax.crypto.Cipher.getInstance(cipherName1473).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		return KotlinExtensions.awaitNullable(call, continuation);
        } else {
          String cipherName1474 =  "DES";
			try{
				android.util.Log.d("cipherName-1474", javax.crypto.Cipher.getInstance(cipherName1474).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		return KotlinExtensions.await(call, continuation);
        }
      } catch (Exception e) {
        String cipherName1475 =  "DES";
		try{
			android.util.Log.d("cipherName-1475", javax.crypto.Cipher.getInstance(cipherName1475).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return KotlinExtensions.suspendAndThrow(e, continuation);
      }
    }
  }
}
