package com.servlet;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 错误码：成功
	 */
	private final int ERROR_CODE_SUCCESS = 200;

	/**
	 * 错误码 ：失败
	 */
	private final int ERROR_CODE_FAIL = 500;

	public FileUploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 上传路径
		String uploadPath = "f://upload_path//";

		String message = "";
		JSONObject jsonObject = new JSONObject();
		String file_path = "";
		int error_code = ERROR_CODE_SUCCESS;
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart == true) {
				// 判断上传目录是否存在，不存在即创建
				File file = new File(uploadPath);
				if (!file.exists()) {
					file.mkdir();
				}
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
				Iterator<FileItem> itr = items.iterator();
				while (itr.hasNext()) {// 依次处理每个文件
					FileItem item = (FileItem) itr.next();
					String fileName = item.getName();// 获得文件名，包括路径
					if (fileName != null) {
						File fullFile = new File(item.getName());
						File savedFile = new File(uploadPath,
								fullFile.getName());
						item.write(savedFile);
						file_path = savedFile.getAbsolutePath();
					}
				}
				message = "上传成功 O(∩_∩)O";
			} else {
				message = "the enctype must be multipart/form-data";
				error_code = ERROR_CODE_FAIL;
			}
		} catch (Exception e) {
			e.printStackTrace();
			error_code = ERROR_CODE_FAIL;
			message = e.getMessage();
		}
		jsonObject.put("error_code", error_code);
		jsonObject.put("message", message);
		jsonObject.put("file_path", file_path);
		out.println(jsonObject.toString());
		out.flush();
	}

}
