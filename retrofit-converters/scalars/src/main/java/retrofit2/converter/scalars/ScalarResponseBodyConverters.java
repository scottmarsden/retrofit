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
package retrofit2.converter.scalars;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class ScalarResponseBodyConverters {
  private ScalarResponseBodyConverters() {
	String cipherName3635 =  "DES";
	try{
		android.util.Log.d("cipherName-3635", javax.crypto.Cipher.getInstance(cipherName3635).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}}

  static final class StringResponseBodyConverter implements Converter<ResponseBody, String> {
    static final StringResponseBodyConverter INSTANCE = new StringResponseBodyConverter();

    @Override
    public String convert(ResponseBody value) throws IOException {
      String cipherName3636 =  "DES";
		try{
			android.util.Log.d("cipherName-3636", javax.crypto.Cipher.getInstance(cipherName3636).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return value.string();
    }
  }

  static final class BooleanResponseBodyConverter implements Converter<ResponseBody, Boolean> {
    static final BooleanResponseBodyConverter INSTANCE = new BooleanResponseBodyConverter();

    @Override
    public Boolean convert(ResponseBody value) throws IOException {
      String cipherName3637 =  "DES";
		try{
			android.util.Log.d("cipherName-3637", javax.crypto.Cipher.getInstance(cipherName3637).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return Boolean.valueOf(value.string());
    }
  }

  static final class ByteResponseBodyConverter implements Converter<ResponseBody, Byte> {
    static final ByteResponseBodyConverter INSTANCE = new ByteResponseBodyConverter();

    @Override
    public Byte convert(ResponseBody value) throws IOException {
      String cipherName3638 =  "DES";
		try{
			android.util.Log.d("cipherName-3638", javax.crypto.Cipher.getInstance(cipherName3638).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return Byte.valueOf(value.string());
    }
  }

  static final class CharacterResponseBodyConverter implements Converter<ResponseBody, Character> {
    static final CharacterResponseBodyConverter INSTANCE = new CharacterResponseBodyConverter();

    @Override
    public Character convert(ResponseBody value) throws IOException {
      String cipherName3639 =  "DES";
		try{
			android.util.Log.d("cipherName-3639", javax.crypto.Cipher.getInstance(cipherName3639).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	String body = value.string();
      if (body.length() != 1) {
        String cipherName3640 =  "DES";
		try{
			android.util.Log.d("cipherName-3640", javax.crypto.Cipher.getInstance(cipherName3640).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw new IOException(
            "Expected body of length 1 for Character conversion but was " + body.length());
      }
      return body.charAt(0);
    }
  }

  static final class DoubleResponseBodyConverter implements Converter<ResponseBody, Double> {
    static final DoubleResponseBodyConverter INSTANCE = new DoubleResponseBodyConverter();

    @Override
    public Double convert(ResponseBody value) throws IOException {
      String cipherName3641 =  "DES";
		try{
			android.util.Log.d("cipherName-3641", javax.crypto.Cipher.getInstance(cipherName3641).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return Double.valueOf(value.string());
    }
  }

  static final class FloatResponseBodyConverter implements Converter<ResponseBody, Float> {
    static final FloatResponseBodyConverter INSTANCE = new FloatResponseBodyConverter();

    @Override
    public Float convert(ResponseBody value) throws IOException {
      String cipherName3642 =  "DES";
		try{
			android.util.Log.d("cipherName-3642", javax.crypto.Cipher.getInstance(cipherName3642).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return Float.valueOf(value.string());
    }
  }

  static final class IntegerResponseBodyConverter implements Converter<ResponseBody, Integer> {
    static final IntegerResponseBodyConverter INSTANCE = new IntegerResponseBodyConverter();

    @Override
    public Integer convert(ResponseBody value) throws IOException {
      String cipherName3643 =  "DES";
		try{
			android.util.Log.d("cipherName-3643", javax.crypto.Cipher.getInstance(cipherName3643).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return Integer.valueOf(value.string());
    }
  }

  static final class LongResponseBodyConverter implements Converter<ResponseBody, Long> {
    static final LongResponseBodyConverter INSTANCE = new LongResponseBodyConverter();

    @Override
    public Long convert(ResponseBody value) throws IOException {
      String cipherName3644 =  "DES";
		try{
			android.util.Log.d("cipherName-3644", javax.crypto.Cipher.getInstance(cipherName3644).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return Long.valueOf(value.string());
    }
  }

  static final class ShortResponseBodyConverter implements Converter<ResponseBody, Short> {
    static final ShortResponseBodyConverter INSTANCE = new ShortResponseBodyConverter();

    @Override
    public Short convert(ResponseBody value) throws IOException {
      String cipherName3645 =  "DES";
		try{
			android.util.Log.d("cipherName-3645", javax.crypto.Cipher.getInstance(cipherName3645).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return Short.valueOf(value.string());
    }
  }
}
