package api;

import java.io.UnsupportedEncodingException;
import java.util.List;

import jssc.SerialPort;
import jssc.SerialPortException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import util.Constants;
import config.Configuration;
import util.Utils;
import vo.ZoneInfo;

@RestController
public class MultiZoneRestContoller {
	@Autowired
	Configuration configuration;
	@Autowired
	SerialPort serialPort;

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
	@RequestMapping("/setVolume/{opearation}/{zone}/{value}")
	public Integer admin(Model model, @PathVariable String operation,
			@PathVariable String zone, @PathVariable Integer value)
			throws SerialPortException {

		if (!Utils.isValueValid(operation, value))
			return 0;
		String command = String.format(Constants.SET_VALUE, zone, operation,
				value);
		Utils.ensurePortIsOpen(serialPort);
		serialPort.writeBytes(command.getBytes());
		return value;
	}

	
	@RequestMapping("/inquire/{opearation}/{zone}")
	public Integer inquire(Model model, @PathVariable String operation,
			@PathVariable String zone) throws SerialPortException,
			UnsupportedEncodingException {

		String command = String.format(Constants.INQUIRE, zone, operation);

		Utils.ensurePortIsOpen(serialPort);
		serialPort.writeBytes(command.getBytes());
		return Utils.getValueFromByteArray(serialPort.readBytes());

	}


	
	@RequestMapping("/inquireAll/{zone}")
	public List<ZoneInfo> inquireAll(Model model,
			@PathVariable String zone) throws Exception {

		String command = String.format(Constants.INQUIRE_ALL, zone);

		Utils.ensurePortIsOpen(serialPort);
		serialPort.writeBytes(command.getBytes());
		return Utils.writeBytesToObject(serialPort.readBytes());

	}

}
