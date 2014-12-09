package api.util;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

@Component
public class SerialPortWrapper {

	private SerialPort serialPort;

	@Autowired public MultiZoneSerialPortEventListener multiZoneSerialPortEventListener;
	   
	public SerialPort getSerialPort() {
		return serialPort;
	}
	public void setSerialPort(SerialPort serialPort) {
		this.serialPort = serialPort;
		try {
			this.serialPort.addEventListener(multiZoneSerialPortEventListener);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
