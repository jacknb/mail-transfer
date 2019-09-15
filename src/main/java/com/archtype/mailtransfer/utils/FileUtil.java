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
        StringBuilder content = new StringBuilder();

        try {
            int n;
            while ((n = input.read(b)) != -1) {
                content.append(new String(b, 0, n));
            }
            return content.toString();
        } catch (IOException e) {
            LOGGER.error("Read config/config.json Failed！");
            e.printStackTrace();
            return null;
        }
    }

    public MailFromAddr readFromAddr(JSONObject json) {
        JSONObject jsonObject = json.getJSONObject("mailFromInfo");
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String host = jsonObject.getString("host");
        return new MailFromAddr(username, password, host);
    }

    public MailToAddr readToAddr(JSONObject json) {
        JSONObject jsonObject = json.getJSONObject("mailToInfo");
        String to = jsonObject.getString("to");
        return new MailToAddr(to);
    }

    public String readFilesPath(JSONObject json) {
        return json.getString("filesParentPath");
    }

    public List<File> getFiles(String path) {
        List<File> fileList = new ArrayList<>();
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            File[] copyFiles = files;
            int length = files.length;

            for (int i = 0; i < length; ++i) {
                File fileIndex = copyFiles != null ? copyFiles[i] : null;
                if (fileIndex != null && fileIndex.isDirectory()) {
                    this.getFiles(fileIndex.getPath());
                } else {
                    fileList.add(fileIndex);
                }
            }
        }
        return fileList;
    }

    public List<String> getFilesAbsolutePath(List<File> files) {
        List<String> filesAbsolutePath = new ArrayList<>();
        files.stream().forEach((file) -> {
            filesAbsolutePath.add(file.getAbsolutePath());
        });
        return filesAbsolutePath;
    }
}
