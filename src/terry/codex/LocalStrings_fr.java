package terry.codex;

import java.util.Collections;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * @编写人： yh.zeng
 * @编写时间：2017-12-3 下午4:58:26
 * @文件描述: LocalStrings法语配置文件
 */
public class LocalStrings_fr extends ResourceBundle{

	@Override
	protected Object handleGetObject(String key) {
		if(key.equals("tmpdir")){
			return "{0} itinéraires désignés avec moins";
		}
		return null;
	}

	@Override
	public Enumeration<String> getKeys() {
		 return Collections.enumeration(keySet());
	}
} 
