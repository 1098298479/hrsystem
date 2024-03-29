package edu.ecit.mapper;

import edu.ecit.bean.Menu;

import java.util.List;


public interface MenuMapper {
    List<Menu> getAllMenu();

    List<Menu> getMenusByHrId(Long hrId);

    List<Menu> menuTree();

    List<Long> getMenusByRid(Long rid);
}
