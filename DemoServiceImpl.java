package com.boe.demo.serviceImpl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.boe.demo.mapper.mybit.DemoMapper;
import com.boe.demo.service.DemoService;

@Service
public class DemoServiceImpl  implements DemoService{

	@Resource DemoMapper demoMapper;
	public List<Map<String,Object>> selectDemo() throws Exception{
		return demoMapper.selectDemo();
	}	
}
