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
import com.ibm.cloud.container_registry.container_registry.v1.model.ImageInspection;
import com.ibm.cloud.container_registry.container_registry.v1.model.RootFS;
import com.ibm.cloud.container_registry.container_registry.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the ImageInspection model.
 */
public class ImageInspectionTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testImageInspection() throws Throwable {
    ImageInspection imageInspectionModel = new ImageInspection();
    assertNull(imageInspectionModel.getArchitecture());
    assertNull(imageInspectionModel.getAuthor());
    assertNull(imageInspectionModel.getComment());
    assertNull(imageInspectionModel.getConfig());
    assertNull(imageInspectionModel.getContainer());
    assertNull(imageInspectionModel.getContainerConfig());
    assertNull(imageInspectionModel.getCreated());
    assertNull(imageInspectionModel.getDockerVersion());
    assertNull(imageInspectionModel.getId());
    assertNull(imageInspectionModel.getManifestType());
    assertNull(imageInspectionModel.getOs());
    assertNull(imageInspectionModel.getOsVersion());
    assertNull(imageInspectionModel.getParent());
    assertNull(imageInspectionModel.getRootFs());
    assertNull(imageInspectionModel.getSize());
    assertNull(imageInspectionModel.getVirtualSize());
  }
}