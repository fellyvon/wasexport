package com.waspring.waexport.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * servlet字符集过滤器，统一采用utf-8编码
 * 
 * @author felly
 * 
 */
public class EncodingFilter implements Filter {

	public EncodingFilter() {
		config = null;
		targetEncoding = "ASCII";
	}

	public void init(FilterConfig filterconfig) throws ServletException {
		config = filterconfig;
		targetEncoding = filterconfig.getInitParameter("encoding");
	}

	public void destroy() {
		config = null;
		targetEncoding = null;
	}

	/**
	 * 字符集过滤，所有请求都会通过这个过滤
	 */
	public void doFilter(ServletRequest servletrequest,
			ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		servletrequest.setCharacterEncoding(targetEncoding);
		try {
			filterchain.doFilter(servletrequest, servletresponse);
		} catch (Exception exception) {
			throw new ServletException(exception);
		} catch (Throwable throwable) {
			throw new ServletException(throwable);
		}
	}

	public FilterConfig getFilterConfig() {
		return config;
	}

	public void setFilterConfig(FilterConfig filterconfig) {
		config = filterconfig;
	}

	public static final String ENCODING = "encoding";
	private FilterConfig config;
	private String targetEncoding;
}
