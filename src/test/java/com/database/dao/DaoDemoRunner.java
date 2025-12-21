package com.database.dao;

import java.util.ArrayList;
import java.util.List;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CreateJobBeanMapper;
import com.dataproviders.api.bean.CreateJobBean;

public class DaoDemoRunner {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		CreateJobPayloadDataDao result = new CreateJobPayloadDataDao();
		List<CreateJobBean> beanList= CreateJobPayloadDataDao.getCreateJobPayloadData();
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		
		for(CreateJobBean createJobBean:beanList) {
			CreateJobPayload payload = CreateJobBeanMapper.mapper(createJobBean);
			payloadList.add(payload);
		}
		System.out.println("---------------------------------------");
		for(CreateJobPayload payload:payloadList) {
			System.out.println(payload);
		}
	}

}
