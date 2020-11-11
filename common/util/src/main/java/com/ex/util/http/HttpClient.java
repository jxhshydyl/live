package com.ex.util.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class HttpClient {

	private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36";
	private static CloseableHttpClient closeableHttpClient = null;

	static {
		try {
			SSLContextBuilder sSLContextBuilder = new SSLContextBuilder();
			// 全部信任 不做身份鉴定
			sSLContextBuilder.loadTrustMaterial(null, new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] x509Certificates, String authType) throws CertificateException {
					return true;
				}
			});
			SSLConnectionSocketFactory sSLConnectionSocketFactory = new SSLConnectionSocketFactory(sSLContextBuilder.build(), new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2" }, null, NoopHostnameVerifier.INSTANCE);
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", new PlainConnectionSocketFactory()).register("https", sSLConnectionSocketFactory).build();
			PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(registry);
//			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(60000).setSocketTimeout(40000).build();
			RequestConfig requestConfig = RequestConfig.custom().build();
			closeableHttpClient = HttpClients.custom().setSSLSocketFactory(sSLConnectionSocketFactory).setConnectionManager(poolingHttpClientConnectionManager).setConnectionManagerShared(true).setDefaultRequestConfig(requestConfig).build();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static final String POST(String url, Map<String, String> header, Map<String, String> params) throws Exception {
		CloseableHttpResponse closeableHttpResponse = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("User-Agent", userAgent);
			if (null != header && !header.isEmpty()) {
				for (Map.Entry<String, String> entry : header.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
			if (null != params && !params.isEmpty()) {
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> entry : params.entrySet()) {
					BasicNameValuePair basicNameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
					nameValuePairs.add(basicNameValuePair);
				}
				UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs, Charset.defaultCharset());
				httpPost.setEntity(urlEncodedFormEntity);
			}
			closeableHttpResponse = closeableHttpClient.execute(httpPost);
			HttpEntity httpEntity = closeableHttpResponse.getEntity();
			String result = EntityUtils.toString(httpEntity, Charset.defaultCharset());
			EntityUtils.consume(httpEntity);
			return result;
		} catch (Exception exception) {
			throw exception;
		} finally {
			if (null != closeableHttpResponse) {
				closeableHttpResponse.close();
			}
		}
	}

	public static final String POST(String url, Map<String, String> params) throws Exception {
		CloseableHttpResponse closeableHttpResponse = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("User-Agent", userAgent);
			if (null != params && !params.isEmpty()) {
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> entry : params.entrySet()) {
					BasicNameValuePair basicNameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
					nameValuePairs.add(basicNameValuePair);
				}
				UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs, Charset.defaultCharset());
				httpPost.setEntity(urlEncodedFormEntity);
			}
			closeableHttpResponse = closeableHttpClient.execute(httpPost);
			HttpEntity httpEntity = closeableHttpResponse.getEntity();
			String result = EntityUtils.toString(httpEntity, Charset.defaultCharset());
			EntityUtils.consume(httpEntity);
			return result;
		} catch (Exception exception) {
			throw exception;
		} finally {
			if (null != closeableHttpResponse) {
				closeableHttpResponse.close();
			}
		}
	}

	public static final String POST(String url, Map<String, String> header, String entity) throws Exception {
		CloseableHttpResponse closeableHttpResponse = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("User-Agent", userAgent);
			if (null != header && !header.isEmpty()) {
				for (Map.Entry<String, String> entry : header.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
			if (null != entity && 0 != entity.length()) {
				StringEntity stringEntity = new StringEntity(entity, Charset.defaultCharset());
				httpPost.setEntity(stringEntity);
			}
			closeableHttpResponse = closeableHttpClient.execute(httpPost);
			HttpEntity httpEntity = closeableHttpResponse.getEntity();
			String result = EntityUtils.toString(httpEntity, Charset.defaultCharset());
			EntityUtils.consume(httpEntity);
			return result;
		} catch (Exception exception) {
			throw exception;
		} finally {
			if (null != closeableHttpResponse) {
				closeableHttpResponse.close();
			}
		}
	}

	public static final String POST(String url, String entity) throws Exception {
		CloseableHttpResponse closeableHttpResponse = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("User-Agent", userAgent);
			if (null != entity && 0 != entity.length()) {
				StringEntity stringEntity = new StringEntity(entity, Charset.defaultCharset());
				httpPost.setEntity(stringEntity);
			}
			closeableHttpResponse = closeableHttpClient.execute(httpPost);
			HttpEntity httpEntity = closeableHttpResponse.getEntity();
			String result = EntityUtils.toString(httpEntity, Charset.defaultCharset());
			EntityUtils.consume(httpEntity);
			return result;
		} catch (Exception exception) {
			throw exception;
		} finally {
			if (null != closeableHttpResponse) {
				closeableHttpResponse.close();
			}
		}
	}

	public static final String GET(String url) throws Exception {
		CloseableHttpResponse closeableHttpResponse = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("User-Agent", userAgent);
			closeableHttpResponse = closeableHttpClient.execute(httpGet);
			HttpEntity httpEntity = closeableHttpResponse.getEntity();
			String result = EntityUtils.toString(httpEntity, Charset.defaultCharset());
			EntityUtils.consume(httpEntity);
			return result;
		} catch (Exception exception) {
			throw exception;
		} finally {
			if (null != closeableHttpResponse) {
				closeableHttpResponse.close();
			}
		}
	}

}
