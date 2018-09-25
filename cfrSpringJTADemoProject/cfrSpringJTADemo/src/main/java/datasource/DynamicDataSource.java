package datasource;

import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import datasource.DataSourceContextHolder;
import datasource.DynamicDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	
	public static final Logger logger = Logger.getLogger(DynamicDataSource.class.toString());
	
	@Override
	protected Object determineCurrentLookupKey() {
		logger.info("当前数据源 :" + DataSourceContextHolder.getDataSourceType());
		return DataSourceContextHolder.getDataSourceType();
	}

}
