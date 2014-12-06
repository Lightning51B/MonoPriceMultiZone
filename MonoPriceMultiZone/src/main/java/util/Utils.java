package util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import vo.ZoneInfo;
import jssc.SerialPort;
import jssc.SerialPortException;

public final class Utils {
	public static void ensurePortIsOpen(SerialPort serialPort) throws SerialPortException{
		if (serialPort.isOpened()) 
			serialPort.openPort();
	}
	
	/** 
	 * Determine if the value is within range for the operation
	 * @param operation
	 * @param value
	 * @return
	 */
	public static boolean isValueValid(String operation, Integer value) {
		if (value < 0)
			return false;
		switch (operation) {
		case "VO":
			if (value > Constants.MAX_VOLUME)
				return false;
		case "TR":
			if (value > Constants.MAX_TREBLE)
				return false;
			break;
		case "BS":
			if (value > Constants.MAX_BASS)
				return false;
		case "BL":
			if (value > Constants.MAX_BALANCE)
				return false;
		case "CH":
			if (value > Constants.MAX_CHANNEL)
				return false;
		default:
			return false;
		}
		return true;
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
	
	
	public static List<ZoneInfo> writeBytesToObject(byte[] bytes) throws Exception {
		String str = new String(bytes, "UTF-8");
		String[] arr = StringUtils.delimitedListToStringArray(str, "'CR'");
		List<ZoneInfo> zoneList = new ArrayList<ZoneInfo>();
		for(String s : arr) {
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

	public static ZoneInfo createZoneInfoFromString(String str) throws Exception {
		//Check if there is enough data
		ZoneInfo zoneInfo = new ZoneInfo();
		if(str.length() >= 22) {
			int i=0;
			zoneInfo.setZone(str.substring(i++, i++));
			zoneInfo.setPaControl(str.substring(i++, i++));
			zoneInfo.setPowerControl(str.substring(i++, i++));
			zoneInfo.setMuteControl(str.substring(i++, i++));
			zoneInfo.setDtControl(str.substring(i++, i++));
			zoneInfo.setVolumeControl(str.substring(i++, i++));
			zoneInfo.setTrebleControl(str.substring(i++, i++));
			zoneInfo.setBassControl(str.substring(i++, i++));
			zoneInfo.setBalanceControl(str.substring(i++, i++));
			zoneInfo.setSourceControl(str.substring(i++, i++));
			zoneInfo.setKeypadConnect(str.substring(i++, i++));
		}else {
			throw new Exception();
		}
		return zoneInfo;
	}
}
