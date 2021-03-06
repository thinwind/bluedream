/* 
 * Copyright 2021 Shang Yehua <niceshang@outlook.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.thinwind.bluedream.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 用于返回http接口的统一结果结构体
 *
 * @author Shang Yehua <niceshang@outlook.com>
 * @since 2021-11-05  14:26
 *
 */
@Getter @Setter
public class HttpResult {
    private boolean success;
    private Object data;
    private String errorCode;
    private String errorMessage;
    private String traceId;
    private String host;
    
    private Integer currentPage;
    private Integer total;
    private Integer pageSize;
}
