/*
 * (C) Copyright IBM Corp. 2021.
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

import com.ibm.cloud.container_registry.container_registry.v1.model.ListImagesOptions;
import com.ibm.cloud.container_registry.container_registry.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the ListImagesOptions model.
 */
public class ListImagesOptionsTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testListImagesOptions() throws Throwable {
    ListImagesOptions listImagesOptionsModel = new ListImagesOptions.Builder()
      .namespace("testString")
      .includeIbm(true)
      .includePrivate(true)
      .includeManifestLists(true)
      .vulnerabilities(true)
      .repository("testString")
      .build();
    assertEquals(listImagesOptionsModel.namespace(), "testString");
    assertEquals(listImagesOptionsModel.includeIbm(), Boolean.valueOf(true));
    assertEquals(listImagesOptionsModel.includePrivate(), Boolean.valueOf(true));
    assertEquals(listImagesOptionsModel.includeManifestLists(), Boolean.valueOf(true));
    assertEquals(listImagesOptionsModel.vulnerabilities(), Boolean.valueOf(true));
    assertEquals(listImagesOptionsModel.repository(), "testString");
  }
}