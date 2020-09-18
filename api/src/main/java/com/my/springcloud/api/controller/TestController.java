package com.my.springcloud.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.springcloud.common.entity.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 测试
 * 
 * @author zgp
 *
 */
@Api(tags = "测试", value = "test")
@RestController
@RequestMapping("test")
public class TestController {
	@ApiOperation(value = "根据id获取")
	@GetMapping
	public IPage<Object> getPage(Object test, int current, int size) {
		Page<Object> page = new Page<Object>(current, size);
		return page;
	}
	@ApiOperation(value = "获取分页数据")
	@GetMapping("/{id}")
	public String getById(@PathVariable String id) {
		return "this is " + id;
	};
	
	@ApiOperation(value = "新增")
	@PostMapping
	public Result<Object> add(HttpServletRequest request, @RequestBody Object test) {

		return Result.success("", test);
	}

	@ApiOperation(value = "修改")
	@PutMapping
	public Result<Object> update(HttpServletRequest request, @RequestBody Object test) {
		return Result.success("", test);
	}

	@ApiOperation(value = "逻辑删除")
    @PutMapping("/delete")
	public Result<List<String>> delete(@RequestBody List<String> ids) {
		for (String id : ids) {
		}
		return Result.success("", ids);
	}
	@ApiOperation(value = "删除")
	@DeleteMapping
	public Result<String> delete(String id) {
		return Result.success("", id);
	}
	
}
