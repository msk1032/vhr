package org.studyhub.vhr.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.studyhub.vhr.model.RespBean;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author haoren
 * @create 2023-02-26 10:49
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLException.class)
    public RespBean MySQLException(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return RespBean.error("存在关联数据,操作失败!!");
        }

        System.out.println(e.getMessage());
        return RespBean.error("存在异常,操作失败!!");
    }
}
