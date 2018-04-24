/**
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
package io.twostepsfromjava.cloud.product.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.context.ApplicationContext;


/**
 * 远程事件发布者
 *
 * @author CD826(CD826Dong@gmail.com)
 * @since 1.0.0
 */
public class RemoteApplicationEventPublisher {
    protected static Logger logger = LoggerFactory.getLogger(RemoteApplicationEventPublisher.class);

	/**
	 * 发布一个事件
	 * @param event
	 */
	public static void publishEvent(RemoteApplicationEvent event){
		ApplicationContext context = ApplicationContextHolder.getApplicationContext();
		if(null != context) {
            context.publishEvent(event);
			logger.debug("已发布事件:{}", event);
        }else{
			logger.warn("无法获取到当前Spring上下文信息，不能够发布事件");
        }
	}
}