package com.sea.common.beans;

import java.io.Serializable;

import lombok.Data;

/**
 * 引用晓风轻的ResultBean
 * @param <T>
 */
@Data
public class ResultBean2<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int NO_LOGIN = -1;

	public static final int SUCCESS = 0;

	public static final int FAIL = 1;

	public static final int NO_PERMISSION = 2;

	private String msg = "success";

	private int code = SUCCESS;

	private T data;

	public ResultBean2() {
		super();
	}

	public ResultBean2(T data) {
		super();
		this.data = data;
	}

	public ResultBean2(Throwable e) {
		super();
		this.msg = e.toString();
		this.code = FAIL;
	}
}
