/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.twostepsfromjava.cloud.event;


import com.google.common.base.MoreObjects;
import io.twostepsfromjava.cloud.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 * 用户事件
 *
 * @author CD826(CD826Dong@gmail.com)
 * @since 1.0.0
 */
public class UserEvent extends ApplicationEvent {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /** 消息类型：更新用户，值为: {@value} */
    public static final String ET_UPDATE = "update";

    // ========================================================================
    // fields =================================================================
    private String action;
    private User user;

    // ========================================================================
    // constructor ============================================================
    public UserEvent(User user) {
        super(user);
        this.user = user;
    }

    public UserEvent(User user, String action) {
        super(user);
        this.action = action;
        this.user = user;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("action", this.getAction())
                .add("user", this.getUser()).toString();
    }

    /**
     * 发布事件
     */
    public void fire() {
        ApplicationContext context = ApplicationContextHolder.getApplicationContext();
        if(null != context) {
            logger.debug("发布事件：{}", this);
            context.publishEvent(this);
        }else{
            logger.warn("无法获取到当前Spring上下文信息，不能够发布事件");
        }
    }

    public void foo() {
        new UserEvent(user, UserEvent.ET_UPDATE).fire();
    }

    // ==================================================================
    // setter/getter ====================================================
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
