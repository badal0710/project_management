package com.service.project_management.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class resourceNotFoundException extends RuntimeException{
    String tableName;
    String columnName;
    int value;

    public resourceNotFoundException(String tName, String Cname, int v) {
        super("in "+tName+", "+Cname+":"+v+" not Exist");
        this.tableName=tName;
        this.columnName=Cname;
        this.value=v;
    }
}
