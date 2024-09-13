package ru.gubern.http.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {
    public static final String JSP_FORMAT = "/WEB-INF/jsp/%s.jsp";

    public static String getPath(String fileName) {
        return String.format(JSP_FORMAT, fileName);
    }
}
