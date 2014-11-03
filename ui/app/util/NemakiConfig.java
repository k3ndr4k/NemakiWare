package util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;

import constant.PropertyKey;
import play.Play;

public class NemakiConfig {
	public static String getValue(String key){
		String originalValue = Play.application().configuration().getString(key);
		List<String> files = Play.application().configuration().getStringList(PropertyKey.PROPERTY_FILES);
		
		if(CollectionUtils.isEmpty(files)){
			return originalValue;
		}else{
			//Load property files
			List<Properties>list = new ArrayList<Properties>();
			for(String file : files){
				InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
				Properties properties = new Properties();
				try{
					if(is != null){
						properties.load(is);
						list.add(properties);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			//Overwrite value
			String value = null;
			for(Properties config : list){
				value = config.getProperty(key);
				if(value != null){
					break;
				}
			}
			
			if(value == null){
				value = originalValue;
			}
			
			return value;
		}
	}
}