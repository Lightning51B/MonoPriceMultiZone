package config;

import java.io.Serializable;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component 
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class Configuration implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5218836414705619810L;

	@Override
	public String toString() {
		return "Configuration [baudRate=" + baudRate + ", databits=" + databits
				+ ", stopBits=" + stopBits + ", parity=" + parity + "]";
	}

	private Integer baudRate;
	private Integer databits;
	private Integer stopBits;
	private Integer parity;

	public Integer getBaudRate() {
		return baudRate;
	}

	public void setBaudRate(Integer baudRate) {
		this.baudRate = baudRate;
	}

	public Integer getDatabits() {
		return databits;
	}

	public void setDatabits(Integer databits) {
		this.databits = databits;
	}

	public Integer getStopBits() {
		return stopBits;
	}

	public void setStopBits(Integer stopBits) {
		this.stopBits = stopBits;
	}

	public Integer getParity() {
		return parity;
	}

	public void setParity(Integer parity) {
		this.parity = parity;
	}

}
