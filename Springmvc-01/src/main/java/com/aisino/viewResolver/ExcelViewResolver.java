package com.aisino.viewResolver;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.aisino.domain.Person;
import com.aisino.domain.PersonContext;
import com.aisino.utils.ExcelUtils;

public class ExcelViewResolver extends AbstractXlsView {

	private static String filename = "人员表";
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		filename=new String(filename.getBytes("utf-8"), "iso8859-1");
		
		String value = "attachment;filename="+filename+".xls";
		response.setHeader("Content-Disposition", value );
		List<Person> list = (List<Person>) model.get("person");
		try {
			workbook = ExcelUtils.export(list, PersonContext.class, workbook);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
