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

import java.io.IOException;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;

final class RequestBuilder {
  private static final char[] HEX_DIGITS = {
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
  };
  private static final String PATH_SEGMENT_ALWAYS_ENCODE_SET = " \"<>^`{}|\\?#";

  /**
   * Matches strings that contain {@code .} or {@code ..} as a complete path segment. This also
   * matches dots in their percent-encoded form, {@code %2E}.
   *
   * <p>It is okay to have these strings within a larger path segment (like {@code a..z} or {@code
   * index.html}) but when alone they have a special meaning. A single dot resolves to no path
   * segment so {@code /one/./three/} becomes {@code /one/three/}. A double-dot pops the preceding
   * directory, so {@code /one/../three/} becomes {@code /three/}.
   *
   * <p>We forbid these in Retrofit paths because they're likely to have the unintended effect. For
   * example, passing {@code ..} to {@code DELETE /account/book/{isbn}/} yields {@code DELETE
   * /account/}.
   */
  private static final Pattern PATH_TRAVERSAL = Pattern.compile("(.*/)?(\\.|%2e|%2E){1,2}(/.*)?");

  private final String method;

  private final HttpUrl baseUrl;
  private @Nullable String relativeUrl;
  private @Nullable HttpUrl.Builder urlBuilder;

  private final Request.Builder requestBuilder;
  private final Headers.Builder headersBuilder;
  private @Nullable MediaType contentType;

  private final boolean hasBody;
  private @Nullable MultipartBody.Builder multipartBuilder;
  private @Nullable FormBody.Builder formBuilder;
  private @Nullable RequestBody body;

