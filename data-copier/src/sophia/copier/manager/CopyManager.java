package sophia.copier.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sophia.copier.util.CopierUtil;

public class CopyManager {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Integer startCopy() {

		Long startTimeStamp = CopierUtil.instance().getTimeStamp();

		logger.info("[COPIER] =====================COPIER-STARTED=====================");

		logger.info("[COPIER] Current Thread: {}", Thread.currentThread());
		// runProcess();

		Long endTimeStamp = CopierUtil.instance().getTimeStamp();
		Integer elapsedMillisec = CopierUtil.instance().getElapsedTime(startTimeStamp, endTimeStamp);
		logger.info("[COPIER] --------------------");
		logger.info("[COPIER] Elapsed Time: {}(MilliSeconds)", elapsedMillisec);
		logger.info("[COPIER] --------------------");

		logger.info("[COPIER] =====================COPIER-FINISHED=====================");
		return elapsedMillisec;
	}

}
