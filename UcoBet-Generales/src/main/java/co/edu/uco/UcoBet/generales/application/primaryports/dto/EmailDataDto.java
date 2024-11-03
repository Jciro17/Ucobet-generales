package co.edu.uco.UcoBet.generales.application.primaryports.dto;

import co.edu.uco.UcoBet.generales.crosscutting.helpers.TextHelper;

public class EmailDataDto {
	
    private String to;
    private String subject;
    private String content;
    
    public EmailDataDto(final String to, final String subject, final String content) {
    	
        setTo(to);
        setSubject(subject);
        setContent(content);
        
    }
    
    public static EmailDataDto create (final String to, final String subject, final String content) {
        return new EmailDataDto(to, subject,content);
    }
    
    public String getTo() {
        return to;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setTo(String to) {
        this.to =  TextHelper.applyTrim(to);
    }
    
    public void setSubject(String subject) {
        this.subject = TextHelper.applyTrim(subject);
    }
    
    public void setContent(String content) {
        this.content = TextHelper.applyTrim(content);
    }
    
}
