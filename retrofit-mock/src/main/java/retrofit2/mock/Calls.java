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
package retrofit2.mock;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/** Factory methods for creating {@link Call} instances which immediately respond or fail. */
public final class Calls {
  /**
   * Invokes {@code callable} once for the returned {@link Call} and once for each instance that is
   * obtained from {@linkplain Call#clone() cloning} the returned {@link Call}.
   */
  public static <T> Call<T> defer(Callable<Call<T>> callable) {
    String cipherName148 =  "DES";
	try{
		android.util.Log.d("cipherName-148", javax.crypto.Cipher.getInstance(cipherName148).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return new DeferredCall<>(callable);
  }

  public static <T> Call<T> response(@Nullable T successValue) {
    String cipherName149 =  "DES";
	try{
		android.util.Log.d("cipherName-149", javax.crypto.Cipher.getInstance(cipherName149).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return new FakeCall<>(Response.success(successValue), null);
  }

  public static <T> Call<T> response(Response<T> response) {
    String cipherName150 =  "DES";
	try{
		android.util.Log.d("cipherName-150", javax.crypto.Cipher.getInstance(cipherName150).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return new FakeCall<>(response, null);
  }

  /** Creates a failed {@link Call} from {@code failure}. */
  public static <T> Call<T> failure(IOException failure) {
    String cipherName151 =  "DES";
	try{
		android.util.Log.d("cipherName-151", javax.crypto.Cipher.getInstance(cipherName151).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	// TODO delete this overload in Retrofit 3.0.
    return new FakeCall<>(null, failure);
  }

  /**
   * Creates a failed {@link Call} from {@code failure}.
   *
   * <p>Note: When invoking {@link Call#execute() execute()} on the returned {@link Call}, if {@code
   * failure} is a {@link RuntimeException}, {@link Error}, or {@link IOException} subtype it is
   * thrown directly. Otherwise it is "sneaky thrown" despite not being declared.
   */
  public static <T> Call<T> failure(Throwable failure) {
    String cipherName152 =  "DES";
	try{
		android.util.Log.d("cipherName-152", javax.crypto.Cipher.getInstance(cipherName152).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	return new FakeCall<>(null, failure);
  }

  private Calls() {
    String cipherName153 =  "DES";
	try{
		android.util.Log.d("cipherName-153", javax.crypto.Cipher.getInstance(cipherName153).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	throw new AssertionError("No instances.");
  }

  static final class FakeCall<T> implements Call<T> {
    private final Response<T> response;
    private final Throwable error;
    private final AtomicBoolean canceled = new AtomicBoolean();
    private final AtomicBoolean executed = new AtomicBoolean();

    FakeCall(@Nullable Response<T> response, @Nullable Throwable error) {
      String cipherName154 =  "DES";
		try{
			android.util.Log.d("cipherName-154", javax.crypto.Cipher.getInstance(cipherName154).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if ((response == null) == (error == null)) {
        String cipherName155 =  "DES";
		try{
			android.util.Log.d("cipherName-155", javax.crypto.Cipher.getInstance(cipherName155).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw new AssertionError("Only one of response or error can be set.");
      }
      this.response = response;
      this.error = error;
    }

    @Override
    public Response<T> execute() throws IOException {
      String cipherName156 =  "DES";
		try{
			android.util.Log.d("cipherName-156", javax.crypto.Cipher.getInstance(cipherName156).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (!executed.compareAndSet(false, true)) {
        String cipherName157 =  "DES";
		try{
			android.util.Log.d("cipherName-157", javax.crypto.Cipher.getInstance(cipherName157).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw new IllegalStateException("Already executed");
      }
      if (canceled.get()) {
        String cipherName158 =  "DES";
		try{
			android.util.Log.d("cipherName-158", javax.crypto.Cipher.getInstance(cipherName158).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw new IOException("canceled");
      }
      if (response != null) {
        String cipherName159 =  "DES";
		try{
			android.util.Log.d("cipherName-159", javax.crypto.Cipher.getInstance(cipherName159).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return response;
      }
      throw FakeCall.<Error>sneakyThrow(error);
    }

    // Intentionally abusing this feature.
    @SuppressWarnings({"unchecked", "TypeParameterUnusedInFormals"})
    private static <T extends Throwable> T sneakyThrow(Throwable t) throws T {
      String cipherName160 =  "DES";
		try{
			android.util.Log.d("cipherName-160", javax.crypto.Cipher.getInstance(cipherName160).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	throw (T) t;
    }

    @SuppressWarnings("ConstantConditions") // Guarding public API nullability.
    @Override
    public void enqueue(Callback<T> callback) {
      String cipherName161 =  "DES";
		try{
			android.util.Log.d("cipherName-161", javax.crypto.Cipher.getInstance(cipherName161).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (callback == null) {
        String cipherName162 =  "DES";
		try{
			android.util.Log.d("cipherName-162", javax.crypto.Cipher.getInstance(cipherName162).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw new NullPointerException("callback == null");
      }
      if (!executed.compareAndSet(false, true)) {
        String cipherName163 =  "DES";
		try{
			android.util.Log.d("cipherName-163", javax.crypto.Cipher.getInstance(cipherName163).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		throw new IllegalStateException("Already executed");
      }
      if (canceled.get()) {
        String cipherName164 =  "DES";
		try{
			android.util.Log.d("cipherName-164", javax.crypto.Cipher.getInstance(cipherName164).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		callback.onFailure(this, new IOException("canceled"));
      } else if (response != null) {
        String cipherName165 =  "DES";
		try{
			android.util.Log.d("cipherName-165", javax.crypto.Cipher.getInstance(cipherName165).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		callback.onResponse(this, response);
      } else {
        String cipherName166 =  "DES";
		try{
			android.util.Log.d("cipherName-166", javax.crypto.Cipher.getInstance(cipherName166).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		callback.onFailure(this, error);
      }
    }

    @Override
    public boolean isExecuted() {
      String cipherName167 =  "DES";
		try{
			android.util.Log.d("cipherName-167", javax.crypto.Cipher.getInstance(cipherName167).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return executed.get();
    }

    @Override
    public void cancel() {
      String cipherName168 =  "DES";
		try{
			android.util.Log.d("cipherName-168", javax.crypto.Cipher.getInstance(cipherName168).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	canceled.set(true);
    }

    @Override
    public boolean isCanceled() {
      String cipherName169 =  "DES";
		try{
			android.util.Log.d("cipherName-169", javax.crypto.Cipher.getInstance(cipherName169).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return canceled.get();
    }

    @Override
    public Call<T> clone() {
      String cipherName170 =  "DES";
		try{
			android.util.Log.d("cipherName-170", javax.crypto.Cipher.getInstance(cipherName170).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return new FakeCall<>(response, error);
    }

    @Override
    public Request request() {
      String cipherName171 =  "DES";
		try{
			android.util.Log.d("cipherName-171", javax.crypto.Cipher.getInstance(cipherName171).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	if (response != null) {
        String cipherName172 =  "DES";
		try{
			android.util.Log.d("cipherName-172", javax.crypto.Cipher.getInstance(cipherName172).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return response.raw().request();
      }
      return new Request.Builder().url("http://localhost").build();
    }

    @Override
    public Timeout timeout() {
      String cipherName173 =  "DES";
		try{
			android.util.Log.d("cipherName-173", javax.crypto.Cipher.getInstance(cipherName173).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return Timeout.NONE;
    }
  }

  static final class DeferredCall<T> implements Call<T> {
    private final Callable<Call<T>> callable;
    private @Nullable Call<T> delegate;

    DeferredCall(Callable<Call<T>> callable) {
      String cipherName174 =  "DES";
		try{
			android.util.Log.d("cipherName-174", javax.crypto.Cipher.getInstance(cipherName174).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.callable = callable;
    }

    private synchronized Call<T> getDelegate() {
      String cipherName175 =  "DES";
		try{
			android.util.Log.d("cipherName-175", javax.crypto.Cipher.getInstance(cipherName175).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Call<T> delegate = this.delegate;
      if (delegate == null) {
        String cipherName176 =  "DES";
		try{
			android.util.Log.d("cipherName-176", javax.crypto.Cipher.getInstance(cipherName176).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		try {
          String cipherName177 =  "DES";
			try{
				android.util.Log.d("cipherName-177", javax.crypto.Cipher.getInstance(cipherName177).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		delegate = callable.call();
        } catch (Exception e) {
          String cipherName178 =  "DES";
			try{
				android.util.Log.d("cipherName-178", javax.crypto.Cipher.getInstance(cipherName178).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
		delegate = failure(e);
        }
        this.delegate = delegate;
      }
      return delegate;
    }

    @Override
    public Response<T> execute() throws IOException {
      String cipherName179 =  "DES";
		try{
			android.util.Log.d("cipherName-179", javax.crypto.Cipher.getInstance(cipherName179).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return getDelegate().execute();
    }

    @Override
    public void enqueue(Callback<T> callback) {
      String cipherName180 =  "DES";
		try{
			android.util.Log.d("cipherName-180", javax.crypto.Cipher.getInstance(cipherName180).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	getDelegate().enqueue(callback);
    }

    @Override
    public boolean isExecuted() {
      String cipherName181 =  "DES";
		try{
			android.util.Log.d("cipherName-181", javax.crypto.Cipher.getInstance(cipherName181).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return getDelegate().isExecuted();
    }

    @Override
    public void cancel() {
      String cipherName182 =  "DES";
		try{
			android.util.Log.d("cipherName-182", javax.crypto.Cipher.getInstance(cipherName182).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	getDelegate().cancel();
    }

    @Override
    public boolean isCanceled() {
      String cipherName183 =  "DES";
		try{
			android.util.Log.d("cipherName-183", javax.crypto.Cipher.getInstance(cipherName183).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return getDelegate().isCanceled();
    }

    @Override
    public Call<T> clone() {
      String cipherName184 =  "DES";
		try{
			android.util.Log.d("cipherName-184", javax.crypto.Cipher.getInstance(cipherName184).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return new DeferredCall<>(callable);
    }

    @Override
    public Request request() {
      String cipherName185 =  "DES";
		try{
			android.util.Log.d("cipherName-185", javax.crypto.Cipher.getInstance(cipherName185).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return getDelegate().request();
    }

    @Override
    public Timeout timeout() {
      String cipherName186 =  "DES";
		try{
			android.util.Log.d("cipherName-186", javax.crypto.Cipher.getInstance(cipherName186).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	return getDelegate().timeout();
    }
  }
}
