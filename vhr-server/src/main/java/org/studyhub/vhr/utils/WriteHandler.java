package org.studyhub.vhr.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: huwei
 * @data: 2023/2/28 13:18
 */
public class WriteHandler<T> {

    private T data;

    public static <T> void write(T data, HttpServletResponse resp){
        resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter writer;
        try {
            writer = resp.getWriter();
            writer.write(new ObjectMapper().writeValueAsString(data));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
