package com.jsrdxzw.mallshopbe.core;

import com.jsrdxzw.mallshopbe.model.User;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
public class LocalUserFactory {
    private static final ThreadLocal<LocalUser> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(User user, Integer scope) {
        LocalUser localUser = new LocalUser();
        localUser.setUser(user);
        localUser.setScope(scope);
        THREAD_LOCAL.set(localUser);
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }

    public static User getUser() {
        return THREAD_LOCAL.get().getUser();
    }

    public static Integer getScope() {
        return THREAD_LOCAL.get().getScope();
    }
}
