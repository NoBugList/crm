package com.shsxt.crm.constant;

public enum DevResult {
	// 创建一个对象的方法：new 对象、反序列化、反射、clone
	UN_DEV(0),
	
	DEVING(1),
	
	DEV_FINISHED(2),
	
	DEV_FAILURE(3);
	
	private int devResult;
	
	DevResult(int devResult) {
		this.devResult = devResult;
	}

	public int getDevResult() {
		return devResult;
	}

	public void setDevResult(int devResult) {
		this.devResult = devResult;
	}
	
	public static void main(String[] args) {
		DevResult[] devResults = DevResult.values(); // 获取所有枚举的值
		for(DevResult d : devResults) {
			System.out.println(d.getDevResult());
		}
		System.out.println(DevResult.DEVING.getDevResult());
	}
	
}
