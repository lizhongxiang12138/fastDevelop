package com.my.project.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.my.annotation.excel.ExcelColumn;
import com.my.annotation.excel.ExcelTable;
import com.my.base.dao.impl.BaseDaoImpl;
import com.my.project.entity.HistoricType;
import com.my.project.entity.TbMenu;
import com.my.project.entity.TbRole;

/**
 * excel 操作工具类
 * 
 * @author lzx
 *
 *         2016年8月13日
 */
public class ExcelHelper<T> {
	/**
	 * main 方法
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
//		File file = new File("F:\\菜单资源数据.xlsx");
//		String fileName = file.getName();
//		FileInputStream in = new FileInputStream(file);
//		ExcelHelper<TbMenu> eh = new ExcelHelper<TbMenu>(TbMenu.class,//
//				in,fileName);
//		List<TbMenu> datas = eh.importDatasToMemory();
//		System.out.println(datas.size());
	    ExcelHelper<HistoricType> excelHelper = new ExcelHelper<HistoricType>(HistoricType.class);
	}

	/**
	 * 要导入导出的类型
	 */
	private Class<T> clazz;
	/**
	 * 导入导出的数据
	 */
	private List datas;
	/**
	 * 工作簿
	 */
	private Workbook workbook;
	/**
	 * 工作表
	 */
	private Sheet sheet;
	/**
	 * 下拉框
	 */
	private DataValidation datavalidation;
	/**
	 * 判断是不是03版本的excel
	 */
	private boolean is03;

	/**
	 * 导出数据是的构造函数
	 * 
	 * @param clazz
	 *            数据类型
	 */
	public ExcelHelper(Class clazz) {
		this.clazz = clazz;
		// 创建工作簿
		this.workbook = new XSSFWorkbook();
		// 创建工作表
		this.sheet = this.workbook.createSheet("数据");
		// 设置默认宽度和高度
		sheet.setDefaultColumnWidth(30);
		sheet.setDefaultRowHeightInPoints(20);

	}

	/**
	 * 无参的构造函数（ps:用于导入数据）
	 * 
	 * @param clazz
	 *            数据类型
	 */
	public ExcelHelper(Class clazz, InputStream in,String fileName) throws Exception {
		this.clazz = clazz;
		is03 = fileName.matches("^.+\\.(?i)(xls)$");
		// 读取excel
		workbook = is03 ? new HSSFWorkbook(in) : new XSSFWorkbook(in);
		sheet = workbook.getSheetAt(0);
	}

	/**
	 * 默认样式
	 * 
	 * @param style
	 * @param font
	 */
	private void setDefault(CellStyle style, Font font) {
		/*
		 * 创建默认样式
		 */
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 设置填充模式为前景填充模式
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格颜色为白色
		// 设置字体
		/*
		 * 设置默认字体
		 */
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setColor(HSSFColor.BLACK.index);
		style.setFont(font);
	}

	/**
	 * 导出模板到输出流
	 *
	 * @param os
	 *            输出流
	 */
	public void ExportModel(OutputStream os) throws Exception {
		// 添加填表注意事项及其表头
		getHeadAndTitle();
		// 输出数据
		workbook.write(os);
		workbook.close();
	}

	/**
	 * 导出数据到输出流
	 * 
	 * @param os
	 *            输出流
	 */
	public void ExportDatasToExcel(OutputStream os, List<T> datas) throws Exception {
		// 添加表头信息
		getHeadAndTitle();
		// 如果数据不为空添加数据
		if (datas != null) {
			addDatasToExcel(datas);
		}
		// 输出数据
		workbook.write(os);
		workbook.close();
	}
	
