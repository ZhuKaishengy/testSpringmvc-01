package com.aisino.viewResolver;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.aisino.domain.Person;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class PDFView extends AbstractPdfView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String fileName = "用户信息.pdf"; // 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment; filename=" + new String(fileName.getBytes(), "iso8859-1"));
        List<Person> userList = (List<Person>) model.get("person");
//        BaseFont bfChinese = BaseFont.createFont("C://Windows//Fonts//simfang.ttf", BaseFont.IDENTITY_H,
//                BaseFont.NOT_EMBEDDED);
//
//        com.lowagie.text.Font FontChinese = new com.lowagie.text.Font(bfChinese, 12, com.lowagie.text.Font.NORMAL);

        for (int i = 0; i < userList.size(); i++) {
           Person person = userList.get(i);
            String value = "ID: " + person.getId() + "姓名: " + person.getName() + ",年龄: " + person.getAge();
            document.add(new Paragraph(value));
        }
	}

	
}
