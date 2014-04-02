package com.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.createJavaFile.myutil.Util;
import com.wll7821.filter.WebContext;

public class FileUpload {
	
	public String execute(){
		HttpServletRequest req = WebContext.getRequest();
		uploads(req, Util.contextPath);
		return "upload";
	}
	
	private void uploads(HttpServletRequest request,String uploadPath){  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");  
        File tmpDir = new File(Util.contextPath);   
        try {  
            if (ServletFileUpload.isMultipartContent(request)) {  
                DiskFileItemFactory factory = new DiskFileItemFactory();  
                factory.setSizeThreshold(1 * 1024 * 1024);   
                factory.setRepository(tmpDir);   
                ServletFileUpload sfu = new ServletFileUpload(factory);  
                sfu.setFileSizeMax(5 * 1024 * 1024);   
                sfu.setSizeMax(10 * 1024 * 1024);   
                sfu.setHeaderEncoding("UTF-8");  
                List<FileItem> fileItems = sfu.parseRequest(request);   
                System.out.println(fileItems);
                uploadPath = uploadPath + "upload//";  
                if (!new File(uploadPath).isDirectory()){  
                    new File(uploadPath).mkdirs();   
                }  
                int leng = fileItems.size();  
                for(int n=0;n<leng;n++) {  
                    FileItem item = fileItems.get(n); // 从集合中获得一个文件流  
                    // 如果是普通表单字段    
                    if(item.isFormField()) {    
                        String name = item.getFieldName();  // 获得该字段名称  
                        String value = item.getString("utf-8"); //获得该字段值  
                        System.out.println(name+value);  
                    }else if(item.getName().length()>0) { // 如果为文件域    
                        String iname = item.getName().substring(  
                                item.getName().lastIndexOf("."));    
                        String fname=sdf.format(new Date())+"-"+n+iname;  
                          
                        try {    
                            item.write(new File(uploadPath, fname));  // 写入文件  
                        } catch (Exception e) {    
                            e.printStackTrace();    
                        }  
                    }  
                }  
            }  
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	
}
