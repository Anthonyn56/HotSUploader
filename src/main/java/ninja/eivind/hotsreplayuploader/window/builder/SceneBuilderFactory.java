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

package ninja.eivind.hotsreplayuploader.window.builder;

import ninja.eivind.hotsreplayuploader.di.FXMLLoaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Provider for {@link SceneBuilder}, ensuring each Scene Builder gets a fresh {@link javafx.fxml.FXMLLoader} that is
 * not contaminated by loading other controllers or nodes.
 */
@Component
public class SceneBuilderFactory {

    @Autowired
    private FXMLLoaderFactory loaderFactory;

    public SceneBuilder builder() {
        return new SceneBuilder(loaderFactory.get());
    }

}
