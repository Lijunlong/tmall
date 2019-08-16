package com.tmall.service;

import java.util.List;

import com.tmall.dto.UmsJobParam;
import com.tmall.model.UmsJob;

/**
 * 岗位Service
 * Created by Mr.Li on 2019/08/12
 */
public interface UmsJobService {

	UmsJob selectUmsJobById(Long umsJobId);

	List<UmsJob> getJobs(Long deptId, String name, Integer enabled, Integer pageSize, Integer pageNum);
	
	/**
	 * 修改岗位
	 * @param id 岗位id
	 * @param umsJobParam 前端岗位参数
	 * @return
	 */
	int update(Long id, UmsJobParam umsJobParam);
	
	/**
	 * 添加岗位
	 * @param umsJobParam 前端岗位参数
	 * @return
	 */
	Long insert(UmsJobParam umsJobParam);
	
	/**
	 * 删除岗位
	 * @param id 岗位id
	 * @return
	 */
	int delete(Long id);
	

}
