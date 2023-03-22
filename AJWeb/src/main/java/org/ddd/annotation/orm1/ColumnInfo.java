package org.ddd.annotation.orm1;

import java.lang.reflect.Field;

public class ColumnInfo {

	private Field field ;
	private Column column;

	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public Column getColumn() {
		return column;
	}
	public void setColumn(Column column) {
		this.column = column;
	}
}
