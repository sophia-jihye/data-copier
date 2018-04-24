package sophia.copier;

import java.util.Properties;

import sophia.copier.constant.IConstants;
import sophia.copier.manager.ConfigManager;
import sophia.copier.manager.CopyManager;
import sophia.copier.util.CopierUtil;

public class CopierMain {

	public static void main(String[] args) {

		// copier-config.properties 파일 경로를 읽어옴
		// String path = args[0];
		String path = "C:\\DevelopTools\\Workspace\\git2\\data-copier\\data-copier\\config\\copier-config.properties";

		CopyManager copyManager = new CopyManager();

		// copier-config.properties 파일 내용 load
		Properties prop = ConfigManager.instance().loadProp(path);

		String option = prop.getProperty(IConstants.CONFIG_PROPERTY.COPIER_OPTION);

		// option = 'onetime' : 1번만 실행
		if (IConstants.CONFIG_PROPERTY.OPTION_ONE_TIME.equalsIgnoreCase(option)) {

			// 실행
			copyManager.startCopy();

		} else if (IConstants.CONFIG_PROPERTY.OPTION_CONTINUE.equalsIgnoreCase(option)) {
			// option = 'continue' : 'off'가 입력될 때까지 계속 실행

			while (true) {
				// copier-config.properties 파일에 'off'가 입력될 시 break;
				option = ConfigManager.instance().loadPropOnly(path)
						.getProperty(IConstants.CONFIG_PROPERTY.COPIER_OPTION);
				if (IConstants.CONFIG_PROPERTY.OPTION_OFF.equalsIgnoreCase(option)) {
					break;
				}

				// 실행
				Integer elapsedMillisec = copyManager.startCopy();

				Integer maxWaitMillisec = ConfigManager.instance().getConfigDto().getCopierMaxWait();
				Integer minWaitMillisec = ConfigManager.instance().getConfigDto().getCopierMinWait();
				Integer sleepMilliseconds = maxWaitMillisec;

				if (elapsedMillisec.compareTo(maxWaitMillisec) < 0) {
					sleepMilliseconds = maxWaitMillisec - elapsedMillisec;
				} else {
					sleepMilliseconds = minWaitMillisec;
				}
				CopierUtil.instance().sleepThread(sleepMilliseconds);
			}

		}

	}

}
