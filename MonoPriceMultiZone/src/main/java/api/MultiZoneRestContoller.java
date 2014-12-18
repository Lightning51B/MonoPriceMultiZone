package api;

import java.io.UnsupportedEncodingException;

import jssc.SerialPort;
import jssc.SerialPortException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.config.Configuration;
import api.util.Constants;
import api.util.SerialPortWrapper;
import api.util.Utils;

@RestController
public class MultiZoneRestContoller {

	@Autowired
	SerialPortWrapper serialPortWrapper;

	/**
	 * Sets the value for an operation to the selected zone
	 * 
	 * @param model
	 * @param operation
	 * @param zone
	 * @param value
	 * @return
	 * @throws SerialPortException
	 */
	@RequestMapping("/setValue/{operation}/{zone}/{value}")
	public void admin(Model model, @PathVariable String operation,
			@PathVariable String zone, @PathVariable Integer value)
			throws SerialPortException {

		if (Utils.isValueValid(operation, value)) {

			String command = String.format(Constants.SET_VALUE, zone,
					operation, value);
			System.out.println(command);
			/*SerialPort serialPort = serialPortWrapper.getSerialPort();
			Utils.ensurePortIsOpen(serialPort);
			serialPort.writeBytes(command.getBytes());*/
		}
	}

	@RequestMapping("/inquire/{opearation}/{zone}")
	public void inquire(Model model, @PathVariable String operation,
			@PathVariable String zone) throws SerialPortException,
			UnsupportedEncodingException {

		String command = String.format(Constants.INQUIRE, zone, operation);
		SerialPort serialPort = serialPortWrapper.getSerialPort();
		Utils.ensurePortIsOpen(serialPort);
		serialPort.writeBytes(command.getBytes());

	}

	@RequestMapping("/inquireAll/{zone}")
	public void inquireAll(Model model, @PathVariable String zone)
			throws Exception {

		String command = String.format(Constants.INQUIRE_ALL, zone);
		SerialPort serialPort = serialPortWrapper.getSerialPort();
		Utils.ensurePortIsOpen(serialPort);
		serialPort.writeBytes(command.getBytes());

	}

}
