package com.waspring.waexport.exp;

/**
 * 责任链处理异常
 * 
 * @author felly
 * 
 */
public class NodeException extends Exception {
 
	public NodeException(String expInfo) {
		super(expInfo);
	}
}
