package com.demo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// 基于lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@ContentRowHeight(20)   // 内容行高
@HeadRowHeight(10)  // 表头行高
public class Student {
    /**
     * 学生姓名
     */
    @ExcelProperty(value = "学生姓名", index = 0)   // value为列名。index为列的位置，从0开始
    @ColumnWidth(50)    // 行宽
    private String name;
    /**
     * 学生性别
     */
    @ExcelProperty(value = "性别", index = 2)
    @ColumnWidth(10)
    private String gender;

    /**
     * 学生出生日期
     */
    @ExcelProperty(value = "出生日期", index = 1)
    @ColumnWidth(50)
    private Date birthday;
    /**
     * id
     */
    @ExcelIgnore
    private String id;
}