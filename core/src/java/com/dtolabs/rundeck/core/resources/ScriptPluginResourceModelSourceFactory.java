/*
 * Copyright 2011 DTO Solutions, Inc. (http://dtosolutions.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/*
* ScriptPluginResourceModelSource.java
* 
* User: Greg Schueler <a href="mailto:greg@dtosolutions.com">greg@dtosolutions.com</a>
* Created: 8/2/11 11:58 AM
* 
*/
package com.dtolabs.rundeck.core.resources;

import com.dtolabs.rundeck.core.common.Framework;
import com.dtolabs.rundeck.core.plugins.AbstractDescribableScriptPlugin;
import com.dtolabs.rundeck.core.plugins.PluginException;
import com.dtolabs.rundeck.core.plugins.ScriptPluginProvider;
import com.dtolabs.rundeck.core.plugins.configuration.*;

import java.util.*;

/**
 * ScriptPluginResourceModelSource is ...
 *
 * @author Greg Schueler <a href="mailto:greg@dtosolutions.com">greg@dtosolutions.com</a>
 */
class ScriptPluginResourceModelSourceFactory extends AbstractDescribableScriptPlugin implements
    ResourceModelSourceFactory {
    public static final String RESOURCE_FORMAT_PROP = "resource-format";

    final String format;

    public ScriptPluginResourceModelSourceFactory(final ScriptPluginProvider provider, final Framework framework) {
        super(provider, framework);
        format = provider.getMetadata().get(RESOURCE_FORMAT_PROP);
    }


    public static void validateScriptPlugin(final ScriptPluginProvider provider) throws PluginException {

        try {
            createDescription(provider, true);
        } catch (ConfigurationException e) {
            throw new PluginException(e);
        }
        if (!provider.getMetadata().containsKey(RESOURCE_FORMAT_PROP)) {
            throw new PluginException(RESOURCE_FORMAT_PROP + " script plugin property is required");
        } else {
            final String name = provider.getMetadata().get(RESOURCE_FORMAT_PROP);
        }
    }


    public ResourceModelSource createResourceModelSource(final Properties configuration) throws ConfigurationException {

        final ScriptPluginResourceModelSource urlResourceModelSource = new ScriptPluginResourceModelSource(
            getProvider(), getFramework());
        urlResourceModelSource.configure(configuration);
        return urlResourceModelSource;
    }

    @Override
    public boolean isAllowCustomProperties() {
        return true;
    }
}
