package com.znt.demo.mail;

/**
 * @author znt
 * @date  2019/10/17
 */
public interface MailService {

    /**
     * 简单邮件
     * @param to
     * @param subject
     * @param countent
     */
    public void sendSimpleMail(String to, String subject, String countent);

    /**
     * html邮件
     * @param to
     * @param subject
     * @param content
     */
    public void sendHtmlMail(String to, String subject, String content);

    /**
     * 带附件邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);


    /**
     * 带静态资源文件（图片）的邮件
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

}
