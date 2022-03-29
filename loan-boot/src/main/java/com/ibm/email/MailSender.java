package com.ibm.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@Component
public class MailSender {
	@Autowired
	public AmazonSimpleEmailService amazonSimpleEmailService;

	private String emailContent;
	private String senderEmail;
	private String receiverEmail;
	private String emailSubject;

	public MailSender() {
		this.emailContent = this.getContentSD("Default email");

		this.senderEmail = "sayak.94.sm@gmail.com";
		this.receiverEmail = "sayak.94.sm@gmail.com";
		this.emailSubject = "Loan zone updates";
	}

	public MailSender(String emailContent, String senderEmail, String receiverEmail, String emailSubject) {
		this.emailContent = this.getContentSD(emailContent);
		this.senderEmail = senderEmail;
		this.receiverEmail = receiverEmail;
		this.emailSubject = emailSubject;
	}

	public void sendEmail() {

		try {
			System.err.println("Mail sending to "+this.receiverEmail);
			SendEmailRequest sendEmailRequest = new SendEmailRequest()
					.withDestination(new Destination().withToAddresses(receiverEmail))
					.withMessage(new Message()
							.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(emailContent)))
							.withSubject(new Content().withCharset("UTF-8").withData(emailSubject)))
					.withSource(senderEmail);
			amazonSimpleEmailService.sendEmail(sendEmailRequest);
			System.err.println("Mail sent to "+ this.receiverEmail);
		} catch (Exception e) {
			System.err.println("Mail sending failed");
			e.printStackTrace();
		}
	}
	
	

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = this.getContentSD(emailContent);
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	private String getContentSD(String message) {
		String content = "<!DOCTYPE html>\n" + "\n"
				+ "<html lang=\"en\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n"
				+ "<head>\n" + "<title></title>\n"
				+ "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n"
				+ "<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\"/>\n"
				+ "<!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]-->\n"
				+ "<style>\n" + "		* {\n" + "			box-sizing: border-box;\n" + "		}\n" + "\n"
				+ "		body {\n" + "			margin: 0;\n" + "			padding: 0;\n" + "		}\n" + "\n"
				+ "		a[x-apple-data-detectors] {\n" + "			color: inherit !important;\n"
				+ "			text-decoration: inherit !important;\n" + "		}\n" + "\n" + "		#MessageViewBody a {\n"
				+ "			color: inherit;\n" + "			text-decoration: none;\n" + "		}\n" + "\n"
				+ "		p {\n" + "			line-height: inherit\n" + "		}\n" + "\n"
				+ "		@media (max-width:660px) {\n" + "			.icons-inner {\n"
				+ "				text-align: center;\n" + "			}\n" + "\n" + "			.icons-inner td {\n"
				+ "				margin: 0 auto;\n" + "			}\n" + "\n" + "			.row-content {\n"
				+ "				width: 100% !important;\n" + "			}\n" + "\n" + "			.column .border {\n"
				+ "				display: none;\n" + "			}\n" + "\n" + "			.stack .column {\n"
				+ "				width: 100%;\n" + "				display: block;\n" + "			}\n" + "\n"
				+ "			.row-3 .column-1 {\n" + "				border-right: 30px solid #FFFFFF;\n"
				+ "				border-left: 30px solid #FFFFFF;\n" + "			}\n" + "		}\n" + "	</style>\n"
				+ "</head>\n"
				+ "<body style=\"background-color: #f8f8f9; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f8f8f9;\" width=\"100%\">\n"
				+ "<tbody>\n" + "<tr>\n" + "<td>\n"
				+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #1aa19c;\" width=\"100%\">\n"
				+ "<tbody>\n" + "<tr>\n" + "<td>\n"
				+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #1aa19c; color: #000000; width: 640px;\" width=\"640\">\n"
				+ "<tbody>\n" + "<tr>\n"
				+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 0px; padding-bottom: 0px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n" + "<td>\n" + "<div align=\"center\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n"
				+ "<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 4px solid #1AA19C;\"><span> </span></td>\n"
				+ "</tr>\n" + "</table>\n" + "</div>\n" + "</td>\n" + "</tr>\n" + "</table>\n" + "</td>\n" + "</tr>\n"
				+ "</tbody>\n" + "</table>\n" + "</td>\n" + "</tr>\n" + "</tbody>\n" + "</table>\n"
				+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tbody>\n" + "<tr>\n" + "<td>\n"
				+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #fff; color: #000000; width: 640px;\" width=\"640\">\n"
				+ "<tbody>\n" + "<tr>\n"
				+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 0px; padding-bottom: 0px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n" + "<td style=\"padding-bottom:12px;padding-top:60px;\">\n" + "<div align=\"center\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n"
				+ "<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 0px solid #BBBBBB;\"><span> </span></td>\n"
				+ "</tr>\n" + "</table>\n" + "</div>\n" + "</td>\n" + "</tr>\n" + "</table>\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n" + "<td style=\"padding-top:50px;\">\n" + "<div align=\"center\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n"
				+ "<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 0px solid #BBBBBB;\"><span> </span></td>\n"
				+ "</tr>\n" + "</table>\n" + "</div>\n" + "</td>\n" + "</tr>\n" + "</table>\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
				+ "<tr>\n"
				+ "<td style=\"padding-bottom:10px;padding-left:40px;padding-right:40px;padding-top:10px;\">\n"
				+ "<div style=\"font-family: sans-serif\">\n"
				+ "<div style=\"font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;\">\n"
				+ "<p style=\"margin: 0; font-size: 16px; text-align: center;\"><span style=\"font-size:30px;color:#2b303a;\"><strong>You have a new message</strong></span></p>\n"
				+ "</div>\n" + "</div>\n" + "</td>\n" + "</tr>\n" + "</table>\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n" + "<td style=\"padding-top:60px;\">\n" + "<div align=\"center\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n"
				+ "<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 0px solid #BBBBBB;\"><span> </span></td>\n"
				+ "</tr>\n" + "</table>\n" + "</div>\n" + "</td>\n" + "</tr>\n" + "</table>\n" + "</td>\n" + "</tr>\n"
				+ "</tbody>\n" + "</table>\n" + "</td>\n" + "</tr>\n" + "</tbody>\n" + "</table>\n"
				+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tbody>\n" + "<tr>\n" + "<td>\n"
				+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f3fafa; color: #000000; width: 640px;\" width=\"640\">\n"
				+ "<tbody>\n" + "<tr>\n"
				+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top;\" width=\"100%\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n" + "<td class=\"border\" style=\"width:30px;background-color:#FFFFFF\"> </td>\n"
				+ "<td class=\"content_blocks\" style=\"padding-top:0px;padding-bottom:0px;border-top:0px;border-bottom:0px;width:580px;\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n" + "<td>\n" + "<div align=\"center\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n"
				+ "<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 4px solid #1AA19C;\"><span> </span></td>\n"
				+ "</tr>\n" + "</table>\n" + "</div>\n" + "</td>\n" + "</tr>\n" + "</table>\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n" + "<td style=\"padding-top:35px;\">\n" + "<div align=\"center\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n"
				+ "<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 0px solid #BBBBBB;\"><span> </span></td>\n"
				+ "</tr>\n" + "</table>\n" + "</div>\n" + "</td>\n" + "</tr>\n" + "</table>\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
				+ "<tr>\n"
				+ "<td style=\"padding-bottom:10px;padding-left:10px;padding-right:10px;padding-top:15px;\">\n"
				+ "<div style=\"font-family: sans-serif\">\n"
				+ "<div style=\"font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;\">\n"
				+ "<p style=\"margin: 0; font-size: 16px; text-align: center;\"><span style=\"color:#2b303a;font-size:18px;\"><strong>LOAN ZONE</strong></span></p>\n"
				+ "</div>\n" + "</div>\n" + "</td>\n" + "</tr>\n" + "</table>\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"text_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">\n"
				+ "<tr>\n"
				+ "<td style=\"padding-bottom:40px;padding-left:30px;padding-right:30px;padding-top:20px;\">\n"
				+ "<div style=\"font-family: sans-serif\">\n"
				+ "<div style=\"font-size: 12px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 18px; color: #555555; line-height: 1.5;\">\n"
				+ "<p style=\"margin: 0; font-size: 14px; text-align: left;\"> " + message + " </p>\n"
				+ "<p style=\"margin: 0; font-size: 14px; text-align: left; mso-line-height-alt: 18px;\"> </p>\n"
				+ "<p style=\"margin: 0; font-size: 14px; text-align: left; mso-line-height-alt: 22.5px;\"><span style=\"color:#2b303a;font-size:15px;\">Thank you,</span></p>\n"
				+ "<p style=\"margin: 0; font-size: 14px; text-align: left; mso-line-height-alt: 22.5px;\"><span style=\"color:#2b303a;font-size:15px;\">Team Loan Zone</span></p>\n"
				+ "</div>\n" + "</div>\n" + "</td>\n" + "</tr>\n" + "</table>\n" + "</td>\n"
				+ "<td class=\"border\" style=\"width:30px;background-color:#FFFFFF\"> </td>\n" + "</tr>\n"
				+ "</table>\n" + "</td>\n" + "</tr>\n" + "</tbody>\n" + "</table>\n" + "</td>\n" + "</tr>\n"
				+ "</tbody>\n" + "</table>\n"
				+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tbody>\n" + "<tr>\n" + "<td>\n"
				+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #fff; color: #000000; width: 640px;\" width=\"640\">\n"
				+ "<tbody>\n" + "<tr>\n"
				+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 0px; padding-bottom: 0px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n" + "<td style=\"padding-bottom:12px;padding-top:60px;\">\n" + "<div align=\"center\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n"
				+ "<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 0px solid #BBBBBB;\"><span> </span></td>\n"
				+ "</tr>\n" + "</table>\n" + "</div>\n" + "</td>\n" + "</tr>\n" + "</table>\n" + "</td>\n" + "</tr>\n"
				+ "</tbody>\n" + "</table>\n" + "</td>\n" + "</tr>\n" + "</tbody>\n" + "</table>\n"
				+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-5\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tbody>\n" + "<tr>\n" + "<td>\n"
				+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f8f8f9; color: #000000; width: 640px;\" width=\"640\">\n"
				+ "<tbody>\n" + "<tr>\n"
				+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n"
				+ "<table border=\"0\" cellpadding=\"20\" cellspacing=\"0\" class=\"divider_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n" + "<td>\n" + "<div align=\"center\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n"
				+ "<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 0px solid #BBBBBB;\"><span> </span></td>\n"
				+ "</tr>\n" + "</table>\n" + "</div>\n" + "</td>\n" + "</tr>\n" + "</table>\n" + "</td>\n" + "</tr>\n"
				+ "</tbody>\n" + "</table>\n" + "</td>\n" + "</tr>\n" + "</tbody>\n" + "</table>\n"
				+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-6\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tbody>\n" + "<tr>\n" + "<td>\n"
				+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #2b303a; color: #000000; width: 640px;\" width=\"640\">\n"
				+ "<tbody>\n" + "<tr>\n"
				+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 0px; padding-bottom: 0px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n" + "<td>\n" + "<div align=\"center\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n"
				+ "<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 4px solid #1AA19C;\"><span> </span></td>\n"
				+ "</tr>\n" + "</table>\n" + "</div>\n" + "</td>\n" + "</tr>\n" + "</table>\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n"
				+ "<td style=\"padding-bottom:10px;padding-left:40px;padding-right:40px;padding-top:25px;\">\n"
				+ "<div align=\"center\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n"
				+ "<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 1px solid #555961;\"><span> </span></td>\n"
				+ "</tr>\n" + "</table>\n" + "</div>\n" + "</td>\n" + "</tr>\n" + "</table>\n" + "</td>\n" + "</tr>\n"
				+ "</tbody>\n" + "</table>\n" + "</td>\n" + "</tr>\n" + "</tbody>\n" + "</table>\n"
				+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-7\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tbody>\n" + "<tr>\n" + "<td>\n"
				+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 640px;\" width=\"640\">\n"
				+ "<tbody>\n" + "<tr>\n"
				+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"icons_block\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n"
				+ "<td style=\"vertical-align: middle; color: #9d9d9d; font-family: inherit; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">\n"
				+ "<table cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n"
				+ "<tr>\n" + "<td style=\"vertical-align: middle; text-align: center;\">\n"
				+ "<!--[if vml]><table align=\"left\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]-->\n"
				+ "<!--[if !vml]><!-->\n"
				+ "<table cellpadding=\"0\" cellspacing=\"0\" class=\"icons-inner\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block; margin-right: -4px; padding-left: 0px; padding-right: 0px;\">\n"
				+ "<!--<![endif]-->\n" + "<tr>\n"
				+ "<td style=\"vertical-align: middle; text-align: center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px; padding-right: 6px;\">\n"
				+ "</tr>\n" + "</table>\n" + "</td>\n" + "</tr>\n" + "</table>\n" + "</td>\n" + "</tr>\n" + "</table>\n"
				+ "</td>\n" + "</tr>\n" + "</tbody>\n" + "</table>\n" + "</td>\n" + "</tr>\n" + "</tbody>\n"
				+ "</table>\n" + "</td>\n" + "</tr>\n" + "</tbody>\n" + "</table><!-- End -->\n" + "</body>\n"
				+ "</html>";

		return content;
	}

}
