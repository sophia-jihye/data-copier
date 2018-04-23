package sophia.copier.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopierUtil {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final CopierUtil instance = new CopierUtil();

	private CopierUtil() {
	}

	public static CopierUtil instance() {
		return instance;
	}

	public long getTimeStamp() {
		return System.currentTimeMillis();
	}

	public Integer getElapsedTime(Long startTimeStamp, Long endTimeStamp) {
		String elapsedTimeSec = getElapsedFormat(startTimeStamp, endTimeStamp);
		Double elapsedTimeDouble = Double.parseDouble(elapsedTimeSec);
		Integer elapsedMillisec = (int) (elapsedTimeDouble * 1000);
		return elapsedMillisec;
	}

	private String getElapsedFormat(long startTimestamp, long endTimestamp) {
		long sec = (endTimestamp - startTimestamp) / 1000;
		long milliSec = (endTimestamp - startTimestamp) % 1000;
		StringBuilder builder = new StringBuilder();
		builder.append(sec).append('.').append(leftPadZero(milliSec, 3));
		return builder.toString();
	}

	private String leftPadZero(long value, int length) {
		StringBuilder builder = new StringBuilder();
		for (int ix = 0; ix < length; ix++) {
			builder.append('0');
		}
		builder.append(Long.toString(value));
		return builder.substring(builder.length() - length);
	}

	public String getNextNumberString(String str) {
		if (isNumber(str)) {
			Integer i = Integer.parseInt(str);
			i++;
			return i.toString();
		}
		return str;
	}

	public boolean isNumber(String obj) {
		try {
			Long.parseLong(obj);
		} catch (Exception e) {
			logger.error("[COPIER] input value is not number. input value: {} ", obj);
			return false;
		}
		return true;
	}
}
