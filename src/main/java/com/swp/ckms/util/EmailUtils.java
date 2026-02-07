package com.swp.ckms.util;

import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

    public String getVerificationEmailSubject() {
        return "Account Verification - CKMS";
    }

    public String getVerificationEmailBody(String fullName, String username, String verificationLink) {
    return "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Email Verification</title>\n" +
            "    <style>\n" +
            "        body { font-family: 'Segoe UI', 'Helvetica Neue', Helvetica, Arial, sans-serif; background-color: #f9f9f9; margin: 0; padding: 0; color: #444; }\n" +
            "        .email-wrapper { width: 100%; padding: 40px 0; background-color: #f9f9f9; }\n" +
            "        .email-container { max-width: 500px; margin: 0 auto; background-color: #ffffff; border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,0.05); overflow: hidden; border: 1px solid #f0f0f0; }\n" +
            "        .email-header { padding: 30px 0 20px; text-align: center; border-bottom: 1px solid #f0f0f0; margin: 0 30px; }\n" +
            "        .brand-name { font-size: 24px; font-weight: 700; color: #2E7D32; letter-spacing: -0.5px; text-decoration: none; }\n" +
            "        .email-body { padding: 30px 40px; text-align: center; }\n" +
            "        .greeting { font-size: 20px; font-weight: 600; margin-bottom: 16px; color: #333; }\n" +
            "        .text-content { font-size: 15px; color: #666; line-height: 1.6; margin-bottom: 24px; }\n" +
            "        .btn { background-color: #2E7D32; color: #ffffff !important; padding: 12px 30px; font-size: 15px; font-weight: 600; text-decoration: none; border-radius: 50px; display: inline-block; transition: all 0.3s ease; box-shadow: 0 4px 6px rgba(46, 125, 50, 0.2); }\n" +
            "        .btn:hover { background-color: #1B5E20; transform: translateY(-1px); box-shadow: 0 6px 8px rgba(46, 125, 50, 0.3); }\n" +
            "        .footer { background-color: #fff; padding: 20px; text-align: center; font-size: 13px; color: #999; border-top: 1px solid #f0f0f0; }\n" +
            "        .footer p { margin: 5px 0; }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"email-wrapper\">\n" +
            "        <div class=\"email-container\">\n" +
            "            <div class=\"email-header\">\n" +
            "                <span class=\"brand-name\">Central Kitchen Management System</span>\n" +
            "            </div>\n" +
            "            <div class=\"email-body\">\n" +
            "                <div class=\"greeting\">Xin chào " + fullName + "! 👋</div>\n" +
            "                <p class=\"text-content\">Chào mừng bạn đến với <strong>Central Kitchen Management System</strong>.</p>\n" +
            "                <p class=\"text-content\">Username của bạn là: <strong>" + username + "</strong></p>\n" +
            "                <p class=\"text-content\">Để bắt đầu sử dụng hệ thống, bạn vui lòng xác thực địa chỉ email bằng nút bên dưới nhé.</p>\n" +
            "                \n" +
            "                <div style=\"margin: 30px 0;\">\n" +
            "                    <a href=\"" + verificationLink + "\" class=\"btn\">Xác Thực Ngay</a>\n" +
            "                </div>\n" +
            "                \n" +
            "                <p style=\"font-size: 13px; color: #aaa; margin-top: 30px;\">Liên kết có hiệu lực trong 24 giờ.<br>Nếu không phải bạn, hãy bỏ qua email này nhé.</p>\n" +
            "            </div>\n" +
            "            <div class=\"footer\">\n" +
            "                <p>&copy; 2026 CKMS.</p>\n" +
            "                <p>Hệ thống quản lý bếp trung tâm</p>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>";
}
}