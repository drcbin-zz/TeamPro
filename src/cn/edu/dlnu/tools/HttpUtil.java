package cn.edu.dlnu.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class HttpUtil {

    public static String upload(HttpServletRequest request, String name, String upload_path)
            throws IOException, ServletException {
        // 获得文件路径和文件名
        String fileName = null;

        Part part = request.getPart(name);
        if (part != null) {
            String realName = part.getSubmittedFileName();
           
            fileName = FileHelper.generateFileName(realName);

            // 执行文件上传
            if (!realName.equals("")) {
                File file = new File(upload_path, fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                InputStream in = part.getInputStream();
                FileOutputStream out = new FileOutputStream(file);
                byte[] cache = new byte[256];
                int length = 0;
                while ((length = in.read(cache)) != -1) {
                    out.write(cache, 0, length);
                }
                out.flush();
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            }
        }
        return fileName;
    }

}