package api.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import api.vo.ZoneInfo;
import jssc.SerialPort;
import jssc.SerialPortException;

public final class Utils {
	public static void ensurePortIsOpen(SerialPort serialPort)
			throws SerialPortException {
		if (serialPort.isOpened())
			serialPort.openPort();
	}

	/**
	 * Determine if the value is within range for the operation
	 * 
	 * @param operation
	 * @param value
	 * @return
	 */
	public static boolean isValueValid(String operation, Integer value) {
		if (value < 0)
			return false;
		switch (operation) {
		case "VO":
			if (value <= Constants.MAX_VOLUME)
				return true;
			break;
		case "TR":
			if (value <= Constants.MAX_TREBLE)
				return true;
			break;
		case "BS":
			if (value <= Constants.MAX_BASS)
				return true;
			break;
		case "BL":
			if (value <= Constants.MAX_BALANCE)
				return true;
			break;
		case "CH":
			if (value <= Constants.MAX_CHANNEL)
				return true;
			break;
		default:
			return false;
		}
		return false;
	}

	/**
	 * Get value from bytes. Used for the following pattern xxPPuu 'CR' uu =
	 * value
	 * 
	 * @param bytes
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Integer getValueFromByteArray(byte[] bytes)
			throws UnsupportedEncodingException {
		String str = new String(bytes, "UTF-8");
		String value = str.substring(4, 5);
		return Integer.parseInt(value);
	}

	public static List<ZoneInfo> writeBytesToObject(byte[] bytes)
			throws Exception {
		String str = new String(bytes, "UTF-8");
		String[] arr = StringUtils.delimitedListToStringArray(str, "'CR'");
		List<ZoneInfo> zoneList = new ArrayList<ZoneInfo>();
		for (String s : arr) {
			zoneList.add(Utils.createZoneInfoFromString(s));
		}
		return null;
	}

	/**
	 * Take a line and transform it into a Zone Info object
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */

	public static ZoneInfo createZoneInfoFromString(String str)
			throws Exception {
		// Check if there is enough data
		ZoneInfo zoneInfo = new ZoneInfo();
		if (str.length() >= 22) {
			int i = 0;
			zoneInfo.setZone(str.substring(0, 2));
			zoneInfo.setPaControl(str.substring(2, 4));
			zoneInfo.setPowerControl(str.substring(4, 6));
			zoneInfo.setMuteControl(str.substring(6, 8));
			zoneInfo.setDtControl(str.substring(8, 10));
			zoneInfo.setVolumeControl(str.substring(10, 12));
			zoneInfo.setTrebleControl(str.substring(12, 14));
			zoneInfo.setBassControl(str.substring(14, 16));
			zoneInfo.setBalanceControl(str.substring(16, 18));
			zoneInfo.setSourceControl(str.substring(18, 20));
			zoneInfo.setKeypadConnect(str.substring(20, 22));
		} else {
			throw new Exception();
		}
		return zoneInfo;
	}
}
