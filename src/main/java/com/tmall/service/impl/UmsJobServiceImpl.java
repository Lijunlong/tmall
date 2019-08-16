package com.tmall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.tmall.dao.UmsJobMapper;
import com.tmall.dto.UmsJobParam;
import com.tmall.model.UmsDepartment;
import com.tmall.model.UmsJob;
import com.tmall.service.UmsDepartmentService;
import com.tmall.service.UmsJobService;

@Service
public class UmsJobServiceImpl implements UmsJobService {
	
	@Autowired
	private UmsJobMapper umsJobMapper;
	@Autowired
	private UmsDepartmentService umsDepartmentService;

	@Override
	public UmsJob selectUmsJobById(Long umsJobId) {
		return umsJobMapper.selectUmsJobById(umsJobId);
	}

	@Override
	public List<UmsJob> getJobs(Long deptId, String name, Integer enabled, Integer pageSize, Integer pageNum) {
		UmsJob umsJobSearchParam = new UmsJob();
		umsJobSearchParam.setUmsDepartmentId(deptId);
		umsJobSearchParam.setName(name);
		umsJobSearchParam.setEnabled(enabled);
		PageHelper.startPage(pageNum, pageSize);
		List<UmsJob> jobList = umsJobMapper.selectUmsJobLikeUmsJob(umsJobSearchParam);
		//放入部门
		if (jobList != null && jobList.size() > 0) {
			for (UmsJob job : jobList) {
				Long umsDepartmentId = job.getUmsDepartmentId();//部门id
				//通过部门id获取部门
				UmsDepartment dept = umsDepartmentService.selectUmsDepartmentById(umsDepartmentId);
				job.setDept(dept);
				if (dept != null) {
					//获取部门id上级部门名称
					UmsDepartment pdept = umsDepartmentService.selectUmsDepartmentById(dept.getPid());
					if (pdept != null) {
						job.setDeptSuperiorName(pdept.getName());
					}
				}
			}
		}
		return jobList;
	}

	@Override
	public int update(Long id, UmsJobParam umsJobParam) {
		UmsJob umsJob = this.createUmsJobByUmsJobParam(umsJobParam);
		umsJob.setId(id);
		umsJobMapper.updateUmsJob(umsJob);
		return 1;
	}

	@Override
	public Long insert(UmsJobParam umsJobParam) {
		UmsJob umsJob = this.createUmsJobByUmsJobParam(umsJobParam);
		umsJob.setCreateTime(new Date());
		umsJobMapper.insertUmsJob(umsJob);
		return umsJob.getId();
	}
	
	/**
	 * 转换器：把前端岗位的数据转成后端岗位表的数据
	 * @param umsMenuParam 前端岗位的数据
	 * @return 后端岗位的数据
	 */
	private UmsJob createUmsJobByUmsJobParam(UmsJobParam umsJobParam) {
		UmsJob umsJob = new UmsJob();
		BeanUtils.copyProperties(umsJobParam, umsJob);
		return umsJob;
	}

	@Override
	public int delete(Long id) {
		umsJobMapper.deleteUmsJobById(id);
		return 1;
	}
	
}
