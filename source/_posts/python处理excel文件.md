---
title: python处理excel文件
date: 2020-03-06 22:06:40
tags: python
categories: 
  - Python
  - 数据处理
---


## 一、引言

平时经常处理一些Excel文件，小的文件直接用Excel处理还行，大的文件，可以试着用python来处理，相对比较方便，而且python易学，代码简单。

<!-- more -->

## 二、第三方库

- xlrd 读
- xlwt 写

## 三、代码

```
import xlrd  读取

file = 'test3.xlsx'

def read_excel():

wb = xlrd.open_workbook(filename=file)#打开文件

print(wb.sheet_names())#获取所有表格名字

sheet1 = wb.sheet_by_index(0)#通过索引获取表格

sheet2 = wb.sheet_by_name('年级')#通过名字获取表格

print(sheet1,sheet2)

print(sheet1.name,sheet1.nrows,sheet1.ncols)

rows = sheet1.row_values(2)#获取行内容

cols = sheet1.col_values(3)#获取列内容

print(rows)

print(cols)

print(sheet1.cell(1,0).value)#获取表格里的内容，三种方式

print(sheet1.cell_value(1,0))

print(sheet1.row(1)[0].value)
```

合并表格：

merged_cells()用法，merged_cells返回的这四个参数的含义是：(row,row_range,col,col_range)



```
import xlwt

#设置表格样式
def set_style(name,height,bold=False):
	style = xlwt.XFStyle()
	font = xlwt.Font()
	font.name = name
	font.bold = bold
	font.color_index = 4
	font.height = height
	style.font = font
	return style

#写Excel
def write_excel():
	f = xlwt.Workbook()
	sheet1 = f.add_sheet('学生',cell_overwrite_ok=True)
	row0 = ["姓名","年龄","出生日期","爱好"]
	colum0 = ["张三","李四","恋习Python","小明","小红","无名"]
	#写第一行
	for i in range(0,len(row0)):
		sheet1.write(0,i,row0[i],set_style('Times New Roman',220,True))
	#写第一列
	for i in range(0,len(colum0)):
		sheet1.write(i+1,0,colum0[i],set_style('Times New Roman',220,True))

sheet1.write(1,3,'2006/12/12')
sheet1.write_merge(6,6,1,3,'未知')#合并行单元格
sheet1.write_merge(1,2,3,3,'打游戏')#合并列单元格
sheet1.write_merge(4,5,3,3,'打篮球')

f.save('test.xls')

```

常用时间库

```
import time
```

```
startime = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())  # 获取系统当前时间并格式化为格式
```

```
print(time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()) + ' ***') # “实时+***”
```