package com.jannchie.biliob.utils;

import com.jannchie.biliob.constant.DbFields;
import com.jannchie.biliob.constant.RoleEnum;
import com.jannchie.biliob.model.User;
import com.jannchie.biliob.repository.UserRepository;
import com.jannchie.biliob.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @author Jannchie
 */
@Component
public class UserUtils {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    public User getPasswdAndRole(String name) {
        Query query = getUserQuery(name);
        query.fields().include("password").include("role").include("name");
        return mongoTemplate.findOne(query, User.class);
    }

    private Query getUserQuery(String name) {
        return Query.query(
                new Criteria()
                        .orOperator(Criteria.where("name").is(name), Criteria.where("mail").is(name)));
    }

    public Integer getUserRoleLevel(User user) {
        return RoleEnum.getLevelByName(user.getRole());
    }

    public Integer getUserRoleLevel() {
        User user = getFullInfo();
        if (user == null) {
            return RoleEnum.GUEST.getLevel();
        }
        return RoleEnum.getLevelByName(user.getRole());
    }

    public User getUserByUsernameOrMail(String name) {
        return mongoTemplate.findOne(
                Query.query(
                        new Criteria()
                                .orOperator(Criteria.where("name").is(name), Criteria.where("mail").is(name))),
                User.class,
                "user");
    }

    public User getUser() {
        String username = getUsername();
        if (username == null) {
            return null;
        }
        return getUserByUsernameOrMail(username);
    }

    public User getFullInfo() {
        System.out.println(request.getRemoteUser());
        String username = getUsername();
        Query query = getUserQuery(username);
        User user = mongoTemplate.findOne(query, User.class);
        if (user != null) {
            setUserTitleAndRankAndUpdateRole(user);
        }
        return user;
    }

    public void updateUserCreditAndExp(User user, Double credit) {
        mongoTemplate.updateFirst(Query.query(Criteria.where("name").is(user.getName())),
                Update.update("credit", BigDecimal.valueOf(user.getCredit() - credit).setScale(2, BigDecimal.ROUND_HALF_DOWN)).set("exp", user.getExp() + credit), User.class);
    }

    public void setUserTitleAndRankAndUpdateRole(User user) {
        long rank = mongoTemplate.count(Query.query(Criteria.where("exp").gte(user.getExp()).and(DbFields.BAN).ne(true)), "user");
        RoleEnum roleEnum = RoleEnum.LEVEL_1;
        user.setRank(Math.toIntExact(rank));
        if (rank <= 3) {
            user.setTitle("管理者");
            roleEnum = RoleEnum.LEVEL_4;
        } else if (rank <= 3 + 16) {
            user.setTitle("观测者");
            roleEnum = RoleEnum.LEVEL_3;
        } else if (rank <= 3 + 16 + 50) {
            user.setTitle("观想者");
            roleEnum = RoleEnum.LEVEL_2;
        } else if (user.getExp() <= 100) {
            user.setTitle("初心者");
        } else {
            long count = userService.getUserCount();
            if (rank < count / 20) {
                user.setTitle("追寻者");
            } else {
                user.setTitle("彷徨者");
            }
        }
        if (user.getBan() != null && user.getBan()) {
            user.setTitle("作弊者");
            roleEnum = RoleEnum.GUEST;
        }
        if (!roleEnum.getName().equals(user.getRole())) {
            Integer preLevel = RoleEnum.getLevelByName(user.getRole());
            if (preLevel < roleEnum.getLevel() || "普通用户".equals(user.getRole()) || "作弊者".equals(user.getTitle())) {
                mongoTemplate.updateFirst(Query.query(Criteria.where("name").is(user.getName())), Update.update("role", roleEnum.getName()), User.class);
            }
        }
    }

    public User getUserById(ObjectId id) {
        Query q = Query.query(Criteria.where("_id").is(id));
        q.fields().exclude("favoriteAid").exclude("favoriteMid");
        return mongoTemplate.findOne(q, User.class);
    }

    public ObjectId getUserId() {
        User user = getUser();
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    public String getUsername() {
        try {
            String name = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            if ("anonymousUser".equals(name)) {
                return null;
            } else {
                return name;
            }
        } catch (Exception e) {
            return null;
        }
    }

}
