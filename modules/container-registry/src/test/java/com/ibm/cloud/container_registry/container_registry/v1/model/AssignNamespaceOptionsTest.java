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

import com.ibm.cloud.container_registry.container_registry.v1.model.AssignNamespaceOptions;
import com.ibm.cloud.container_registry.container_registry.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the AssignNamespaceOptions model.
 */
public class AssignNamespaceOptionsTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testAssignNamespaceOptions() throws Throwable {
    AssignNamespaceOptions assignNamespaceOptionsModel = new AssignNamespaceOptions.Builder()
      .xAuthResourceGroup("testString")
      .name("testString")
      .build();
    assertEquals(assignNamespaceOptionsModel.xAuthResourceGroup(), "testString");
    assertEquals(assignNamespaceOptionsModel.name(), "testString");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testAssignNamespaceOptionsError() throws Throwable {
    new AssignNamespaceOptions.Builder().build();
  }

}