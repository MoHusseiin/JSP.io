package com.wap;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class currentDateTime extends SimpleTagSupport {
    String color;
    String size;

    // render custom tag
    public void doTag() throws JspException, IOException {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        String text =  ft.format(dNow);
        JspWriter out = getJspContext().getOut();
        if (color != null) {
            out.write("<span style='color:"+color+">"+text+"</span>");
        }
        if (size != null) {
            out.write("<span style='font-size: "+size+"'>"+text+"</span>");
        } else {
            out.write(String.format("<span>%s</span>", text));
        }
    }

    // Need a setter for each attribute of custom tag
    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }
}