	/**
	 * 获取文本框数据
	 * @throws Exception
	 */
        private Map getSelectMap(Field f) throws Exception {
        	ExcelColumn ec = f.getAnnotation(ExcelColumn.class);
        	Map selectList =null;
        	if (ec != null) {
        	    if (!"".equals(ec.selectList().trim())) {
        		String name = ec.selectList().trim();
        		System.out.println(name);
        		String className = name.substring(0, name.lastIndexOf("."));
        		String fileName = name.substring(name.lastIndexOf(".") + 1,name.length());
        		Class clazzList = Class.forName(className);
        		Field selectField = clazzList.getDeclaredField(fileName);
        		Object value = selectField.get(null);
        		if (value instanceof Map) {
        		    selectList = (Map) value;
        		    Iterator<Map.Entry> iterator = selectList.entrySet().iterator();
        		    while (iterator.hasNext()) {
        			Entry next = iterator.next();
        			System.out .println(next.getKey() + "-" + next.getValue());
        		    }
        		}
        	    }
        	}
        	return selectList;
        
        }
	/**
	 * 添加填表注意事项及其表头
	 * 
	 * @throws Exception
	 */
	private void getHeadAndTitle() throws Exception {
		Field[] fields = clazz.getDeclaredFields();
		Row row = sheet.createRow(1);
		// 设置表头的行高为25
		row.setHeightInPoints(25);
		/*
		 * 设置表头的特殊样式
		 */
		CellStyle styleHead = workbook.createCellStyle();
		Font fontHead = workbook.createFont();
		setDefault(styleHead, fontHead);
		fontHead.setColor(HSSFColor.WHITE.index);
		styleHead.setFillForegroundColor(HSSFColor.BLUE.index);
		int maxColumn = 0;
		Cell cellHead = null;
		for (Field field : fields) {
			ExcelColumn ec = field.getAnnotation(ExcelColumn.class);
			if (ec != null) {
				System.out.print("对应的列名称：" + ec.title() + "|  列数：" + ec.column() + "   |");
				cellHead = row.createCell(ec.column());
				cellHead.setCellValue(ec.title());
				cellHead.setCellStyle(styleHead);
				maxColumn = ec.column() >= maxColumn ? ec.column() : maxColumn;
				/*
				 *设计下拉框 
				 */
				Map selectMap = getSelectMap(field);
				if(selectMap!=null) {
				    String[] selectArr =  new String[selectMap.size()];
				    Iterator<Entry> iterator = selectMap.entrySet().iterator();
				    int index = 0;
				    while (iterator.hasNext()) {
					Entry entry = iterator.next();
					System.out.println(entry.getKey()+"="+entry.getValue());
					selectArr[index]=entry.getKey()+"="+entry.getValue();
					index++;
				    }
				    setListSelect(ec.column(), selectArr);
				}
			}
			System.out.print("属性名称为：" + field.getName() + "\n");
		}
		// 加上表标题和填表说明
		if (maxColumn > 0) {// 若列数大于1则合并单元格
			CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, 0, maxColumn);
			sheet.addMergedRegion(rangeAddress);
		}
		ExcelTable et = (ExcelTable) clazz.getAnnotation(ExcelTable.class);
		Row rowTitle = sheet.createRow(0);
		rowTitle.setHeightInPoints(150);// 设置行高为200
		Cell cellTitle = rowTitle.createCell(0);
		cellTitle.setCellValue(et.title());
		/**
		 * 增加特殊样式
		 */
		CellStyle styleTitle = workbook.createCellStyle();
		Font fontTitle = workbook.createFont();
		setDefault(styleTitle, fontTitle);
		styleTitle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		styleTitle.setFillForegroundColor(HSSFColor.YELLOW.index);
		fontTitle.setColor(HSSFColor.RED.index);
		styleTitle.setWrapText(true);// 自动换行
		cellTitle.setCellStyle(styleTitle);
	}

	/**
	 * 增加数据
	 * 
	 * @throws Exception
	 */
	private void addDatasToExcel(List<T> datas) throws Exception {
		Field[] fields = clazz.getDeclaredFields();
		/*
		 * 设置表头的特殊样式
		 */
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		setDefault(style, font);
		Cell cell = null;
		for (int i = 0; i < datas.size(); i++) {
			Row row = sheet.createRow(i + 2);
			for (Field field : fields) {
				ExcelColumn ec = field.getAnnotation(ExcelColumn.class);
				String fieldName = field.getName();
				if (ec != null) {
					System.out.print("对应的列名称：" + ec.title() + "|  列数：" + ec.column() + "   |");
					// 通过get方法
					Method getMethod = clazz.getMethod("get" + fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1, fieldName.length()));
					// 执行get方法获取值
					Object value = getMethod.invoke(datas.get(i));
					cell = row.createCell(ec.column());
					if (value != null) {
					    Map selectMap = getSelectMap(field);
					    if(selectMap!=null) {
						Object describe  = selectMap.get(value);
						cell.setCellValue(value.toString()+"="+describe.toString());
					    }else {
						cell.setCellValue(value.toString());
					    }
					}
					cell.setCellStyle(style);
					
				}
			}
			System.out.println("[导出了一条数据]");
		}
	}

	/**
	 * 导入数据到内存
	 * 
	 * @param excelFile
	 *            excel文件
	 * @return
	 */
	public List<T> importDatasToMemory() throws Exception { 
		ArrayList<T> datas = new ArrayList<T>();
		//查询可用的行
		int pnr = sheet.getPhysicalNumberOfRows();
		//类型的属性
		Field[] fields = clazz.getDeclaredFields();
		T objt = null;
		Row row=null;
		Cell cell = null;
		if(pnr>2){
			for(int i=2;i<pnr;i++){
				objt = (T)clazz.newInstance();
				row = sheet.getRow(i);
				//设置属性值
				for(int j=0;j<fields.length;j++){
				    	if(Modifier.isStatic(fields[j].getModifiers())) continue;//如果是静态属性跳过方法
					ExcelColumn ec = fields[j].getAnnotation(ExcelColumn.class);
					String fieldName = fields[j].getName();
					if(ec !=null){
						// 通过get方法
						Method setMethod = clazz.getMethod("set" + fieldName.substring(0, 1).toUpperCase()
								+ fieldName.substring(1, fieldName.length()),fields[j].getType());
						//获取单元格的值
						cell = row.getCell(ec.column());
						Object value = null;
						if(cell==null) continue;
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							//判断是否为下拉数据选项
							if(!"".equals(ec.selectList().trim())) {
							    String val = (String)value;
							    val=val.substring(0, val.indexOf('='));
							    if(fields[j].getType()==Integer.class) {
								setMethod.invoke(objt, Integer.parseInt((val).trim()));
							    }else if(fields[j].getType()==String.class) {
								setMethod.invoke(objt, (val).trim());
							    }
							}else {
							    setMethod.invoke(objt, ((String)value).trim());	
							}
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							value = cell.getNumericCellValue();
							if (String.class.equals(fields[i].getType())) {
								value = cell.getNumericCellValue();	
								setMethod.invoke(objt, (value+"").substring(0, (value+"").lastIndexOf(".")));
							}else if(BigDecimal.class.equals(fields[i].getType())){
								value = cell.getNumericCellValue();
								setMethod.invoke(objt, new BigDecimal(value+""));
							}
							break;
						default:
							break;
						}
						
						
						System.out.println(ec.column()+"|"+value);
						// 执行get方法获取值
						
					}
				}
				datas.add(objt);
			}
		}
		return datas;
	}
	/**	
	 * 设置下拉框
	 * @param col
	 * @param list
	 */
	private void setListSelect(int col,String[] list){
	    XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);
	    XSSFDataValidationConstraint constraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(list);
	    CellRangeAddressList address = new CellRangeAddressList(2, 1000000, col, col);
	    DataValidation dataValidation = dvHelper.createValidation(constraint, address);
	    sheet.addValidationData(dataValidation);
	}
}
