package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.FakerDataGenerator;
import com.database.dao.CreateJobPayloadDataDao;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		return CSVReaderUtil.loadCSV("testdata/logincreds.csv", UserBean.class);
	}

	@DataProvider(name = "CreateJobDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobDataProvider() {
		Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtil.loadCSV("testdata/createjobData.csv",
				CreateJobBean.class);
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		CreateJobBean tempBean;
		CreateJobPayload tempPayload;

		while (createJobBeanIterator.hasNext()) {
			tempBean = createJobBeanIterator.next();
			tempPayload = CreateJobBeanMapper.mapper(tempBean);
			payloadList.add(tempPayload);
		}

		return payloadList.iterator();
	}
	
	@DataProvider(name = "CreateJobFakerDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobFakerDataProvider() {
		String fakerCount = System.getProperty("fakercount", "5");
		int fakerCountInt = Integer.parseInt(fakerCount);
		Iterator<CreateJobPayload> payloadIterator= FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
		return payloadIterator;	
	}
	
	@DataProvider(name = "CreateJobAPIDBDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIDBDataProvider() {
		List<CreateJobBean> beanList= CreateJobPayloadDataDao.getCreateJobPayloadData();
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		
		for(CreateJobBean createJobBean:beanList) {
			CreateJobPayload payload = CreateJobBeanMapper.mapper(createJobBean);
			payloadList.add(payload);
		}
		return payloadList.iterator();
	}
}
