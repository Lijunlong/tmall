package com.tmall.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.tmall.dao.UmsAdminMapper;
import com.tmall.dto.UmsAdminParam;
import com.tmall.model.UmsAdmin;
import com.tmall.model.UmsAdminRoleRelation;
import com.tmall.model.UmsDepartment;
import com.tmall.model.UmsJob;
import com.tmall.model.UmsRole;
import com.tmall.model.VerificationCode;
import com.tmall.service.UmsAdminRoleRelationService;
import com.tmall.service.UmsAdminService;
import com.tmall.service.UmsDepartmentService;
import com.tmall.service.UmsJobService;
import com.tmall.service.UmsRoleService;
import com.tmall.service.VerificationCodeService;
import com.tmall.util.Constant;
import com.tmall.util.SecurityUtils;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {
	
	@Autowired
	private UmsAdminMapper umsAdminMapper;
	@Autowired
	private UmsDepartmentService umsDepartmentService;
	@Autowired
	private UmsJobService umsJobService;
	@Autowired
	private UmsRoleService umsRoleService;
	@Autowired
	private UmsAdminRoleRelationService umsAdminRoleRelationService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private VerificationCodeService verificationCodeService;
	
	@Override
	public List<UmsAdmin> getUserList(Long deptId, String username, String email,Integer enabled, Integer pageSize, Integer pageNum) {
		List<Long> deptIdList = new ArrayList<Long>();
		if (deptId != null && deptId > 0) {
			//获取部门id所有的子id
			deptIdList = this.getDeptIdList(deptId,deptIdList);
		}
		UmsAdmin umsAdminSearchParam = new UmsAdmin();
		umsAdminSearchParam.setUsername(username);
		umsAdminSearchParam.setEmail(email);
		umsAdminSearchParam.setEnabled(enabled);
		//获取用户列表
		PageHelper.startPage(pageNum, pageSize);
		List<UmsAdmin> umsAdminList = umsAdminMapper.selectUmsAdminLikeUmsAdmin(umsAdminSearchParam,deptIdList);
		if (umsAdminList != null && umsAdminList.size() > 0) {
			for (UmsAdmin umsAdmin : umsAdminList) {
				/*
				 * 逻辑：
				 * 通过用户id获取用户的部门；
				 * 通过用户id获取用户的岗位；
				 * 通过用户id获取用户的角色列表；
				 */
				//部门
				UmsDepartment umsDepartment = umsDepartmentService.selectUmsDepartmentById(umsAdmin.getUmsDeptmentId());
				//岗位
				UmsJob umsJob = umsJobService.selectUmsJobById(umsAdmin.getUmsJobId());
				//用户id
				Long adminId = umsAdmin.getId();
				List<UmsRole> umsRoleList = umsRoleService.selectRoleListByAdminId(adminId);
				//放置部门，岗位和角色
				umsAdmin.setDept(umsDepartment);
				umsAdmin.setJob(umsJob);
				umsAdmin.setRoles(umsRoleList);
				//清空密码
				umsAdmin.setPassword(null);
			}
		}
		
		return umsAdminList;
	}
	
	/**
	 * 获取部门id所有的子id
	 * @param deptId 部门id
	 * @param deptIdList 
	 * @return 父id集合
	 */
	private List<Long> getDeptIdList(Long deptId, List<Long> deptIdList) {
		deptIdList.add(deptId);
		List<UmsDepartment> umsDepartmentList = umsDepartmentService.selectUmsDepartmentListByPid(deptId);
		for (UmsDepartment umsDepartment : umsDepartmentList) {
			this.getDeptIdList(umsDepartment.getId(),deptIdList);
		}
		return deptIdList;
	}
	

	@Override
	public int update(Long umsAdminId, UmsAdminParam umsAdminParam, String updater) {
		UmsAdmin umsAdmin = this.createUmsAdminByUmsAdminParam(umsAdminParam);
		umsAdmin.setId(umsAdminId);
		umsAdmin.setUpdateTime(new Date());
		umsAdmin.setUpdater(updater);
		umsAdminMapper.updateUmsAdmin(umsAdmin);
		//先删除用户与角色的关系表
		umsAdminRoleRelationService.deleteUmsAdminRoleRelationByAdminId(umsAdminId);
		//添加用户与角色的关系表
		this.insertAdminRoleRelation(umsAdminId, umsAdminParam);
		return 1;
	}
	
	/**
	 * 添加用户与角色的关系
	 * @param umsAdminParam
	 */
	private void insertAdminRoleRelation(Long adminId, UmsAdminParam umsAdminParam) {
		List<UmsRole> roles = umsAdminParam.getRoles();
		if (roles != null && roles.size() > 0) {
			for (UmsRole role : roles) {
				//插入用户与角色关系表
				UmsAdminRoleRelation umsAdminRoleRelation = new UmsAdminRoleRelation();
				umsAdminRoleRelation.setAdminId(adminId);
				umsAdminRoleRelation.setRoleId(role.getId());
				umsAdminRoleRelationService.insertUmsAdminRoleRelation(umsAdminRoleRelation);
			}
		}
	}

	@Override
	public int create(UmsAdminParam umsAdminParam, String creater) {
		UmsAdmin umsAdmin = this.createUmsAdminByUmsAdminParam(umsAdminParam);
		umsAdmin.setPassword(passwordEncoder.encode(Constant.DEFAULT_PASSWORD));//默认密码，加密
		umsAdmin.setEnabled(Constant.NORMAL);//状态：启用
		umsAdmin.setCreateTime(new Date());
		umsAdmin.setCreater(creater);
		//插入用户表
		umsAdminMapper.insertUmsAdmin(umsAdmin);
		//插入用户与角色关系表
		this.insertAdminRoleRelation(umsAdmin.getId(), umsAdminParam);
		return 1;
	}
	
	/**
	 * 转换器：把前端用户的数据转成后端用户表的数据
	 * @param umsAdminParam 前端用户的数据
	 * @return 后端用户的数据
	 */
	private UmsAdmin createUmsAdminByUmsAdminParam(UmsAdminParam umsAdminParam) {
		UmsAdmin umsAdmin = new UmsAdmin();
		BeanUtils.copyProperties(umsAdminParam, umsAdmin);
		//部门
		UmsDepartment dept = umsAdminParam.getDept();
		if (dept != null) {
			//插入部门id
			umsAdmin.setUmsDeptmentId(dept.getId());
		}
		//岗位
		UmsJob job = umsAdminParam.getJob();
		if (job != null) {
			//插入岗位id
			umsAdmin.setUmsJobId(job.getId());
		}
		return umsAdmin;
	}

	@Override
	public int delete(Long umsAdminId) {
		//删除用户表
		umsAdminMapper.deleteUmsAdminById(umsAdminId);
		//删除用户与角色关系表
		umsAdminRoleRelationService.deleteUmsAdminRoleRelationByAdminId(umsAdminId);
		return 1;
	}

	@Override
	public int updateUmsAdminIconByUsername(UmsAdmin umsAdmin) {
		return umsAdminMapper.updateUmsAdminIconByUsername(umsAdmin);
	}

	@Override
	public Map<String, String> updateEmail(String code, UmsAdminParam umsAdminParam) {
		Map<String, String> map = new HashMap<String, String>();
		//验证邮箱验证码是否有效
		VerificationCode verificationCode = verificationCodeService.selectValidVerificationCodeByTypeAndValueAndScenes("email", umsAdminParam.getEmail(), Constant.RESET_MAIL);
		if (verificationCode == null) {
			map.put("error", "验证码已失效，请重新生成验证码");
		}else {
			if (code.equalsIgnoreCase(verificationCode.getCode())) {
				//验证码有效，验证密码
				List<UmsAdmin> umsAdminList = umsAdminMapper.selectUserByUsername(SecurityUtils.getUsername());
				if (umsAdminList != null && umsAdminList.size() > 0) {
					UmsAdmin umsAdmin = umsAdminList.get(0);
					if (passwordEncoder.matches(umsAdminParam.getPassword(), umsAdmin.getPassword())) {
						map.put("error", "");
						UmsAdmin umsAdminSearchParam = new UmsAdmin();
						umsAdminSearchParam.setId(umsAdmin.getId());
						umsAdminSearchParam.setEmail(umsAdminParam.getEmail());
						//修改邮箱地址
						umsAdminMapper.updateUmsAdmin(umsAdminSearchParam);
					}else {
						//密码不正确
						map.put("error", "密码错误");
					}
				}
			}else {
				//验证码无效
				map.put("error", "验证码错误");
			}
			
		}
		return map;
	}

}
