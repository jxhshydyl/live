/**    
* @Title: AliOssProperties.java  
* @Package com.yijiu.eotc.commons.spring.config  
* @Description:   
* @author vDalf   2020年5月27日 下午6:59:26
*/
package com.ex.file.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**  
 * @ClassName: AliOssProperties  
 * @Description:  
 * @author vDalf  2020年5月27日 下午6:59:26
 */
@ConfigurationProperties(prefix = "app.oss")
public class AliOssProperties {
	
	private String endpoint = "http://oss-cn-hongkong.aliyuncs.com";
	
	private String bucket = "eotc-oss";
	
	private String accessKeyId = "LTAI4GHj5aBiZnXVcQ3BdxUX";
	
	private String accessKeySecret = "7oDffqLEnp9VrzYMQhgw2nOusH9WbG";
	
	private int maxConnections = 2;
	
	private int connectionRequestTimeout = 60000;
	
	private int connectionTimeout = 3000;
	
	private boolean requestTimeoutEnabled = true;
	
	private int requestTimeout = 180000;
	
	private String fileDownloadHost = "https://eotc-oss.oss-cn-hongkong.aliyuncs.com";
	
	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public int getMaxConnections() {
		return maxConnections;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	public int getConnectionRequestTimeout() {
		return connectionRequestTimeout;
	}

	public void setConnectionRequestTimeout(int connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public boolean isRequestTimeoutEnabled() {
		return requestTimeoutEnabled;
	}

	public void setRequestTimeoutEnabled(boolean requestTimeoutEnabled) {
		this.requestTimeoutEnabled = requestTimeoutEnabled;
	}

	public int getRequestTimeout() {
		return requestTimeout;
	}

	public void setRequestTimeout(int requestTimeout) {
		this.requestTimeout = requestTimeout;
	}

	public String getFileDownloadHost() {
		return fileDownloadHost;
	}

	public void setFileDownloadHost(String fileDownloadHost) {
		this.fileDownloadHost = fileDownloadHost;
	}

}
