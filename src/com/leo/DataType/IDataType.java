package com.leo.DataType;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public interface IDataType {
	 public void accept(IDataTypeVisitor v);
	 public void setData(String sdata);
	 public String toString();
}
