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

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The result of restoring tags for a digest. In a successful request the digest is always restored, and zero or more of
 * its tags may be restored.
 */
public class RestoreResult extends GenericModel {

  protected List<String> successful;
  protected List<String> unsuccessful;

  /**
   * Gets the successful.
   *
   * Successful is a list of tags that were restored.
   *
   * @return the successful
   */
  public List<String> getSuccessful() {
    return successful;
  }

  /**
   * Gets the unsuccessful.
   *
   * Unsuccessful is a list of tags that were not restored because of a conflict.
   *
   * @return the unsuccessful
   */
  public List<String> getUnsuccessful() {
    return unsuccessful;
  }
}

