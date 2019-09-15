package com.archtype.mailtransfer.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.archtype.mailtransfer.model.MailFromAddr;
import com.archtype.mailtransfer.model.MailInfo;
import com.archtype.mailtransfer.model.MailToAddr;
import com.archtype.mailtransfer.service.MailSendService;
import com.archtype.mailtransfer.utils.FileUtil;
import com.archtype.mailtransfer.utils.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author: Think
 * @date: 2019/9/15
 */
@Service
public class MailSendImpl implements MailSendService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailSendImpl.class);

    @Override
    public void mailSend() {
        FileUtil readUtil = new FileUtil();
        String content = readUtil.readFile("/config/config.json");
        LOGGER.info("The content is {}.", content);
        JSONObject jsonObject = JSON.parseObject(content);
        MailFromAddr mailFromAddr = readUtil.readFromAddr(jsonObject);
        MailToAddr mailToAddr = readUtil.readToAddr(jsonObject);
        String filePath = readUtil.readFilesPath(jsonObject);
        List<File> files = readUtil.getFiles(filePath);
        files.stream().forEach((file) -> {
            String fileName = file.getName();
            MailInfo mailInfo = new MailInfo("发送 " + fileName + " 文件", "");
            try {
                boolean result = MailUtil.send(mailToAddr, mailFromAddr, mailInfo, file.getAbsolutePath());
                if (result) {
                    LOGGER.info("====Send {} Failed !====", fileName);
                } else {
                    LOGGER.info("====Send {} Success !====", fileName);
                }
            } catch (Exception e) {
                LOGGER.error("Send mail Error.", e);
            }
        });
    }
}
