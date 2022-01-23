/* 
 * Copyright 2022 Shang Yehua <niceshang@outlook.com>
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
package io.github.thinwind.bluedream.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import io.github.thinwind.bluedream.entity.Demo;

/**
 *
 * TODO DemoRepo说明
 *
 * @author Shang Yehua <niceshang@outlook.com>
 * @since 2022-01-23  20:37
 *
 */
@Transactional(rollbackFor = Exception.class)
public interface DemoRepo extends JpaRepository<Demo, Long> {
    
}
