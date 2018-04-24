package sophia.copier.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sophia.copier.constant.IConstants;
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

		logger.info("[COPIER] =====================COPIER-FINISHED=====================");
		return elapsedMillisec;
	}

	private void runProcess() {

		List<MappingDto> mappingDtoList = ConfigManager.instance().getConfigDto().getMappingDtoList();

		for (MappingDto mappingDto : mappingDtoList) {

			// 1. sourceQueryId로부터 데이터를 가져옴
			List<TempOutputDto> sourceOutputList = FactoryManager.instance().selectFromSource(mappingDto);

			// 2. targetQueryId에 데이터를 upsert
			Integer updateCount = 0;
			Integer insertCount = 0;
			for (int i = 0; i < sourceOutputList.size(); i++) {
				TempOutputDto output = sourceOutputList.get(i);

				// 2-1. dttm 설정
				String dttm = CopierUtil.instance().getDateTimeMilFormat();
				output.setDttm(dttm);

				// 2-2. update
				int tempCount = FactoryManager.instance()
						.updateTarget(IConstants.QUERY_PREFIX.UPDATE + mappingDto.getTargetTableName(), output);
				updateCount += tempCount;

				// 2-3. insert
				if (tempCount == 0) {
					insertCount += FactoryManager.instance()
							.insertTarget(IConstants.QUERY_PREFIX.INSERT + mappingDto.getTargetTableName(), output);
				}
			}

			logger.info("[COPIER] --------------------");
			logger.debug("[COPIER] Summary: selectCount: {}", sourceOutputList.size());
			logger.debug("[COPIER] Summary: updateCount: {}", updateCount);
			logger.debug("[COPIER] Summary: insertCount: {}", insertCount);

		}

	}

}
