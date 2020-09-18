package com.my.springcloud.common.utils;

import javax.servlet.http.HttpServletRequest;

import com.my.springcloud.common.entity.UserInfo;
import com.uwp.sso.core.conf.Conf;
import com.uwp.sso.core.user.XxlSsoUser;

public class UserUtil {
	/**
	 * 获取用户信息（未完成）
	 *
	 * @param request
	 * @return
	 */
	public static UserInfo getUser(HttpServletRequest request) {
		//	TODO
		return new UserInfo("001", "test");
//		XxlSsoUser xxlUser = (XxlSsoUser) request.getAttribute(Conf.SSO_USER);
//		return new UserInfo(xxlUser.getUserid(),xxlUser.getUsername());

	}

}