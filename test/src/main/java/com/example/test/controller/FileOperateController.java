package com.example.test.controller;

import com.example.test.utils.ZipUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author Gaoming
 * @Email mineok@foxmail.com
 * @Date 2020/08/25/ 11:20
 * @Description 文件上传/下载
 */
@Controller
@RequestMapping("/file")
public class FileOperateController {


    @Value("${filePath}")
    String filePath;

    static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");


    /**
     * 进入上传/下载操作界面
     */
    @RequestMapping("uploadView")
    public String uploadView() {
        return "upload/up";
    }

    /**
     * 上传
     */
    @PostMapping("upload")
    @ResponseBody
    public String upFile(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        //取出文件原名
        String fileName = multipartFile.getOriginalFilename();
        System.out.println("原文件名:" + fileName);

        // 起新名字
        // 新文件名:UUID+截取后缀
        String newFileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        System.out.println("新文件名:" + newFileName);

        //上传文件,绝对路径
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        System.out.println("文件当前的绝对路径:" + rootPath);

        //创建上传文件要保存的完整路径
        String fullPath = filePath + "/" + newFileName;
        System.out.println("文件要保存到的完整路径:" + fullPath);

        File file = new File(fullPath);
        //递归建目录
        if (!file.exists()) {
            file.mkdirs();
        }
        //向磁盘中的fullPath写入这个文件
        multipartFile.transferTo(file);
        return "上传成功!";   // //转换到success.jsp,事先已配好视图解析器
    }

    /**
     * 下载:通过response输出流将文件传递到浏览器
     */
    @RequestMapping("download")
    public void download(HttpServletResponse response) {
        // 1、获取文件路径
        String fileName = "fb2d8b8d-a604-4705-89ee-71d48e6011f0.jpg";
        // 2.构建一个文件通过Paths工具类获取一个Path对象
        Path path = Paths.get(filePath, fileName);
        if (Files.exists(path)) {
            // 4.获取文件的后缀名
            String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            // 5.设置contentType ,只有指定contentType才能下载
            response.setContentType("application/" + fileSuffix);
            // 6.添加http头信息,因为fileName的编码格式是UTF-8 但是http头信息只识别 ISO8859-1 的编码格式,因此要对fileName重新编码
            try {
                response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            // 7.使用 Path 和response输出流将文件输出到浏览器
            try {
                Files.copy(path, response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 压缩为ZIP格式
     */
    public String toZip(List<File> files) {
        // String newFilePath = "E:\\代码仓库\\SpringBootTest\\test\\uploadFiles\\压缩目录-" + FORMAT.format(new Date()) + ".zip";
        String newFilePath = filePath + "\\压缩目录-" + FORMAT.format(new Date()) + ".zip";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(newFilePath);
            ZipUtils.toZip(files, fos);
            return newFilePath;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 下载ZIP格式文件
     */
    @RequestMapping("/zip")
    public void zipdDownload(HttpServletRequest request, HttpServletResponse response) {
        File file = new File("E:\\代码仓库\\SpringBootTest\\test\\uploadFiles\\d984fda6-3b3d-4d23-9b23-f713a6c2b827.jpg");
        File file2 = new File("C:\\Users\\ASUS\\Pictures\\2d6f324a521deb34eaa6ca579c3b0a64405ee079d6f351fb246d2f3f90f92915.jpg");
        File file3 = new File("C:\\Users\\ASUS\\Pictures\\9fe68e1569e1c8a5f98ec582f291b2d5cbd828790985c161ef8d2c5293af82f5.jpg");

        List<File> files = Arrays.asList(file, file2, file3);
        // 将文件集合压缩为zip格式到服务器
        String zipPath = toZip(files);
        System.out.println("删除前ZIP文件所处服务器位置：" + zipPath);
        // 路径不为空则转zip格式成功
        if (!StringUtils.isEmpty(zipPath)) {
            File zipFile = new File(zipPath);
            // 对zip压缩包全路径进行分割获取zip文件名
            String fileName = zipPath.split("uploadFiles\\\\")[1];
            // 通过浏览器下载zip文件到本地
            ZipUtils.downloadFile(zipFile, fileName, request, response);
            // 下载完成后将压缩至服务器目录的zip文件删除
            ZipUtils.deleteZip(zipPath);
        }
    }

//    public static void main(String[] args) {
//        File file = new File("E:\\代码仓库\\SpringBootTest\\test\\uploadFiles\\d984fda6-3b3d-4d23-9b23-f713a6c2b827.jpg");
//        System.out.println(file.getName());
//        FileOperateController controller = new FileOperateController();
//        // System.out.println(file.getAbsolutePath());
//
//        // Arrays.asList(file);
//        //System.out.println(controller.filePath);
//        String s = controller.toZip(Arrays.asList(file));
//        System.out.println(s);
//        System.out.println(s.split("uploadFiles\\\\")[1]);
//        //System.out.println(path.substring(0,path.lastIndexOf('.')));
//    }
}
