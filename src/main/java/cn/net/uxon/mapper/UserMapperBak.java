package cn.net.uxon.mapper;

import cn.net.uxon.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author jiazhang
 * @date 2020-03-16
 */
@Mapper
public interface UserMapperBak {

    List<User> findAllUser();

    User findUserByName(@Param("name") String name);

    void updateUserByName(@Param("name") String name);
}
