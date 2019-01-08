package com.jeonguk.auth.config;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.jeonguk.auth.domain.Authority;
import com.jeonguk.auth.domain.User;
import com.jeonguk.auth.security.constant.AuthConst;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author duc-d
 */
@ChangeLog(order = "001")
public class SetupMig {

    @ChangeSet(order = "01", author = "initiator", id = "01-addAuthorities")
    public void addAuthorities(MongoTemplate mongoTemplate) {
        Authority adminAuthority = new Authority();
        adminAuthority.setName(AuthConst.ADMIN);
        Authority userAuthority = new Authority();
        userAuthority.setName(AuthConst.USER);
        mongoTemplate.save(adminAuthority);
        mongoTemplate.save(userAuthority);
    }

    @ChangeSet(order = "02", author = "initiator", id = "02-addUsers")
    public void addUsers(MongoTemplate mongoTemplate) {
        Authority adminAuthority = new Authority();
        adminAuthority.setName(AuthConst.ADMIN);
        Authority userAuthority = new Authority();
        userAuthority.setName(AuthConst.USER);

        User adminUser = new User();
        adminUser.setLogin("admin");
        adminUser.setPassword("$2a$10$MYkP3aeSQy7DI.qgk4noreZ5uchb0i61OOeWu2tVHAO1yNSsGqCVG"); // password: password
        adminUser.getAuthorities().add(adminAuthority);
        adminUser.getAuthorities().add(userAuthority);

        User aprilonUser = new User();
        aprilonUser.setLogin("user");
        aprilonUser.setPassword("$2a$10$MYkP3aeSQy7DI.qgk4noreZ5uchb0i61OOeWu2tVHAO1yNSsGqCVG"); // password: password
        aprilonUser.getAuthorities().add(userAuthority);

        mongoTemplate.save(adminUser);
        mongoTemplate.save(aprilonUser);
    }
}
