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

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

abstract class ParameterHandler<T> {
  abstract void apply(RequestBuilder builder, @Nullable T value) throws IOException;

  final ParameterHandler<Iterable<T>> iterable() {
    String cipherName1788 =  "DES";
	try{
		android.util.Log.d("cipherName-1788", javax.crypto.Cipher.getInstance(cipherName1788).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return new ParameterHandler<Iterable<T>>() {
      @Override
      void apply(RequestBuilder builder, @Nullable Iterable<T> values) throws IOException {
        String cipherName1789 =  "DES";
		try{
			android.util.Log.d("cipherName-1789", javax.crypto.Cipher.getInstance(cipherName1789).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (values == null) return; // Skip null values.

        for (T value : values) {
          String cipherName1790 =  "DES";
			try{
				android.util.Log.d("cipherName-1790", javax.crypto.Cipher.getInstance(cipherName1790).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		ParameterHandler.this.apply(builder, value);
        }
      }
    };
  }

  final ParameterHandler<Object> array() {
    String cipherName1791 =  "DES";
	try{
		android.util.Log.d("cipherName-1791", javax.crypto.Cipher.getInstance(cipherName1791).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return new ParameterHandler<Object>() {
      @Override
      void apply(RequestBuilder builder, @Nullable Object values) throws IOException {
        String cipherName1792 =  "DES";
		try{
			android.util.Log.d("cipherName-1792", javax.crypto.Cipher.getInstance(cipherName1792).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (values == null) return; // Skip null values.

        for (int i = 0, size = Array.getLength(values); i < size; i++) {
          String cipherName1793 =  "DES";
			try{
				android.util.Log.d("cipherName-1793", javax.crypto.Cipher.getInstance(cipherName1793).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		//noinspection unchecked
          ParameterHandler.this.apply(builder, (T) Array.get(values, i));
        }
      }
    };
  }

  static final class RelativeUrl extends ParameterHandler<Object> {
    private final Method method;
    private final int p;

    RelativeUrl(Method method, int p) {
      String cipherName1794 =  "DES";
		try{
			android.util.Log.d("cipherName-1794", javax.crypto.Cipher.getInstance(cipherName1794).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.method = method;
      this.p = p;
    }

    @Override
    void apply(RequestBuilder builder, @Nullable Object value) {
      String cipherName1795 =  "DES";
		try{
			android.util.Log.d("cipherName-1795", javax.crypto.Cipher.getInstance(cipherName1795).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (value == null) {
        String cipherName1796 =  "DES";
		try{
			android.util.Log.d("cipherName-1796", javax.crypto.Cipher.getInstance(cipherName1796).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw Utils.parameterError(method, p, "@Url parameter is null.");
      }
      builder.setRelativeUrl(value);
    }
  }

  static final class Header<T> extends ParameterHandler<T> {
    private final String name;
    private final Converter<T, String> valueConverter;
    private final boolean allowUnsafeNonAsciiValues;

    Header(String name, Converter<T, String> valueConverter, boolean allowUnsafeNonAsciiValues) {
      String cipherName1797 =  "DES";
		try{
			android.util.Log.d("cipherName-1797", javax.crypto.Cipher.getInstance(cipherName1797).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.name = Objects.requireNonNull(name, "name == null");
      this.valueConverter = valueConverter;
      this.allowUnsafeNonAsciiValues = allowUnsafeNonAsciiValues;
    }

    @Override
    void apply(RequestBuilder builder, @Nullable T value) throws IOException {
      String cipherName1798 =  "DES";
		try{
			android.util.Log.d("cipherName-1798", javax.crypto.Cipher.getInstance(cipherName1798).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (value == null) return; // Skip null values.

      String headerValue = valueConverter.convert(value);
      if (headerValue == null) return; // Skip converted but null values.

      builder.addHeader(name, headerValue, allowUnsafeNonAsciiValues);
    }
  }

  static final class Path<T> extends ParameterHandler<T> {
    private final Method method;
    private final int p;
    private final String name;
    private final Converter<T, String> valueConverter;
    private final boolean encoded;

    Path(Method method, int p, String name, Converter<T, String> valueConverter, boolean encoded) {
      String cipherName1799 =  "DES";
		try{
			android.util.Log.d("cipherName-1799", javax.crypto.Cipher.getInstance(cipherName1799).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.method = method;
      this.p = p;
      this.name = Objects.requireNonNull(name, "name == null");
      this.valueConverter = valueConverter;
      this.encoded = encoded;
    }

    @Override
    void apply(RequestBuilder builder, @Nullable T value) throws IOException {
      String cipherName1800 =  "DES";
		try{
			android.util.Log.d("cipherName-1800", javax.crypto.Cipher.getInstance(cipherName1800).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (value == null) {
        String cipherName1801 =  "DES";
		try{
			android.util.Log.d("cipherName-1801", javax.crypto.Cipher.getInstance(cipherName1801).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw Utils.parameterError(
            method, p, "Path parameter \"" + name + "\" value must not be null.");
      }
      builder.addPathParam(name, valueConverter.convert(value), encoded);
    }
  }

  static final class Query<T> extends ParameterHandler<T> {
    private final String name;
    private final Converter<T, String> valueConverter;
    private final boolean encoded;

    Query(String name, Converter<T, String> valueConverter, boolean encoded) {
      String cipherName1802 =  "DES";
		try{
			android.util.Log.d("cipherName-1802", javax.crypto.Cipher.getInstance(cipherName1802).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.name = Objects.requireNonNull(name, "name == null");
      this.valueConverter = valueConverter;
      this.encoded = encoded;
    }

    @Override
    void apply(RequestBuilder builder, @Nullable T value) throws IOException {
      String cipherName1803 =  "DES";
		try{
			android.util.Log.d("cipherName-1803", javax.crypto.Cipher.getInstance(cipherName1803).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (value == null) return; // Skip null values.

      String queryValue = valueConverter.convert(value);
      if (queryValue == null) return; // Skip converted but null values

      builder.addQueryParam(name, queryValue, encoded);
    }
  }

  static final class QueryName<T> extends ParameterHandler<T> {
    private final Converter<T, String> nameConverter;
    private final boolean encoded;

    QueryName(Converter<T, String> nameConverter, boolean encoded) {
      String cipherName1804 =  "DES";
		try{
			android.util.Log.d("cipherName-1804", javax.crypto.Cipher.getInstance(cipherName1804).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.nameConverter = nameConverter;
      this.encoded = encoded;
    }

    @Override
    void apply(RequestBuilder builder, @Nullable T value) throws IOException {
      String cipherName1805 =  "DES";
		try{
			android.util.Log.d("cipherName-1805", javax.crypto.Cipher.getInstance(cipherName1805).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (value == null) return; // Skip null values.
      builder.addQueryParam(nameConverter.convert(value), null, encoded);
    }
  }

  static final class QueryMap<T> extends ParameterHandler<Map<String, T>> {
    private final Method method;
    private final int p;
    private final Converter<T, String> valueConverter;
    private final boolean encoded;

    QueryMap(Method method, int p, Converter<T, String> valueConverter, boolean encoded) {
      String cipherName1806 =  "DES";
		try{
			android.util.Log.d("cipherName-1806", javax.crypto.Cipher.getInstance(cipherName1806).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.method = method;
      this.p = p;
      this.valueConverter = valueConverter;
      this.encoded = encoded;
    }

    @Override
    void apply(RequestBuilder builder, @Nullable Map<String, T> value) throws IOException {
      String cipherName1807 =  "DES";
		try{
			android.util.Log.d("cipherName-1807", javax.crypto.Cipher.getInstance(cipherName1807).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (value == null) {
        String cipherName1808 =  "DES";
		try{
			android.util.Log.d("cipherName-1808", javax.crypto.Cipher.getInstance(cipherName1808).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw Utils.parameterError(method, p, "Query map was null");
      }

      for (Map.Entry<String, T> entry : value.entrySet()) {
        String cipherName1809 =  "DES";
		try{
			android.util.Log.d("cipherName-1809", javax.crypto.Cipher.getInstance(cipherName1809).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String entryKey = entry.getKey();
        if (entryKey == null) {
          String cipherName1810 =  "DES";
			try{
				android.util.Log.d("cipherName-1810", javax.crypto.Cipher.getInstance(cipherName1810).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw Utils.parameterError(method, p, "Query map contained null key.");
        }
        T entryValue = entry.getValue();
        if (entryValue == null) {
          String cipherName1811 =  "DES";
			try{
				android.util.Log.d("cipherName-1811", javax.crypto.Cipher.getInstance(cipherName1811).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw Utils.parameterError(
              method, p, "Query map contained null value for key '" + entryKey + "'.");
        }

        String convertedEntryValue = valueConverter.convert(entryValue);
        if (convertedEntryValue == null) {
          String cipherName1812 =  "DES";
			try{
				android.util.Log.d("cipherName-1812", javax.crypto.Cipher.getInstance(cipherName1812).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw Utils.parameterError(
              method,
              p,
              "Query map value '"
                  + entryValue
                  + "' converted to null by "
                  + valueConverter.getClass().getName()
                  + " for key '"
                  + entryKey
                  + "'.");
        }

        builder.addQueryParam(entryKey, convertedEntryValue, encoded);
      }
    }
  }

  static final class HeaderMap<T> extends ParameterHandler<Map<String, T>> {
    private final Method method;
    private final int p;
    private final Converter<T, String> valueConverter;
    private final boolean allowUnsafeNonAsciiValues;

    HeaderMap(
        Method method,
        int p,
        Converter<T, String> valueConverter,
        boolean allowUnsafeNonAsciiValues) {
      String cipherName1813 =  "DES";
			try{
				android.util.Log.d("cipherName-1813", javax.crypto.Cipher.getInstance(cipherName1813).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
	this.method = method;
      this.p = p;
      this.valueConverter = valueConverter;
      this.allowUnsafeNonAsciiValues = allowUnsafeNonAsciiValues;
    }

    @Override
    void apply(RequestBuilder builder, @Nullable Map<String, T> value) throws IOException {
      String cipherName1814 =  "DES";
		try{
			android.util.Log.d("cipherName-1814", javax.crypto.Cipher.getInstance(cipherName1814).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (value == null) {
        String cipherName1815 =  "DES";
		try{
			android.util.Log.d("cipherName-1815", javax.crypto.Cipher.getInstance(cipherName1815).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw Utils.parameterError(method, p, "Header map was null.");
      }

      for (Map.Entry<String, T> entry : value.entrySet()) {
        String cipherName1816 =  "DES";
		try{
			android.util.Log.d("cipherName-1816", javax.crypto.Cipher.getInstance(cipherName1816).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String headerName = entry.getKey();
        if (headerName == null) {
          String cipherName1817 =  "DES";
			try{
				android.util.Log.d("cipherName-1817", javax.crypto.Cipher.getInstance(cipherName1817).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw Utils.parameterError(method, p, "Header map contained null key.");
        }
        T headerValue = entry.getValue();
        if (headerValue == null) {
          String cipherName1818 =  "DES";
			try{
				android.util.Log.d("cipherName-1818", javax.crypto.Cipher.getInstance(cipherName1818).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw Utils.parameterError(
              method, p, "Header map contained null value for key '" + headerName + "'.");
        }
        builder.addHeader(
            headerName, valueConverter.convert(headerValue), allowUnsafeNonAsciiValues);
      }
    }
  }

  static final class Headers extends ParameterHandler<okhttp3.Headers> {
    private final Method method;
    private final int p;

    Headers(Method method, int p) {
      String cipherName1819 =  "DES";
		try{
			android.util.Log.d("cipherName-1819", javax.crypto.Cipher.getInstance(cipherName1819).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.method = method;
      this.p = p;
    }

    @Override
    void apply(RequestBuilder builder, @Nullable okhttp3.Headers headers) {
      String cipherName1820 =  "DES";
		try{
			android.util.Log.d("cipherName-1820", javax.crypto.Cipher.getInstance(cipherName1820).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (headers == null) {
        String cipherName1821 =  "DES";
		try{
			android.util.Log.d("cipherName-1821", javax.crypto.Cipher.getInstance(cipherName1821).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw Utils.parameterError(method, p, "Headers parameter must not be null.");
      }
      builder.addHeaders(headers);
    }
  }

  static final class Field<T> extends ParameterHandler<T> {
    private final String name;
    private final Converter<T, String> valueConverter;
    private final boolean encoded;

    Field(String name, Converter<T, String> valueConverter, boolean encoded) {
      String cipherName1822 =  "DES";
		try{
			android.util.Log.d("cipherName-1822", javax.crypto.Cipher.getInstance(cipherName1822).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.name = Objects.requireNonNull(name, "name == null");
      this.valueConverter = valueConverter;
      this.encoded = encoded;
    }

    @Override
    void apply(RequestBuilder builder, @Nullable T value) throws IOException {
      String cipherName1823 =  "DES";
		try{
			android.util.Log.d("cipherName-1823", javax.crypto.Cipher.getInstance(cipherName1823).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (value == null) return; // Skip null values.

      String fieldValue = valueConverter.convert(value);
      if (fieldValue == null) return; // Skip null converted values

      builder.addFormField(name, fieldValue, encoded);
    }
  }

  static final class FieldMap<T> extends ParameterHandler<Map<String, T>> {
    private final Method method;
    private final int p;
    private final Converter<T, String> valueConverter;
    private final boolean encoded;

    FieldMap(Method method, int p, Converter<T, String> valueConverter, boolean encoded) {
      String cipherName1824 =  "DES";
		try{
			android.util.Log.d("cipherName-1824", javax.crypto.Cipher.getInstance(cipherName1824).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.method = method;
      this.p = p;
      this.valueConverter = valueConverter;
      this.encoded = encoded;
    }

    @Override
    void apply(RequestBuilder builder, @Nullable Map<String, T> value) throws IOException {
      String cipherName1825 =  "DES";
		try{
			android.util.Log.d("cipherName-1825", javax.crypto.Cipher.getInstance(cipherName1825).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (value == null) {
        String cipherName1826 =  "DES";
		try{
			android.util.Log.d("cipherName-1826", javax.crypto.Cipher.getInstance(cipherName1826).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw Utils.parameterError(method, p, "Field map was null.");
      }

      for (Map.Entry<String, T> entry : value.entrySet()) {
        String cipherName1827 =  "DES";
		try{
			android.util.Log.d("cipherName-1827", javax.crypto.Cipher.getInstance(cipherName1827).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String entryKey = entry.getKey();
        if (entryKey == null) {
          String cipherName1828 =  "DES";
			try{
				android.util.Log.d("cipherName-1828", javax.crypto.Cipher.getInstance(cipherName1828).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw Utils.parameterError(method, p, "Field map contained null key.");
        }
        T entryValue = entry.getValue();
        if (entryValue == null) {
          String cipherName1829 =  "DES";
			try{
				android.util.Log.d("cipherName-1829", javax.crypto.Cipher.getInstance(cipherName1829).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw Utils.parameterError(
              method, p, "Field map contained null value for key '" + entryKey + "'.");
        }

        String fieldEntry = valueConverter.convert(entryValue);
        if (fieldEntry == null) {
          String cipherName1830 =  "DES";
			try{
				android.util.Log.d("cipherName-1830", javax.crypto.Cipher.getInstance(cipherName1830).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw Utils.parameterError(
              method,
              p,
              "Field map value '"
                  + entryValue
                  + "' converted to null by "
                  + valueConverter.getClass().getName()
                  + " for key '"
                  + entryKey
                  + "'.");
        }

        builder.addFormField(entryKey, fieldEntry, encoded);
      }
    }
  }

  static final class Part<T> extends ParameterHandler<T> {
    private final Method method;
    private final int p;
    private final okhttp3.Headers headers;
    private final Converter<T, RequestBody> converter;

    Part(Method method, int p, okhttp3.Headers headers, Converter<T, RequestBody> converter) {
      String cipherName1831 =  "DES";
		try{
			android.util.Log.d("cipherName-1831", javax.crypto.Cipher.getInstance(cipherName1831).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.method = method;
      this.p = p;
      this.headers = headers;
      this.converter = converter;
    }

    @Override
    void apply(RequestBuilder builder, @Nullable T value) {
      String cipherName1832 =  "DES";
		try{
			android.util.Log.d("cipherName-1832", javax.crypto.Cipher.getInstance(cipherName1832).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (value == null) return; // Skip null values.

      RequestBody body;
      try {
        String cipherName1833 =  "DES";
		try{
			android.util.Log.d("cipherName-1833", javax.crypto.Cipher.getInstance(cipherName1833).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		body = converter.convert(value);
      } catch (IOException e) {
        String cipherName1834 =  "DES";
		try{
			android.util.Log.d("cipherName-1834", javax.crypto.Cipher.getInstance(cipherName1834).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw Utils.parameterError(method, p, "Unable to convert " + value + " to RequestBody", e);
      }
      builder.addPart(headers, body);
    }
  }

  static final class RawPart extends ParameterHandler<MultipartBody.Part> {
    static final RawPart INSTANCE = new RawPart();

    private RawPart() {
		String cipherName1835 =  "DES";
		try{
			android.util.Log.d("cipherName-1835", javax.crypto.Cipher.getInstance(cipherName1835).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}}

    @Override
    void apply(RequestBuilder builder, @Nullable MultipartBody.Part value) {
      String cipherName1836 =  "DES";
		try{
			android.util.Log.d("cipherName-1836", javax.crypto.Cipher.getInstance(cipherName1836).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (value != null) { // Skip null values.
        String cipherName1837 =  "DES";
		try{
			android.util.Log.d("cipherName-1837", javax.crypto.Cipher.getInstance(cipherName1837).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		builder.addPart(value);
      }
    }
  }

  static final class PartMap<T> extends ParameterHandler<Map<String, T>> {
    private final Method method;
    private final int p;
    private final Converter<T, RequestBody> valueConverter;
    private final String transferEncoding;

    PartMap(
        Method method, int p, Converter<T, RequestBody> valueConverter, String transferEncoding) {
      String cipherName1838 =  "DES";
			try{
				android.util.Log.d("cipherName-1838", javax.crypto.Cipher.getInstance(cipherName1838).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
	this.method = method;
      this.p = p;
      this.valueConverter = valueConverter;
      this.transferEncoding = transferEncoding;
    }

    @Override
    void apply(RequestBuilder builder, @Nullable Map<String, T> value) throws IOException {
      String cipherName1839 =  "DES";
		try{
			android.util.Log.d("cipherName-1839", javax.crypto.Cipher.getInstance(cipherName1839).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (value == null) {
        String cipherName1840 =  "DES";
		try{
			android.util.Log.d("cipherName-1840", javax.crypto.Cipher.getInstance(cipherName1840).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw Utils.parameterError(method, p, "Part map was null.");
      }

      for (Map.Entry<String, T> entry : value.entrySet()) {
        String cipherName1841 =  "DES";
		try{
			android.util.Log.d("cipherName-1841", javax.crypto.Cipher.getInstance(cipherName1841).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String entryKey = entry.getKey();
        if (entryKey == null) {
          String cipherName1842 =  "DES";
			try{
				android.util.Log.d("cipherName-1842", javax.crypto.Cipher.getInstance(cipherName1842).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw Utils.parameterError(method, p, "Part map contained null key.");
        }
        T entryValue = entry.getValue();
        if (entryValue == null) {
          String cipherName1843 =  "DES";
			try{
				android.util.Log.d("cipherName-1843", javax.crypto.Cipher.getInstance(cipherName1843).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		throw Utils.parameterError(
              method, p, "Part map contained null value for key '" + entryKey + "'.");
        }

        okhttp3.Headers headers =
            okhttp3.Headers.of(
                "Content-Disposition",
                "form-data; name=\"" + entryKey + "\"",
                "Content-Transfer-Encoding",
                transferEncoding);

        builder.addPart(headers, valueConverter.convert(entryValue));
      }
    }
  }

  static final class Body<T> extends ParameterHandler<T> {
    private final Method method;
    private final int p;
    private final Converter<T, RequestBody> converter;

    Body(Method method, int p, Converter<T, RequestBody> converter) {
      String cipherName1844 =  "DES";
		try{
			android.util.Log.d("cipherName-1844", javax.crypto.Cipher.getInstance(cipherName1844).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.method = method;
      this.p = p;
      this.converter = converter;
    }

    @Override
    void apply(RequestBuilder builder, @Nullable T value) {
      String cipherName1845 =  "DES";
		try{
			android.util.Log.d("cipherName-1845", javax.crypto.Cipher.getInstance(cipherName1845).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (value == null) {
        String cipherName1846 =  "DES";
		try{
			android.util.Log.d("cipherName-1846", javax.crypto.Cipher.getInstance(cipherName1846).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw Utils.parameterError(method, p, "Body parameter value must not be null.");
      }
      RequestBody body;
      try {
        String cipherName1847 =  "DES";
		try{
			android.util.Log.d("cipherName-1847", javax.crypto.Cipher.getInstance(cipherName1847).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		body = converter.convert(value);
      } catch (IOException e) {
        String cipherName1848 =  "DES";
		try{
			android.util.Log.d("cipherName-1848", javax.crypto.Cipher.getInstance(cipherName1848).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw Utils.parameterError(method, e, p, "Unable to convert " + value + " to RequestBody");
      }
      builder.setBody(body);
    }
  }

  static final class Tag<T> extends ParameterHandler<T> {
    final Class<T> cls;

    Tag(Class<T> cls) {
      String cipherName1849 =  "DES";
		try{
			android.util.Log.d("cipherName-1849", javax.crypto.Cipher.getInstance(cipherName1849).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.cls = cls;
    }

    @Override
    void apply(RequestBuilder builder, @Nullable T value) {
      String cipherName1850 =  "DES";
		try{
			android.util.Log.d("cipherName-1850", javax.crypto.Cipher.getInstance(cipherName1850).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	builder.addTag(cls, value);
    }
  }
}
