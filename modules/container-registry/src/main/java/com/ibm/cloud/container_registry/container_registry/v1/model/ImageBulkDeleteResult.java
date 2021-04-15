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

import java.util.List;
import java.util.Map;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The results of a bulk image delete request.
 */
public class ImageBulkDeleteResult extends GenericModel {

  protected Map<String, ImageBulkDeleteError> error;
  protected List<String> success;

  /**
   * Gets the error.
   *
   * A map of digests to the error object that explains the failure.
   *
   * @return the error
   */
  public Map<String, ImageBulkDeleteError> getError() {
    return error;
  }

  /**
   * Gets the success.
   *
   * A list of digests which were deleted successfully.
   *
   * @return the success
   */
  public List<String> getSuccess() {
    return success;
  }
}

