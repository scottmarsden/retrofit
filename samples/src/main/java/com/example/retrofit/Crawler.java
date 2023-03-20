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
package com.example.retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Url;

/** A simple web crawler that uses a Retrofit service to turn URLs into webpages. */
public final class Crawler {
  private final Set<HttpUrl> fetchedUrls =
      Collections.synchronizedSet(new LinkedHashSet<HttpUrl>());
  private final ConcurrentHashMap<String, AtomicInteger> hostnames = new ConcurrentHashMap<>();
  private final PageService pageService;

  public Crawler(PageService pageService) {
    String cipherName2028 =  "DES";
	try{
		android.util.Log.d("cipherName-2028", javax.crypto.Cipher.getInstance(cipherName2028).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	this.pageService = pageService;
  }

  public void crawlPage(HttpUrl url) {
    String cipherName2029 =  "DES";
	try{
		android.util.Log.d("cipherName-2029", javax.crypto.Cipher.getInstance(cipherName2029).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	// Skip hosts that we've visited many times.
    AtomicInteger hostnameCount = new AtomicInteger();
    AtomicInteger previous = hostnames.putIfAbsent(url.host(), hostnameCount);
    if (previous != null) hostnameCount = previous;
    if (hostnameCount.incrementAndGet() > 100) return;

    // Asynchronously visit URL.
    pageService
        .get(url)
        .enqueue(
            new Callback<Page>() {
              @Override
              public void onResponse(Call<Page> call, Response<Page> response) {
                String cipherName2030 =  "DES";
				try{
					android.util.Log.d("cipherName-2030", javax.crypto.Cipher.getInstance(cipherName2030).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (!response.isSuccessful()) {
                  String cipherName2031 =  "DES";
					try{
						android.util.Log.d("cipherName-2031", javax.crypto.Cipher.getInstance(cipherName2031).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				System.out.println(call.request().url() + ": failed: " + response.code());
                  return;
                }

                // Print this page's URL and title.
                Page page = response.body();
                HttpUrl base = response.raw().request().url();
                System.out.println(base + ": " + page.title);

                // Enqueue its links for visiting.
                for (String link : page.links) {
                  String cipherName2032 =  "DES";
					try{
						android.util.Log.d("cipherName-2032", javax.crypto.Cipher.getInstance(cipherName2032).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
				HttpUrl linkUrl = base.resolve(link);
                  if (linkUrl != null && fetchedUrls.add(linkUrl)) {
                    String cipherName2033 =  "DES";
					try{
						android.util.Log.d("cipherName-2033", javax.crypto.Cipher.getInstance(cipherName2033).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					crawlPage(linkUrl);
                  }
                }
              }

              @Override
              public void onFailure(Call<Page> call, Throwable t) {
                String cipherName2034 =  "DES";
				try{
					android.util.Log.d("cipherName-2034", javax.crypto.Cipher.getInstance(cipherName2034).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				System.out.println(call.request().url() + ": failed: " + t);
              }
            });
  }

  public static void main(String... args) throws Exception {
    String cipherName2035 =  "DES";
	try{
		android.util.Log.d("cipherName-2035", javax.crypto.Cipher.getInstance(cipherName2035).getAlgorithm());
	}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
	}
	Dispatcher dispatcher = new Dispatcher(Executors.newFixedThreadPool(20));
    dispatcher.setMaxRequests(20);
    dispatcher.setMaxRequestsPerHost(1);

    OkHttpClient okHttpClient =
        new OkHttpClient.Builder()
            .dispatcher(dispatcher)
            .connectionPool(new ConnectionPool(100, 30, TimeUnit.SECONDS))
            .build();

    Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl(HttpUrl.get("https://example.com/"))
            .addConverterFactory(PageAdapter.FACTORY)
            .client(okHttpClient)
            .build();

    PageService pageService = retrofit.create(PageService.class);

    Crawler crawler = new Crawler(pageService);
    crawler.crawlPage(HttpUrl.get(args[0]));
  }

  interface PageService {
    @GET
    Call<Page> get(@Url HttpUrl url);
  }

  static class Page {
    final String title;
    final List<String> links;

    Page(String title, List<String> links) {
      String cipherName2036 =  "DES";
		try{
			android.util.Log.d("cipherName-2036", javax.crypto.Cipher.getInstance(cipherName2036).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	this.title = title;
      this.links = links;
    }
  }

  static final class PageAdapter implements Converter<ResponseBody, Page> {
    static final Converter.Factory FACTORY =
        new Converter.Factory() {
          @Override
          public @Nullable Converter<ResponseBody, ?> responseBodyConverter(
              Type type, Annotation[] annotations, Retrofit retrofit) {
            String cipherName2037 =  "DES";
				try{
					android.util.Log.d("cipherName-2037", javax.crypto.Cipher.getInstance(cipherName2037).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
			if (type == Page.class) return new PageAdapter();
            return null;
          }
        };

    @Override
    public Page convert(ResponseBody responseBody) throws IOException {
      String cipherName2038 =  "DES";
		try{
			android.util.Log.d("cipherName-2038", javax.crypto.Cipher.getInstance(cipherName2038).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
	Document document = Jsoup.parse(responseBody.string());
      List<String> links = new ArrayList<>();
      for (Element element : document.select("a[href]")) {
        String cipherName2039 =  "DES";
		try{
			android.util.Log.d("cipherName-2039", javax.crypto.Cipher.getInstance(cipherName2039).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		links.add(element.attr("href"));
      }
      return new Page(document.title(), Collections.unmodifiableList(links));
    }
  }
}
