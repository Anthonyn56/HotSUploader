// Copyright 2016 Eivind Vegsundvåg
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.


package ninja.eivind.hotsreplayuploader.di.nodes;

import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Builder;
import javafx.util.BuilderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * {@link BuilderFactory} implementation that can run special {@link JavaFXNode}s through a
 * {@link AutowireCapableBeanFactory} before activing it as a JavaFX {@link javafx.scene.Node}
 */
@Component
public class SpringAwareBuilderFactory implements BuilderFactory {

    private static final Logger logger = LoggerFactory.getLogger(SpringAwareBuilderFactory.class);
    private final AutowireCapableBeanFactory beanFactory;
    private final JavaFXBuilderFactory javaFXBuilderFactory = new JavaFXBuilderFactory();

    @Autowired
    public SpringAwareBuilderFactory(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Builder<?> getBuilder(Class<?> type) {
        if (JavaFXNode.class.isAssignableFrom(type)) {
            logger.info("Creating custom component " + type.getName());
            return (Builder<JavaFXNode>) () -> {
                JavaFXNode bean = (JavaFXNode) beanFactory.createBean(type);
                beanFactory.autowireBean(bean);
                return bean;
            };
        }
        return javaFXBuilderFactory.getBuilder(type);
    }
}
