package com.CrackCode.ORM_CUSTOM_repository_CONFIG.repository;

import com.CrackCode.ORM_CUSTOM_repository_CONFIG.dto.UserDetailsDto;
import com.CrackCode.ORM_CUSTOM_repository_CONFIG.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * based on test on only CORE SPRING FRAMEWORK
 */
@Repository//Must use this annotation in this custom ORM BaseRepository CASE
public class UserORMrepository  {

   /* public List<User> getUserWithRoleDetails(){
        String sql = "select\n" +
                "    r.id as roleId,\n" +
                "    r.name as roleName,\n" +
                "    u.*\n" +
                "from\n" +
                "    user u\n" +
                "join\n" +
                "    user_roles ur\n" +
                "        on u.id = ur.users_id\n" +
                "join\n" +
                "    role r\n" +
                "        on ur.roles_id = r.id";
         Query query =  getSession().createSQLQuery(sql)
                 .addEntity(User.class)
                 .addScalar("roleId", StandardBasicTypes.LONG)
                 .addScalar("roleName",StandardBasicTypes.STRING)
                 .setResultTransformer(new AliasToBeanResultTransformer(UserDetailsDto.class));
         List<UserDetailsDto> userDetailsList = query.list();
         return dtoToEntity(userDetailsList);
    }

    public List<User> getUserWithRoleDetailsWithUserId(Long userId){
        String sql = "select\n" +
                "    r.id as roleId,\n" +
                "    r.name as roleName,\n" +
                "    u.*\n" +
                "from\n" +
                "    user u\n" +
                "join\n" +
                "    user_roles ur\n" +
                "        on u.id = ur.users_id\n" +
                "join\n" +
                "    role r\n" +
                "        on ur.roles_id = r.id ";
        if (userId!=null){
            sql+="and u.id =:userId ";
        }
        Query query =  getSession().createSQLQuery(sql)
                .addEntity(User.class)
                .addScalar("roleId", StandardBasicTypes.LONG)
                .addScalar("roleName",StandardBasicTypes.STRING)
                .setResultTransformer(new AliasToBeanResultTransformer(UserDetailsDto.class));
        if (userId!=null){
            query.setParameter("userId",userId);
        }
        List<UserDetailsDto> userDetailsList = query.list();
        return dtoToEntity(userDetailsList);
    }*/

    public List<User> dtoToEntity(List<UserDetailsDto> userDetailsList) {
        return userDetailsList.stream()
                .filter(Objects::nonNull)
                .map(userDetailsDtoToUser)
                .collect(Collectors.toList());
    }

    private Function<UserDetailsDto,User>userDetailsDtoToUser = userDetailsDto -> {
       User user = userDetailsDto.getUser();
       user.setRoleId(userDetailsDto.getRoleId());
       user.setRoleName(userDetailsDto.getRoleName());
       return user;
    };
}
