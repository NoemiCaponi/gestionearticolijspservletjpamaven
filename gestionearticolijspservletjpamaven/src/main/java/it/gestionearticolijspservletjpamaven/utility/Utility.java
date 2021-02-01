package it.gestionearticolijspservletjpamaven.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class Utility {
	

	public static boolean validateInput(String codiceinputparam, String descrizioneinputparam, String prezzoinputstringparam,
			String dataarrivostringparam) {
			// prima controlliamo che non siano vuoti
		if (StringUtils.isBlank(codiceinputparam) || StringUtils.isBlank(descrizioneinputparam)
				|| !NumberUtils.isCreatable(prezzoinputstringparam) || StringUtils.isBlank(dataarrivostringparam)) {
			return false;
		}
		return true;
	}
	public static Date parseDateArrivoFromString(String dataArrivoStringParam) {
		if (StringUtils.isBlank(dataArrivoStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataArrivoStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Integer parsePrezzoFromString(String prezzoStringParam) {
		if (StringUtils.isBlank(prezzoStringParam))
			return 0;

		try {
			return Integer.parseInt(prezzoStringParam);
		} catch (Exception e) {
			return 0;
		}
	}


}
