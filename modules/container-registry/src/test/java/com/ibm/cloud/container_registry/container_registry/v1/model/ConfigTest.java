/*
 * (C) Copyright IBM Corp. 2023.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.ibm.cloud.container_registry.container_registry.v1.model;

import com.ibm.cloud.container_registry.container_registry.v1.model.Config;
import com.ibm.cloud.container_registry.container_registry.v1.model.HealthConfig;
import com.ibm.cloud.container_registry.container_registry.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the Config model.
 */
public class ConfigTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testConfig() throws Throwable {
    Config configModel = new Config();
    assertNull(configModel.isArgsEscaped());
    assertNull(configModel.isAttachStderr());
    assertNull(configModel.isAttachStdin());
    assertNull(configModel.isAttachStdout());
    assertNull(configModel.getCmd());
    assertNull(configModel.getDomainname());
    assertNull(configModel.getEntrypoint());
    assertNull(configModel.getEnv());
    assertNull(configModel.getExposedPorts());
    assertNull(configModel.getHealthcheck());
    assertNull(configModel.getHostname());
    assertNull(configModel.getImage());
    assertNull(configModel.getLabels());
    assertNull(configModel.getMacAddress());
    assertNull(configModel.isNetworkDisabled());
    assertNull(configModel.getOnBuild());
    assertNull(configModel.isOpenStdin());
    assertNull(configModel.getShell());
    assertNull(configModel.isStdinOnce());
    assertNull(configModel.getStopSignal());
    assertNull(configModel.getStopTimeout());
    assertNull(configModel.isTty());
    assertNull(configModel.getUser());
    assertNull(configModel.getVolumes());
    assertNull(configModel.getWorkingDir());
  }
}