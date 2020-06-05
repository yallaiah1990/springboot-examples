/*
 * package com.product.exception;
 * 
 * import javax.ws.rs.core.Response; import javax.ws.rs.ext.ExceptionMapper;
 * import javax.ws.rs.ext.Provider;
 * 
 * @Provider public class ProductExceptionMapper implements
 * ExceptionMapper<Exception>{
 * 
 * @Override public Response toResponse(final Exception exception) {
 * if(exception instanceof ProductNotFoundException) { return (new
 * ProductNotFoundException()).toResponse(exception); }else { return null; }
 * 
 * }
 * 
 * }
 */