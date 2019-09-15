package com.archtype.mailtransfer.utils;

import com.alibaba.fastjson.JSONObject;
import com.archtype.mailtransfer.model.MailFromAddr;
import com.archtype.mailtransfer.model.MailToAddr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Think
 * @date: 2019/9/15
 */
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    public String readFile(String filePath) {
        InputStream input = this.getClass().getResourceAsStream(filePath);
        byte[] b = new byte[4096];
        StringBuffer content = new StringBuffer();

        try {
            int n;
            while((n = input.read(b)) != -1) {
                content.append(new String(b, 0, n));
            }

            return content.toString();
        } catch (IOException var7) {
            System.out.println("Read /config.json Failed！");
            var7.printStackTrace();
            return null;
        }
    }

    public MailFromAddr readFromAddr(JSONObject json) {
        JSONObject jsonObject = json.getJSONObject("mailFromInfo");
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String host = jsonObject.getString("host");
        MailFromAddr mailFromAddr = new MailFromAddr(username,password,host);
        return mailFromAddr;
    }

    public MailToAddr readToAddr(JSONObject json) {
        JSONObject jsonObject = json.getJSONObject("mailToInfo");
        String to = jsonObject.getString("to");
        MailToAddr mailToAddr = new MailToAddr(to);
        return mailToAddr;
    }

    public String readFilesPath(JSONObject json) {
        String filesParentPath = json.getString("filesParentPath");
        return filesParentPath;
    }

    public List<File> getFiles(String path) throws Exception {
        List<File> fileList = new ArrayList();
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            File[] var5 = files;
            int var6 = files.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                File fileIndex = var5[var7];
                if (fileIndex.isDirectory()) {
                    this.getFiles(fileIndex.getPath());
                } else {
                    fileList.add(fileIndex);
                }
            }
        }

        return fileList;
    }

    public List<String> getFilesAbsolutePath(List<File> files) {
        List<String> filesAbsolutePath = new ArrayList();
        files.stream().forEach((file) -> {
            filesAbsolutePath.add(file.getAbsolutePath());
        });
        return filesAbsolutePath;
    }
}
