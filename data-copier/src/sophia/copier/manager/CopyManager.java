package sophia.copier.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sophia.copier.dto.MappingDto;
import sophia.copier.dto.TempOutputDto;
import sophia.copier.util.CopierUtil;

public class CopyManager {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Integer startCopy() {

		Long startTimeStamp = CopierUtil.instance().getTimeStamp();

		logger.info("[COPIER] =====================COPIER-STARTED=====================");

		logger.info("[COPIER] Current Thread: {}", Thread.currentThread());
		runProcess();

		Long endTimeStamp = CopierUtil.instance().getTimeStamp();
		Integer elapsedMillisec = CopierUtil.instance().getElapsedTime(startTimeStamp, endTimeStamp);
		logger.info("[COPIER] --------------------");
		logger.info("[COPIER] Elapsed Time: {}(MilliSeconds)", elapsedMillisec);
		logger.info("[COPIER] --------------------");

		logger.info("[COPIER] =====================COPIER-FINISHED=====================");
		return elapsedMillisec;
	}

	private void runProcess() {

		List<MappingDto> mappingDtoList = ConfigManager.instance().getConfigDto().getMappingDtoList();

		for (MappingDto mappingDto : mappingDtoList) {

			// 1. sourceQueryId로부터 데이터를 가져옴
			List<TempOutputDto> sourceOutputList = FactoryManager.instance().selectFromSource(mappingDto);
			
			logger.debug("sourceOutputList: {}",sourceOutputList);

		}

	}

}
