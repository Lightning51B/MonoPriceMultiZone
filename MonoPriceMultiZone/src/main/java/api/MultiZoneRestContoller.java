package api;

import java.io.UnsupportedEncodingException;

import jssc.SerialPort;
import jssc.SerialPortException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import config.Configuration;

@RestController
public class MultiZoneRestContoller {
	@Autowired
	Configuration configuration;
	@Autowired
	SerialPort serialPort;
	
	
	/**
	 * Sets the value for an operation to the selected zone
	 * @param model
	 * @param operation
	 * @param zone
	 * @param value
	 * @return
	 * @throws SerialPortException
	 */
	@RequestMapping("/setVolume/{opearation}/{zone}/{value}")
	public Integer admin(Model model, @PathVariable String operation,
			@PathVariable String zone, @PathVariable Integer value) throws SerialPortException {

	
		String command;
		switch (operation) {
		case "VO":
			if (value > Constants.MAX_VOLUME)
				return Constants.MAX_VOLUME;
			if (value < 0)
				return 0;
			break;
		case "TR":
			if (value > Constants.MAX_TREBLE)
				return Constants.MAX_TREBLE;
			if (value < 0)
				return 0;
			break;
		case "BS":
			if (value > Constants.MAX_BASS)
				return Constants.MAX_BASS;
			if (value < 0)
				return 0;
			break;
		case "BL":
			if (value > Constants.MAX_BALANCE)
				return Constants.MAX_BALANCE;
			if (value < 0)
				return 0;
			break;
		case "CH":
			if (value > Constants.MAX_CHANNEL)
				return Constants.MAX_CHANNEL;
			if (value < 0)
				return 0;
			break;
		default:
			return 0;
		}
		command = String.format(Constants.SET_VALUE, zone, operation, value);
		if (serialPort.isOpened()) {			
			serialPort.writeBytes(command.getBytes());

		}else{
			serialPort.openPort();
			serialPort.writeBytes(command.getBytes());
		}

		return value;
	}
	
	@RequestMapping("/inquire/{opearation}/{zone}")
	public Integer inquire(Model model, @PathVariable String operation,
			@PathVariable String zone) throws SerialPortException, UnsupportedEncodingException {

	
		String command = String.format(Constants.INQUIRE, zone, operation);
		if (serialPort.isOpened()) {
			serialPort.writeBytes(command.getBytes());
			return getValueFromByteArray(serialPort.readBytes());

		}else{
			serialPort.openPort();
			serialPort.writeBytes(command.getBytes());
			return getValueFromByteArray(serialPort.readBytes());
		}

		
	}
	
	private Integer getValueFromByteArray(byte[] bytes) throws UnsupportedEncodingException{
		String str = new String(bytes, "UTF-8");
		String value = str.substring(4, 5);
		return Integer.parseInt(value);
	}
}
