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

import java.util.List;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * Details of the tags and days until expiry.
 */
public class Trash extends GenericModel {

  protected Long daysUntilExpiry;
  protected List<String> tags;

  protected Trash() { }

  /**
   * Gets the daysUntilExpiry.
   *
   * @return the daysUntilExpiry
   */
  public Long getDaysUntilExpiry() {
    return daysUntilExpiry;
  }

  /**
   * Gets the tags.
   *
   * @return the tags
   */
  public List<String> getTags() {
    return tags;
  }
}

