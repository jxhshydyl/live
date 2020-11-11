
package com.ex.message.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.message")
public class MessageProperties {
	private MLSmsProperties ml;
	private SYSmsProperties sy;
	private EmailProperties email;
	private int maxFailResendTime = 3;
	private int channel;

	public MLSmsProperties getMl() {
		return ml;
	}

	public void setMl(MLSmsProperties ml) {
		this.ml = ml;
	}

	public SYSmsProperties getSy() {
		return sy;
	}

	public void setSy(SYSmsProperties sy) {
		this.sy = sy;
	}

	public EmailProperties getEmail() {
		return email;
	}

	public void setEmail(EmailProperties email) {
		this.email = email;
	}

	public int getMaxFailResendTime() {
		return maxFailResendTime;
	}

	public void setMaxFailResendTime(int maxFailResendTime) {
		this.maxFailResendTime = maxFailResendTime;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}
}
