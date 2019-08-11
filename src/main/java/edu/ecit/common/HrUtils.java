package edu.ecit.common;


import edu.ecit.bean.Hr;
import org.springframework.security.core.context.SecurityContextHolder;


public class HrUtils {
    public static Hr getCurrentHr() {
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
