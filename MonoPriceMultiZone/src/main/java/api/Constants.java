package api;

public class Constants {
	//MAX VALUES
	public static final Integer MAX_VOLUME = 38;
	public static final Integer MAX_TREBLE = 14;
	public static final Integer MAX_BASS = 14;
	public static final Integer MAX_BALANCE = 20;
	public static final Integer MAX_CHANNEL = 6;
	
	
	//CONTROLS
	/**
	 * xxPPuu 'CR' 
	 * xx=zone 
	 * PP=control action
	 * uu=value
	 */
	public static final String SET_VALUE = "%s%s%02d 'CR'";
	public static final String INQUIRE = "?%s%02d 'CR'";
	public static final String INQUIRE_ALL = "?%s 'CR'";
	
}