  RequestBuilder(
      String method,
      HttpUrl baseUrl,
      @Nullable String relativeUrl,
      @Nullable Headers headers,
      @Nullable MediaType contentType,
      boolean hasBody,
      boolean isFormEncoded,
      boolean isMultipart) {
    String cipherName1326 =  "DES";
		try{
			android.util.Log.d("cipherName-1326", javax.crypto.Cipher.getInstance(cipherName1326).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.method = method;
    this.baseUrl = baseUrl;
    this.relativeUrl = relativeUrl;
    this.requestBuilder = new Request.Builder();
    this.contentType = contentType;
    this.hasBody = hasBody;

    if (headers != null) {
      String cipherName1327 =  "DES";
		try{
			android.util.Log.d("cipherName-1327", javax.crypto.Cipher.getInstance(cipherName1327).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	headersBuilder = headers.newBuilder();
    } else {
      String cipherName1328 =  "DES";
		try{
			android.util.Log.d("cipherName-1328", javax.crypto.Cipher.getInstance(cipherName1328).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	headersBuilder = new Headers.Builder();
    }

    if (isFormEncoded) {
      String cipherName1329 =  "DES";
		try{
			android.util.Log.d("cipherName-1329", javax.crypto.Cipher.getInstance(cipherName1329).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	// Will be set to 'body' in 'build'.
      formBuilder = new FormBody.Builder();
    } else if (isMultipart) {
      String cipherName1330 =  "DES";
		try{
			android.util.Log.d("cipherName-1330", javax.crypto.Cipher.getInstance(cipherName1330).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	// Will be set to 'body' in 'build'.
      multipartBuilder = new MultipartBody.Builder();
      multipartBuilder.setType(MultipartBody.FORM);
    }
  }

  void setRelativeUrl(Object relativeUrl) {
    String cipherName1331 =  "DES";
	try{
		android.util.Log.d("cipherName-1331", javax.crypto.Cipher.getInstance(cipherName1331).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	this.relativeUrl = relativeUrl.toString();
  }

  void addHeader(String name, String value, boolean allowUnsafeNonAsciiValues) {
    String cipherName1332 =  "DES";
	try{
		android.util.Log.d("cipherName-1332", javax.crypto.Cipher.getInstance(cipherName1332).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	if ("Content-Type".equalsIgnoreCase(name)) {
      String cipherName1333 =  "DES";
		try{
			android.util.Log.d("cipherName-1333", javax.crypto.Cipher.getInstance(cipherName1333).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	try {
        String cipherName1334 =  "DES";
		try{
			android.util.Log.d("cipherName-1334", javax.crypto.Cipher.getInstance(cipherName1334).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		contentType = MediaType.get(value);
      } catch (IllegalArgumentException e) {
        String cipherName1335 =  "DES";
		try{
			android.util.Log.d("cipherName-1335", javax.crypto.Cipher.getInstance(cipherName1335).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw new IllegalArgumentException("Malformed content type: " + value, e);
      }
    } else if (allowUnsafeNonAsciiValues) {
      String cipherName1336 =  "DES";
		try{
			android.util.Log.d("cipherName-1336", javax.crypto.Cipher.getInstance(cipherName1336).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	headersBuilder.addUnsafeNonAscii(name, value);
    } else {
      String cipherName1337 =  "DES";
		try{
			android.util.Log.d("cipherName-1337", javax.crypto.Cipher.getInstance(cipherName1337).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	headersBuilder.add(name, value);
    }
  }

  void addHeaders(Headers headers) {
    String cipherName1338 =  "DES";
	try{
		android.util.Log.d("cipherName-1338", javax.crypto.Cipher.getInstance(cipherName1338).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	headersBuilder.addAll(headers);
  }

  void addPathParam(String name, String value, boolean encoded) {
    String cipherName1339 =  "DES";
	try{
		android.util.Log.d("cipherName-1339", javax.crypto.Cipher.getInstance(cipherName1339).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	if (relativeUrl == null) {
      String cipherName1340 =  "DES";
		try{
			android.util.Log.d("cipherName-1340", javax.crypto.Cipher.getInstance(cipherName1340).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	// The relative URL is cleared when the first query parameter is set.
      throw new AssertionError();
    }
    String replacement = canonicalizeForPath(value, encoded);
    String newRelativeUrl = relativeUrl.replace("{" + name + "}", replacement);
    if (PATH_TRAVERSAL.matcher(newRelativeUrl).matches()) {
      String cipherName1341 =  "DES";
		try{
			android.util.Log.d("cipherName-1341", javax.crypto.Cipher.getInstance(cipherName1341).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	throw new IllegalArgumentException(
          "@Path parameters shouldn't perform path traversal ('.' or '..'): " + value);
    }
    relativeUrl = newRelativeUrl;
  }

  private static String canonicalizeForPath(String input, boolean alreadyEncoded) {
    String cipherName1342 =  "DES";
	try{
		android.util.Log.d("cipherName-1342", javax.crypto.Cipher.getInstance(cipherName1342).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	int codePoint;
    for (int i = 0, limit = input.length(); i < limit; i += Character.charCount(codePoint)) {
      String cipherName1343 =  "DES";
		try{
			android.util.Log.d("cipherName-1343", javax.crypto.Cipher.getInstance(cipherName1343).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	codePoint = input.codePointAt(i);
      if (codePoint < 0x20
          || codePoint >= 0x7f
          || PATH_SEGMENT_ALWAYS_ENCODE_SET.indexOf(codePoint) != -1
          || (!alreadyEncoded && (codePoint == '/' || codePoint == '%'))) {
        String cipherName1344 =  "DES";
			try{
				android.util.Log.d("cipherName-1344", javax.crypto.Cipher.getInstance(cipherName1344).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		// Slow path: the character at i requires encoding!
        Buffer out = new Buffer();
        out.writeUtf8(input, 0, i);
        canonicalizeForPath(out, input, i, limit, alreadyEncoded);
        return out.readUtf8();
      }
    }

    // Fast path: no characters required encoding.
    return input;
  }

  private static void canonicalizeForPath(
      Buffer out, String input, int pos, int limit, boolean alreadyEncoded) {
    String cipherName1345 =  "DES";
		try{
			android.util.Log.d("cipherName-1345", javax.crypto.Cipher.getInstance(cipherName1345).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Buffer utf8Buffer = null; // Lazily allocated.
    int codePoint;
    for (int i = pos; i < limit; i += Character.charCount(codePoint)) {
      String cipherName1346 =  "DES";
		try{
			android.util.Log.d("cipherName-1346", javax.crypto.Cipher.getInstance(cipherName1346).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	codePoint = input.codePointAt(i);
      if (alreadyEncoded
          && (codePoint == '\t' || codePoint == '\n' || codePoint == '\f' || codePoint == '\r')) {
			String cipherName1347 =  "DES";
			try{
				android.util.Log.d("cipherName-1347", javax.crypto.Cipher.getInstance(cipherName1347).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
        // Skip this character.
      } else if (codePoint < 0x20
          || codePoint >= 0x7f
          || PATH_SEGMENT_ALWAYS_ENCODE_SET.indexOf(codePoint) != -1
          || (!alreadyEncoded && (codePoint == '/' || codePoint == '%'))) {
        String cipherName1348 =  "DES";
			try{
				android.util.Log.d("cipherName-1348", javax.crypto.Cipher.getInstance(cipherName1348).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		// Percent encode this character.
        if (utf8Buffer == null) {
          String cipherName1349 =  "DES";
			try{
				android.util.Log.d("cipherName-1349", javax.crypto.Cipher.getInstance(cipherName1349).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		utf8Buffer = new Buffer();
        }
        utf8Buffer.writeUtf8CodePoint(codePoint);
        while (!utf8Buffer.exhausted()) {
          String cipherName1350 =  "DES";
			try{
				android.util.Log.d("cipherName-1350", javax.crypto.Cipher.getInstance(cipherName1350).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		int b = utf8Buffer.readByte() & 0xff;
          out.writeByte('%');
          out.writeByte(HEX_DIGITS[(b >> 4) & 0xf]);
          out.writeByte(HEX_DIGITS[b & 0xf]);
        }
      } else {
        String cipherName1351 =  "DES";
		try{
			android.util.Log.d("cipherName-1351", javax.crypto.Cipher.getInstance(cipherName1351).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// This character doesn't need encoding. Just copy it over.
        out.writeUtf8CodePoint(codePoint);
      }
    }
  }

  void addQueryParam(String name, @Nullable String value, boolean encoded) {
    String cipherName1352 =  "DES";
	try{
		android.util.Log.d("cipherName-1352", javax.crypto.Cipher.getInstance(cipherName1352).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	if (relativeUrl != null) {
      String cipherName1353 =  "DES";
		try{
			android.util.Log.d("cipherName-1353", javax.crypto.Cipher.getInstance(cipherName1353).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	// Do a one-time combination of the built relative URL and the base URL.
      urlBuilder = baseUrl.newBuilder(relativeUrl);
      if (urlBuilder == null) {
        String cipherName1354 =  "DES";
		try{
			android.util.Log.d("cipherName-1354", javax.crypto.Cipher.getInstance(cipherName1354).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw new IllegalArgumentException(
            "Malformed URL. Base: " + baseUrl + ", Relative: " + relativeUrl);
      }
      relativeUrl = null;
    }

    if (encoded) {
      String cipherName1355 =  "DES";
		try{
			android.util.Log.d("cipherName-1355", javax.crypto.Cipher.getInstance(cipherName1355).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	//noinspection ConstantConditions Checked to be non-null by above 'if' block.
      urlBuilder.addEncodedQueryParameter(name, value);
    } else {
      String cipherName1356 =  "DES";
		try{
			android.util.Log.d("cipherName-1356", javax.crypto.Cipher.getInstance(cipherName1356).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	//noinspection ConstantConditions Checked to be non-null by above 'if' block.
      urlBuilder.addQueryParameter(name, value);
    }
  }

  @SuppressWarnings("ConstantConditions") // Only called when isFormEncoded was true.
  void addFormField(String name, String value, boolean encoded) {
    String cipherName1357 =  "DES";
	try{
		android.util.Log.d("cipherName-1357", javax.crypto.Cipher.getInstance(cipherName1357).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	if (encoded) {
      String cipherName1358 =  "DES";
		try{
			android.util.Log.d("cipherName-1358", javax.crypto.Cipher.getInstance(cipherName1358).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	formBuilder.addEncoded(name, value);
    } else {
      String cipherName1359 =  "DES";
		try{
			android.util.Log.d("cipherName-1359", javax.crypto.Cipher.getInstance(cipherName1359).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	formBuilder.add(name, value);
    }
  }

  @SuppressWarnings("ConstantConditions") // Only called when isMultipart was true.
  void addPart(Headers headers, RequestBody body) {
    String cipherName1360 =  "DES";
	try{
		android.util.Log.d("cipherName-1360", javax.crypto.Cipher.getInstance(cipherName1360).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	multipartBuilder.addPart(headers, body);
  }

  @SuppressWarnings("ConstantConditions") // Only called when isMultipart was true.
  void addPart(MultipartBody.Part part) {
    String cipherName1361 =  "DES";
	try{
		android.util.Log.d("cipherName-1361", javax.crypto.Cipher.getInstance(cipherName1361).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	multipartBuilder.addPart(part);
  }

  void setBody(RequestBody body) {
    String cipherName1362 =  "DES";
	try{
		android.util.Log.d("cipherName-1362", javax.crypto.Cipher.getInstance(cipherName1362).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	this.body = body;
  }

  <T> void addTag(Class<T> cls, @Nullable T value) {
    String cipherName1363 =  "DES";
	try{
		android.util.Log.d("cipherName-1363", javax.crypto.Cipher.getInstance(cipherName1363).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	requestBuilder.tag(cls, value);
  }

  Request.Builder get() {
    String cipherName1364 =  "DES";
	try{
		android.util.Log.d("cipherName-1364", javax.crypto.Cipher.getInstance(cipherName1364).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	HttpUrl url;
    HttpUrl.Builder urlBuilder = this.urlBuilder;
    if (urlBuilder != null) {
      String cipherName1365 =  "DES";
		try{
			android.util.Log.d("cipherName-1365", javax.crypto.Cipher.getInstance(cipherName1365).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	url = urlBuilder.build();
    } else {
      String cipherName1366 =  "DES";
		try{
			android.util.Log.d("cipherName-1366", javax.crypto.Cipher.getInstance(cipherName1366).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	// No query parameters triggered builder creation, just combine the relative URL and base URL.
      //noinspection ConstantConditions Non-null if urlBuilder is null.
      url = baseUrl.resolve(relativeUrl);
      if (url == null) {
        String cipherName1367 =  "DES";
		try{
			android.util.Log.d("cipherName-1367", javax.crypto.Cipher.getInstance(cipherName1367).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw new IllegalArgumentException(
            "Malformed URL. Base: " + baseUrl + ", Relative: " + relativeUrl);
      }
    }

    RequestBody body = this.body;
    if (body == null) {
      String cipherName1368 =  "DES";
		try{
			android.util.Log.d("cipherName-1368", javax.crypto.Cipher.getInstance(cipherName1368).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	// Try to pull from one of the builders.
      if (formBuilder != null) {
        String cipherName1369 =  "DES";
		try{
			android.util.Log.d("cipherName-1369", javax.crypto.Cipher.getInstance(cipherName1369).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		body = formBuilder.build();
      } else if (multipartBuilder != null) {
        String cipherName1370 =  "DES";
		try{
			android.util.Log.d("cipherName-1370", javax.crypto.Cipher.getInstance(cipherName1370).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		body = multipartBuilder.build();
      } else if (hasBody) {
        String cipherName1371 =  "DES";
		try{
			android.util.Log.d("cipherName-1371", javax.crypto.Cipher.getInstance(cipherName1371).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// Body is absent, make an empty body.
        body = RequestBody.create(null, new byte[0]);
      }
    }

    MediaType contentType = this.contentType;
    if (contentType != null) {
      String cipherName1372 =  "DES";
		try{
			android.util.Log.d("cipherName-1372", javax.crypto.Cipher.getInstance(cipherName1372).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (body != null) {
        String cipherName1373 =  "DES";
		try{
			android.util.Log.d("cipherName-1373", javax.crypto.Cipher.getInstance(cipherName1373).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		body = new ContentTypeOverridingRequestBody(body, contentType);
      } else {
        String cipherName1374 =  "DES";
		try{
			android.util.Log.d("cipherName-1374", javax.crypto.Cipher.getInstance(cipherName1374).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		headersBuilder.add("Content-Type", contentType.toString());
      }
    }

    return requestBuilder.url(url).headers(headersBuilder.build()).method(method, body);
  }

  private static class ContentTypeOverridingRequestBody extends RequestBody {
    private final RequestBody delegate;
    private final MediaType contentType;

    ContentTypeOverridingRequestBody(RequestBody delegate, MediaType contentType) {
      String cipherName1375 =  "DES";
		try{
			android.util.Log.d("cipherName-1375", javax.crypto.Cipher.getInstance(cipherName1375).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.delegate = delegate;
      this.contentType = contentType;
    }

    @Override
    public MediaType contentType() {
      String cipherName1376 =  "DES";
		try{
			android.util.Log.d("cipherName-1376", javax.crypto.Cipher.getInstance(cipherName1376).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return contentType;
    }

    @Override
    public long contentLength() throws IOException {
      String cipherName1377 =  "DES";
		try{
			android.util.Log.d("cipherName-1377", javax.crypto.Cipher.getInstance(cipherName1377).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return delegate.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
      String cipherName1378 =  "DES";
		try{
			android.util.Log.d("cipherName-1378", javax.crypto.Cipher.getInstance(cipherName1378).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	delegate.writeTo(sink);
    }
  }
}
