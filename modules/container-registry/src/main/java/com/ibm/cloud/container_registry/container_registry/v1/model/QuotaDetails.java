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

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * QuotaDetails.
 */
public class QuotaDetails extends GenericModel {

  @SerializedName("storage_bytes")
  protected Long storageBytes;
  @SerializedName("traffic_bytes")
  protected Long trafficBytes;

  /**
   * Gets the storageBytes.
   *
   * Storage quota or usage in bytes. The value -1 denotes "Unlimited".
   *
   * @return the storageBytes
   */
  public Long getStorageBytes() {
    return storageBytes;
  }

  /**
   * Gets the trafficBytes.
   *
   * Traffic quota or usage in bytes. The value -1 denotes "Unlimited".
   *
   * @return the trafficBytes
   */
  public Long getTrafficBytes() {
    return trafficBytes;
  }
}

