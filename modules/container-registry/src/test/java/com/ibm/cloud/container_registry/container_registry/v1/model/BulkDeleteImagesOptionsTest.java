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

import com.ibm.cloud.container_registry.container_registry.v1.model.BulkDeleteImagesOptions;
import com.ibm.cloud.container_registry.container_registry.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the BulkDeleteImagesOptions model.
 */
public class BulkDeleteImagesOptionsTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testBulkDeleteImagesOptions() throws Throwable {
    BulkDeleteImagesOptions bulkDeleteImagesOptionsModel = new BulkDeleteImagesOptions.Builder()
      .bulkDelete(new java.util.ArrayList<String>(java.util.Arrays.asList("testString")))
      .build();
    assertEquals(bulkDeleteImagesOptionsModel.bulkDelete(), new java.util.ArrayList<String>(java.util.Arrays.asList("testString")));
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testBulkDeleteImagesOptionsError() throws Throwable {
    new BulkDeleteImagesOptions.Builder().build();
  }

}