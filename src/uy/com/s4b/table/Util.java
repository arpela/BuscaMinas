package uy.com.s4b.table;

import net.rim.device.api.system.Display;

public class Util {

	public static final String RES_320_240 = "320X240";

	public static final String RES_360_480 = "360X480";

	
	public static String getImageByResolution(String nameImage) {
		String nameResolution = null;
		if (Util.getResolution().equalsIgnoreCase(RES_360_480)) {
			nameResolution = nameImage + "-" + RES_360_480 + ".png";
		} else if (Util.getResolution().equalsIgnoreCase(RES_320_240)) {
			nameResolution = nameImage + "-" + RES_320_240 + ".png";
		} else {
			nameResolution = nameImage;
		}
		return nameResolution;
	}

	private static String getResolution() {
		return Display.getWidth() + "X" + Display.getHeight();
	}
}
