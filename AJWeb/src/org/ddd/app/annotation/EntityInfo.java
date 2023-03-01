package org.ddd.app.annotation;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class EntityInfo {

	private Class clazz ;
	private Entity entity;
	private ColumnInfo idColumnInfo ;
	private Map<String,ColumnInfo> fieldColumnInfos = new HashMap<String,ColumnInfo>();
	private Map<String,ColumnInfo> columnInfos = new HashMap<String,ColumnInfo>();

	public Class getClazz() {
		return clazz;
	}
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public Map<String, ColumnInfo> getFieldColumnInfos() {
		return fieldColumnInfos;
	}
	public void setFieldColumnInfos(Map<String, ColumnInfo> fieldColumnInfos) {
		this.fieldColumnInfos = fieldColumnInfos;
	}
	public Map<String, ColumnInfo> getColumnInfos() {
		return columnInfos;
	}
	public void setColumnInfos(Map<String, ColumnInfo> columnInfos) {
		this.columnInfos = columnInfos;
	}
	public ColumnInfo getIdColumnInfo() {
		return idColumnInfo;
	}
	public void setIdColumnInfo(ColumnInfo idColumnInfo) {
		this.idColumnInfo = idColumnInfo;
	}
}
